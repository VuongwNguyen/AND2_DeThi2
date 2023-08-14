package com.example.and_dethi2.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context, "QLSP", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qND = "create table User(Username text primary key,Password text, Fullname text)";
        sqLiteDatabase.execSQL(qND);

        String qSP = "create table Products(ID Integer primary key autoincrement ," +
                "nameSP text,PriceSP Integer,Amount Integer, Avatar text)";
        sqLiteDatabase.execSQL(qSP);

        String data = "insert into Products(nameSP ,PriceSP ,Amount , Avatar ) values('bánh quy bơ',20000,10,'ic_all_inclusive')," +
                "('Kẹo Dừa',5000,20,'ic_delete_24')," +
                "('Bánh Gạo',15000,35,'ic_edit_24')," +
                "('Bánh Ú',17000,40,'ic_person_24')," +
                "('Bánh Gạo',15000,35,'polytechnic')";
        sqLiteDatabase.execSQL(data);
        String User = "insert into User values ('Vuong','123','NguyenVuong')," +
                "('Vuong123','Vuong123','Vuong123')," +
                "('nqvuong','nqvuong','nqvuong')," +
                "('nqv','nqv','nqv')," +
                "('qvuong134','qvuong134','qvuong134');";
        sqLiteDatabase.execSQL(User);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists User");
        sqLiteDatabase.execSQL("drop table if exists Products");
    }
    public int Login(String User, String Pass) {
        int result = 0;
        String str[] = new String[2];
        str[0] = User;
        str[1] = Pass;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from User where Username=? and Password =?", str);
        if (cursor.moveToNext()) {
            result = 1;
        }
        return result;
    }
}
