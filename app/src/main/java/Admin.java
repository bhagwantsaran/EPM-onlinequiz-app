package com.example.najss.javatp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin extends AppCompatActivity {
    Button b1,b5,b2,b3,b4;
    int total=0;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        b1=(Button)findViewById(R.id.button14);
        b2=(Button)findViewById(R.id.button12);
        b3=(Button)findViewById(R.id.button7);
        b4=(Button)findViewById(R.id.button13) ;
        b5=(Button)findViewById(R.id.button19);
        updateTotal();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin.this, Selectsubject.class);
                Toast.makeText(Admin.this, "Send"+total, Toast.LENGTH_LONG).show();
                intent.putExtra("total",total);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin.this, Addsubject.class);
                //intent.putExtra("total",total);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin.this, Mysubject.class);
                intent.putExtra("total",total);
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin.this, Selectsubjectcontent.class);
                intent.putExtra("total",total);
                startActivity(intent);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin.this, MainActivity.class)); //Go back to home page
                finish();
            }
        });
    }
    private void updateTotal() {
        database=FirebaseDatabase.getInstance();
        myRef = database.getReference("No_of_subject");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer count=dataSnapshot.getValue(Integer.class);
                total=(int)count;

                // go(count);
                Toast.makeText(Admin.this, "completed"+total, Toast.LENGTH_LONG).show();
                //finish();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Toast.makeText(Selectsubject.this, "failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}

