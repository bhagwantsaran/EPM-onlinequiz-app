package com.example.najss.javatp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Mysubject extends AppCompatActivity {
    TextView t1;
    int i=1;
    int total=0;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysubject);
        t1=(TextView)findViewById(R.id.textView21);
        database = FirebaseDatabase.getInstance();
        Intent i=getIntent();
        total=i.getIntExtra("total",total);
        updateSubject();

    }

    private void updateSubject() {
        myRef = database.getReference("subject").child(String.valueOf(i));
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String count = dataSnapshot.getValue(String.class);
                if(i<=total && !count.equals("null")){
                t1.setText(t1.getText().toString()+i+"."+count+"\n");
               i++;
              updateSubject();}

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Toast.makeText(Selectsubject.this, "failed", Toast.LENGTH_LONG).show();
            }
        });

    }
}
