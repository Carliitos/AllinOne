package com.usefulapps.allinone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {
    RelativeLayout calculadora;
    RelativeLayout todo;
    RelativeLayout password;
    RelativeLayout reminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculadora = findViewById(R.id.calculadora);
        todo = findViewById(R.id.todo);
        reminder = findViewById(R.id.reminder);

        password = findViewById(R.id.password);
        calculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Calculadora.class));
            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, PasswordGenerator.class));
            }
        });
        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, ToDo.class));
            }
        });
        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Reminder.class));
            }
        });


    }
}
