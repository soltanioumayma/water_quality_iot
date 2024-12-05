
package com.example.waterquality;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity3 extends AppCompatActivity {

    private TextView question1, question2, question3, question4, question5, question6, question7, question8;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // Initialize Text-to-Speech
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.FRENCH); // Set language to French
            }
        });

        // Initialize questions
        question1 = findViewById(R.id.question1);
        question2 = findViewById(R.id.question2);
        question3 = findViewById(R.id.question3);
        question4 = findViewById(R.id.question4);
        question5 = findViewById(R.id.question5);
        question6 = findViewById(R.id.question6);
        question7 = findViewById(R.id.question7);
        question8 = findViewById(R.id.question8);

        // Add listeners for each question
        question1.setOnClickListener(v -> showAlertDialogAndSpeak("C'est quoi l'eau acide, neutre, basique ?", "L'eau acide a un pH inférieur à 7, l'eau neutre a un pH de 7, et l'eau basique a un pH supérieur à 7."));
        question2.setOnClickListener(v -> showAlertDialogAndSpeak("Qu'est-ce que la conductivité électrique ?", "La conductivité électrique mesure la capacité de l'eau à conduire l'électricité. Plus l'eau est salée, plus la conductivité est élevée."));
        question3.setOnClickListener(v -> showAlertDialogAndSpeak("C'est quoi la salinité ?", "La salinité est la quantité de sels dissous dans l'eau. Elle est importante pour la qualité de l'eau d'irrigation."));
        question4.setOnClickListener(v -> showAlertDialogAndSpeak("Comment garantir une bonne qualité d'eau pour l'irrigation ?", "L'utilisation de filtres, la gestion de l'eau de pluie et éviter les produits chimiques est essentielle pour garantir une bonne qualité d'eau pour l'irrigation."));
        question5.setOnClickListener(v -> showAlertDialogAndSpeak("Quels sont les effets d'une eau trop salée sur les plantes ?", "L'eau trop salée peut endommager les racines des plantes, empêchant l'absorption d'eau et de nutriments nécessaires."));
        question6.setOnClickListener(v -> showAlertDialogAndSpeak("Comment vérifier la qualité de l'eau avant l'irrigation ?", "Il est important de tester régulièrement le pH, la salinité et la conductivité de l'eau à l'aide d'instruments appropriés."));
        question7.setOnClickListener(v -> showAlertDialogAndSpeak("Pourquoi est-il important de tester régulièrement l'eau d'irrigation ?", "Tester régulièrement l'eau permet d'identifier tout changement dans la qualité et de prévenir les problèmes pour les plantes."));
        question8.setOnClickListener(v -> showAlertDialogAndSpeak("Quels sont les erreurs courantes en irrigation à éviter ?", "Éviter l'irrigation excessive, l'utilisation d'eau de mauvaise qualité et l'irrigation aux mauvaises heures de la journée."));

        // Initialize the button
        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(v -> {
            // Navigate to Activity4
            Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
            startActivity(intent);
        });
    }

    // Method to show AlertDialog and speak the content
    private void showAlertDialogAndSpeak(String question, String answer) {
        // Show the dialog
        new AlertDialog.Builder(MainActivity3.this)
                .setTitle(question)
                .setMessage(answer)
                .setPositiveButton("OK", null)
                .show();

        // Speak the content
        speak(question + " " + answer);
    }

    // Method to handle text-to-speech
    private void speak(String text) {
        if (tts != null) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    @Override
    protected void onDestroy() {
        // Shutdown Text-to-Speech to release resources
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}