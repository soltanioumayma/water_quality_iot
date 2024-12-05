package com.example.waterquality;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MainActivity6 extends AppCompatActivity {

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        // Initialize Text-to-Speech
        textToSpeech = new TextToSpeech(this, new OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.FRENCH); // Set the language for TTS
                }
            }
        });

        // Sensor Images and Explanations
        ImageView phSensor = findViewById(R.id.phSensor);
        ImageView turbiditySensor = findViewById(R.id.turbiditySensor);
        ImageView salinitySensor = findViewById(R.id.salinitySensor);
        ImageView conductivitySensor = findViewById(R.id.sensor4);
        ImageView oxygenSensor = findViewById(R.id.sensor5);
        ImageView temperatureSensor = findViewById(R.id.sensor6);

        // Set up click listeners for each sensor
        phSensor.setOnClickListener(v -> showDialogAndSpeak("Le capteur de pH mesure le niveau d'acidité ou d'alcalinité de l'eau."));
        turbiditySensor.setOnClickListener(v -> showDialogAndSpeak("Le capteur de turbidité mesure la clarté de l'eau, indiquant la présence de particules en suspension."));
        salinitySensor.setOnClickListener(v -> showDialogAndSpeak("Le capteur de salinité mesure la concentration en sel dans l'eau."));
        conductivitySensor.setOnClickListener(v -> showDialogAndSpeak("Le capteur de conductivité mesure la capacité de l'eau à conduire l'électricité, liée à la concentration en ions."));
        oxygenSensor.setOnClickListener(v -> showDialogAndSpeak("Le capteur d'oxygène dissous mesure la quantité d'oxygène présente dans l'eau, essentielle pour les organismes aquatiques."));
        temperatureSensor.setOnClickListener(v -> showDialogAndSpeak("Le capteur de température de l'eau mesure la chaleur ou la fraîcheur de l'eau, influençant les conditions biologiques."));
    }

    // Method to show dialog and speak text
    private void showDialogAndSpeak(String text) {
        // Display a dialog with the text
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(text)
                .setCancelable(true)
                .setTitle("Information")
                .setPositiveButton("OK", (dialog, id) -> dialog.dismiss())
                .show();

        // Speak the text using TTS
        if (textToSpeech != null) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
