package com.example.pcontact;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBhelper extends SQLiteOpenHelper {
    public static final String DB_Name = "my_db";
    public static final String TAB_Contact = "contact";
    public static final String COL_Name = "name";
    public static final String COL_Email = "email";
    public static final String COL_phone = "phone";


    public DBhelper(@Nullable Context context) {
        super(context, DB_Name, null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table if not exists contact(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, phone TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
