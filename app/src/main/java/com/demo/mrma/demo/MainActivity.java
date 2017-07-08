package com.demo.mrma.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton recovery,frontCamera;
    private RecyclerView styleList;
    private List<Integer> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.show_photo);
        init();
        initData();
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
        recovery = (FloatingActionButton) findViewById(R.id.recovery_button);
        frontCamera = (FloatingActionButton) findViewById(R.id.front_camera_button);
        styleList = (RecyclerView) findViewById(R.id.list_view);
    }

    public void initData () {
        mDatas = new ArrayList<>(Arrays.asList(R.drawable.style_001,
                R.drawable.style_002,R.drawable.style_003,R.drawable.style_004,
                R.drawable.style_005,R.drawable.style_006));
    }

    public void allClick () {

//      recovery clickEvent
        recovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

//        frontCamera clickEvent
//        frontCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "haha", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}
