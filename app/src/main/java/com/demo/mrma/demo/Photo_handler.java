package com.demo.mrma.demo;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Photo_handler extends Activity {

    private FloatingActionButton backBtn,recoveryBtn,we_chatBtn,downloadBtn;
    private List<Styles> styles;
    private RecyclerView rv;
    private MyAdapter myAdapter;
    private ImageView iv_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_handler);
        init();
        allClick();
//        初始化数据
        initializeData();
//        初始化适配器
//        initializeAdapter();
//        设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(linearLayoutManager);
//        设置adapter
        myAdapter = new MyAdapter(styles);
        rv.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(Photo_handler.this, position, Toast.LENGTH_SHORT).show();
            }
        });
//        设置imgview
        receive();
    }

    public void init () {
        backBtn = findViewById(R.id.back_button);
        recoveryBtn = findViewById(R.id.recovery_button);
        we_chatBtn = findViewById(R.id.we_chat_button);
        downloadBtn = findViewById(R.id.download_button);
        rv = findViewById(R.id.list_view);
        iv_image = findViewById(R.id.iv_image);
    }

    //    初始化数据

    private void initializeData(){
        styles = new ArrayList<>();
        styles.add(new Styles(R.drawable.style_001));
        styles.add(new Styles(R.drawable.style_002));
        styles.add(new Styles(R.drawable.style_003));
        styles.add(new Styles(R.drawable.style_004));
        styles.add(new Styles(R.drawable.style_005));
        styles.add(new Styles(R.drawable.style_006));
    }

//    接收bitmap对象
    public void receive () {
        Intent intent = getIntent();
        if (intent != null) {
            Bitmap bitmap = intent.getParcelableExtra("bitmap");
            Bitmap bit = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());
            iv_image.setImageBitmap(bit);
        }
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
