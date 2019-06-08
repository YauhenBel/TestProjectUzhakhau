package com.example.genya.testprojectuzhakhau.adapters;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private RecyclerView mRecyclerView;


    //конструктор
    public Adapter(ArrayList<Data> arrayList, MainActivityPresenter mainPresenter) {
        this.arrayList = arrayList;
        this.mainPresenter = mainPresenter;

    }

    private Adapter(Parcel in) {
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


    //указываем, какой макет использовать для элементов списка
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.designofitem, parent, false);
        return new ViewHolder(view);
    }



    //заполняем макет данными
    @Override
    public void onBindViewHolder(@NonNull final Adapter.ViewHolder holder, final int position) {

            holder.textView.setText(String.valueOf(arrayList.get(position).getNumber()));
            holder.imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG, "onClick: position = " + position);

                    //Log.i(TAG, "onClick: id = " + arrayList.get(i).getId() + ", num = " + arrayList.get(i).getNumber());

                    notifyItemChanged(position);
                    notifyItemRangeChanged(position, arrayList.size());
                    delItem(position);
                }
            });
    }

    private void delItem(final int i){
        /*Log.i(TAG, "delItem: i = " + i + ", id =" + arrayList.get(i).getId() + "' num = " +
                arrayList.get(i).getNumber() );*/
        mainPresenter.delElement(i);
        arrayList.remove(i);

        //notifyItemRangeChanged(arrayList.size(), 0);
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

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    //создаем объект текстового поля из макета
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageButton imageButton;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvNumber);
            imageButton = itemView.findViewById(R.id.btnDel);
        }
    }

    public ArrayList<Data> getArrayList() {
        return arrayList;
    }
}
