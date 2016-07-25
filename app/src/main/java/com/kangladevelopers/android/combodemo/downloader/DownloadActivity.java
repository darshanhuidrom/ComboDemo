package com.kangladevelopers.android.combodemo.downloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kangladevelopers.android.combodemo.R;

public class DownloadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        new YouTubePageStreamUriGetter(this) {
            @Override
            public void onCompleted(String url) {
                String url1 = url;
            }
        }.execute("https://youtu.be/mfPUyaFYwak");
    }
}
