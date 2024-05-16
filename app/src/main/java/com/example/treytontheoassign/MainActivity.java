package com.example.treytontheoassign;

import android.graphics.drawable.Drawable;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    int attempt;
//    List<Combo> comboList = new ArrayList<>();

    RecyclerView rvComboList;
    Button btnButton;
    private ComboAdapter comboAdapter;
    private DBHelper dbHelper;

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

        dbHelper.addCombo(new Combo(1, "Dangerous Red"));
        dbHelper.addCombo(new Combo(2,"Calm Blue"));
        dbHelper.addCombo(new Combo(3,"Harmonious Green"));
        dbHelper.addCombo(new Combo(4, "Mysterious Black"));
        dbHelper.addCombo(new Combo(5, "Pure White"));


//        comboList.add(new Combo(1, "Dangerous Red"));
//        comboList.add(new Combo(2,"Calm Blue"));
//        comboList.add(new Combo(3,"Harmonious Green"));
//        comboList.add(new Combo(4, "Mysterious Black"));
//        comboList.add(new Combo(5, "Pure White "));



        List<Combo> comboList = dbHelper.getAllCombo();


//        dbHelper.removeCombo(comboList.get(0));

        comboAdapter = new ComboAdapter(comboList);
        Log.d("MainActivity", "comboList size: " + comboList.size());


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