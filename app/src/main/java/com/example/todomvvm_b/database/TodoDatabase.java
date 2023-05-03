package com.example.todomvvm_b.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@TypeConverters(DateTypeConverter.class)
@Database(entities = {Todo.class}, version = 1, exportSchema = false)
public abstract class TodoDatabase extends RoomDatabase {

    public  abstract TodoDao getTodoDao();

     public static  TodoDatabase INSTANCE;

     public static ExecutorService databaseWriteExecutor =
             Executors.newFixedThreadPool(4);

     public static TodoDatabase getInstance(Context context){
         if(INSTANCE == null){
             synchronized (TodoDatabase.class){
                 if(INSTANCE == null){
                     INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                             TodoDatabase.class, "todo_db")
                             .addCallback(callback)
                             .allowMainThreadQueries()
                             .build();
                 }
             }
         }
         return INSTANCE;
     }

     private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){

         @Override
         public void onCreate(@NonNull SupportSQLiteDatabase db) {
             super.onCreate(db);

             TodoDao dao = TodoDatabase.INSTANCE.getTodoDao();

             databaseWriteExecutor.execute(new Runnable() {
                 @Override
                 public void run() {
                     dao.deleteALL();
                 }
             });


             for (int i = 0; i < 30; i++) {
                 Todo todo = new Todo();
                 todo.setId(1);
                 todo.setTitle("title" + i);
                 todo.setDate(new Date());
                 todo.setComplete(false);
                 databaseWriteExecutor.execute(new Runnable() {
                     @Override
                     public void run() {
                         dao.addTodo(todo);
                     }
                 });

             }
         }
     };



}
