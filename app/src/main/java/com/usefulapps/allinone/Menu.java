package com.usefulapps.allinone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Menu extends AppCompatActivity {
    RelativeLayout calculadora;
    RelativeLayout todo;
    RelativeLayout password;
    RelativeLayout reminder;
    RelativeLayout weather;
    RelativeLayout news;
    TextView user;
    ImageView cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculadora = findViewById(R.id.calculadora);
        todo = findViewById(R.id.todo);
        reminder = findViewById(R.id.reminder);
        weather = findViewById(R.id.weather);
        news = findViewById(R.id.news);
        cerrar = findViewById(R.id.cerrar);
        user = findViewById(R.id.user);
        FirebaseUser logeduser = FirebaseAuth.getInstance().getCurrentUser();

        int pos = logeduser.getEmail().indexOf("@");

        user.setText("Bienvenid@ "+logeduser.getEmail().substring(0,pos));

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
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Weather.class));
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, News.class));
            }
        });
    }
}