package com.example.android_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper  {

    public static final String DBName = "Database.db";
    public DB(Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table users(email Text primary key, username Text, password TEXT, phoneNumber TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String email, String username, String password, String phoneNumber){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("phoneNumber", phoneNumber);
        long result = sqLiteDatabase.insert("users", null, contentValues);
        if (result == 1) return true;
        else return false;
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor1 = sqLiteDatabase.rawQuery("Select * from users where email = ?", new String[] {email});
        if (cursor1.getCount() > 0) return true;
        else return false;
    }

    public Boolean checkUsernamePassword(String email, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[] {email, password});

        if (cursor.getCount() > 0) return true;
        else return false;
    }


}
