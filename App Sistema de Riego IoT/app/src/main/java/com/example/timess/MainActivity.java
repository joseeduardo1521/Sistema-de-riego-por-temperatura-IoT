package com.example.timess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    private TextView texto1;
    private TextView texto2;
    private TextView texto3;
    private DatabaseReference mDatabase;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference firstDatabase = databaseReference.child("Humedad");
    private DatabaseReference secondDatabase = databaseReference.child("Temperatura");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto1 = (TextView) findViewById(R.id.mms);
        texto2 = (TextView) findViewById(R.id.mms2);
        texto3 = (TextView) findViewById(R.id.info);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        getUserInfo();

    }


    private void getUserInfo(){
        mDatabase.child("Ejemplo_01").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            if(snapshot.exists()){
                String Temperatura = snapshot.child("Temperatura").getValue().toString();
                String Humedad = snapshot.child("Humedad").getValue().toString();
                String HumedadSu = snapshot.child("Humedad Tierra").getValue().toString();
                texto1.setText(Temperatura);
                texto2.setText(Humedad);
                texto3.setText(HumedadSu);
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


/*
    @Override
    public void onDataChange(DataSnapshot dataSnapshot){
        if(dataSnapshot.getValue(String.class)!=null){
            String key = dataSnapshot.getKey();
            if(key.equals("d")){
                String first = dataSnapshot.getValue(String.class);
                textView.setText(first);
            }
            if(key.equals("d")){
                String second = dataSnapshot.getValue(String.class);
                textView2.setText(second);
            }
        }
    }

    public void onCancelled(DatabaseError databaseError){

    }

     databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue(String.class)!=null){
                    String key = snapshot.getKey();
                    if(key.equals("d")){
                        String first = snapshot.getValue(String.class);
                        textView.setText(first);
                    }
                    if(key.equals("d")){
                        String second = snapshot.getValue(String.class);
                        textView2.setText(second);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


     String key = snapshot.getKey();
                if(key.equals("Humedad")){
                    String first = snapshot.getValue(String.class);
                    texto1.setText(first);
                }
                if(key.equals("Temperatura")){
                    String second = snapshot.getValue(String.class);
                    texto2.setText(second);
                }



*/
}