package com.example.todomvvm_b;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todomvvm_b.database.Todo;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository repository;
    private List<Todo> todoList;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<Todo>> getAllTodos(){
       return  repository.getAllTodos();
    }

    public void addTodo(Todo todo){
        repository.addTodo(todo);
    }




}
