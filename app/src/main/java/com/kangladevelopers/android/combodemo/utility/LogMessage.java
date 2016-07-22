package com.kangladevelopers.android.combodemo.utility;

/**
 * Created by DARSHAN HUIDROJM on 12/11/2015.
 */

import android.content.Context;
import android.util.Log;

/**
 * This class is used to display log and Toast message,which is meant for developing purpose
 */
public class LogMessage {

    public static void showLogMessage(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void showToastMessage(Context context, String msg) {
      //  Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    /*LogMessage.showToastMessage(BaseApplication.getAppContext(),"");
    LogMessage.showLogMessage(TAG, ">>>>>>");
    private static final String TAG = HomeActivity.class.getSimpleName();*/
}
