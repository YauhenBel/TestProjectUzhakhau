package com.example.genya.testprojectuzhakhau.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.genya.testprojectuzhakhau.adapters.Adapter;
import com.example.genya.testprojectuzhakhau.model.MainActivityModel;
import com.example.genya.testprojectuzhakhau.objects.Data;

import java.util.ArrayList;

public class MainActivityPresenter {
    private MainActivityModel mainActivityModel;

    public MainActivityPresenter(Context context, SharedPreferences preferences) {
        mainActivityModel = new MainActivityModel(context, preferences);

    }

    //обращаемся к БД для получения данных
    private ArrayList<Data> getData(){
        mainActivityModel.workWithDB();
        return mainActivityModel.getListData();
    }

    //возвращаем адаптер с данными для recyclerView
    public Adapter getAdapter(){
        Adapter adapter = new Adapter(getData());
        mainActivityModel.workWithDB();
        return adapter;
    }





}
