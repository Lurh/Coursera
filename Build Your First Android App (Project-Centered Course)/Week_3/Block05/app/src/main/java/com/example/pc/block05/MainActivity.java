package com.example.pc.block05;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("COLOR_PREF", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        final RelativeLayout layout = findViewById(R.id.layout);
        if(preferences.contains("colorId"))
            layout.setBackgroundColor(preferences.getInt("colorId",0));

        RadioGroup radioGroup_colors = findViewById(R.id.radioGroup_colors);
        radioGroup_colors.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int colorCode = 0;
                if(checkedId == R.id.radioButton_blue)
                    colorCode = Color.BLUE;
                else if(checkedId == R.id.radioButton_magenta)
                    colorCode = Color.MAGENTA;
                else if(checkedId == R.id.radioButton_yellow)
                    colorCode = Color.YELLOW;
                layout.setBackgroundColor(colorCode);
                editor.putInt("colorId",colorCode);
                editor.commit();
            }
        });
    }
}
