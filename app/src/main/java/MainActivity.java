package com.example.najss.javatp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends Activity {
    Button b1, b2,b3,b4;
    EditText ed1, ed2;
    TextView tv;
    private ProgressBar progressBar;
    TextView tx1;
    int counter = 3,total=0;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
       b3=(Button)findViewById(R.id.button3);
       b4=(Button)findViewById(R.id.button11);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        b2 = (Button) findViewById(R.id.button2);
        auth=FirebaseAuth.getInstance();


        //database=FirebaseDatabase.getInstance();
        //ref=database.getReference("user");
        updateTotal();
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = ed1.getText().toString().trim();
                final String password = ed2.getText().toString().trim();
                if("2016ucp1389@mnit.ac.in".equals(email)&& "1389".equals(password))
                {
                    Intent intent = new Intent(MainActivity.this, Admin.class);
                    startActivity(intent);
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var1) {
                Toast.makeText(MainActivity.this, "send"+total, Toast.LENGTH_LONG).show();
                final String email = ed1.getText().toString();
                final String password = ed2.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        ed2.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(MainActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, "Successfully Login", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(MainActivity.this, login.class);
                                    String Uid=auth.getUid();
                                    intent.putExtra("total",total);
                                    intent.putExtra("Uid",Uid);
                                    Toast.makeText(MainActivity.this, "send"+total, Toast.LENGTH_LONG).show();
                                    startActivity(intent);
                                    //finish();
                                }
                            }
                        });
            }


        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Sa.class);
                startActivity(intent);
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
                Toast.makeText(MainActivity.this, "completed"+total, Toast.LENGTH_LONG).show();
                //finish();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Toast.makeText(Selectsubject.this, "failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}