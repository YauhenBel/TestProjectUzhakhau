package com.example.genya.testprojectuzhakhau.presenter;

import com.example.genya.testprojectuzhakhau.adapters.Adapter;
import com.example.genya.testprojectuzhakhau.objects.Numbers;

public class MainActivityPresenter {


    private Numbers fillData(Numbers numbers){
        numbers = new Numbers();
        for (int i = 0; i < 20; i++){
            numbers.addElement();
        }

        return numbers;
    }

    public Adapter getAdapter(Numbers numbers){
        Adapter adapter = new Adapter(fillData(numbers).getNumbers());
        return adapter;
    }

}
