package com.example.buspass;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log; // Added for logging
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class chalocard extends AppCompatActivity {

    private ImageView profileImage;
    private TextView tvCct, tvName, tvGender, tvContact, tvCategory, tvSource, tvDestination;
    private EditText etName, etContact, etSource, etDestination;
    private RadioGroup rgGender, rgCategory;
    private RadioButton rbMale, rbFemale, rbOther, rbStudent, rbEmployee;
    private Button buttonSubmit;
    private DatabaseHelper databaseHelper; // Database Helper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chalocard);

        // Initialize views
        profileImage = findViewById(R.id.profile_image);
        tvCct = findViewById(R.id.tv_cct);
        tvName = findViewById(R.id.tv_name);
        etName = findViewById(R.id.et_name);
        tvGender = findViewById(R.id.tv_gender);
        rgGender = findViewById(R.id.rg_gender);
        rbMale = findViewById(R.id.rb_male);
        rbFemale = findViewById(R.id.rb_female);
        rbOther = findViewById(R.id.rb_other);
        tvContact = findViewById(R.id.tv_contact);
        etContact = findViewById(R.id.et_contact);
        tvCategory = findViewById(R.id.tv_category);
        rgCategory = findViewById(R.id.rg_category);
        rbStudent = findViewById(R.id.rb_student);
        rbEmployee = findViewById(R.id.rb_employee);
        tvSource = findViewById(R.id.tv_source);
        etSource = findViewById(R.id.et_source);
        tvDestination = findViewById(R.id.tv_destination);
        etDestination = findViewById(R.id.et_destination);
        buttonSubmit = findViewById(R.id.button1);

        // Initialize database helper
        databaseHelper = new DatabaseHelper(this);

        // Set button click listener
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }

    private void submitForm() {
        String name = etName.getText().toString();
        String contact = etContact.getText().toString();
        String source = etSource.getText().toString();
        String destination = etDestination.getText().toString();

        // Validate Name
        if (name.isEmpty() || !name.matches("^[a-zA-Z\\s]{1,50}$")) {
            etName.setError("Valid name (up to 50 characters, no special characters) is required");
            return;
        }

        // Validate Contact
        if (contact.isEmpty() || !contact.matches("\\d{10}")) {
            etContact.setError("Valid 10-digit contact number is required");
            return;
        }

        // Validate Source
        if (source.isEmpty()) {
            etSource.setError("Source is required");
            return;
        }

        // Validate Destination
        if (destination.isEmpty()) {
            etDestination.setError("Destination is required");
            return;
        }

        // Validate Gender
        int selectedGenderId = rgGender.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate Category
        int selectedCategoryId = rgCategory.getCheckedRadioButtonId();
        if (selectedCategoryId == -1) {
            Toast.makeText(this, "Please select a category", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get selected Gender and Category
        RadioButton selectedGenderButton = findViewById(selectedGenderId);
        String gender = (selectedGenderButton != null) ? selectedGenderButton.getText().toString() : "";

        RadioButton selectedCategoryButton = findViewById(selectedCategoryId);
        String category = (selectedCategoryButton != null) ? selectedCategoryButton.getText().toString() : "";

        // Insert data into database
        boolean isInserted = databaseHelper.insertData(name, contact, gender, category, source, destination);
        if (isInserted) {
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
            Log.d("Database", "Data inserted into cards table successfully");
        } else {
            Toast.makeText(this, "Data Insertion Failed", Toast.LENGTH_SHORT).show();
            Log.e("Database", "Failed to insert data into cards table");
        }

        // Navigate to PaymentActivity
        Intent intent = new Intent(chalocard.this, payment.class);
        intent.putExtra("name", name);
        intent.putExtra("contact", contact);
        startActivity(intent);
    }
}
