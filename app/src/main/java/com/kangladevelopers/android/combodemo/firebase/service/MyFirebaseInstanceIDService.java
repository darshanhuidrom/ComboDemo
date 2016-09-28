package com.kangladevelopers.android.combodemo.firebase.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.kangladevelopers.android.combodemo.utility.AppPreference;
import com.kangladevelopers.android.combodemo.utility.Constant;
import com.kangladevelopers.android.combodemo.utility.MyApplication;

/**
 * Created by priyabrat on 28/6/16.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    // This firebase use the account dhuidrom@regalix-inc.com

    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {

        //Getting registration token
        if(AppPreference.isInstalledFirst(MyApplication.getAppContext())){
            return;
        }
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        AppPreference.saveToAppPreference(getApplicationContext(), Constant.TKN, refreshedToken);

        //Displaying token on logcat
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        try{
            if(refreshedToken !=null )
            {
               // HTTP.sendRegistrationToServer(getApplicationContext(),refreshedToken);
            }
        }catch (Exception e){
            Log.d(TAG,"FCM token update Error"+e);
        }

    }
}
