package com.example.studybuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class createActivity extends AppCompatActivity{
    private EditText inputEmail, inputPassword, inputTeam, inputFirst, inputLast;
    private Button btnSignUp;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //use back button to go back to login page
        final Button createAccountButton = (Button) findViewById(R.id.goBackCreateButton);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent backIntent = new Intent(v.getContext(), MainActivity.class);
                startActivity(backIntent);
            }
        });

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        //variables to contact specific id's for account creation
        btnSignUp = (Button) findViewById(R.id.createAccountButton);
        inputEmail = (EditText) findViewById(R.id.editText2);
        inputPassword = (EditText) findViewById(R.id.passEditCreate);
        inputTeam = (EditText) findViewById(R.id.uNameEnterCreate);
        inputFirst = (EditText) findViewById(R.id.fNameEnterCreate);
        inputLast = (EditText) findViewById(R.id.lNameEnterCreate);

        //create account button logic
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String team = inputTeam.getText().toString().trim();
                String firstName = inputFirst.getText().toString().trim();
                String lastName = inputLast.getText().toString().trim();

                //use to prompt email entry is field is null, input validation
                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(team)) {
                    Toast.makeText(getApplicationContext(), "Enter team name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(firstName)) {
                    Toast.makeText(getApplicationContext(), "Enter team name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(lastName)) {
                    Toast.makeText(getApplicationContext(), "Enter last name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter a minimum of 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                //create user
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(createActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(createActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        // If sign up fails, display a message to the user. If sign up succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed up user can be handled in the listener
                        if(!task.isSuccessful()) {
                            Toast.makeText(createActivity.this, "Creation failed." + task.getException(), Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(createActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
            }
        });
    }
}
