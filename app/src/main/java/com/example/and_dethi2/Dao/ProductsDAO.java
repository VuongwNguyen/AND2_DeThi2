package com.example.and_dethi2.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.and_dethi2.Database.DBhelper;
import com.example.and_dethi2.Model.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductsDAO {
    private final DBhelper dbHelper;

    public ProductsDAO(Context context) {
        dbHelper = new DBhelper(context);
    }

    //    hàm hiển thị
    public List<Products> getListPD() {
        List<Products> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try {
            Cursor cursor = database.rawQuery("select * from Products", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    list.add(new Products(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getInt(3),
                            cursor.getString(4)));
                } while (cursor.moveToNext());
                database.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.e("Error", "getListPD(): " + e);
        }
        database.endTransaction();
        return list;
    }

    public boolean deleteProducts(int ID) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long check = database.delete("Products", "ID=?", new String[]{String.valueOf(ID)});
        return check != -1;
    }

    //    hàm sửa
    public boolean updateProducts(Products product) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nameSP", product.getName());
        values.put("PriceSP", String.valueOf(product.getPrice()));
        values.put("Amount", String.valueOf(product.getAmount()));
        values.put("Avatar", product.getAvatar());

        long check = database.update("Products", values, "ID=?", new String[]{String.valueOf(product.getID())});
        return check != -1;
    }
}
