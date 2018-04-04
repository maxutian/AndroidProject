package com.demo.mrma.demo;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.demo.mrma.demo.Helper.BitmapHelper;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Photo_handler extends Activity {

    private FloatingActionButton backBtn,feedBackBtn,shareBtn,downloadBtn;
    private List<Styles> styles;
    private String[] styleName = {"style_001", "style_002", "style_003", "style_004"};
    private int[] useTime = {0, 0, 0, 0};
    private Bitmap srcBmp;
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
//        设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(linearLayoutManager);
//        设置adapter
        myAdapter = new MyAdapter(Photo_handler.this, styles, styleName, useTime, srcBmp);
        rv.setAdapter(myAdapter);
//        设置imgview图片
        srcBmp = BitmapHelper.getInstance().getBitmap();
        iv_image.setImageBitmap(srcBmp);
    }

    public void init () {
        backBtn = findViewById(R.id.back_button);
        feedBackBtn = findViewById(R.id.feed_back_button);
        shareBtn = findViewById(R.id.share_button);
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

//        反馈按钮
        feedBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent feedback = new Intent();
                feedback.setClass(Photo_handler.this, Feed_Back.class);
                startActivity(feedback);
            }
        });

//        分享按钮
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    private void shareImg(Uri uri){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        if(uri!=null){
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            shareIntent.setType("image/*");
        }
        startActivity(shareIntent);
    }

}
