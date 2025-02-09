package com.example.buspass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Card extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card); // This is your current activity_card.xml

        // Get the buttons from the layout
        Button buttonCCT = findViewById(R.id.button);
        Button buttonChaloCard = findViewById(R.id.button2);
        Button buttonJanaSnehi = findViewById(R.id.button3);
        Button buttonView = findViewById(R.id.buttonView);  // Add the "View" button

        // Set click listeners for each button
        buttonCCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Card.this, cct.class);
                startActivity(intent);
            }
        });

        buttonChaloCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Card.this, chalocard.class);
                startActivity(intent);
            }
        });

        buttonJanaSnehi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Card.this, janasnehi.class);
                startActivity(intent);
            }
        });

        // Set click listener for the "View" button
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Card.this, ClassListActivity.class);
                startActivity(intent);
            }
        });
    }
}
