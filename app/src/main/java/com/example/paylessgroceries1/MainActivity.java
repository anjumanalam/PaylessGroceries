package com.example.paylessgroceries1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    //declare private variables
    private EditText mUser, mPass;
    private Button btnSignIn;
    private TextView forgotPass, signUp;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mUser = findViewById(R.id.username);
        mPass = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.signInBtn);
        forgotPass = findViewById(R.id.forgotPassLink);
        signUp = findViewById(R.id.signUpLink);

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }

//        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//            if (firebaseAuth.getCurrentUser() != null) {
//                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//                finish();
//                return;
//            }
//            }
//        };

        //Click 'Sign In' button
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mUser.getText().toString();
                final String pwd = mPass.getText().toString();

                if (TextUtils.isEmpty(user)){
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //authenticate user
                mAuth.signInWithEmailAndPassword(user, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            //error
                            Toast.makeText(MainActivity.this, "Incorrect Credentials", Toast.LENGTH_LONG).show();
                        }
                        else {
                            startActivity(new Intent(MainActivity.this, HomeActivity.class));
                        }
                    }
                });

                //finish();
                //return;
            }
        });

        //Click 'Forgot Password' link
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ForgotPasswordActivity.class));
                //finish();
                //return;
            }
        });

        //Click 'Sign Up' link
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateAccountActivity.class));
                //finish();
                //return;
            }
        });
    } //[END] onCreate method

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(firebaseAuthListener);
//
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
////        updateUI(currentUser);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mAuth.removeAuthStateListener(firebaseAuthListener);
//    }



} //[END] MainActivity
