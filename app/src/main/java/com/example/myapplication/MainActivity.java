package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
TextView txt;
Button login;
    EditText inputemail,inputpassword;

    String emailPattern= "/^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$/";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=findViewById(R.id.loginbtn);
        txt=findViewById(R.id.newuser);
        inputemail=findViewById(R.id.inputEmail);
        inputpassword=findViewById(R.id.inputpassword);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
            }
        });



      txt.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(MainActivity.this,NewUser.class);
              startActivity(intent);
          }
      });


    }

    private void performLogin() {
        String email = inputemail.getText().toString();
        String password = inputpassword.getText().toString();


        if (email.matches(emailPattern)) {
            inputemail.setError("Enter correct email");
        } else if (password.isEmpty() || password.length() < 6) {
            inputpassword.setError("Enter proper password");
        } else {
            progressDialog.setMessage("Please Wait While Login...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                  if(task.isSuccessful()){
                      progressDialog.dismiss();
                      sendUserToNextActivity();
                      Toast.makeText(MainActivity.this, "Now proceed to verification process", Toast.LENGTH_SHORT).show();
                  }
                  else {
                      progressDialog.dismiss();
                      Toast.makeText(MainActivity.this, "User Not Found", Toast.LENGTH_SHORT).show();
                  }
                }
            });


        }

    }
    private void sendUserToNextActivity() {
        Intent intent=new Intent(MainActivity.this,entermobilenumber.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}