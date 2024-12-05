package com.example.waterquality;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    private TextView questionText, scoreText;
    private RadioGroup answerGroup;
    private Button submitAnswer, getGiftButton, discoverSensorsButton;
    private int currentQuestionIndex = 0;
    private int score = 0;

    // Questions et réponses mises à jour
    private final String[] questions = {
            "C'est quoi une eau acide ?", // Nouvelle question 1
            "Que mesure un capteur de conductivité ?",
            "Que signifie une eau avec une turbidité élevée ?",
            "Comment mesurer la salinité de l'eau ?",
            "Comment assurer une bonne qualité de l'eau ?" // Nouvelle question 5
    };

    private final String[][] options = {
            {"Une eau avec un pH inférieur à 7", "Une eau avec un pH supérieur à 7", "Une eau potable", "Une eau distillée"}, // Options pour la nouvelle question 1
            {"La température", "La capacité à conduire l'électricité", "Le pH", "L'oxygène dissous"},
            {"Elle est propre", "Elle contient des particules en suspension", "Elle est chaude", "Elle est acide"},
            {"Avec un capteur de température", "Avec un capteur de salinité", "Avec un capteur de pH", "Avec un capteur de turbidité"},
            {"Utiliser des filtres, éviter la pollution, et surveiller la qualité", "Utiliser uniquement de l'eau en bouteille", "Faire bouillir l'eau", "Ajouter des produits chimiques"} // Options pour la nouvelle question 5
    };

    private final int[] correctAnswers = {0, 1, 1, 1, 0}; // Index des bonnes réponses mises à jour

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        // Initialisation des vues
        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.scoreText);
        answerGroup = findViewById(R.id.answerGroup);
        submitAnswer = findViewById(R.id.submitAnswer);
        getGiftButton = findViewById(R.id.getGiftButton);
        discoverSensorsButton = findViewById(R.id.discoverSensorsButton);

        // Charger la première question
        loadQuestion();

        // Action sur le bouton "Submit"
        submitAnswer.setOnClickListener(v -> {
            int selectedId = answerGroup.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton selectedOption = findViewById(selectedId);
                int answerIndex = answerGroup.indexOfChild(selectedOption);

                // Vérifier si la réponse est correcte
                if (answerIndex == correctAnswers[currentQuestionIndex]) {
                    score++;
                    Toast.makeText(this, "Bonne réponse !", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Mauvaise réponse.", Toast.LENGTH_SHORT).show();
                }

                // Mettre à jour l'affichage du score
                scoreText.setText("Score: " + score);

                // Passer à la question suivante
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.length) {
                    loadQuestion();
                } else {
                    showFinalScore();
                }
            }
        });

        // Action sur le bouton "Get Your Gift"
        getGiftButton.setOnClickListener(v -> {
            // Naviguer vers l'écran de cadeau ou afficher un message de félicitations
            Intent intent = new Intent(MainActivity4.this, MainActivity5.class);
            startActivity(intent);
        });

        // Action sur le bouton "Discover More Sensors"
        discoverSensorsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity4.this, MainActivity6.class);
            startActivity(intent);
        });
    }

    private void loadQuestion() {
        // Réinitialiser les options et afficher la question actuelle
        answerGroup.clearCheck();
        questionText.setText(questions[currentQuestionIndex]);
        ((RadioButton) answerGroup.getChildAt(0)).setText(options[currentQuestionIndex][0]);
        ((RadioButton) answerGroup.getChildAt(1)).setText(options[currentQuestionIndex][1]);
        ((RadioButton) answerGroup.getChildAt(2)).setText(options[currentQuestionIndex][2]);
        ((RadioButton) answerGroup.getChildAt(3)).setText(options[currentQuestionIndex][3]);
    }

    private void showFinalScore() {
        // Afficher le score final et afficher le bouton "Get Your Gift" si toutes les réponses sont correctes
        scoreText.setText("Score final: " + score);
        if (score == questions.length) {
            getGiftButton.setVisibility(View.VISIBLE);
        }
        submitAnswer.setVisibility(View.GONE);
    }
}
