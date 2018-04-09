package com.demo.mrma.demo;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.demo.mrma.demo.Helper.BitmapHelper;
import com.github.clans.fab.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Photo_handler extends Activity {

    private FloatingActionButton backBtn,recoveryBtn,shareBtn,downloadBtn;
    private List<Styles> styles;
    private String[] styleName = {"GRAY_SCALE", "SEPIA", "BRIGHT", "VINTAGE_PINHOLE"};
    private int[] useTime = {0, 0, 0, 0};
    private Bitmap srcBmp;
    private RecyclerView rv;
    private MyAdapter myAdapter;
    private ImageView iv_image;
    private Uri uri;

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
//        设置imgview图片
        srcBmp = BitmapHelper.getInstance().getBitmap();
        iv_image.setImageBitmap(srcBmp);
//        设置adapter
        myAdapter = new MyAdapter(Photo_handler.this, styles, styleName, useTime, srcBmp, iv_image);
        rv.setAdapter(myAdapter);
    }

    public void init () {
        backBtn = findViewById(R.id.back_button);
        recoveryBtn = findViewById(R.id.recovery_button);
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

//        恢复按钮
        recoveryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_image.setImageBitmap(srcBmp);
            }
        });

//        分享按钮
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_image.setDrawingCacheEnabled(true);
                Bitmap bitmap = iv_image.getDrawingCache();
                saveImageToGallery(Photo_handler.this, bitmap);
                iv_image.setDrawingCacheEnabled(false);
                shareImg(uri);
            }
        });

//        下载按钮
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_image.setDrawingCacheEnabled(true);
                Bitmap bitmap = iv_image.getDrawingCache();
                saveImageToGallery(Photo_handler.this, bitmap);
                iv_image.setDrawingCacheEnabled(false);
                Toast.makeText(Photo_handler.this, "照片已保存", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "MXT");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = UUID.randomUUID() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 最后通知图库更新
        uri = Uri.parse("file://" + file.getAbsolutePath());
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
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
