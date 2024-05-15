package com.example.treytontheoassign;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
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
    Drawable[] drawables;
    TextView tvComboName;
    List<ImageView> ivComboImage = new ArrayList<>();

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

        ivComboImage.add(findViewById(R.id.ivTestCombo0));
        ivComboImage.add(findViewById(R.id.ivTestCombo1));
        ivComboImage.add(findViewById(R.id.ivTestCombo2));
        ivComboImage.add(findViewById(R.id.ivTestCombo3));
        ivComboImage.add(findViewById(R.id.ivTestCombo4));
        ivComboImage.add(findViewById(R.id.ivTestCombo5));
        ivComboImage.add(findViewById(R.id.ivTestCombo6));
        ivComboImage.add(findViewById(R.id.ivTestCombo7));


        Bundle receivedComboBundle = getIntent().getExtras();

        currentCombo = (Combo) receivedComboBundle.getSerializable("combo");
//        Cannot transfer the vector drawable
//        drawables = (Drawable[]) receivedComboBundle.getSerializable("drawables");


        tvComboName = findViewById(R.id.tvComboName);
        tvComboName.setText(currentCombo.getComboName());

        updateCombo(currentCombo, drawables);


        Log.d("TestActivity", "onCreate: " + currentCombo.getComboName());

    }

    private void updateCombo(Combo currentCombo, Drawable[] drawables) {
        for (int i = 0; i < 8; i++) {
            ivComboImage.get(i).setImageDrawable(null);
        }
        for (int i = 0; i < currentCombo.getComboItems().size(); i++) {
            Utils.setImageBaseOnValue(ivComboImage.get(i), currentCombo.getComboItems().get(i));
//            ivComboImage.get(i).setImageDrawable(drawables[currentCombo.getComboItems().get(i)]);
        }
    }


}