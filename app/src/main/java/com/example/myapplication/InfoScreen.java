package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InfoScreen extends AppCompatActivity {
Button btn;

EditText firstname ,lastname,schoolname,classnum;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_screen);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        Home_Fragment home_fragment =new Home_Fragment();

        btn=findViewById(R.id.btnsave);
        firstname=findViewById(R.id.firstName);
        classnum=findViewById(R.id.classnum);
        lastname=findViewById(R.id.lastname);
        schoolname=findViewById(R.id.schoolname);
       String text=firstname.getText().toString();
        String ln=lastname.getText().toString();
        String sc=schoolname.getText().toString();
        String cl=classnum.getText().toString();




String name=firstname.getText().toString();
String d="anand";

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                //if(TextUtils.isEmpty(firstname.getText().toString()) || TextUtils.isEmpty(lastname.getText().toString()) || TextUtils.isEmpty(schoolname.getText().toString()) || TextUtils.isEmpty(classnum.getText().toString()) ){
                  //  Toast.makeText(InfoScreen.this, "Not filled yet", Toast.LENGTH_SHORT).show();
              //  }
              //  else{
                   Bundle bundle=new Bundle();

                     bundle.putString("name", d);
                    home_fragment.setArguments(bundle);
                    fragmentTransaction.add(R.id.fragment_container,home_fragment);
                    fragmentTransaction.commit();
                    Intent i=new Intent(InfoScreen.this,DashBoard.class);
                    //i.putExtra("name",firstname.getText().toString());
                    startActivity(i);

              //  }
               // Intent i=new Intent(InfoScreen.this,DashBoard.class);
               // i.putExtra("name",firstname.getText().toString());
               // startActivity(i);
            }
        });

    }


}