package com.example.najss.javatp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Selectsubjectcontent extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    int total=0,j=0,i=0;
    String subject;
    String[] items;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectsubject);
        final Spinner dropdown = findViewById(R.id.spinner1);
        database = FirebaseDatabase.getInstance();
        b1=(Button)findViewById(R.id.button18);
        //updateTotal();
       Intent i=getIntent();
        total=i.getIntExtra("total",total);
        //Toast.makeText(Selectsubjectcontent.this, "got"+total, Toast.LENGTH_LONG).show();
        items=new String[total] ;

        items[0]=new String("Science");
        for(int i1=1;i1<total;i1++)
        items[i1]=new String("1");
        //items[2]=new String("2");
        updateSubject(items);
        // database = FirebaseDatabase.getInstance();




        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Selectsubjectcontent.this, Acontent.class);
                subject = dropdown.getSelectedItem().toString();
                intent.putExtra("subject",subject);
                startActivity(intent);
            }
        });
    }



    private void updateSubject(final String items[]) {

        i++;
        if(i<=total) {
            myRef = database.getReference("subject").child(String.valueOf(i));
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String count = dataSnapshot.getValue(String.class);
                    items[i-1]=new String(count);
                    //goes(count);
                    // Toast.makeText(Selectsubject.this, "completed" + count, Toast.LENGTH_LONG).show();
                    updateSubject(items);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Toast.makeText(Selectsubject.this, "failed", Toast.LENGTH_LONG).show();
                }
            });

        }

    }


}
