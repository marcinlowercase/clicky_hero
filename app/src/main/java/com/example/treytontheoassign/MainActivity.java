package com.example.treytontheoassign;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    int attempt;
    List<Combo> comboList = new ArrayList<>();

    RecyclerView rvComboList;
    Button btnButton;
    private ComboAdapter comboAdapter;

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




        comboList.add(new Combo("Dangerous Red"));
        comboList.add(new Combo("Calm Blue"));
        comboList.add(new Combo("Harmonious Green"));
        comboList.add(new Combo("Mysterious Black"));
        comboList.add(new Combo("Pure White "));




        comboAdapter = new ComboAdapter(comboList);


        rvComboList.setAdapter(comboAdapter);

        btnButton = findViewById(R.id.btnRestart);
        btnButton.setOnClickListener(ev -> {
            for (Combo c : comboList){
                c.restartCombo();
            }
            comboAdapter.notifyDataSetChanged();
        });



    }
}