package com.regalix.android.combodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

// https://www.codeofaninja.com/2013/12/android-autocompletetextview-custom-arrayadapter-sqlite.html

import com.regalix.android.combodemo.adapter.AutocompleteCustomArrayAdapter;

public class AutoCompleteTest extends AppCompatActivity {

    private AutoCompleteTextView act;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_test);
        act= (AutoCompleteTextView) findViewById(R.id.act);

        String[] str ={"data1:M//test1","data2:M//","data3:F","data4:M","data5:F","data6:M","data7:F","data8:F","data9:F",};
        String[] displayData = new String[str.length];
        for (int i = 0; i < str.length; i++) {
            displayData[i] = getOnlyName(str[i]);
        }
        final AutocompleteCustomArrayAdapter adapter = new AutocompleteCustomArrayAdapter(getApplicationContext(),R.layout.text_view,str,displayData);
        act.setAdapter(adapter);
        act.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String ss = adapter.getdata()[position];
                if(isTrue(ss)){
                    Toast.makeText(getApplicationContext(),ss+" is M",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),ss+" is F",Toast.LENGTH_SHORT).show();
                }

            }

            });


    }



    private String getOnlyName(String data) {
        int pos = data.indexOf(":");
        return data.substring(0, pos);
    }
    private boolean isTrue(String s){
        int pos = s.indexOf(":");
        if(s.substring(pos+1,pos+2).equals("M")){
            return true;
        }
        return false;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }




}
