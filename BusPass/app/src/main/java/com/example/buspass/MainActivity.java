
package com.example.buspass;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Apply window insets listener to handle system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Start the marquee animation for the background image
        ImageView marqueeBackground = findViewById(R.id.marquee_background);
        startMarqueeAnimation(marqueeBackground);

        // Set up the Get Started button
        Button gsButton = findViewById(R.id.btn_gs);
        gsButton.setOnClickListener(v -> btn_gs());
    }

    // Start marquee animation
    private void startMarqueeAnimation(ImageView marqueeBackground) {
        // Assuming the image width is larger than the screen width
        ObjectAnimator animator = ObjectAnimator.ofFloat(marqueeBackground, "translationX", -1300f, 1300f);
        animator.setDuration(10000); // 10 seconds
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.RESTART);
        animator.start();
    }

    // Button click action to navigate to Card activity
    public void btn_gs() {
        Intent intent = new Intent(MainActivity.this, Card.class);
        startActivity(intent);
    }
}
