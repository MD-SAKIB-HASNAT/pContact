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

    public AllContact readContact( int id ){
        AllContact allContact = new AllContact();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("contact",null,"id = ?", new String[]{id+""} ,null,null,null);

        while (cursor.moveToNext()){
            //@SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COL_Id));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COL_Name));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(COL_Email));
            @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(COL_phone));

            allContact = new AllContact(name,id,phone,email);

        }
        cursor.close();

        db.close();
        return allContact;
    }
    public boolean readContactbyName(String pname){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("contact",null,"name = ?", new String[]{pname} ,null,null,null);
        String newName="";
        while (cursor.moveToNext()){
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COL_Name));
            newName = name;
        }
        cursor.close();

        db.close();
        if(newName.equals(pname)) return true;
        else return false;
    }
    public boolean readContactbyEmail(String pemail){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("contact",null,"email = ?", new String[]{pemail} ,null,null,null);
        String newEmail="";
        while (cursor.moveToNext()){
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(COL_Email));
            newEmail = email;
        }
        cursor.close();

        db.close();
        if(newEmail.equals(pemail)) return true;
        else return false;
    }

    public boolean readContactbyPhone(String pphone){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("contact",null,"name = ?", new String[]{pphone} ,null,null,null);
        String newPhone="";
        while (cursor.moveToNext()){
            @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(COL_phone));
            newPhone = phone;
        }
        cursor.close();

        db.close();
        if(newPhone.equals(pphone)) return true;
        else return false;
    }
    public long updateContact(AllContact ac){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_Id,ac.getId());
        cv.put(COL_Name, ac.getcName());
        cv.put(COL_Email,ac.getcEmail());
        cv.put(COL_phone,ac.getcPhone());

        long result=  db.update(TAB_Contact,cv,"id = ?",new String[]{ac.getId()+""});

        db.close();
        return result;
    }

    public long deleteContact(int id){
        SQLiteDatabase db = getWritableDatabase();
        long result = db.delete(TAB_Contact,"id = ?",new String[]{id+""});
        db.close();
        return result;
    }
}
