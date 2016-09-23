package com.kangladevelopers.android.combodemo.notification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kangladevelopers.android.combodemo.R;

public class NotificationSenderActivity extends AppCompatActivity {

    EditText etMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_sender);
        etMessage= (EditText) findViewById(R.id.etMessage);
    }

    public void onNotificationSend(View view){
        Toast.makeText(getApplicationContext(),"do something here.",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setAction("com.android.custom_broadcast");
        intent.putExtra("message",etMessage.getText().toString());
        sendBroadcast(intent);

    }
}
