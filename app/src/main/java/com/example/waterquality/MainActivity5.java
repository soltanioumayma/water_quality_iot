package com.example.waterquality;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.waterquality.R;

public class MainActivity5 extends AppCompatActivity {

    private boolean isGiftRevealed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        // Initialize views
        ImageView mysteryBox = findViewById(R.id.mysteryBox);
        TextView giftMessage = findViewById(R.id.giftMessage);
        Button knowMoreButton = findViewById(R.id.knowMoreButton);

        // Handle mystery box click
        mysteryBox.setOnClickListener(v -> {
            if (!isGiftRevealed) {
                mysteryBox.setImageResource(R.drawable.img_59); // Replace with your sensor image
                giftMessage.setText("Votre cadeau est un capteur de turbidité!");
                isGiftRevealed = true;
            }
        });

        // Handle "Know More About Sensors" button click
        knowMoreButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity5.this, MainActivity6.class);
            startActivity(intent);
        });
    }
}
