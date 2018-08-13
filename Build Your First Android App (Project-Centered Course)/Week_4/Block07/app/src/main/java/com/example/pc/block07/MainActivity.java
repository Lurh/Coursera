package com.example.pc.block07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {
    NumberPicker possibilities;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        possibilities = findViewById(R.id.numberpicker);
        webView = findViewById(R.id.webView);
        String[] possibilitiesStrings = {
                "Android",
                "Checklist text-input field",
                "Coursera",
                "Supelec"
        };
        possibilities.setDisplayedValues(possibilitiesStrings);
        possibilities.setMinValue(0);
        possibilities.setMaxValue(possibilitiesStrings.length - 1);

    }

    public void navigate(View v){
        int choice = possibilities.getValue();
        webView.setWebChromeClient(new WebChromeClient());
        if(choice == 0)
            webView.loadUrl("http://www.baidu.com");
        else if(choice == 1)
            webView.loadUrl("http://www.yuque.com");
        else if(choice == 2)
            webView.loadUrl("http://www.coursera.org");
        else if(choice == 3)
            webView.loadUrl("http://leetcode.com");
    }
}
