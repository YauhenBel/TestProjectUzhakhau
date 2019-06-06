package com.example.genya.testprojectuzhakhau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class SQLiteDB extends SQLiteOpenHelper{

    public static final String NAME = "testproject";
    public static final int VERSION = 1;

    public SQLiteDB(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DataTable.CREATE_SCRIPT);
        Log.i(TAG, "onCreate: CreateDB");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
