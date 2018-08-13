package com.example.pc.convertmilestokm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonConvertMilesToKm = findViewById(R.id.buttonMilesToKm);
        buttonConvertMilesToKm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textMiles = findViewById(R.id.editTextMiles);
                EditText textKm = findViewById(R.id.editTextKm);
                double vMiles = Double.valueOf(textMiles.getText().toString());
                double vKm = vMiles / 0.62137;
                DecimalFormat formatVal = new DecimalFormat("##.##");
                textKm.setText(formatVal.format(vKm));
            }
        });
        Button buttonConvertKmToMiles = findViewById(R.id.buttonKmToMiles);
        buttonConvertKmToMiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textMiles = findViewById(R.id.editTextMiles);
                EditText textKm = findViewById(R.id.editTextKm);
                double vKm = Double.valueOf(textKm.getText().toString());
                double vMiles = vKm * 0.62137;
                DecimalFormat formatVal = new DecimalFormat("##.##");
                textMiles.setText(formatVal.format(vMiles));
            }
        });
    }
}
