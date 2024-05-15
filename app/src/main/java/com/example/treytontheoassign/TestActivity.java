package com.example.treytontheoassign;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TestActivity extends AppCompatActivity {

    TextView tvComboName;
    ImageView[] ivComboImage = new ImageView[8];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivComboImage[0] = findViewById(R.id.ivTestCombo0);
        ivComboImage[1] = findViewById(R.id.ivTestCombo1);
        ivComboImage[2] = findViewById(R.id.ivTestCombo2);
        ivComboImage[3] = findViewById(R.id.ivTestCombo3);
        ivComboImage[4] = findViewById(R.id.ivTestCombo4);
        ivComboImage[5] = findViewById(R.id.ivTestCombo5);
        ivComboImage[6] = findViewById(R.id.ivTestCombo6);
        ivComboImage[7] = findViewById(R.id.ivTestCombo7);

        Bundle receivedComboBundle = getIntent().getExtras();

        Combo currentCombo = (Combo) receivedComboBundle.getSerializable("combo");

        tvComboName = findViewById(R.id.tvComboName);
        tvComboName.setText(currentCombo.getComboName());

        updateCombo(currentCombo);


        Log.d("TestActivity", "onCreate: " + currentCombo.getComboName());

    }

    private void updateCombo(Combo currentCombo) {
        for (int i = 0; i < 8; i++) {
            ivComboImage[i].setImageDrawable(null);
        }
    }
}