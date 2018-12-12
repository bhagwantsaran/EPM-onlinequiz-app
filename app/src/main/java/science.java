package com.example.najss.javatp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class science extends AppCompatActivity {

    Button b1,b2;
    TextView t1;
    FirebaseDatabase database;
    DatabaseReference myRef;
    int total1;
    String[] items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science);
        final Spinner dropdown = findViewById(R.id.spinner1);
        b1 = (Button) findViewById(R.id.button8);
        b2 = (Button) findViewById(R.id.button9);
        t1=(TextView)findViewById(R.id.textView8);

        Intent i=getIntent();

         final String subject=i.getStringExtra("subject");
         final String id=i.getStringExtra("id");
        int l=0;
        l = i.getIntExtra("l",l);
        items=new String[l+1];
        items[0]="none";
         for(int i1=1;i1<=l;i1++)
             items[i1]= String.valueOf(i1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);
         final String tsubject="Subjects/"+subject+"/"+subject;

        t1.setText("Hello  "+i.getStringExtra("usert").toString());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(science.this, ViewUploadsActivity.class);
                intent.putExtra("subject",subject);
                startActivity(intent);




            }
        });

       b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(science.this, quiz.class);

               String s =dropdown.getSelectedItem().toString();
               if("none".equals(s))
                   Toast.makeText(science.this, "quiz is not selected" , Toast.LENGTH_LONG).show();
               //String tsubject1=tsubject+s;
                else {
                   intent.putExtra("path", tsubject + s);
                   intent.putExtra("subjectid", id);
                   intent.putExtra("quizid", subject + s);
                   startActivity(intent);

               }


            }
        });

    }
}
