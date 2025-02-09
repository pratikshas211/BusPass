package com.example.buspass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;
    TextView textView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI components and DatabaseHelper
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        textView = findViewById(R.id.signupText);
        databaseHelper = new DatabaseHelper(this);

        // Login button click listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputUsername = username.getText().toString().trim();
                String inputPassword = password.getText().toString().trim();

                // Check if the fields are not empty
                if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(login.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                } else {
                    // Check credentials with database
                    boolean isValidUser = databaseHelper.checkUser(inputUsername, inputPassword);

                    if (isValidUser) {
                        Toast.makeText(login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this, MainActivity.class);
                        startActivity(intent);
                        finish();  // Close the login activity
                    } else {
                        Toast.makeText(login.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Redirect to registration page
        textView.setOnClickListener(view -> {
            Intent intent = new Intent(login.this, register.class);
            startActivity(intent);
        });
    }
}
