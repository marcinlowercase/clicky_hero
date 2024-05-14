package com.example.treytontheoassign;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toolbar;

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


    final Drawable[] drawables = new Drawable[4];
    List<Combo> comboList = new ArrayList<>();

    RecyclerView rvComboList;
    Button btnButton;
    private ComboAdapter comboAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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


        drawables[0] = getDrawable(R.drawable.up);
        drawables[1] = getDrawable(R.drawable.right);
        drawables[2] = getDrawable(R.drawable.down);
        drawables[3] = getDrawable(R.drawable.left);



        comboAdapter = new ComboAdapter(comboList, drawables);


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