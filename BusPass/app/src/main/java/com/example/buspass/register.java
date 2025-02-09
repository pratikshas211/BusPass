package com.example.buspass;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class register extends AppCompatActivity {

    // Declare EditText and DatabaseHelper variables
    EditText usernameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    DatabaseHelper databaseHelper;

    // Regular expressions for validation
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s]{2,50}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Initialize EditText fields and database helper
        usernameEditText = findViewById(R.id.username);
        emailEditText = findViewById(R.id.Email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.conpassword);
        databaseHelper = new DatabaseHelper(this);
        Button loginRedirectBtn;

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Register button click listener
        findViewById(R.id.signupbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        // Find and handle the "Login" button click
        loginRedirectBtn = findViewById(R.id.loginRedirectBtn);
        loginRedirectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to login activity
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
            }
        });

    }

    // Method to handle user registration with validations
    private void registerUser() {
        String username = usernameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        // Validate name
        if (username.isEmpty()) {
            Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!NAME_PATTERN.matcher(username).matches()) {
            Toast.makeText(this, "Please enter a valid username", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate email
        if (email.isEmpty()) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate password
        if (password.isEmpty()) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            Toast.makeText(this, "Password must be at least 8 characters long, include an uppercase letter, a lowercase letter, a digit, and a special character", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate confirm password
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insert data into database and show a toast message
        boolean isInserted = databaseHelper.insertUser(username, email, password);

        if (isInserted) {
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }

}
