package com.example.genya.testprojectuzhakhau.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.genya.testprojectuzhakhau.database.DataTable;
import com.example.genya.testprojectuzhakhau.database.SQLiteDB;
import com.example.genya.testprojectuzhakhau.objects.Data;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MainActivityModel {

    private Context mContext;
    private SQLiteDB mSqLiteDB;
    private SQLiteDatabase mDB;
    private SharedPreferences mPref;
    private ArrayList<Data> listData;


    public MainActivityModel(Context context, SharedPreferences preferences) {
        this.mContext = context;
        mPref = preferences;
    }

    //работа с БД - подключение, заполнение данными, загрузка данных в приложение
    public void workWithDB(){
        Log.i(TAG, "workWithDB");
        connToDB();
        fillDB();
        getData();


    }

    //подключаемся к БД
    private void connToDB(){
        mSqLiteDB = new SQLiteDB(mContext);
        mDB = mSqLiteDB.getWritableDatabase();
    }

    //при первом запуске приложения заполняем БД тестовыми данными
    private void fillDB() {
        if (!mPref.getBoolean("isDB", false)) {
            Log.i(TAG, "fillDB: !mPref.getBoolean(\"isDB\", false)");
            ContentValues cv = new ContentValues();
            for (int i = 0; i < 20; i++) {
                cv.put(DataTable.COLUMN.NUMBERS, i);
                Log.i(TAG, "workWithDB: id = " + mDB.insert(DataTable.TABLE, null, cv));
            }
            SharedPreferences.Editor editor = mPref.edit();
            editor.putBoolean("isDB", true);
            editor.apply();

        }
    }

    //выгружаем данные в arrayList
    private void getData(){
        listData = new ArrayList<>();
        Log.i(TAG, "workWithDB: Запрос в таблицу....");
        Cursor c = mDB.query(DataTable.TABLE, null, null,
                null, null, null, null);
        if (c.moveToFirst()){
            int idColIndex = c.getColumnIndex(DataTable.COLUMN.ID);
            int numColIndex = c.getColumnIndex(DataTable.COLUMN.NUMBERS);

            do {
                Log.i(TAG, "workWithDB: id = " + c.getInt(idColIndex) + ", number = "
                        + c.getInt(numColIndex));
                listData.add(new Data(c.getInt(idColIndex), c.getInt(numColIndex)));
            }while (c.moveToNext());
        }else {
            Log.i(TAG, "workWithDB: Данных в базе нет.");
        }
        listData.add(null);
        mSqLiteDB.close();
    }

    public ArrayList<Data> getListData() {
        return listData;
    }
}
