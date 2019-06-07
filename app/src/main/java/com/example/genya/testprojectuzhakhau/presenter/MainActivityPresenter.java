package com.example.genya.testprojectuzhakhau.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.genya.testprojectuzhakhau.adapters.Adapter;
import com.example.genya.testprojectuzhakhau.model.MainActivityModel;
import com.example.genya.testprojectuzhakhau.objects.Data;

import java.util.ArrayList;

public class MainActivityPresenter {
    private MainActivityModel mainActivityModel;
    private Context context;

    public MainActivityPresenter(Context context, SharedPreferences preferences) {
        mainActivityModel = new MainActivityModel(context, preferences);
        this.context = context;

    }

    public MainActivityPresenter(Context context){
        mainActivityModel = new MainActivityModel(context);
        this.context = context;
    }

    //обращаемся к БД для получения данных
    private ArrayList<Data> getData(){
        mainActivityModel.workWithDB();
        return mainActivityModel.getListData();
    }

    //возвращаем адаптер с данными для recyclerView
    public Adapter getAdapter(){
        Adapter adapter = new Adapter(getData(), context);
        mainActivityModel.workWithDB();
        return adapter;
    }

    public Data addNewElement(int number){
        return new Data((int) mainActivityModel.addDataInDB(number), number);
    }





}
