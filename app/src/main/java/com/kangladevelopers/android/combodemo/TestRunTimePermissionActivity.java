package com.kangladevelopers.android.combodemo;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kangladevelopers.android.combodemo.utility.Utility;

public class TestRunTimePermissionActivity extends RuntimePermissionsActivity {
    private static final int REQUEST_PERMISSIONS = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_run_time_permission);
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        Utility.printHashKey(getApplicationContext());
        if (currentapiVersion >=23){
            // Do something for lollipop and above versions
            requestAppPermission(new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_EXTERNAL_STORAGE},R.string.app_name,REQUEST_PERMISSIONS);

        } else{
        }

    }

    @Override
    public void onPermissionGranted(int requestCode) {
        Toast.makeText(this, "Permissions Received.", Toast.LENGTH_LONG).show();
    }

    public void onButtonClick(View view) {
        Toast.makeText(this, "onButtonClick", Toast.LENGTH_LONG).show();
    }
}
