package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

import org.w3c.dom.Text;

public class NewUser extends AppCompatActivity {

    TextView alreadyhaveanaccount;
    Button registerbtn;
    EditText inputemail,inputpassword,inputmobilenumber,inputconfirmpassword;

    String emailPattern= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        alreadyhaveanaccount=findViewById(R.id.alreadyhaveanaccount);
        registerbtn=findViewById(R.id.registerbtn);
        inputemail=findViewById(R.id.email);

        inputpassword=findViewById(R.id.password);
        inputconfirmpassword=findViewById(R.id.confirmpassword);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();


        alreadyhaveanaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforAuth();
            }
        });





    }

    private void PerforAuth() {
        String email=inputemail.getText().toString();
        String password=inputpassword.getText().toString();
        String confirmPassword=inputconfirmpassword.getText().toString();



         if(password.isEmpty() || password.length()<6){
       inputpassword.setError("Enter proper password");
        } else if (!password.equals(confirmPassword)) {
            inputconfirmpassword.setError("Password not match !!!!!");
        }
        else {
            progressDialog.setMessage("Please Wait While Registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(NewUser.this, "Now proceed to verification process", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(NewUser.this, "User already exist", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }

    }

    private void sendUserToNextActivity() {
        Intent intent=new Intent(NewUser.this,entermobilenumber.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}