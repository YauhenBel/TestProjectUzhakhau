package com.example.genya.testprojectuzhakhau.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
    private ContentValues mCv;
    private Editor editor;
    private String [] mSelectionArgs, mColumns;
    public String FLAG_NUMBER = "number";


    @SuppressLint("CommitPrefEdits")
    public MainActivityModel(Context context, SharedPreferences preferences) {
        this.mContext = context;
        mPref = preferences;
        editor = mPref.edit();
    }

    //работа с БД - подключение, заполнение данными, загрузка данных в приложение
    public void workWithDB(){
        Log.i(TAG, "workWithDB");
        connToDB();
        fillDB();
        getData();
        closeConnection();


    }

    //подключаемся к БД
    public void connToDB(){
        mSqLiteDB = new SQLiteDB(mContext);
        mDB = mSqLiteDB.getWritableDatabase();
        mCv = new ContentValues();
    }

    //при первом запуске приложения заполняем БД тестовыми данными
    private void fillDB() {
        if (!mPref.getBoolean("isDB", false)) {
            Log.i(TAG, "fillDB: !mPref.getBoolean(\"isDB\", false)");
            for (int i = 0; i < 20; i++) {
                addDataInDB();
            }
            editor.putBoolean("isDB", true);
            editor.putInt(FLAG_NUMBER, 20);
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
            if (mPref.getInt(FLAG_NUMBER, 0) == 0){
                Log.i(TAG, "getData: " + listData.get(listData.size()-1).getNumber());
                editor.putInt(FLAG_NUMBER, listData.get(listData.size()-1).getNumber());
                editor.apply();
            }
        }else {
            Log.i(TAG, "workWithDB: Данных в базе нет.");
        }
    }

    public ArrayList<Data> getListData() {
        return listData;
    }

    public long addDataInDB(){
        int number = mPref.getInt("number", 0);
        Log.i(TAG, "addDataInDB: number = " + number);
        mCv.put(DataTable.COLUMN.NUMBERS, number);
        long lastID = mDB.insert(DataTable.TABLE, null, mCv);
        number++;
        editor.putInt(FLAG_NUMBER, number);
        editor.apply();
        return lastID;
    }

    public String doQueryToDB(long id){
        mSelectionArgs = new String [] {String.valueOf(id)};
        mColumns = new String[] {DataTable.COLUMN.NUMBERS};
        Cursor c = mDB.query(DataTable.TABLE, mColumns,
                DataTable.COLUMN.ID + " = ?", mSelectionArgs,
                null, null, null);
        if (c.moveToFirst()){
            int numColIndex = c.getColumnIndex(DataTable.COLUMN.NUMBERS);
            Log.i(TAG, "workWithDB:  number = "
                    + c.getInt(numColIndex));
            return id + " " + c.getInt(numColIndex);
        }

        return "-1";

    }

    public void closeConnection(){
        mSqLiteDB.close();
        mDB.close();
    }

    public void delDataFromDB(int id){
       mDB.delete(DataTable.TABLE, DataTable.COLUMN.ID + "=" + id, null);
    }
}
