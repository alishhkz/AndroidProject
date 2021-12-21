package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText email;
    EditText password;
    EditText phonenum;
    //FirebaseAuth firebaseAuth;
    Button loginbtn;
    TextView createText;
    DB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phonenum = findViewById(R.id.number);
        //firebaseAuth = FirebaseAuth.getInstance();
        loginbtn = findViewById(R.id.loginbtn);
        createText = findViewById(R.id.createText);
        DB = new DB(this);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emaill = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if (TextUtils.isEmpty(emaill)) {
                    email.setError("Email is Required");
                    return;
                }
                else if (TextUtils.isEmpty(pass)) {
                    password.setError("Password is Required");
                    return;
                }
                else {
                    if(DB.checkUsernamePassword(emaill, pass)){
                        Toast.makeText(LoginActivity.this, "Signed in!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), CharactersActivity.class);
                        startActivity(intent);
                    } else Toast.makeText(LoginActivity.this, "Something is wrong! Try again.", Toast.LENGTH_SHORT).show();
                }
                /*firebaseAuth.signInWithEmailAndPassword(emaill,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this,"Login Succesful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),CharactersActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this,"Error" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }

                    }
                });*/
            }
        });

        createText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });
    }
}
