package com.demo.mrma.demo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import utils.Configutils;

import static java.lang.System.out;


public class MainActivity extends AppCompatActivity {

    private FloatingActionButton flashBtn,shotBtn,frontCameraBtn,albumBtn;
    SurfaceView surfaceview = null;
    SurfaceHolder holder = null;
    Camera camera = null;
    Camera.Parameters parameters = null;
    boolean isOpenLightSign = false;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    int position = 0;//1表示前置摄像头，0表示后置摄像头
    private File tempFile;
    private Uri sharePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        初始化
        init();
//        所有点击事件
        allClick();
//        初始化拍照界面
        setCamera();
    }

//    实例化变量
    public void init () {
        flashBtn = (FloatingActionButton) findViewById(R.id.flash_button);
        shotBtn = (FloatingActionButton) findViewById(R.id.camera_button);
        albumBtn = (FloatingActionButton) findViewById(R.id.album_button);
        frontCameraBtn = (FloatingActionButton) findViewById(R.id.front_camera_button);
        surfaceview = (SurfaceView) findViewById(R.id.surfaceview);
    }

    public void setCamera () {

    /*
        * 画面捕捉*/
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
        Log.i("TEST","Granted");
        //init(barcodeScannerView, getIntent(), null);
    } else {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA}, 1);//1 can be another integer
    }
    holder = surfaceview.getHolder();
    holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//    holder.setFixedSize(177,144);
    holder.setKeepScreenOn(true);
    holder.addCallback(new MySurfaceCallBack());  //回调函数

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
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                camera.autoFocus(new Camera.AutoFocusCallback(){
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        if(success){
                            camera.takePicture(null,null,new TakePic());

                        }
                    }
                });
            }
        });

//        切换摄像头
        frontCameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeCamera();
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
                changeLight();
                isOpenLightSign = !isOpenLightSign;    //更改状态
            }
        });

    }

    /*
    * 闪光灯*/
    private void changeLight(){
        Camera.Parameters parameters = camera.getParameters();
        if (isOpenLightSign){
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            flashBtn.setImageResource(R.drawable.ic_flash_off);
        }else {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
            flashBtn.setImageResource(R.drawable.ic_flash);
        }
        camera.setParameters(parameters);
    }

    /*
    * 摄像头切换*/
    private void changeCamera(){
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int count = Camera.getNumberOfCameras();    //获取摄像头个数
        for (int i = 0;i < count;i++){
            Camera.getCameraInfo(i,cameraInfo);
            if (position == 1){
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK){
                    try {
                        camera.stopPreview();    //停止预览
                        camera.release();
                        camera = null;
                        camera = Camera.open(i);
                        camera.setDisplayOrientation(getRotation(MainActivity.this));
                        camera.setPreviewDisplay(holder);
                        camera.startPreview();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    position = 0;
                    break;
                }

            }else {
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT){
                    try {
                        camera.stopPreview();
                        camera.release();
                        camera = null;
                        camera = Camera.open(i);
                        camera.setDisplayOrientation(getRotation(MainActivity.this));
                        camera.setPreviewDisplay(holder);
                        camera.startPreview();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    position = 1;
                    break;
                }

            }
        }

    }

    class TakePic implements Camera.PictureCallback{

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            if (data.length > 0){  //大于0代表有数据
                Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, Photo_handler.class);
//                intent.putExtra("selectedImage", bitmap);
//                startActivity(intent);
                if(bitmap!=null){

                    File file=new File(Environment.getExternalStorageDirectory()+"/isifeng");
                    if(!file.isDirectory()){
                        file.mkdir();
                    }

                    file=new File(Environment.getExternalStorageDirectory()+"/isifeng",System.currentTimeMillis()+".jpg");


                    try
                    {
                        FileOutputStream fileOutputStream=new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100, fileOutputStream);

                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                    catch(Exception exception)
                    {
                        exception.printStackTrace();
                    }

                }
            }
        }
    }

    /*
    * 回调函数*/
    class MySurfaceCallBack implements SurfaceHolder.Callback{

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            try {
                camera = Camera.open();
                camera.setDisplayOrientation(getRotation(MainActivity.this));
                camera.setPreviewDisplay(holder);
                camera.startPreview();  //画面开始预览
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        /*
        * 画面参数*/
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            parameters = camera.getParameters();
            List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();  //画面大小
            if (supportedPictureSizes.isEmpty()){
                parameters.setPreviewSize(i1,i2);

            }else {
                Camera.Size size = supportedPictureSizes.get(0);
                parameters.setPreviewSize(size.width,size.height);  //预览
            }
            parameters.setPictureFormat(PixelFormat.JPEG);  //格式
            parameters.setPictureSize(i1,i2);  //大小
            parameters.setJpegQuality(80);  //质量
            parameters.setPreviewFrameRate(5);  //每秒帧数
            System.out.println(i1);

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (camera != null){
                camera.release();
                camera = null;
            }
        }
    }

    /*
    * 界面旋转*/
    private int getRotation(Activity activity){
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();//界面旋转程度
        int degree = 0;
        switch (rotation){
            case Surface.ROTATION_0:
                degree = 90;
                break;
            case Surface.ROTATION_90:
                degree = 0;
                break;
            case Surface.ROTATION_180:
                degree = 270;
                break;
            case Surface.ROTATION_270:
                degree = 180;
                break;
        }
        return degree;
    }

    @Override
    /*
    * 画面失去焦点*/
    protected void onPause() {
        super.onPause();
        if (camera != null)
            camera.stopPreview();
    }

    @Override
    /*
    * 画面恢复*/
    protected void onRestart() {
        super.onRestart();
        if (camera != null){
            camera.startPreview();
        }else {
            try {
                camera = Camera.open();
                camera.setDisplayOrientation(getRotation(MainActivity.this));
                camera.setPreviewDisplay(holder);
                camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    /*
    * 摄像适配*/
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
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
                // 传递uri
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
