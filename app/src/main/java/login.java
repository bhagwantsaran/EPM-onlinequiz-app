package com.example.najss.javatp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    Button b1,b2,b3;
    TextView t1;
   private FirebaseAuth auth;
 //   DatabaseReference reference;
    FirebaseDatabase database;
    DatabaseReference myRef;
    int total=0,j=0,i=0;
    String subject,usert;
    String[] items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Spinner dropdown = findViewById(R.id.spinner1);
        b1 = (Button) findViewById(R.id.button18);
        b2=(Button)findViewById(R.id.button6);
        b3=(Button)findViewById(R.id.button10);
    t1=(TextView)findViewById(R.id.textView4);
        auth = FirebaseAuth.getInstance();
        String id=auth.getUid();
        myRef = FirebaseDatabase.getInstance().getReference("Users").child(id);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Users user=dataSnapshot.getValue(Users.class);
                usert=user.getFirst_name();
                t1.setText("Hello "+user.getFirst_name());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Intent i=getIntent();
        total=i.getIntExtra("total",total);


        //Toast.makeText(Selectsubject.this, "new"+total, Toast.LENGTH_LONG).show();
        items=new String[total+1] ;

        items[0]=new String("none");
        for(int i1=1;i1<=total;i1++)
            items[i1]=new String("none");

       updateSubject(items);
        // database = FirebaseDatabase.getInstance();




        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(login.this, science.class);
                subject = dropdown.getSelectedItem().toString();
                if("none".equals(subject))
                    Toast.makeText(login.this, "subject is not selected", Toast.LENGTH_LONG).show();
                    else{
                    int id = 0;
                    for (int i = 0; i < total; i++)
                        if (items[i].equals(subject))
                            id = i ;
                    final int finalId = id;
                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference("No_of_quiz").child(String.valueOf(id));
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            int l = dataSnapshot.getValue(Integer.class);
                            intent.putExtra("subject", subject);
                            intent.putExtra("id", String.valueOf(finalId));
                            intent.putExtra("usert", usert);
                            intent.putExtra("l", l);
                            startActivity(intent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte=new Intent(login.this,performance.class);
                startActivity(inte);

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut(); //End user session
                startActivity(new Intent(login.this, MainActivity.class)); //Go back to home page
                finish();
            }
        });


    }
    private void updateSubject(final String items[]) {

        i++;
        if(i<=total) {
            database=FirebaseDatabase.getInstance();
            myRef = database.getReference("subject").child(String.valueOf(i));
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String count = dataSnapshot.getValue(String.class);
                    items[i]=new String(count);

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
