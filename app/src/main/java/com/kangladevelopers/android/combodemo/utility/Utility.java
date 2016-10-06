package com.kangladevelopers.android.combodemo.utility;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.kangladevelopers.android.combodemo.pojo.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by DARSHAN HUIDROJM on 7/21/2016.
 */

// http://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
public class Utility {


    static public class CustomComparator implements Comparator<User> {// may be it would be Model

        @Override
        public int compare(User obj1, User obj2) {

            return obj1.getFirstName().compareTo(obj2.getFirstName());
        }
    }


    public List<User> convertArrayToUsers(String data, String splitter) {

        String[] array = data.split(splitter);
        String temp;
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            temp = array[i];
            int pos = temp.indexOf(" ");
            User user = new User(temp.substring(0, pos), temp.substring(pos + 1));
            users.add(user);
        }
        return users;
    }


    public static String parseDateByFormatToString(Date date, String stringFormat) {
        SimpleDateFormat format = new SimpleDateFormat(stringFormat);
        String dateString = format.format(date);
        return dateString;
    }

    public static String parseDateByFormatToString(Date date) {
        return parseDateByFormatToString(date, DateFormatString.COMMON_FORMAT);
    }

    public static Date convertStringToDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat(DateFormatString.COMMON_FORMAT);
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date convertStringToDate(String dateString, String stringFormat) {
        SimpleDateFormat format = new SimpleDateFormat(stringFormat);
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static String removeSpaceFromEnd(String s) {

        boolean b = true;
        while (b) {
            if (s.charAt(s.length()) == ' ') {
                s = s.substring(0, s.length() - 1);
                b = true;
            } else {
                b = false;
            }
        }
        return s;
    }

    public static String removeSpaceFromFirst(String name) {
        boolean flag = true;
        while (flag) {
            if (name.charAt(0) == ' ') {
                name = name.substring(1);
                flag = true;
            } else {
                flag = false;
            }
        }
        return name;
    }

    public static String removeSpaceFromFirstAndEnd(String name) {
        return removeSpaceFromEnd(removeSpaceFromFirst(name));
    }


    public static void printHashKey(Context pContext) {

        String packageName = pContext.getPackageName();
        try {
            PackageInfo info = pContext.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i(">>>>", "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e(">>>>", "printHashKey()", e);
        } catch (Exception e) {
            Log.e(">>>>", "printHashKey()", e);
        }
    }


    public static boolean isTablet(Context context) {
        String device_screen;
        if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE) {
            device_screen = "large";
            return true;
        } else {
            device_screen = "small";
            return false;
        }
    }


    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }


}
