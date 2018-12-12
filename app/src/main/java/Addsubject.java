package com.example.najss.javatp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Addsubject extends AppCompatActivity {

    EditText e1;
    Button b1;
    FirebaseDatabase database;
    DatabaseReference myRef;
    int total=0,j=0,i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsubject);
        e1=(EditText)findViewById(R.id.editText6);
        b1=(Button)findViewById(R.id.button17);

        database = FirebaseDatabase.getInstance();
        updateTotal();

        b1.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                final String subject=e1.getText().toString().trim();
                if("".equals(subject))
                {
                    Toast.makeText(Addsubject.this, "enter subject Name", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(Addsubject.this, "Subject is Added Successfully", Toast.LENGTH_LONG).show();
                total++;
                myRef = database.getReference("subject/"+total);
                myRef.setValue(subject);
                myRef=database.getReference("No_of_subject");
                myRef.setValue(total);
                    myRef = database.getReference("No_of_quiz/"+total);
                    myRef.setValue(0);
                }
            }
        });
    }

    private void updateTotal() {
        myRef = database.getReference("No_of_subject");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer count=dataSnapshot.getValue(Integer.class);
                total=(int)count;

                // go(count);
                Toast.makeText(Addsubject.this, "completed"+total, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Toast.makeText(Selectsubject.this, "failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}
