package com.demo.mrma.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by 309 on 2017/7/27.
 */

public class Feed_Back extends Activity {

    private Button submitBtn, backBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_back);
    }

    public void init() {
        submitBtn = findViewById(R.id.submitBtn);
        backBtn = findViewById(R.id.back_button);
    }

    public void allClick() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Feed_Back.this, Photo_handler.class);
                startActivity(intent);
            }
        });
    }

}
