package com.example.genya.testprojectuzhakhau.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.genya.testprojectuzhakhau.R;
import com.example.genya.testprojectuzhakhau.objects.Data;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Data> arrayList;

    //конструктор
    public Adapter(ArrayList<Data> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getItemViewType(int position) {
        int i = arrayList.size() - 1 - position;
        if (arrayList.get(i) != null){
            return 1;
        }
        return 0;
    }

    //указываем, какой макет использовать для элементов списка
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: viewType = " + viewType);
        View view;
        if (viewType == 1) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.designofitem, parent, false);
        }
        else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.designofbuttonitem, parent, false);
        }


        return new ViewHolder(view);
    }



    //заполняем макет данными
    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        int i = arrayList.size() - 1 - position;
        if (arrayList.get(i) != null) {
            holder.textView.setText(String.valueOf(arrayList.get(i).getNumber()));
            Log.i(TAG, "onBindViewHolder: " + i);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    //создаем объект текстового поля из макета
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvNumber);
        }
    }
}
