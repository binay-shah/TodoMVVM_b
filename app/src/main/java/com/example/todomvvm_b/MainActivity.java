package com.example.todomvvm_b;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.todomvvm_b.database.Todo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
   private ViewModel viewModel;
   private RecyclerView recyclerView;
   private TodoAdapter adapter;
   private FloatingActionButton addFloatingActionButton;
   private List<Todo> todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       viewModel = new ViewModelProvider(this).get(ViewModel.class);
        recyclerView = findViewById(R.id.todo_list_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TodoAdapter();
       // updateData();
        viewModel.getAllTodos().observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                if(todos != null)

                    adapter.submitData(todos);
            }
        });
        recyclerView.setAdapter(adapter);
        addFloatingActionButton = findViewById(R.id.floatingActionButton);
        addFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTodoActivity.class);
                startActivity(intent);
            }
        });

       // Log.d(TAG, "size: "+todoList.get(0));
//        for (int i = 0; i < todoList.size(); i++) {
//            Log.d(TAG, ""+todoList.get(i));
//        }

    }




}