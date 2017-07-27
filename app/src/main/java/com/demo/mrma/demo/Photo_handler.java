package com.demo.mrma.demo;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Photo_handler extends Activity {

    private FloatingActionButton backBtn,feedBackBtn,shareBtn,downloadBtn;
    private List<Styles> styles;
    private String[] styleName = {"style_001", "style_002", "style_003", "style_004"};
    private int[] useTime = {0, 0, 0, 0};
    private RecyclerView rv;
    private MyAdapter myAdapter;
    private ImageView iv_image;
    private Uri myUri;

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
        myAdapter = new MyAdapter(Photo_handler.this, styles, styleName, useTime);
        rv.setAdapter(myAdapter);
//        设置imgview图片
        receive();
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

    //    接收uri&&bitmap
    public void receive () {
        Intent intent = getIntent();
        if (intent != null) {
            Uri uri = getIntent().getData();
            myUri = uri;
            Bitmap bitmap = getBitmapFromUri(uri);
            iv_image.setImageBitmap(bitmap);
        }
        Bitmap bitmap = this.getIntent().getParcelableExtra("selectedImage");
        if(bitmap != null)
        {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            // 设置想要的大小
            WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            int newWidth = display.getWidth();
            int newHeight = display.getHeight();
            // 计算缩放比例
            float scaleWidth = ((float) newWidth) / height;
            float scaleHeight = ((float) newHeight) / width;
            // 取得想要缩放的matrix参数
            Matrix matrix = new Matrix();
            matrix.setRotate(90);
            matrix.postScale(scaleWidth, scaleHeight);
            // 得到新的图片
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
            iv_image.setImageBitmap(bitmap);
            iv_image.invalidate();
        }
    }

    //    将uri转成bitmap对象
    private Bitmap getBitmapFromUri(Uri uri)
    {
        try
        {
            // 读取uri所在的图片
            return (MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
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

//        反馈按钮
        feedBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

//        分享按钮
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareImg(myUri);
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
