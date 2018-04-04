package com.demo.mrma.demo.Helper;

import android.graphics.Bitmap;

/**
 * Created by mr.ma on 2018/3/31.
 */

public class BitmapHelper {
    private Bitmap bitmap = null;
    private static final BitmapHelper instance = new BitmapHelper();

    public BitmapHelper() {

    }

    public static BitmapHelper getInstance() {
        return instance;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
