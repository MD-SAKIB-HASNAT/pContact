package com.example.pcontact;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ContactCrud extends DBhelper{

    public ContactCrud(@Nullable Context context) {
        super(context);
    }

    public long insertContact(String name, String email, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_Name, name);
        cv.put(COL_Email,email);
        cv.put(COL_phone,phone);

        long result = db.insert(TAB_Contact,null,cv);

        db.close();
        return result;
    }
    public ArrayList<AllContact> readContacts(){
        ArrayList<AllContact> allContacts= new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("contact",null,null,null,null,null,null);

        while (cursor.moveToNext()){
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COL_Id));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COL_Name));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(COL_Email));
            @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(COL_phone));

            AllContact contact = new AllContact(name,id,phone,email);
            allContacts.add(contact);

        }
        cursor.close();

        db.close();
        return allContacts;
    }
}
