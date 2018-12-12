package com.example.najss.javatp;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.najss.javatp.Constant.FIRST_COLUMN;
import static com.example.najss.javatp.Constant.SECOND_COLUMN;
import static com.example.najss.javatp.Constant.THIRD_COLUMN;
import static com.example.najss.javatp.Constant.FOURTH_COLUMN;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;


public class performance extends Activity {
    private ArrayList<HashMap> list;
listviewAdapter adapter;
ListView lview;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);

      lview = (ListView) findViewById(R.id.listview);
        populateList();
        adapter = new listviewAdapter(this, list);
        lview.setAdapter(adapter);

    }

    private void populateList() {

        list = new ArrayList<HashMap>();
        FirebaseDatabase database;
        DatabaseReference mRef;
        database = FirebaseDatabase.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String Uid = auth.getUid();//id;
        mRef = database.getReference("Score_board/" + Uid);
      //Toast.makeText(performance.this,"go"+Uid,Toast.LENGTH_LONG);
        HashMap temp = new HashMap();
        temp.put(FIRST_COLUMN, "" + "1");
        temp.put(SECOND_COLUMN, "" + Uid);
        temp.put(THIRD_COLUMN, "" + "3");
        temp.put(FOURTH_COLUMN, "" + "4");
        list.add(temp);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    //Toast.makeText(performance.this,"go",Toast.LENGTH_LONG);
                   Marks marks = ds.getValue(Marks.class);
                    HashMap temp = new HashMap();
                    temp.put(FIRST_COLUMN, "" + "1");
                    temp.put(SECOND_COLUMN, "" + "2");
                    temp.put(THIRD_COLUMN, "" + "3");
                    temp.put(FOURTH_COLUMN, "" + "4");
                    list.add(temp);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
