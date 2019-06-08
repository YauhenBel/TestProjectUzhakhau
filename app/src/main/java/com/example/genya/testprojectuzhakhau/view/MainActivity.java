package com.example.genya.testprojectuzhakhau.view;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.genya.testprojectuzhakhau.R;
import com.example.genya.testprojectuzhakhau.adapters.Adapter;
import com.example.genya.testprojectuzhakhau.objects.Data;
import com.example.genya.testprojectuzhakhau.presenter.MainActivityPresenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity" ;
    private MainActivityPresenter mMainActivityPresenter;
    private RecyclerView mRecyclerView;
    SharedPreferences preferences;
    private Adapter adapter;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("info", MODE_PRIVATE);
        mMainActivityPresenter = new MainActivityPresenter(this, preferences);
        btnAdd = findViewById(R.id.btnAdd);
        init(savedInstanceState);


    }

    //инициализируем данными recyclerView
    private void init(Bundle savedInstanceState){

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.setAdapter(mMainActivityPresenter.addNewElement());
                mRecyclerView.refreshDrawableState();

            }
        });



        mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        if (savedInstanceState != null){
            adapter = savedInstanceState.getParcelable("adapter");
        }else {
            adapter = mMainActivityPresenter.getAdapter();
        }

        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("adapter", adapter);

    }

}
