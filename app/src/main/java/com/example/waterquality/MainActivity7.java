package com.example.waterquality;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

// Inside your MainActivity7.java

public class MainActivity7 extends AppCompatActivity {
    private Button waterButton, evaluationButton, tipsAndTricksButton;
    private DatabaseReference databaseReference;

    // TextViews for displaying real-time data
    private TextView ph1, sal_data, cond_data;
    private TextView osmoticData, hardnessData, resistivityData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        // Initialize buttons
        waterButton = findViewById(R.id.water_button);
        evaluationButton = findViewById(R.id.tips);
        tipsAndTricksButton = findViewById(R.id.new_button); // Tips and Tricks button

        // Initialize TextViews for pH, Salinity, and Conductivity
        ph1 = findViewById(R.id.ph1);
        sal_data = findViewById(R.id.sal_data);
        cond_data = findViewById(R.id.cond_data);

        // Initialize additional TextViews for Osmotic Pressure, Hardness, and Resistivity
        osmoticData = findViewById(R.id.osmotic_data);
        hardnessData = findViewById(R.id.hardness_data);
        resistivityData = findViewById(R.id.resistivity_data);

        // Initialize Firebase reference
        databaseReference = FirebaseDatabase.getInstance().getReference("water_Quality");

        // Water Button Click Listener
        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity7.this, "Great! Thanks for controlling your water before irrigation.", Toast.LENGTH_SHORT).show();
            }
        });

        // Evaluation Button Click Listener
        evaluationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluatePlantData();
            }
        });

        // Tips and Tricks Button Click Listener
        tipsAndTricksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Activity8 when the "Tips and Tricks" button is clicked
                Intent intent = new Intent(MainActivity7.this, MainActivity8.class);
                startActivity(intent);
            }
        });
    }

    private void evaluatePlantData() {
        // Fetch real-time data from Firebase
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Retrieve the values, default to 0 if null
                Double conductivityValue = dataSnapshot.child("Conductivity").getValue(Double.class);
                Double phValue = dataSnapshot.child("PH").getValue(Double.class);
                Double salinityValue = dataSnapshot.child("salinity").getValue(Double.class);

                // Check if any value is missing
                if (phValue == null || conductivityValue == null || salinityValue == null) {
                    Toast.makeText(MainActivity7.this, "Missing data from Firebase.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Update TextViews with real-time data and set colors based on conditions
                // Update pH TextView
                ph1.setText(String.valueOf(phValue));
                if (phValue < 6.8) {
                    ph1.setTextColor(Color.RED); // Acidic
                } else if (phValue > 7.5) {
                    ph1.setTextColor(Color.RED); // Basic
                } else {
                    ph1.setTextColor(Color.GREEN); // Neutral
                }

                // Update Salinity TextView
                sal_data.setText(String.valueOf(salinityValue) + " mg/L");
                if (salinityValue >= 500 && salinityValue <= 1500) {
                    sal_data.setTextColor(Color.GREEN); // Good
                } else {
                    sal_data.setTextColor(Color.RED); // Not Good
                }

                // Update Conductivity TextView
                cond_data.setText(String.valueOf(conductivityValue) + " µS/cm");
                if (conductivityValue >= 700 && conductivityValue <= 3000) {
                    cond_data.setTextColor(Color.GREEN); // Good
                } else {
                    cond_data.setTextColor(Color.RED); // Not Good
                }

                // Calculate Osmotic Pressure, Hardness, and Resistivity
                double osmoticPressure = 0.036 * conductivityValue; // Simplified relation
                double hardness = 2.5 * (conductivityValue / 1000); // Approximation based on mS/cm
                double resistivity = conductivityValue == 0 ? 0 : 1.0 / conductivityValue; // Resistivity in MΩ·cm
                osmoticData.setText(String.format("%.2f atm", osmoticPressure));
                hardnessData.setText(String.format("%.2f mg/L", hardness));
                resistivityData.setText(String.format("%.4f Ω-cm", resistivity));

                // Static guidelines for plant irrigation
                String tips = "### Plant Irrigation Guidelines ###\n"
                        + "- **pH Range**: 5.5 to 7.5 (optimal)\n"
                        + "- **Conductivity**: 700 to 3000 µS/cm\n"
                        + "- **Salinity**: 500 to 1500 mg/L\n\n";

                // Real-time evaluation logic
                String evaluationMessage = "### Real-Time Water Quality ###\n"
                        + "- **PH**: " + phValue + "\n"
                        + "- **Conductivity**: " + conductivityValue + " µS/cm\n"
                        + "- **Salinity**: " + salinityValue + " mg/L\n"
                        + "- **Osmotic Pressure**: " + String.format("%.2f", osmoticPressure) + " atm\n"
                        + "- **Hardness**: " + String.format("%.2f", hardness) + " mg/L CaCO3\n"
                        + "- **Resistivity**: " + String.format("%.4f", resistivity) + " MΩ·cm\n\n";

                // Evaluate water quality
                if (phValue >= 5.5 && phValue <= 7.5 && conductivityValue >= 700 && conductivityValue <= 3000 && salinityValue >= 500 && salinityValue <= 1500) {
                    evaluationMessage += "✅ The water quality is good for irrigation.";
                } else {
                    evaluationMessage += "⚠️ The water quality is not suitable for irrigation. Adjust the parameters.";
                }

                // Combine guidelines and evaluation results
                String finalMessage = tips + evaluationMessage;

                // Show evaluation result in a pop-up
                new AlertDialog.Builder(MainActivity7.this)
                        .setTitle("Water Quality Evaluation")
                        .setMessage(finalMessage)
                        .setPositiveButton("OK", null)
                        .show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity7.this, "Failed to fetch data from Firebase.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

