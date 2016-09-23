package com.kangladevelopers.android.combodemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by DARSHAN HUIDROJM on 9/23/2016.
 */
public class MyBoundService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class MyBinder extends Binder {

    }
}
