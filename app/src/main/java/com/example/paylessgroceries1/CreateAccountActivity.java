package com.example.paylessgroceries1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccountActivity extends AppCompatActivity {

    //declare private variables
    private TextInputEditText mfName, mlName;
    private EditText mEmail, mPass, mrPass, mZip;
    private Button register;

    //declare Firebase variables for authentication
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //Set toolbar as actionbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();

        mfName = findViewById(R.id.f_name);
        mlName = findViewById(R.id.l_name);
        mEmail = findViewById(R.id.email);
        mPass = findViewById(R.id.password);
        mrPass = findViewById(R.id.r_password);
        mZip = findViewById(R.id.zip_code);
        register = findViewById(R.id.createAccountBtn);

        //Set up a Listener for when the user hits the 'Create Account' button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String pwd = mPass.getText().toString();

                if (email.isEmpty()){
                    mEmail.setError("Please enter email address.");
                    mEmail.requestFocus();
                }
                else if (pwd.isEmpty()){
                    mPass.setError("Please enter a password.");
                    mPass.requestFocus();
                }
                else if (email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(CreateAccountActivity.this, "Required fields are empty!", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(CreateAccountActivity.this, HomeActivity.class));

                                String userID = mAuth.getCurrentUser().getUid();
                                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
                                current_user_db.setValue(true);
                            }
                            else{
                                Toast.makeText(CreateAccountActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                            }
                        } //[END] onComplete method
                    }); //[END] createUserWithEmailAndPassword
                }
            } //[END] onClick method
        }); //[END] OnClickListener for Create Account

    } //[END] onCreate method


} //[END] CreateAccountActivity class