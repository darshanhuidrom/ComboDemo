package com.kangladevelopers.android.combodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.kangladevelopers.android.combodemo.utility.Utility;

public class PopUpWindowActivity extends AppCompatActivity {

    private PopupWindow popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_window);
    }

    public  void showPopUp(View view){

         popup = new PopupWindow(this);
        View layout = getLayoutInflater().inflate(R.layout.popup, null);
        popup.setContentView(layout);
        // Set content width and height
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(Utility.dip2px(getApplicationContext(),200));
        // Closes the popup window when touch outside of it - when looses focus
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        // Show anchored to button
        popup.showAsDropDown(view);
    }

    public void onSortClick(View view){
       TextView tv = (TextView) view;
        Toast.makeText(getApplicationContext(),tv.getText()+"",Toast.LENGTH_SHORT).show();
        popup.dismiss();

    }

}
