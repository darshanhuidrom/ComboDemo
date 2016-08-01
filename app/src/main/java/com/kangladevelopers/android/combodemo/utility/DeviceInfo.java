package com.kangladevelopers.android.combodemo.utility;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by DARSHAN HUIDROJM on 7/18/2016.
 */
public class DeviceInfo {


    private static String TAG = "DeviceInfo";
    public static final String OS = "android";

    public static String getMACAddress() {
        WifiManager manager = (WifiManager) MyApplication.getAppContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        String address = info.getMacAddress();
        Log.d(TAG, "Mac id: " + address);
        return address;
    }

    public static String getIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager)MyApplication.getAppContext().getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }
}
