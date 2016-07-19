package com.kangladevelopers.android.combodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

//https://professorneurus.wordpress.com/2013/10/23/adding-multiple-clicking-regions-to-an-android-textview/

// http://www.viralandroid.com/2016/03/android-material-design-profile-screen-xml-ui-design.html
//http://www.rajnikantvscidjokes.in/man-ruling-twitter-extraordinarily-creative-photoshop-skills-dont-send-pic/3/
public class MainActivity extends AppCompatActivity {

    private LinearLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parentLayout = (LinearLayout) findViewById(R.id.parent_layout);
        TextView textView = (TextView) findViewById(R.id.tv_multi_clikable);
        makeTagLinks("add1,add2,add3,add1,add2,add3,add1,add2,add3",textView );
    }


    private void addTextView(LinearLayout linearLayout) {
        View view = LayoutInflater.from(this).inflate(R.layout.text_view, null);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);
        view.setLayoutParams(params);
        linearLayout.addView(view);
    }

    public void onAddClick(View view) {
        addTextView(parentLayout);
    }

    public void onTextClick(View view) {
        Toast.makeText(getApplicationContext(), "onTextClick", Toast.LENGTH_SHORT).show();
    }


    private void makeTagLinks(final String text, final TextView tv) {
        if (text == null || tv == null) {
            return;
        }
        final SpannableString ss = new SpannableString(text);

        final List<String> items = Arrays.asList(text.split(","));
        int start = 0, end;

        for ( String item : items) {
            end = start + item.length();
            if (start < end) {
                ss.setSpan(new MyClickableSpan(item), start, end, 0);
            }
            start += item.length()+1;//comma and space in the original text ;)
        }
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(ss, TextView.BufferType.SPANNABLE);

    }


    private class MyClickableSpan extends ClickableSpan {
        private  String mText;
        private MyClickableSpan(final String text) {
            mText = text;
        }
        @Override
        public void onClick(final View widget) {
            Toast.makeText(getApplicationContext(),mText,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(getResources().getColor(R.color.colorPrimaryDark));
            ds.setUnderlineText(false);
        }
    }

    private class CustomComparator implements Comparator<Object> {// may be it would be Model
        @Override
        public int compare(Object obj1, Object obj2) {
            return new Date().compareTo(new Date());
        }
    }

}
