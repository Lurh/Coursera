package com.example.pc.block1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonBlue, buttonPink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonBlue = findViewById(R.id.button_blueinvisible);
        buttonBlue.setOnClickListener(this);
        buttonPink = findViewById(R.id.button_pinkpanther);
        buttonPink.setOnClickListener(this);
        buttonPink.setBackgroundColor();
    }
    public void toDo(View v) {
        if (v.equals(buttonBlue))
            v.setVisibility(View.INVISIBLE);
        if (v.equals(buttonPink))
            Toast.makeText(getApplicationContext(),"to do, to do, to do...",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        toDo(v);
    }
}
