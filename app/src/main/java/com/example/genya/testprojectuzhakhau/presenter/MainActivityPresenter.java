package com.example.genya.testprojectuzhakhau.presenter;

import com.example.genya.testprojectuzhakhau.adapters.Adapter;
import com.example.genya.testprojectuzhakhau.objects.Data;

import java.util.ArrayList;

public class MainActivityPresenter {


    private ArrayList<Data> fillData(ArrayList<Data> data){
        data = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            data.add(new Data(i, i));
        }

        return data;
    }

    public Adapter getAdapter(ArrayList<Data> data){
        Adapter adapter = new Adapter(fillData(data));
        return adapter;
    }

}
