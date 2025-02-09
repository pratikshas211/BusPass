
package com.example.buspass;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Pattern;

public class payment extends AppCompatActivity {

    private RadioGroup rgPaymentMethod;
    private RadioButton rbCard, rbUpi;
    private EditText etNameOnCard, etCardNumber, etSecurityCode, etExpirationDate, etUpiId;
    private Button btnConfirmPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        rgPaymentMethod = findViewById(R.id.rg_payment_method);
        rbCard = findViewById(R.id.rb_card);
        rbUpi = findViewById(R.id.rb_upi);
        etNameOnCard = findViewById(R.id.et_name_on_card);
        etCardNumber = findViewById(R.id.et_card_number);
        etSecurityCode = findViewById(R.id.et_security_code);
        etExpirationDate = findViewById(R.id.et_expiration_date);
        etUpiId = findViewById(R.id.et_upi_id);
        btnConfirmPayment = findViewById(R.id.btn_confirm_payment);

        // Hide input fields by default
        toggleInputFields(View.GONE, View.GONE);

        // Set listener for payment method change
        rgPaymentMethod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_card) {
                    // Show card fields and hide UPI field
                    toggleInputFields(View.VISIBLE, View.GONE);
                } else if (checkedId == R.id.rb_upi) {
                    // Show UPI field and hide card fields
                    toggleInputFields(View.GONE, View.VISIBLE);
                }
            }
        });

        // Confirm payment button click listener
        btnConfirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmPayment();
            }
        });
    }

    private void toggleInputFields(int cardVisibility, int upiVisibility) {
        etNameOnCard.setVisibility(cardVisibility);
        etCardNumber.setVisibility(cardVisibility);
        etSecurityCode.setVisibility(cardVisibility);
        etExpirationDate.setVisibility(cardVisibility);
        etUpiId.setVisibility(upiVisibility);
    }

    private void confirmPayment() {
        int selectedPaymentMethodId = rgPaymentMethod.getCheckedRadioButtonId();
        if (selectedPaymentMethodId == R.id.rb_card) {
            // Get card details
            String nameOnCard = etNameOnCard.getText().toString().trim();
            String cardNumber = etCardNumber.getText().toString().trim();
            String securityCode = etSecurityCode.getText().toString().trim();
            String expirationDate = etExpirationDate.getText().toString().trim();

            // Card details validation
            if (!validateName(nameOnCard)) {
                Toast.makeText(payment.this, "Please enter a valid name", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!validateCardNumber(cardNumber)) {
                Toast.makeText(payment.this, "Please enter a valid card number", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!validateSecurityCode(securityCode)) {
                Toast.makeText(payment.this, "Please enter a valid security code", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!validateExpirationDate(expirationDate)) {
                Toast.makeText(payment.this, "Please enter a valid expiration date", Toast.LENGTH_SHORT).show();
                return;
            }

        } else if (selectedPaymentMethodId == R.id.rb_upi) {
            // Get UPI details
            String upiId = etUpiId.getText().toString().trim();
            if (!validateUpiId(upiId)) {
                Toast.makeText(payment.this, "Please enter a valid UPI ID", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            Toast.makeText(payment.this, "Please select a payment method", Toast.LENGTH_SHORT).show();
            return;
        }

        // Display payment success message
        Toast.makeText(payment.this, "Payment successful", Toast.LENGTH_LONG).show();

        // Redirect to MainActivity
        startActivity(new Intent(payment.this, MainActivity.class));
        finish();
    }

    // Validate name (only alphabets)
    private boolean validateName(String name) {
        return !TextUtils.isEmpty(name) && name.matches("[a-zA-Z ]+");
    }

    // Validate card number (only digits, no spaces)
    private boolean validateCardNumber(String cardNumber) {
        return !TextUtils.isEmpty(cardNumber) && cardNumber.matches("\\d{16}");
    }

    // Validate security code (3 or 4 digits)
    private boolean validateSecurityCode(String securityCode) {
        return !TextUtils.isEmpty(securityCode) && securityCode.matches("\\d{3,4}");
    }

    // Validate expiration date (MM/YYYY)
    private boolean validateExpirationDate(String expirationDate) {
        if (TextUtils.isEmpty(expirationDate)) return false;

        // Check format
        if (!expirationDate.matches("(0[1-9]|1[0-2])/\\d{4}")) return false;

        String[] parts = expirationDate.split("/");
        int month = Integer.parseInt(parts[0]);
        int year = Integer.parseInt(parts[1]);

        // Ensure month is between 01 and 12
        if (month < 1 || month > 12) return false;

        // Ensure year is not in the past
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1; // Months are 0-based

        if (year < currentYear || (year == currentYear && month < currentMonth)) return false;

        return true;
    }

    // Validate UPI ID
    private boolean validateUpiId(String upiId) {
        // Check UPI ID format (must contain '@', no spaces, can have . or -)
        return !TextUtils.isEmpty(upiId) && upiId.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9]+");
    }
}
