package com.example.pc.block06;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView imageView_small = findViewById(R.id.imageView_small);
        imageView_small.setImageResource(R.drawable.rat);
        final ImageView imageView_large = findViewById(R.id.imageView_large);
        imageView_large.setImageResource(R.drawable.rat);

        SeekBar tuner = findViewById(R.id.seekBar_tuner);
        tuner.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int lastProgress;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lastProgress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                imageView_large.setColorFilter(Color.argb(255,0,lastProgress,255-lastProgress));
            }
        });
    }
}
