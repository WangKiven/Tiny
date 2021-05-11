package com.zxy.tiny.core;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.TypedValue;

import androidx.annotation.RequiresApi;
import androidx.exifinterface.media.ExifInterface;

import com.zxy.tiny.Tiny;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhengxiaoyong on 2017/4/27.
 */
public class Degrees {

    public static Bitmap handle(Bitmap bitmap, byte[] data) {
        // jpg，heif都有可能方向不对
        /*if (ExifCompat.isJpeg(data)) {
            int orientation = ExifCompat.getOrientation(data);
            bitmap = BitmapKit.rotateBitmap(bitmap, orientation);
        }*/
        int orientation = getOrientation(data);
        if (orientation != 0) {
            bitmap = BitmapKit.rotateBitmap(bitmap, orientation);
        }
        return bitmap;
    }

    public static Bitmap handle(Bitmap bitmap, int resId) {
        InputStream is = null;
        Resources resources = Tiny.getInstance().getApplication().getResources();
        try {
            is = resources.openRawResource(resId, new TypedValue());
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            os.close();

            // jpg，heif都有可能方向不对
            /*if (ExifCompat.isJpeg(os.toByteArray())) {
                int orientation = ExifCompat.getOrientation(os.toByteArray());
                bitmap = BitmapKit.rotateBitmap(bitmap, orientation);
            }*/
            bitmap = handle(bitmap, os.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    //ignore...
                }
            }
        }
        return bitmap;
    }


    public static int getOrientation(byte[] data) {
        int orientation = ExifInterface.ORIENTATION_NORMAL;
        int degree;
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(data);

            ExifInterface exif = new ExifInterface(inputStream);
            orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                degree = 0;
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                degree = 90;
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                degree = 180;
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                degree = 270;
                break;
            default:
                degree = 0;
                break;
        }
        return degree;
    }
}
