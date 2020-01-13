package com.usefulapps.allinone;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ToDo extends AppCompatActivity {

    Button button;
    EditText inputText;
    ListView listView;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_to_do);

        button= findViewById(R.id.addButton);
        inputText=findViewById(R.id.inputText);
        listView=findViewById(R.id.listView);
        list = new ArrayList<>();
    }

    public void onClickAdd(View v){

        String text = inputText.getText().toString();

        if(!text.equals("")){
            list.add(text);
            ArrayAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
            listView.setAdapter(adapter);
            inputText.setText("");
        }
    }
}