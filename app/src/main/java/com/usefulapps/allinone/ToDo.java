package com.usefulapps.allinone;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static java.sql.DriverManager.println;

public class ToDo extends AppCompatActivity {
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    Button button;
    EditText inputText;
    ListView listView;
    ArrayList<String> list;
    Integer ItemCount = 0;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_to_do);

        button= findViewById(R.id.addButton);
        inputText=findViewById(R.id.inputText);
        listView=findViewById(R.id.listView);
        list = new ArrayList<>();
        lv=findViewById(R.id.listView);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("TodoItem").child(currentFirebaseUser.getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    list = new ArrayList<>();
                    int i = 0;
                    ItemCount=0;
                    for(DataSnapshot d : dataSnapshot.getChildren()) {
                        addElement(d.getValue().toString());
                        ItemCount++;
                        i++;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                System.out.println(lv.getItemAtPosition(position).toString());
                FirebaseDatabase.getInstance().getReference("TodoItem").child(currentFirebaseUser.getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    if (snapshot.getValue().equals((lv.getItemAtPosition(position)).toString())){
                                        snapshot.getRef().removeValue();
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
            }
        });
    }

    public void addElement(String mensaje){
        list.add(mensaje);
        ArrayAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        inputText.setText("");
    }

    public void onClickAdd(View v){

        String text = inputText.getText().toString();

        if(!text.equals("")){

            final String userName = currentFirebaseUser.getUid();
            ItemCount++;
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            db.getReference("TodoItem").child(userName).child("TodoItem"+ItemCount).setValue(text).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                }
            });
        }
    }
}