package com.example.genya.testprojectuzhakhau.database;

public class DataTable {

    public static final String TABLE = "Data";

    public static class COLUMN {
        public static final String ID = "_id";
        public static final  String NUMBERS = "numbers";
    }

    public static final String CREATE_SCRIPT =
            String.format("create table %s ("
            + "%s integer primary key autoincrement,"
            + "%s integer);", TABLE, COLUMN.ID, COLUMN.NUMBERS);

}
