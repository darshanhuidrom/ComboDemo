package com.kangladevelopers.android.combodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kangladevelopers.android.combodemo.adapter.DividerItemDecoration;
import com.kangladevelopers.android.combodemo.adapter.RVAdapter;

import java.util.ArrayList;

public class TestRecycler extends AppCompatActivity {

    private RecyclerView rv;
    private RVAdapter rvAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recycler);
        rv= (RecyclerView) findViewById(R.id.rv);
        ArrayList<String> arrayList = new ArrayList<>();
        for(int i=0;i<30;i++){
            arrayList.add("Test "+i);
        }
        rvAdapter = new RVAdapter(getApplicationContext(),arrayList);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
       rv.addItemDecoration(new DividerItemDecoration(getApplicationContext(),R.drawable.divider));

        rv.setAdapter(rvAdapter);
    }
}
