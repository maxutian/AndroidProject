package com.demo.mrma.demo;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

public class Photo_handler extends Activity {

    private FloatingActionButton backBtn,recoveryBtn,we_chatBtn,downloadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_handler);
        init();
        allClick();
    }

    public void init () {
        backBtn = findViewById(R.id.back_button);
        recoveryBtn = findViewById(R.id.recovery_button);
        we_chatBtn = findViewById(R.id.we_chat_button);
        downloadBtn = findViewById(R.id.download_button);
    }

    public void allClick() {

//        返回按钮
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Photo_handler.this, MainActivity.class);
                startActivity(intent);
            }
        });

//        恢复图片按钮
        recoveryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Photo_handler.this, "recovery", Toast.LENGTH_SHORT).show();
            }
        });

//        分享到微信按钮
        we_chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Photo_handler.this, "share", Toast.LENGTH_SHORT).show();
            }
        });

//        下载按钮
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Photo_handler.this, "download", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
