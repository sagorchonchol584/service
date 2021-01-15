package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Regsitation extends AppCompatActivity {

    TextView signin_button,singup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsitation);

        signin_button=(TextView)findViewById(R.id.signin);
        singup=(TextView)findViewById(R.id.textView6);



        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"SIGN IN",Toast.LENGTH_LONG).show();
            }


        });


        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainView.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"SIGN IN",Toast.LENGTH_LONG).show();
            }
        });

    }
}