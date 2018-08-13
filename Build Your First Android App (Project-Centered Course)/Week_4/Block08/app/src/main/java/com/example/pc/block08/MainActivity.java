package com.example.pc.block08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void  enter(View v) {
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        float rating = ratingBar.getRating();
        //Toast.makeText(getApplicationContext(),rating + " stars",Toast.LENGTH_SHORT).show();
        Intent goToSecond = new Intent();
        goToSecond.setClass(this, SecondActivity.class);
        goToSecond.putExtra("nbStars",rating);
        startActivity(goToSecond);
        finish();
    }
}
