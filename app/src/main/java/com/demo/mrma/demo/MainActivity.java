package com.demo.mrma.demo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton flashBtn,shotBtn,frontCameraBtn,albumBtn;
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private File tempFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        初始化
        init();
//        所有点击事件
        allClick();
//        打开相机界面
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
                gallery();

            }
        });

//        切换闪光灯按钮
        flashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

//    打开相册
    public void gallery() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

//    返回选中的图片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Photo_handler.class);
                intent.setData(uri);
                startActivity(intent);
            }
            try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



}
