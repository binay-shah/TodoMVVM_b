package com.example.todomvvm_b;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {

    @Query("SELECT * FROM todos")
    List<Todo> getAllTodos();

    @Insert
    void addTodo(Todo todo);

    @Query("DELETE FROM todos")
    void deleteALL();

    @Update
    void updateTodo(Todo todo);

    @Query("DELETE FROM todos where id = :id")
    void deleteById(int id);
}
