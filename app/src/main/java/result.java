package com.example.najss.javatp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class result extends AppCompatActivity {
    TextView t1,t2,t3;
    private  FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        t1=(TextView)findViewById(R.id.textView14);
        t2=(TextView)findViewById(R.id.textView15);
        t3=(TextView)findViewById(R.id.textView16);
        auth = FirebaseAuth.getInstance();
        String id=auth.getUid();
        Intent i=getIntent();

        String correct=i.getStringExtra("correct");
        String wrong=i.getStringExtra("wrong");
        t1.setText("10");
        t2.setText(correct);
        t3.setText(wrong);
    }
}
