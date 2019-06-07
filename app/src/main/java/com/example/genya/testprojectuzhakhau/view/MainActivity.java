package com.example.genya.testprojectuzhakhau.view;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.genya.testprojectuzhakhau.R;
import com.example.genya.testprojectuzhakhau.objects.Data;
import com.example.genya.testprojectuzhakhau.presenter.MainActivityPresenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity" ;
    private MainActivityPresenter mMainActivityPresenter;
    private RecyclerView mRecyclerView;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("info", MODE_PRIVATE);
        mMainActivityPresenter = new MainActivityPresenter(this, preferences);
        init();


    }

    //инициализируем данными recyclerView
    private void init(){

        mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mMainActivityPresenter.getAdapter());
    }


}
