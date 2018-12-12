package com.example.najss.javatp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Aquiz extends AppCompatActivity {
    Button add,cancel;
    EditText q,o1,o2,o3,o4,a;
    TextView t1;
    int counter=1;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aquiz);
        add=(Button)findViewById(R.id.button16);
        cancel=(Button)findViewById(R.id.button15);
        q=(EditText)findViewById(R.id.editText8);
        o1=(EditText)findViewById(R.id.editText9);
        o2=(EditText)findViewById(R.id.editText10);
        o3=(EditText)findViewById(R.id.editText11);
        o4=(EditText)findViewById(R.id.editText12);
        a=(EditText)findViewById(R.id.editText13);
    t1=(TextView)findViewById(R.id.textView22);
        Intent i=getIntent();
        final String subject=i.getStringExtra("subject");
        final String id=i.getStringExtra("id");
        int ct =0;
        ct = i.getIntExtra("ct",ct);
        t1.setText("question "+String.valueOf(counter));
        database = FirebaseDatabase.getInstance();
        Toast.makeText(Aquiz.this, "Completed"+ct, Toast.LENGTH_LONG).show();
        myRef = database.getReference("No_of_quiz").child(id);

        myRef.setValue(ct);
        Toast.makeText(Aquiz.this, "updated", Toast.LENGTH_LONG).show();
        final int finalCt = ct;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String question=q.getText().toString().trim();
                 String option1=o1.getText().toString().trim();
                 String option2=o2.getText().toString().trim();
                 String option3=o3.getText().toString().trim();
                 String option4=o4.getText().toString().trim();
                 String answer=a.getText().toString().trim();
                if("".equals(question)||"".equals(option1)||"".equals(option2)||"".equals(option3)||"".equals(option4)||"".equals(answer))
                {
                    Toast.makeText(Aquiz.this, "fill all fields", Toast.LENGTH_LONG).show();
                }
                else
                {
                    String mk="Subjects/"+subject+"/"+subject+ finalCt +"/Questions/"+String.valueOf(counter);

                    myRef = database.getReference(mk+"/question");
                    myRef.setValue(question);
                    myRef = database.getReference(mk+"/option1");
                    myRef.setValue(option1);
                    myRef = database.getReference(mk+"/option2");
                    myRef.setValue(option2);
                    myRef = database.getReference(mk+"/option3");
                    myRef.setValue(option3);
                    myRef = database.getReference(mk+"/option4");
                    myRef.setValue(option4);
                    myRef = database.getReference(mk+"/answer");
                    myRef.setValue(answer);
                    q.setText("");
                    o1.setText("");
                    o2.setText("");
                    o3.setText("");
                    o4.setText("");
                    a.setText("");
                    if(counter>=10)
                    {Toast.makeText(Aquiz.this, "Completed", Toast.LENGTH_LONG).show();
                        finish();}
                    counter++;
                    t1.setText("question "+String.valueOf(counter));
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q.setText("");
                o1.setText("");
                o2.setText("");
                o3.setText("");
                o4.setText("");
                a.setText("");
            }
        });

    }
}
