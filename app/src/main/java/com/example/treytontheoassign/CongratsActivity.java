package com.example.treytontheoassign;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CongratsActivity extends AppCompatActivity {


    TextView tvResult;
    Button btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);

        tvResult = findViewById(R.id.tvResult);
        btnRestart = findViewById(R.id.btnRestartCongrats);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String result = extras.getString("result");
            tvResult.setText(result);
        }

        btnRestart.setOnClickListener(v -> {
            Intent intent = new Intent(CongratsActivity.this, MainActivity.class);
            boolean backFromCongrats = true;
            intent.putExtra("backFromCongrats", backFromCongrats);
            startActivity(intent);
            this.finish();
        });

    }
}
