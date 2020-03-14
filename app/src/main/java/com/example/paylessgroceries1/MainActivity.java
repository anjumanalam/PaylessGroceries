package com.example.paylessgroceries1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    //declare private variables
    private EditText mUser, mPass;
    private Button signInButton;
    private TextView forgotPass, tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUser = findViewById(R.id.username);
        mPass = findViewById(R.id.password);
        signInButton = findViewById(R.id.signInBtn);
        forgotPass = findViewById(R.id.forgotPassLink);
        tvSignUp = findViewById(R.id.signUpLink);

        //Action for when sign in button is clicked
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        //Direct to ForgotPasswordActivity when Forgot Password is clicked
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        //Direct to CreateAccountActivity when Sign Up is clicked
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(i);
                finish();
                return;
            }
        });
    }
}