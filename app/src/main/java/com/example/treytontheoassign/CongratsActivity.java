package com.example.treytontheoassign;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CongratsActivity extends AppCompatActivity {

    private Drawable[] drawables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);

        // Initialize drawable
        drawables = new Drawable[1];
        // Retrieving data
        //int correctCombos = getIntent().getIntExtra("correctCombos", 0);
        String message = getIntent().getStringExtra("Congratulations you have finished the game");

        // Initializing views
        drawables[0] = getDrawable(R.drawable.check_mark);
        TextView congratsMessage = findViewById(R.id.congrats_message);
        //TextView comboCount = findViewById(R.id.combo_count);
        Button closeButton = findViewById(R.id.close_button);

        // Setting congratulatory message and correct combo count
        congratsMessage.setText(message);
        //comboCount.setText(getString(R.string.correct_combos, correctCombos));

        // Close button click listener
        closeButton.setOnClickListener(ev -> {
            Intent intent = new Intent(CongratsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
//        closeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }
}
