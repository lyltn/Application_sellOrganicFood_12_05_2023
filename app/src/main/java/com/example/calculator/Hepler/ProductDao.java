package com.example.calculator.Hepler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.calculator.Object.Category;
import com.example.calculator.Object.Product;

import java.util.ArrayList;

public class ProductDao  {

    public static ArrayList<Category> getCate(Context context){
        ArrayList<Category> arrShare = new ArrayList<>();
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.rawQuery("select * from Category", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            Category cate = new Category(cs.getInt(0), cs.getString(1)+"",cs.getString(2)+"",cs.getString(3)+"");
            arrShare.add(cate);
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return arrShare;
    }

    public static ArrayList<Product> getPro(Context context){
        ArrayList<Product> v1 = new ArrayList<>();

        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM Productt", null);
        cs.moveToFirst();

        while (!cs.isAfterLast()){
            Product i = new Product();
            i.setId(cs.getInt(0));
            i.setName(cs.getString(1));
            i.setDescrip(cs.getString(2));
            i.setPic(cs.getString(3));
            i.setQuantity(cs.getInt(4));
            i.setPrice(cs.getDouble(5));
            i.setIdCate(cs.getInt(6));
            v1.add(i);
            cs.moveToNext();

        }
//      name, descrip, pic, quantity, price, categoryid
        cs.close();
        db.close();
        return v1;
    }
    public static ArrayList<Product> findByName(Context context, String a){
        ArrayList<Product> v1 = new ArrayList<>();

        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM Productt where name LIKE ?", new String[] {"%" + a + "%"});
        cs.moveToFirst();

        while (!cs.isAfterLast()){
            Product i = new Product();
            i.setId(cs.getInt(0));
            i.setName(cs.getString(1));
            i.setDescrip(cs.getString(2));
            i.setPic(cs.getString(3));
            i.setQuantity(cs.getInt(4));
            i.setPrice(cs.getDouble(5));
            i.setIdCate(cs.getInt(6));
            v1.add(i);
            cs.moveToNext();

        }
//      name, descrip, pic, quantity, price, categoryid
        cs.close();
        db.close();
        return v1;
    }

    public static Product findById(Context context, String a){
        Product p = new Product();
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM Productt where id = ?", new String[] {a});
        cs.moveToFirst();
        if(!cs.isAfterLast()){
            p.setId(cs.getInt(0));
            p.setName(cs.getString(1));
            p.setDescrip(cs.getString(2));
            p.setPic(cs.getString(3));
            p.setQuantity(cs.getInt(4));
            p.setPrice(cs.getDouble(5));
            p.setIdCate(cs.getInt(6));
            return p;

        }
//      name, descrip, pic, quantity, price, categoryid
        cs.close();
        db.close();
        return null;
    }
    public static Product getP(Context context){
        Product i = new Product();
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM Productt", null);
        cs.moveToFirst();
        while (cs.moveToNext()){
            i.setId(cs.getInt(0));
            i.setName(cs.getString(1));
            i.setDescrip(cs.getString(2));
            i.setPic(cs.getString(3));
            i.setQuantity(cs.getInt(4));
            i.setPrice(cs.getDouble(5));
            i.setIdCate(cs.getInt(6));
        }
        return i;
    }
    public static boolean insertPro(Context context, String name, double price, String des, String pic, int quan, int cateid){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values  = new ContentValues();
        values.put("name", name);
        values.put("price", price);
        values.put("descrip", des);
        values.put("pic", pic);
        values.put("quantity",quan);
        values.put("categoryid",cateid);
        long row = db.insert("Productt", null, values);
        return (row>0);
    }

    public static ArrayList<Product> getItemcategory(Context context, String a){
        ArrayList<Product> v1 = new ArrayList<>();
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.rawQuery("select * from Productt where categoryid = ? ", new String[] { a });
        cs.moveToFirst();

        while (!cs.isAfterLast()){
            Product i = new Product();
            i.setId(cs.getInt(0));
            i.setName(cs.getString(1));
            i.setDescrip(cs.getString(2));
            i.setPic(cs.getString(3));
            i.setQuantity(cs.getInt(4));
            i.setPrice(cs.getDouble(5));
            i.setIdCate(cs.getInt(6));
            v1.add(i);
            cs.moveToNext();

        }
//      name, descrip, pic, quantity, price, categoryid
        cs.close();
        db.close();
        return v1;
    }


}
