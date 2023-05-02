package com.example.todomvvm_b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
   Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = new Repository(getApplication());
        List<Todo> todoList = repository.getAllTodo();
        Log.d(TAG, "size: "+todoList.get(0));
//        for (int i = 0; i < todoList.size(); i++) {
//            Log.d(TAG, ""+todoList.get(i));
//        }

    }
}