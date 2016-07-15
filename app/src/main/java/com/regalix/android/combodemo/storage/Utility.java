package com.regalix.android.combodemo.storage;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by DARSHAN HUIDROJM on 7/15/2016.
 */
public class Utility {
    private static final String BASE_ROOT = "";
    private static final String IMAGE_NAME = "profile.jpg";
    private static final String FOLDER_NAME = "imageDir";
    private Context context;


    public File getRootDirectory() {
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(FOLDER_NAME, Context.MODE_PRIVATE);
        return directory;
    }

    public void saveToInternalStorage(Bitmap bitmapImage) {
        File mypath = new File(getRootDirectory(), IMAGE_NAME);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void loadImageFromStorage(ImageView imageView) {

        try {
            File f = new File(getRootDirectory(), IMAGE_NAME);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            imageView.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

   /* private void loadImageFromStorage(ImageView imageView) {

        File mypath = new File(getRootDirectory(), "profile.jpg");
        loadImageFromStorage(mypath.getPath(), imageView);


    }*/
}
