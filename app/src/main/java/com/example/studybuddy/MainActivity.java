package com.example.studybuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //use create account button to open createActivity
        Button createAccountButton = (Button) findViewById(R.id.createAccountMain);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent cIntent = new Intent(v.getContext(), createActivity.class);
                startActivity(cIntent);
            }
        });
    }
}
