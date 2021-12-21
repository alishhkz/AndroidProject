package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText email;
    EditText password1;
    EditText phonenum;
    //FirebaseAuth firebaseAuth;
    Button signup ;
    DB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password1 = findViewById(R.id.password1);
        phonenum = findViewById(R.id.number);
        //firebaseAuth = FirebaseAuth.getInstance();
        signup = findViewById(R.id.signup);

        DB = new DB(this);
//        if (firebaseAuth.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//            finish();
//        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emaill = email.getText().toString().trim();
                String pass = password1.getText().toString().trim();
                String phone = phonenum.getText().toString().trim();
                String Lusername = username.getText().toString().trim();

                if (TextUtils.isEmpty(Lusername)) {
                    username.setError("Username is Required");
                    return;
                }
                else if (TextUtils.isEmpty(emaill)) {
                    email.setError("Email is Required");
                    return;
                }
                else if(Patterns.EMAIL_ADDRESS.matcher(emaill).matches() == false){
                    email.setError("Please enter a valid email.");
                    return;
                }
                else if (pass.length() < 4) {
                    password1.setError("Password must be 4 characters");
                    return;
                }
                else if (TextUtils.isEmpty(pass)) {
                    password1.setError("Password is Required");
                    return;
                } else if (TextUtils.isEmpty(phone)) {
                    phonenum.setError("Phone Number is Required");
                    return;
                }
                else{
                    if(DB.checkEmail(emaill) == false){
                        Boolean insertData  = DB.insertData(emaill, Lusername, pass, phone);
                        if(insertData){
                            Toast.makeText(RegisterActivity.this, "You are Registered!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        } else{
                            Toast.makeText(RegisterActivity.this, "Registration failed! Try again.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "User with such email already exists! Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
                /*firebaseAuth.createUserWithEmailAndPassword(emaill,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this,"User Created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this,"Error" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });*/
            }
        });
    }
}
