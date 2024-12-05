package com.example.waterquality;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Find the Learn More button
        Button btnLearnMore = findViewById(R.id.btnLearnMore);

        // Set the onClickListener for the Learn More button
        btnLearnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to Activity3
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        // Find the Real-Time Data button
        Button btnRealTimeData = findViewById(R.id.btnRealTimeData);

        // Set the onClickListener for the Real-Time Data button
        btnRealTimeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to Activity7
                Intent intent = new Intent(MainActivity2.this, MainActivity7.class);
                startActivity(intent);
            }
        });
    }

    // Méthode pour afficher les détails du pH
    public void showPhDetails(View view) {
        showDetailsDialog(
                R.drawable.img_74, // Replace with your image resource
                "pH Details",
                "Le pH mesure l'acidité ou l'alcalinité de l'eau. Il est mesuré par un capteur de pH.\n" +
                        "- Le pH de l'eau est important car il influence la biodisponibilité des nutriments et la santé des plantes et des écosystèmes aquatiques. Normes :\n" +
                        "- Eau potable : entre 6.5 et 8.5 (indicateur d'une eau potable et équilibrée)\n" +
                        "- Eau d'irrigation : entre 5.5 et 7.5 (pour favoriser la croissance des plantes sans nuire à leur santé)\n\n" +
                        "Un pH trop bas (acide) ou trop élevé (alcalin) peut affecter l'absorption des nutriments, causer des troubles de croissance chez les plantes, et même rendre l'eau impropre à la consommation.\n\n" +
                        "Conseils :\n" +
                        "- Pour les plantes, le pH optimal est légèrement acide à neutre.\n" +
                        "- Si le pH est trop élevé ou trop bas, il est possible d'ajuster le pH avec des produits spécifiques pour l'eau d'irrigation."
        );
    }

    // Méthode pour afficher les détails de la conductivité
    public void showConductivityDetails(View view) {
        showDetailsDialog(
                R.drawable.img_75, // Replace with your image resource
                "Conductivity Details",
                "La conductivité mesure la capacité de l'eau à conduire l'électricité, ce qui est directement lié à la concentration des ions dissous dans l'eau. Plus il y a d'ions, plus l'eau conduit l'électricité. Cette mesure est utilisée pour évaluer la qualité de l'eau. Elle est mesurée par un capteur de conductivité.\n\n" +
                        "Normes :\n" +
                        "- Eau potable : < 2500 µS/cm (concentration faible en ions, indiquant une eau peu minéralisée et généralement de bonne qualité)\n" +
                        "- Eau d'irrigation : entre 0 et 3000 µS/cm (la conductivité peut varier selon les besoins des plantes, mais des valeurs trop élevées peuvent nuire à leur santé)\n\n" +
                        "Conseils :\n" +
                        "- Si la conductivité est trop faible ou trop élevée, il est conseillé de modifier l'eau pour l'adapter aux besoins des cultures."
        );
    }

    // Méthode pour afficher les détails de la salinité
    public void showSalinityDetails(View view) {
        showDetailsDialog(
                R.drawable.img_76, // Replace with your image resource
                "Salinity Details",
                "La salinité mesure la concentration de sels dissous dans l'eau. Elle est mesurée par un capteur de salinité. La salinité de l'eau peut avoir un impact sur les écosystèmes aquatiques, l'agriculture, et la santé humaine. Normes :\n" +
                        "- Eau potable : < 0.5 g/L (indique une eau douce et propre, généralement utilisée pour la consommation humaine)\n" +
                        "- Eau d'irrigation : entre 0 et 3 g/L (en fonction des besoins des cultures et du type de sol)\n\n" +
                        "Une salinité trop élevée peut rendre l'eau impropre à l'irrigation ou à la consommation, car elle peut déstabiliser l'osmose des cellules végétales et animales. De même, une salinité trop faible peut signifier une carence en minéraux essentiels.\n\n" +
                        "Conseils :\n" +
                        "- Si la salinité dépasse les niveaux recommandés, il peut être nécessaire de diluer l'eau avec de l'eau douce ou de changer la source d'eau."
        );
    }

    // Generic method to show a dialog with details
    private void showDetailsDialog(int imageResId, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);

        // Add an image to the dialog
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(imageResId);
        imageView.setPadding(20, 20, 20, 20);

        builder.setView(imageView);
        builder.setMessage(message);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }
}
