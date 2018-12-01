package com.faculdade.companycrud.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.faculdade.companycrud.Models.Company;

import java.util.ArrayList;
import java.util.List;

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

    public List<Company> read() {

        List<Company> recordsList = new ArrayList<Company>();

        String sql = "SELECT * FROM companys ORDER BY id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String companyName = cursor.getString(cursor.getColumnIndex("name"));
                String companyCNPJ = cursor.getString(cursor.getColumnIndex("cnpj"));
                String companyValue = cursor.getString(cursor.getColumnIndex("value"));
                String companyInscricao = cursor.getString(cursor.getColumnIndex("inscricao"));
                String companyRazao = cursor.getString(cursor.getColumnIndex("razao"));
                String companyPhone = cursor.getString(cursor.getColumnIndex("phone"));


                Company company = new Company();
                company.setId(id);
                company.setName(companyName);
                company.setValue(companyValue);
                company.setRazao(companyRazao);
                company.setPhone(companyPhone);
                company.setInscricao(companyInscricao);
                company.setCnpj(companyInscricao);



                recordsList.add(company);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }
}
