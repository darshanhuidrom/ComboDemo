package com.kangladevelopers.android.combodemo.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kangladevelopers.android.combodemo.R;

public class MyServiceActivity extends AppCompatActivity {

    private MyBoundService boundService;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            // this method will be called if the service is connected.
            // means the method of the service will only be executed if and
            //only if  the service is binded.
            MyBoundService.MyBinder binder = (MyBoundService.MyBinder) service;
            boundService = binder.getService();

            Log.d(">>>>>>", "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            Log.d(">>>>>>", "onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);
    }

    public void onShow(View view) {

        if (MyBoundService.isbound()) {
            Toast.makeText(getApplicationContext(), boundService.getCurrentTime(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "SErvice is not binded", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBindAndUnbind(View view) {
        Button button = (Button) findViewById(R.id.bt_bind_unbind);
        Intent intent = new Intent(this, MyBoundService.class);
        if (MyBoundService.isbound()) {
            unbindService(connection);
            button.setText("Bind Service");
        } else {

            bindService(intent, connection, Context.BIND_AUTO_CREATE);

            button.setText("Unbind Service");
        }
    }
}
