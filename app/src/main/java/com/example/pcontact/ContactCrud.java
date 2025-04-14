package com.example.pcontact;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

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

}
