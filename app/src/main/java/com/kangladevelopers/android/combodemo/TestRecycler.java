package com.kangladevelopers.android.combodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kangladevelopers.android.combodemo.Listener.EndlessRecyclerOnScrollListener;
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
        rvAdapter = new RVAdapter(getApplicationContext(),loadData(0));
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.addItemDecoration(new DividerItemDecoration(getApplicationContext(),R.drawable.divider));
        rv.setAdapter(rvAdapter);
        rv.addOnScrollListener(new EndlessRecyclerOnScrollListener((LinearLayoutManager) rv.getLayoutManager()) {
            @Override
            public void onLoadMore(int current_page) {
                rvAdapter.notifyDataSetChanged( loadData(current_page));

            }
        });
    }

    private ArrayList<String> loadData(int currentPage){
        ArrayList<String> arrayList = new ArrayList<>();
        int nextTotal = currentPage+30;
        for (int i=currentPage;i<=nextTotal;i++){
            arrayList.add("Test "+i);
        }
        return arrayList;
    }
}
