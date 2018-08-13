package com.example.pc.block04;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    MediaPlayer pianoMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pianoMusic = MediaPlayer.create(this,R.raw.summer);
        Switch switch_looping = findViewById(R.id.switch_looping);
        switch_looping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pianoMusic.setLooping(isChecked);
            }
        });
    }
    public void playMusic (View v) {
        pianoMusic.start();
    }
    public  void pauseMusic (View v) {
        if(pianoMusic.isPlaying())
            pianoMusic.pause();
    }
}
