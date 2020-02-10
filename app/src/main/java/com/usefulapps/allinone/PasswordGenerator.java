package com.usefulapps.allinone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class PasswordGenerator extends AppCompatActivity {
    Button generate;
    EditText size;
    TextView password;
    Switch minusculasSwitch;
    Switch mayusculasSwitch;
    Switch caractersSwitch;
    Switch numerosSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_generator);
        size=findViewById(R.id.passsize);
        generate=findViewById(R.id.generate);
        password=findViewById(R.id.password);

        minusculasSwitch = findViewById(R.id.letras);
        mayusculasSwitch = findViewById(R.id.mayusculas);
        caractersSwitch = findViewById(R.id.simbolos);
        numerosSwitch = findViewById(R.id.numbers);

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager _clipboard = (ClipboardManager) PasswordGenerator.this.getSystemService(Context.CLIPBOARD_SERVICE);
                _clipboard.setText(password.getText().toString());
            }
        });

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(!minusculasSwitch.isChecked()&&!mayusculasSwitch.isChecked()&&!numerosSwitch.isChecked()&&!caractersSwitch.isChecked())) {
                    int passSize = Integer.parseInt(size.getText().toString());
                    StringBuilder finalPassword = new StringBuilder();
                    String minusculas = "abcdefghijklmnopqrstuvwxyz";
                    String mayusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                    String numeros = "1234567890";
                    String caracteres = "@#$%();:*&/'";
                    String specificCharacter = ""; //Auxiliar var
                    for (int i = 0; i < passSize; i++) {
                        Random n = new Random();
                        int low = 0;
                        int high = 4;
                        boolean valid = true;
                        do {
                            int result = n.nextInt(high - low) + low;
                            if (result == 0) {
                                specificCharacter = minusculas;
                                if (!minusculasSwitch.isChecked()) {
                                    valid = false;
                                } else {
                                    valid = true;
                                }
                            } else if (result == 1) {
                                specificCharacter = mayusculas;
                                if (!mayusculasSwitch.isChecked()) {
                                    valid = false;
                                } else {
                                    valid = true;
                                }
                            } else if (result == 2) {
                                specificCharacter = numeros;
                                if (!numerosSwitch.isChecked()) {
                                    valid = false;
                                } else {
                                    valid = true;
                                }
                            } else if (result == 3) {
                                specificCharacter = caracteres;
                                if (!caractersSwitch.isChecked()) {
                                    valid = false;
                                } else {
                                    valid = true;
                                }
                            }
                        }
                        while (!valid);
                        Random r = new Random();
                        finalPassword.append(specificCharacter.charAt(r.nextInt(specificCharacter.length())));
                    }
                    password.setText(finalPassword);
                }else{
                    password.setText("Select something");
                }
            }
        });
    }
}
