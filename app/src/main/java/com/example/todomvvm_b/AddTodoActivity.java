package com.example.todomvvm_b;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.todomvvm_b.database.Todo;

import java.util.Date;

public class AddTodoActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText detailEditText;
    private Button button;
    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        titleEditText = findViewById(R.id.title_edit_text);
        detailEditText = findViewById(R.id.detail_edit_text);
        button = findViewById(R.id.add_btn);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEditText.getText().toString();
                String detail = detailEditText.getText().toString();
                Todo todo = new Todo();
                todo.setTitle(title);
                todo.setDetail(detail);
                todo.setDate(new Date());
                todo.setComplete(false);
                viewModel.addTodo(todo);
                finish();
            }
        });
    }
}