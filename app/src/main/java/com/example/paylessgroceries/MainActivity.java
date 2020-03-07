package com.example.paylessgroceries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    /*Declare Sign In button*/
    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Assign id of button from xml file to variable*/
        signInButton = findViewById(R.id.signInBtn);

        /*When button is clicked, validate the data entered and if the data is correct,
        * send user to dashboard.
        * If data is incorrect, provide an alert that credentials are incorrect and to try again
        * at the same page.*/
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
