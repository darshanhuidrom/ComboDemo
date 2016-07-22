package com.kangladevelopers.android.combodemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by DARSHAN HUIDROJM on 7/22/2016.
 */
public class CustomTextView extends TextView {

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextView(Context context) {
        super(context);

    }

    private void init() {
       /* Typeface tf = Typeface.createFromAsset(MyApplication.getAppContext().getAssets(),
                "fonts/HelveticaNeueMedium.ttf");
        setTypeface(tf);*/
        // setTypeface(tf,Typeface.BOLD);
    }
}
