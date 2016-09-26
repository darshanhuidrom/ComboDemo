package com.kangladevelopers.android.combodemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by DARSHAN HUIDROJM on 9/23/2016.
 */
public class MyBoundService extends Service {

    private MyBinder myBinder = new MyBinder();
    private static boolean isBound =false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        isBound=true;
        return myBinder;
    }


    public String getCurrentTime(){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.US);
        return format.format(new Date());
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(">>>>>>","onUnbind");
        isBound=false;
        return super.onUnbind(intent);


    }

    public static boolean  isbound(){
        return isBound;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(">>>>>>","onRebind");
        isBound=true;
        super.onRebind(intent);
    }

    // The purpose of this local binder is to return the reference of the BoundService by using it the the client which in this case is the
    // caller activity will access the method of the bound service.
    class MyBinder extends Binder {

        MyBoundService getService() {
            return MyBoundService.this;
        }

    }
}
