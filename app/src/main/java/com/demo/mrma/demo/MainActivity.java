package com.demo.mrma.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton flashBtn,shotBtn,frontCameraBtn,albumBtn;
    private int clickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        初始化
        init();
//        所有点击事件
        allClick();
    }

//    实例化变量

    public void init () {
        flashBtn = (FloatingActionButton) findViewById(R.id.flash_button);
        shotBtn = (FloatingActionButton) findViewById(R.id.camera_button);
        albumBtn = (FloatingActionButton) findViewById(R.id.album_button);
        frontCameraBtn = (FloatingActionButton) findViewById(R.id.front_camera_button);
    }

//    所有点击事件

    public void allClick () {

//        拍照按钮
        shotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Photo_handler.class);
                startActivity(intent);
            }
        });

//        切换前置摄像头
        frontCameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "front", Toast.LENGTH_SHORT).show();
            }
        });

//        相册按钮
        albumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "album", Toast.LENGTH_SHORT).show();
            }
        });

//        切换闪光灯按钮
        flashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++clickCount;
                if (clickCount == 3){
                    clickCount = 0;
                }
                switch (clickCount){
                    case 0:
                        flashBtn.setImageResource(R.drawable.ic_flash_auto);
                        break;
                    case 1:
                        flashBtn.setImageResource(R.drawable.ic_flash);
                        break;
                    case 2:
                        flashBtn.setImageResource(R.drawable.ic_flash_off);
                        break;
                }
            }
        });

    }

}
