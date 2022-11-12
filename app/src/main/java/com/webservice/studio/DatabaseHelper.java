package com.webservice.studio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "register.db";
    public static final String TABLE_NAME = "USERS";
    public static final String USER_ID = "USER_ID";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USERS (USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public long registerUser(String email, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL, email);
        values.put(PASSWORD,password);
        long result = db.insert(TABLE_NAME,null,values);
        db.close();
        return result;
    }

    public boolean isUserRegistered(String email, String password)
    {
        String[] columns = {USER_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = EMAIL+"=? and "+PASSWORD+"=?";
        String[] selectionArgs = {email,password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

		/*
		Count>0 means that user data is present in the database
		 */
        if(count>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
