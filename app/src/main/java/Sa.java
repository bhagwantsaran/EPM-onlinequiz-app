package com.example.najss.javatp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sa extends AppCompatActivity {

    private EditText inputEmail, inputPassword,nam,lnam,cnfpwd;
    private Button btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    DatabaseReference myRef ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sa);

        //Get Firebase auth instance

        auth = FirebaseAuth.getInstance();
        //mref = FirebaseDatabase.getInstance().getReference();
        //myRef.setValue("Hello, World!");
        // btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.button);
        inputEmail = (EditText) findViewById(R.id.editText);
        nam=(EditText)findViewById(R.id.editText3);
        inputPassword = (EditText) findViewById(R.id.editText2);
        lnam=(EditText)findViewById(R.id.editText4);
        cnfpwd=(EditText)findViewById(R.id.editText5);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


       /* btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =nam.getText().toString().trim();
                String lname=lnam.getText().toString().trim();
              final   String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String cnfpassword=cnfpwd.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if("".equals(name)) {
                    Toast.makeText(getApplicationContext(), "Please Enter Name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(cnfpassword))
                {
                    Toast.makeText(getApplicationContext(), "Password and Confirm Password doesnot match", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Sa.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                Toast.makeText(Sa.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Sa.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {

                                    Toast.makeText(Sa.this, "Successfully Registered" + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                    addUsers();
                                    //myRef.child("email").setValue(email);
                                    Intent intent = new Intent(Sa.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                            public void addUsers()
                            {

                                String first_name=nam.getText().toString();
                                String last_name=lnam.getText().toString();
                                String gmail=inputEmail.getText().toString().trim();
                                String password=inputPassword.getText().toString();
                                String mk="Users";
                               // Firebase
                                //Firebase usersRef = ref.child("Users");
                                database = FirebaseDatabase.getInstance();
                                myRef = database.getReference(mk);
                                String id = auth.getUid();
                               // String id=myRef.push().getKey();
                                Users users=new Users(id,first_name,last_name,gmail,password);
                                myRef.child(id).setValue(users);

                            }
                        });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
