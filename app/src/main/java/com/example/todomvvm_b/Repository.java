package com.example.todomvvm_b;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.todomvvm_b.database.Todo;
import com.example.todomvvm_b.database.TodoDao;
import com.example.todomvvm_b.database.TodoDatabase;

import java.util.List;

public class Repository {

    private TodoDatabase database;
    private TodoDao todoDao;

    public Repository(Application application) {
        database = TodoDatabase.getInstance(application);
        todoDao = database.getTodoDao();
    }

    public LiveData<List<Todo>> getAllTodos(){
        return todoDao.getAllTodos();
    }

    public void Update(Todo todo){
        TodoDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                todoDao.updateTodo(todo);
            }
        });
    }

    public void addTodo(Todo todo){
        TodoDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                todoDao.addTodo(todo);
            }
        });
    }

    public void deleteById(int id){
        TodoDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                todoDao.deleteById(id);
            }
        });
    }

    public void deleteAll(){
        TodoDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                todoDao.deleteALL();
            }
        });
    }
}
