package com.example.najss.javatp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ViewUploadsActivity extends AppCompatActivity {

    ListView listView;

    //database reference to get uploads data
    DatabaseReference mDatabaseReference;
    StorageReference mStorageReference;

    //list to store uploads data
    List<Upload> uploadList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_uploads);
        uploadList = new ArrayList<>();
       // mDatabaseReference.child("uploads/1542385099947.pdf");
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the upload
                Upload upload = uploadList.get(i);
                mStorageReference = FirebaseStorage.getInstance().getReference();
               String str= mStorageReference.child("uploads/1542440775382.pdf").getDownloadUrl().toString();
                //Opening the upload file in browser using the upload url
              //  Toast.makeText(ViewUploadsActivity.class,"123");
                Toast.makeText(ViewUploadsActivity.this,"str"+str,Toast.LENGTH_LONG).show();
                Uri webpage=Uri.parse(upload.getUrl().toString());
               Intent intent = new Intent(Intent.ACTION_VIEW,webpage);
               if(intent.resolveActivity(getPackageManager())!=null)
               {
                   startActivity(intent);
               }
                //intent.setData(Uri.parse(upload.getUrl()));
                //Intent newIntent=Intent.createChooser(intent,"Open File");


            }
        });
        Intent i=getIntent();
        String subject=i.getStringExtra("subject");
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("uploads/"+subject);

        //retrieving upload data from firebase database
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    uploadList.add(upload);
                }

                String[] uploads = new String[uploadList.size()];

                for (int i = 0; i < uploads.length; i++) {
                    uploads[i] = uploadList.get(i).getName();
                }

                //displaying it to list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                listView.setAdapter(adapter);
                listView.setBackgroundColor(Color.BLUE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        }
}
