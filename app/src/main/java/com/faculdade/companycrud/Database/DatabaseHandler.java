package com.faculdade.companycrud.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "CompanyDatabase";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE companys " +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "cnpj TEXT, "+
                "razao TEXT, " +
                "phone TEXT, " +
                "value TEXT, " +
                "inscricao TEXT)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS companys";
        db.execSQL(sql);

        onCreate(db);
    }

}