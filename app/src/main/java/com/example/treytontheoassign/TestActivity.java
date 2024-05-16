package com.example.treytontheoassign;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    Combo currentCombo;
    TextView tvComboName;
    List<ImageView> ivComboImage = new ArrayList<>();
//    List<Integer> buttonResults = new ArrayList<>();

    DBHelper dbHelper = new DBHelper(this);
    int position;

    ImageButton btnUp;
    ImageButton btnDown;
    ImageButton btnLeft;
    ImageButton btnRight;

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
        position = 0;

        ivComboImage.add(findViewById(R.id.ivTestCombo0));
        ivComboImage.add(findViewById(R.id.ivTestCombo1));
        ivComboImage.add(findViewById(R.id.ivTestCombo2));
        ivComboImage.add(findViewById(R.id.ivTestCombo3));
        ivComboImage.add(findViewById(R.id.ivTestCombo4));
        ivComboImage.add(findViewById(R.id.ivTestCombo5));
        ivComboImage.add(findViewById(R.id.ivTestCombo6));
        ivComboImage.add(findViewById(R.id.ivTestCombo7));


        Bundle receivedComboBundle = getIntent().getExtras();

        assert receivedComboBundle != null;
        currentCombo = (Combo) receivedComboBundle.getSerializable("combo");


        tvComboName = findViewById(R.id.tvComboName);
        tvComboName.setText(currentCombo.getComboName());

        updateComboImage(currentCombo);


        // Button listener

        btnUp = findViewById(R.id.btnUp);
        btnDown = findViewById(R.id.btnDown);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);

        btnUp.setOnClickListener(v -> updateResultImage(currentCombo, compareResult(0, currentCombo)));

        btnRight.setOnClickListener(v -> updateResultImage(currentCombo, compareResult(1, currentCombo)));

        btnDown.setOnClickListener(v -> updateResultImage(currentCombo, compareResult(2, currentCombo)));

        btnLeft.setOnClickListener(v -> updateResultImage(currentCombo, compareResult(3, currentCombo)));

    }

    private void updateResultImage(Combo currentCombo, boolean currentResult) {
        Utils.setImageBaseOnResult(ivComboImage.get(position), currentResult);
        position++;
        if (position == currentCombo.getComboItems().size()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    private boolean compareResult(int value, Combo currentCombo) {

        boolean currentResult;

        currentCombo.setAttempted(true);
        dbHelper.updateAttemptStatus(currentCombo);

        if (value == currentCombo.getComboItems().get(position)) {
            currentResult = true;
        } else {
            currentResult = false;
            currentCombo.setCorrect(false);
            dbHelper.updateCorrectStatus(currentCombo);
        }

        return currentResult;
    }

    private void updateComboImage(Combo currentCombo) {
        for (int i = 0; i < 8; i++) {
            ivComboImage.get(i).setImageDrawable(null);
        }
        for (int i = 0; i < currentCombo.getComboItems().size(); i++) {
            Utils.setImageBaseOnValue(ivComboImage.get(i), currentCombo.getComboItems().get(i));
        }
    }


}