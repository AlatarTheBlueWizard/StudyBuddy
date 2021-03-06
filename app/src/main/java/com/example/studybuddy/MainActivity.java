package com.example.studybuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    //variables for login
    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private Button btnLogin;

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

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        inputEmail = (EditText) findViewById(R.id.editText);
        inputPassword = (EditText) findViewById(R.id.passMain);
        btnLogin = (Button) findViewById(R.id.mainLoginButton);

        //login button functionality
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                //authenticate user
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user.
                        // the auth state listener will be notified and logic to handle
                        // signed in user can be handled in the listener.
                        /*if(!task.isSuccessful()) {
                            //there was an error
                            if (password.length() < 6) {
                                inputPassword.setError(getString(R.string.minimum_password));
                            } else {
                                Toast.makeText(MainActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            startActivity(new Intent(MainActivity.this, homeActivity.class));
                            finish();
                        }*/
                        if(!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Sign in failed." + task.getException(), Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(MainActivity.this, homeActivity.class));
                            finish();
                        }
                    }
                });
            }
        });
    }
}
