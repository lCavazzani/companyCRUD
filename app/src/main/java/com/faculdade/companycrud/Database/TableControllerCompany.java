package com.faculdade.companycrud.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.faculdade.companycrud.Models.Company;

public class TableControllerCompany extends DatabaseHandler {

    public TableControllerCompany(Context context) {
        super(context);
    }
    public boolean create(Company company) {

        ContentValues values = new ContentValues();

        values.put("name", company.getName());
        values.put("cnpj", company.getCnpj());
        values.put("razao", company.getRazao());
        values.put("phone", company.getPhone());
        values.put("value", company.getValue());
        values.put("inscricao", company.getInscricao());


        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("companys", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM companys";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;

    }
}
