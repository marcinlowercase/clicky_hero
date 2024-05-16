package com.example.treytontheoassign;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    int attempt;
    RecyclerView rvComboList;
    Button btnRestart;
    private ComboAdapter comboAdapter;
    private DBHelper dbHelper;
    private List<Combo> comboList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        attempt = 0;
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        rvComboList = findViewById(R.id.rvComboList);
        rvComboList.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        dbHelper = new DBHelper(MainActivity.this);

        Bundle bundleFromCongrats = getIntent().getExtras();
        if (bundleFromCongrats != null) {
            if (bundleFromCongrats.containsKey("backFromCongrats")) {
                dbHelper.updateComboItems();
            }
        }

        comboList = dbHelper.getAllCombo();

        for (Combo c : comboList) {
            Log.d("MainActivity", "combo: " + c.toString());
        }

        if (checkAllAttempted(comboList)) {
            Intent intent = new Intent(MainActivity.this, CongratsActivity.class);
            String strResult = "Correct: " +
                    currentSuccess(comboList) +
                    ", Incorrect: " +
                    currentFailed(comboList);

            intent.putExtra("result", strResult);
            startActivity(intent);
            finish();
        }


        comboAdapter = new ComboAdapter(this, comboList);
        Log.d("MainActivity", "comboList size: " + comboList.size());

        rvComboList.setAdapter(comboAdapter);

        btnRestart = findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(ev -> {

            dbHelper.updateComboItems();
            comboList = dbHelper.getAllCombo();

            Log.d("MainActivity", "ComboItems reset in db ");
            for (Combo c : comboList) {
                Log.d("MainActivity", "combo: " + c.toString());
            }

            comboAdapter = new ComboAdapter(this, comboList);
            rvComboList.setAdapter(comboAdapter);
        });


    }

    private int currentFailed(List<Combo> comboList) {
        int incorrect = 0;
        for (Combo c : comboList) {
            if (c.isAttempted()) {
                if (!c.isCorrect()) {
                    incorrect++;
                }
            }
        }
        return incorrect;
    }

    private int currentSuccess(List<Combo> comboList) {
        int correct = 0;
        for (Combo c : comboList) {
            if (c.isAttempted()) {
                if (c.isCorrect()) {
                    correct++;
                }
            }
        }
        return correct;
    }

    private boolean checkAllAttempted(List<Combo> comboList) {
        for (Combo c : comboList) {
            if (!c.isAttempted()) {
                return false;
            }
        }
        return true;
    }
}