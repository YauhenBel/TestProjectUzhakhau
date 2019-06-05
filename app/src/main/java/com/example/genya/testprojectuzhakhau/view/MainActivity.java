package com.example.genya.testprojectuzhakhau.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.genya.testprojectuzhakhau.R;
import com.example.genya.testprojectuzhakhau.objects.Numbers;
import com.example.genya.testprojectuzhakhau.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity" ;
    MainActivityPresenter mainActivityPresenter;
    RecyclerView recyclerView;
    Numbers numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityPresenter = new MainActivityPresenter();
        init();

    }

    private void init(){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mainActivityPresenter.getAdapter(numbers));
    }


}
