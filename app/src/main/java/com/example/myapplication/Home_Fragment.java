package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Home_Fragment extends Fragment {

    TextView textView;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view= inflater.inflate(R.layout.fragment_home_, container, false);
        textView=view.findViewById(R.id.name);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String s = bundle.getString("first");
            textView.setText(s);
        } else {
            // Debugging: Log a message if bundle is null
            Log.d("HomeFragment", "Bundle is null");
        }

        return view;
    }
}