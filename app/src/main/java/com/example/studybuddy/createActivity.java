package com.example.studybuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class createActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //use back button to go back to login page
        Button createAccountButton = (Button) findViewById(R.id.goBackCreateButton);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent backIntent = new Intent(v.getContext(), MainActivity.class);
                startActivity(backIntent);
            }
        });
    }
}
