package com.example.genya.testprojectuzhakhau.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.genya.testprojectuzhakhau.adapters.Adapter;
import com.example.genya.testprojectuzhakhau.model.MainActivityModel;
import com.example.genya.testprojectuzhakhau.objects.Data;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MainActivityPresenter {
    private MainActivityModel mainActivityModel;
    private ArrayList<Data> arrayData;
    private Adapter adapter;

    public MainActivityPresenter(Context context, SharedPreferences preferences) {
        mainActivityModel = new MainActivityModel(context, preferences);
        Log.i(TAG, "MainActivityPresenter: " + preferences.getInt(mainActivityModel.FLAG_NUMBER, 0));

    }

    //обращаемся к БД для получения данных
    private ArrayList<Data> getData(){
        mainActivityModel.workWithDB();
        arrayData = mainActivityModel.getListData();
        return toMirrorList();
    }

    //возвращаем адаптер с данными для recyclerView
    public Adapter getAdapter(){
        adapter = new Adapter(getData(), MainActivityPresenter.this);
        mainActivityModel.closeConnection();
        return adapter;
    }

    public Adapter addNewElement(){
        mainActivityModel.connToDB();
        String result = mainActivityModel.doQueryToDB(mainActivityModel.addDataInDB());
        mainActivityModel.closeConnection();
        if (!result.equals("-1")) {
            String[] data = result.split(" ");
            arrayData.add(new Data(Integer.parseInt(data[0]), Integer.parseInt(data[1])));
            adapter = new Adapter(toMirrorList(), MainActivityPresenter.this);
            return adapter;
        }
        return null;
    }

    public void delElement(int position){
        int w = arrayData.size()-1 - position;
        Log.i(TAG, "delElement: array = " + arrayData.size());
        Log.i(TAG, "delElement: w = " + w);
        Log.i(TAG, "delElement: id = " + arrayData.get(w).getId());
        Log.i(TAG, "delElement: number = " + arrayData.get(w).getNumber());
        mainActivityModel.connToDB();
        mainActivityModel.delDataFromDB(arrayData.get(w).getId());
        mainActivityModel.closeConnection();
        arrayData.remove(w);
    }

    private ArrayList<Data> toMirrorList(){
        ArrayList<Data> arrayList = new ArrayList<>();
        arrayList.clear();
        for (int i = arrayData.size() - 1; i >= 0; i--)
            arrayList.add(arrayData.get(i));

        return arrayList;

    }
}
