package com.example.calculator.Hepler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDao {
    public static boolean insert(Context context, String name, String phone, String username, String pass, String mail){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values  = new ContentValues();
        values.put("name", name);
        values.put("phonenumber", phone);
        values.put("username", username);
        values.put("pass", pass);
        values.put("mail", mail);
        long row = db.insert("User", null, values);
        return (row>0);
    }

    public static boolean getUser(Context context, String user, String pass){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.rawQuery("select * from User where username = ? ", new String[]{user});
        Cursor cs1 = db.rawQuery("select * from User where   pass = ?", new String[]{ pass});
        cs.moveToFirst();
        if(!cs.isAfterLast() && !cs1.isAfterLast()){
            String ten  = cs.getString(1);
            return true;
        }else{
            return false;
        }


    }

    public static String getUser(Context context, String user){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.rawQuery("select * from User where username = ? ", new String[]{user});
        cs.moveToFirst();
        if(!cs.isAfterLast() ){
           return cs.getString(5);

        }else{
            return "";
        }


    }

}
