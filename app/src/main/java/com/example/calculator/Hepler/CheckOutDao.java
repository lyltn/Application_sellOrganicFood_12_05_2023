package com.example.calculator.Hepler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.calculator.Object.Order;
import com.example.calculator.Object.OrderDetail;

import java.util.ArrayList;
import java.util.Calendar;

public class CheckOutDao {
    public static double getDiscount(Context context, String a){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.rawQuery("select * from voucher where name = ? ", new String[] { a });
        cs.moveToFirst();

        while (!cs.isAfterLast()){
            return cs.getDouble(2);

        }
        cs.close();
        db.close();
        return 0;
    }

    public static int getIdOrder(Context context, String username, String address){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.rawQuery("select idorder from Orders where username = ?  and address = ?", new String[] {username, address });
        cs.moveToFirst();
        if(!cs.isAfterLast()){
            return cs.getInt(0);
        }
        return 100;
    }

    public static boolean insertOrders(Context context,String username, String name, String phone, String address, String note, double total){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values  = new ContentValues();
        values.put("username", username);
        values.put("namere", name);
        values.put("phone", phone);
        values.put("address", address);
        values.put("note", note);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // Lưu ý: tháng bắt đầu từ 0
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        values.put("date", dayOfMonth + "/" + (month+1) + "/" + year);
        values.put("total", total);
        values.put("status", 1);
        long row = db.insert("Orders", null, values);
        return (row>0);
    }

    public static boolean insertOrdetail(Context context, int idorder, int idproduct, int quantity, double total){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values  = new ContentValues();
        values.put("idorder", idorder);
        values.put("productid", idproduct);
        values.put("quantity", quantity);
        values.put("total", total);
        long row = db.insert("Order_details", null, values);
        return (row>0);
    }

    public static ArrayList<Order> getOrder(Context context, String a){
        ArrayList<Order> v1 = new ArrayList<>();
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM Orders where username = ? ", new String[]{a});
        cs.moveToFirst();

        while (!cs.isAfterLast()){
            Order i = new Order();
            i.setId(cs.getInt(0));
            i.setUserName(cs.getString(1));
            i.setNameRe(cs.getString(2));
            i.setPhone(cs.getString(3));
            i.setAddress(cs.getString(4));
            i.setNote(cs.getString(5));
            i.setDate(cs.getString(6));
            i.setTotal(cs.getDouble(7));
            i.setStatus(cs.getInt(8));
            v1.add(i);
            cs.moveToNext();

        }
//      name, descrip, pic, quantity, price, categoryid
        cs.close();
        db.close();
        return v1;
    }

    public static ArrayList<OrderDetail> getOrderDetail(Context context, String a){
        ArrayList<OrderDetail> v1 = new ArrayList<>();
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM Order_details where idorder = ? ", new String[]{a});
        cs.moveToFirst();

        while (!cs.isAfterLast()){
            OrderDetail i = new OrderDetail();
            i.setId(cs.getInt(0));
            i.setIdOrder(cs.getInt(1));
            i.setIdProduct(cs.getInt(2));
            i.setQuantity(cs.getInt(3));
            i.setTotal(cs.getDouble(4));
            v1.add(i);
            cs.moveToNext();

        }
//      name, descrip, pic, quantity, price, categoryid
        cs.close();
        db.close();
        return v1;
    }

    public static boolean deleteOrder(Context context, int a){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        int row = db.delete("Orders", "idorder=?", new String[]{a+""});
        return (row>0);
    }
}
