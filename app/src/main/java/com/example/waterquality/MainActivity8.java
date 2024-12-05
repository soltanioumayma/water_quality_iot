package com.example.waterquality;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;

public class MainActivity8 extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        listView = findViewById(R.id.listView);

        // Custom list item layout
        String[] waterTypes = {
                "Eau de Mer (Eau Salée)\nAnalyse : La salinité élevée nuit aux plantes.\nRecommandation : À éviter sauf pour les plantes halophytes.",
                "Eau de Pluie\nAnalyse : Pure et légèrement acide.\nRecommandation : Idéale pour la plupart des plantes.",
                "Eau de Robinet\nAnalyse : Contient chlore, fluor et minéraux variables.\nRecommandation : Convient à la plupart des plantes.",
                "Eau de Source\nAnalyse : Naturelle, équilibrée en minéraux, sans chlore.\nRecommandation : Convient à la plupart des plantes."
        };

        // Use a custom ArrayAdapter for more control
        CustomAdapter adapter = new CustomAdapter(waterTypes);
        listView.setAdapter(adapter);
    }

    // Custom Adapter to populate ListView
    private class CustomAdapter extends ArrayAdapter<String> {
        CustomAdapter(String[] waterTypes) {
            super(MainActivity8.this, R.layout.list_item, waterTypes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            }

            String waterType = getItem(position);
            TextView textView = convertView.findViewById(R.id.waterTypeText);
            ImageView imageView = convertView.findViewById(R.id.icon);

            textView.setText(waterType);

            // Use an icon for each water type (just an example, replace with actual icons)
            if (waterType.contains("Eau de Mer")) {
                imageView.setImageResource(R.drawable.img_82);
            } else if (waterType.contains("Eau de Pluie")) {
                imageView.setImageResource(R.drawable.img_83);
            } else if (waterType.contains("Eau de Robinet")) {
                imageView.setImageResource(R.drawable.img_84);
            } else if (waterType.contains("Eau de Source")) {
                imageView.setImageResource(R.drawable.img_85);
            }

            // Add a fade-in animation for each item
            Animation fadeIn = AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in);
            convertView.startAnimation(fadeIn);

            return convertView;
        }
    }
}
