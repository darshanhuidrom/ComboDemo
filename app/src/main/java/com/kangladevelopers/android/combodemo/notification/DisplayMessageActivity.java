package com.kangladevelopers.android.combodemo.notification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.kangladevelopers.android.combodemo.R;

public class DisplayMessageActivity extends AppCompatActivity {

    private String message;
    TextView tvMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        message= getIntent().getStringExtra("message");
        tvMessage= (TextView) findViewById(R.id.tvMessage);
        tvMessage.setText(message);
    }
}
