package com.ue.proyecto_final.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ManageDB extends SQLiteOpenHelper {

    public static final String DBNAME = "login.db";
    public ManageDB(Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(username TEXT primary key, password TEXT, name TEXT, lastname TEXT, document TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
    }

    public String getuser(String username){
        String name;
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=?", new String[] {username});
        name = cursor.getString(3);
        return name;
    }

    public Boolean insertData(String username, String password, String name, String lastname, String document){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("password", password);
        values.put("name", name);
        values.put("lastname", lastname);
        values.put("document", document);

        long result= db.insert("users", null, values);
        if (result==-1) return false;
        else
            return true;
    }
    public Boolean checkusername(String username) {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=?", new String[] {username});
        if (cursor.getCount()>0) {
            return true;
        }else {
            return false;
        }
    }
    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[] {username,password});
        if (cursor.getCount()>0) {
            return true;
        }else {
            return false;
        }
    }
}
