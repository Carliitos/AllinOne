package com.usefulapps.allinone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Calculadora extends AppCompatActivity {
    RelativeLayout btn1;
    RelativeLayout btn2;
    RelativeLayout btn3;
    RelativeLayout btn4;
    RelativeLayout btn5;
    RelativeLayout btn6;
    RelativeLayout btn7;
    RelativeLayout btn8;
    RelativeLayout btn9;
    RelativeLayout btn0;
    RelativeLayout btndiv;
    RelativeLayout btnmult;
    RelativeLayout btneq;
    RelativeLayout btnres;
    TextView restext;
    RelativeLayout btnsum;
    RelativeLayout btnresta;
    Integer xifra1;
    Integer xifra2;
    String operador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        btndiv = findViewById(R.id.btndivision);
        btnmult = findViewById(R.id.btnmult);
        btnres = findViewById(R.id.btnresult);
        restext = findViewById(R.id.restext);
        btnsum = findViewById(R.id.btnsuma);
        btnresta = findViewById(R.id.btnresta);

        btndiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addsimbolo("/");
            }
        });
        btnmult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addsimbolo("*");
            }
        });
        btnresta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addsimbolo("-");
            }
        });
        btnres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addsimbolo("=");
            }
        });
        btnsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addsimbolo("+");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addnumber(1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addnumber(2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addnumber(3);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addnumber(4);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addnumber(5);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addnumber(6);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addnumber(7);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addnumber(8);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addnumber(9);
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addnumber(0);
            }
        });

    }
    void addnumber(Integer num){
        restext.setText(restext.getText()+num.toString());
    }
    void addsimbolo(String simbolo) {
        if (xifra2 == null) {
            xifra2 = Integer.parseInt(restext.getText().toString());
            if (operador.equals("+")) {
                Integer result = xifra1 + xifra2;
                restext.setText(result + "");
                xifra1 = null;
                xifra2 = null;
            }
        } else {
            if (simbolo.equals("=")) {
                operador = simbolo;
                if (xifra1 == null) {
                    xifra1 = Integer.parseInt(restext.getText().toString());
                    restext.setText("");
                }


            }
        }
    }
}

