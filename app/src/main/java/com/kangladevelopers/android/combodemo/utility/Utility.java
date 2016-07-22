package com.kangladevelopers.android.combodemo.utility;

import android.content.Context;

import com.kangladevelopers.android.combodemo.pojo.User;

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


    public List<User> convertArrayToUsers(String data,String splitter){

        String[] array= data.split(splitter);
        String temp;
        ArrayList<User> users = new ArrayList<>();
        for (int i=0;i<array.length;i++){
            temp = array[i];
            int pos= temp.indexOf(" ");
            User user = new User(temp.substring(0,pos),temp.substring(pos+1));
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
        return parseDateByFormatToString(date,DateFormatString.COMMON_FORMAT);
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
    public static Date convertStringToDate(String dateString,String stringFormat) {
        SimpleDateFormat format = new SimpleDateFormat(stringFormat);
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public static int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
