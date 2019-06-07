package com.example.genya.testprojectuzhakhau.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.genya.testprojectuzhakhau.R;
import com.example.genya.testprojectuzhakhau.objects.Data;
import com.example.genya.testprojectuzhakhau.presenter.MainActivityPresenter;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements Parcelable {

    private ArrayList<Data> arrayList;
    private MainActivityPresenter mainPresenter;


    //конструктор
    public Adapter(ArrayList<Data> arrayList, Context context) {
        this.arrayList = arrayList;
        mainPresenter = new MainActivityPresenter(context);

    }

    protected Adapter(Parcel in) {
    }

    public static final Creator<Adapter> CREATOR = new Creator<Adapter>() {
        @Override
        public Adapter createFromParcel(Parcel in) {
            return new Adapter(in);
        }

        @Override
        public Adapter[] newArray(int size) {
            return new Adapter[size];
        }
    };

    @Override
    public int getItemViewType(int position) {
        int i = arrayList.size()-1-position;
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
    public void onBindViewHolder(@NonNull final Adapter.ViewHolder holder, final int position) {

        final int i = arrayList.size() - 1 - position;
        Log.i(TAG, "onBindViewHolder: " + i);
        if (arrayList.get(i) != null) {
            holder.textView.setText(String.valueOf(arrayList.get(i).getNumber()));
            holder.imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Log.i(TAG, "onClick: id = " + arrayList.get(i).getId() + ", num = " + arrayList.get(i).getNumber());
                    delItem(i);
                }
            });

        } else {
          holder.button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                 addNewItem();
              }
          });
        }
    }

    @SuppressLint("ShowToast")
    private void addNewItem(){
        int numOfDelElement = arrayList.size()-1;
        arrayList.remove(numOfDelElement);
        int cipherToNewRecord = arrayList.get(arrayList.size()-1).getNumber()+1;
        arrayList.add(mainPresenter.addNewElement(cipherToNewRecord));
        arrayList.add(null);
        Log.i(TAG, "onClick: last number = " +
                arrayList.get(arrayList.size()-2).getNumber());
        notifyDataSetChanged();

        //notifyItemRangeChanged(0, arrayList.size());
    }

    private void delItem(int i){
        mainPresenter.delElement(arrayList.get(i).getId());
        arrayList.remove(i);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    //создаем объект текстового поля из макета
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        Button button;
        ImageButton imageButton;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvNumber);
            button = itemView.findViewById(R.id.btnAdd);
            imageButton = itemView.findViewById(R.id.btnDel);
        }
    }

    public ArrayList<Data> getArrayList() {
        return arrayList;
    }
}
