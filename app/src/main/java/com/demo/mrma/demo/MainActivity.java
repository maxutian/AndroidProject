package com.demo.mrma.demo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton shotBtn,frontCameraBtn,albumBtn;
    private RecyclerView styleList;
    private List<Integer> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        初始化
        init();
//        初始化数据
        initData();
//        所有点击事件
        allClick();
//        设置布局管理器
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        styleList.setLayoutManager(linearLayoutManager);
////        设置适配器
//        MyAdapter styleAdapter = new MyAdapter(this, mDatas);
//        styleList.setAdapter(styleAdapter);
    }

    public void init () {
        shotBtn = (FloatingActionButton) findViewById(R.id.camera_button);
        albumBtn = (FloatingActionButton) findViewById(R.id.album_button);
        frontCameraBtn = (FloatingActionButton) findViewById(R.id.front_camera_button);
        styleList = (RecyclerView) findViewById(R.id.list_view);
    }

    public void initData () {
        mDatas = new ArrayList<>(Arrays.asList(R.drawable.style_001,
                R.drawable.style_002,R.drawable.style_003,R.drawable.style_004,
                R.drawable.style_005,R.drawable.style_006));
    }

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

    }
}
