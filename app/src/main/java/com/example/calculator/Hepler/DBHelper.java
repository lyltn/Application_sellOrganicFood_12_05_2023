package com.example.calculator.Hepler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context, "organic", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "Create table User ( userid INTEGER primary key autoincrement, "+
                "name text, phonenumber text, username text, pass text, mail text)";
        sqLiteDatabase.execSQL(sql);

        String sqlCate = "Create table Category (cateid INTEGER primary key autoincrement, name text, pic text, background text)";
        sqLiteDatabase.execSQL(sqlCate);
        String addCate = "Insert into Category (name, pic, background) values ( 'Eggplant', 'cat_1', 'cat_background1')";
        sqLiteDatabase.execSQL(addCate);
        String addCate1 = "Insert into Category (name, pic, background)  values ( 'Potato', 'khoaitay', 'cat_background2')";
        sqLiteDatabase.execSQL(addCate1);
        String addCate2 = "Insert into Category (name, pic, background)  values ('Tomato', 'cachua', 'cat_background3')";
        sqLiteDatabase.execSQL(addCate2);
        String addCate3 = "Insert into Category (name, pic, background)  values ( 'Cauliflower', 'can', 'cat_background4')";
        sqLiteDatabase.execSQL(addCate3);
        String addCate4 = "Insert into Category (name, pic, background)  values ( 'Cabbage', 'su', 'cat_background4')";
        sqLiteDatabase.execSQL(addCate4);
        String addCate5 = "Insert into Category (name, pic, background)  values ( 'Carrot', 'carot', 'cat_background4')";
        sqLiteDatabase.execSQL(addCate5);
        String sqlRo = "CREATE TABLE Productt (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name TEXT,\n" +
                "    descrip TEXT,\n" +
                "    pic TEXT,\n" +
                "    quantity INTEGER,\n" +
                "    price REAL,\n" +
                "    categoryid INTEGER,\n" +
                "     FOREIGN KEY (categoryid) REFERENCES Category(cateid)" +
                ")";
        sqLiteDatabase.execSQL(sqlRo);
        String addPro1 = "INSERT INTO Productt (name, descrip, pic, quantity, price, categoryid) VALUES ('Cauliflower', 'cat_background4 hjjhj jghjgjgjghg hgg slices pepproni, mazzerella cheese, fresh aregano, ground black peper, pizza sauce slices pepproni, mazzerella cheese, fresh aregano, ground black peper, pizza sauce', 'e1', 33, 67.9, 1)";
        sqLiteDatabase.execSQL(addPro1);
        String addPro2 = "INSERT INTO Productt (name, descrip, pic, quantity, price, categoryid) VALUES ('Caulifl', 'cat_background4 hjjhj jghjgjgjghg hgg slices pepproni, mazzerella cheese, fresh aregano, ground black peper, pizza sauce pota1 slices pepproni, mazzerella cheese, fresh aregano, ground black peper, pizza sauce','pota1', 13, 67.6, 2)";
        sqLiteDatabase.execSQL(addPro2);
        String addPro3 = "INSERT INTO Productt (name, descrip, pic, quantity, price, categoryid) VALUES ('Tdgdfgdfg', 'slices pepproni, mazzerella cheese, fresh aregano, ground black peper, pizza sauce,slices pepproni, mazzerella cheese, fresh aregano, ground black peper, pizza sauce" +
                "slices pepproni, mazzerella cheese, fresh aregano, ground black peper, pizza sauce" +
                "slices pepproni, mazzerella cheese, fresh aregano, ground black peper, pizza sauce', 'pota2', 13, 24.6, 2)";
        sqLiteDatabase.execSQL(addPro3);
        String addPro4 = "INSERT INTO Productt (name, descrip, pic, quantity, price, categoryid) VALUES ('uu', 'Lứa ổi sau được anh tiến hành chăm sóc một cách chặt chẽ, tỉ mỉ theo sự phát triển của trái. Anh kiên quyết nói không với việc dùng phân bón hóa học, thuốc bảo vệ thực vật. Hầu như vườn ổi của anh đều sử dụng phân hữu cơ.\n" +
                "Để quả ổi cho chất lượng tốt, anh thăm vườn thường xuyên, bón phân đều đặn và cắt tỉa cành, hạn chế sâu bệnh. Anh đã ra Hòa Bình mua túi bọc chuyên dụng, trái ổi khi lớn bằng ngón chân cái anh bọc bao bên ngoài cho đến khi thu hoạch để giảm thiểu tối đa các loại sâu phá hoại. ', 'toma2', 13, 4.6, 3)";
        sqLiteDatabase.execSQL(addPro4);
        String addPro5 = "INSERT INTO Productt (name, descrip, pic, quantity, price, categoryid) VALUES ('hh', 'slices pepproni, mazzerella cheese, fresh aregano, ground black peper, pizza sauce slices pepproni, mazzerella cheese, fresh aregano, ground black peper, pizza sauce', 'pop_2', 13, 2.6, 4)";
        sqLiteDatabase.execSQL(addPro5);
        String addPro6 = "INSERT INTO Productt (name, descrip, pic, quantity, price, categoryid) VALUES ('uu', 'Lứa ổi sau được anh tiến hành chăm sóc một cách chặt chẽ, tỉ mỉ theo sự phát triển của trái. Anh kiên quyết nói không với việc dùng phân bón hóa học, thuốc bảo vệ thực vật. Hầu như vườn ổi của anh đều sử dụng phân hữu cơ.\n" +
                "Để quả ổi cho chất lượng tốt, anh thăm vườn thường xuyên, bón phân đều đặn và cắt tỉa cành, hạn chế sâu bệnh. Anh đã ra Hòa Bình mua túi bọc chuyên dụng, trái ổi khi lớn bằng ngón chân cái anh bọc bao bên ngoài cho đến khi thu hoạch để giảm thiểu tối đa các loại sâu phá hoại. ', 'pota3', 13, 4.6, 2)";
        sqLiteDatabase.execSQL(addPro6);
        String addPro7 = "INSERT INTO Productt (name, descrip, pic, quantity, price, categoryid) VALUES ('cate3', 'Lứa ổi sau được anh tiến hành chăm sóc một cách chặt chẽ, tỉ mỉ theo sự phát triển của trái. Anh kiên quyết nói không với việc dùng phân bón hóa học, thuốc bảo vệ thực vật. Hầu như vườn ổi của anh đều sử dụng phân hữu cơ.\n" +
                "Để quả ổi cho chất lượng tốt, anh thăm vườn thường xuyên, bón phân đều đặn và cắt tỉa cành, hạn chế sâu bệnh. Anh đã ra Hòa Bình mua túi bọc chuyên dụng, trái ổi khi lớn bằng ngón chân cái anh bọc bao bên ngoài cho đến khi thu hoạch để giảm thiểu tối đa các loại sâu phá hoại. ', 'toma1', 13, 4.6, 3)";
        sqLiteDatabase.execSQL(addPro7);
        String addPro8 = "INSERT INTO Productt (name, descrip, pic, quantity, price, categoryid) VALUES ('Trân', 'Lứa ổi sau được anh tiến hành chăm sóc một cách chặt chẽ, tỉ mỉ theo sự phát triển của trái. Anh kiên quyết nói không với việc dùng phân bón hóa học, thuốc bảo vệ thực vật. Hầu như vườn ổi của anh đều sử dụng phân hữu cơ.\n" +
                "Để quả ổi cho chất lượng tốt, anh thăm vườn thường xuyên, bón phân đều đặn và cắt tỉa cành, hạn chế sâu bệnh. Anh đã ra Hòa Bình mua túi bọc chuyên dụng, trái ổi khi lớn bằng ngón chân cái anh bọc bao bên ngoài cho đến khi thu hoạch để giảm thiểu tối đa các loại sâu phá hoại. ', 'toma1', 13, 4.6, 1)";
        sqLiteDatabase.execSQL(addPro8);
//        String addPro2 = "INSERT INTO Productt VALUES (null, 'Cau', 68, 'cat_background4 hjjhj jghjgjgjghg hgg', 'pop_2', 33, 1)";
//        sqLiteDatabase.execSQL(addPro2);
//        String addPro3 = "INSERT INTO Productt VALUES (null, 'Caulif', 63, 'cat_background4 hjjhj jghjgjgjghg hgg', 'pop_3', 33, 1)";
//        sqLiteDatabase.execSQL(addPro3);
        String order = "CREATE TABLE Orders (idorder INTEGER primary key autoincrement, username text, namere text, phone text, address text, note text, date text, total real, status integer)";
        sqLiteDatabase.execSQL(order);
        String order_details = "create table Order_details (iddetail INTEGER primary key autoincrement, idorder integer, productid integer, quantity integer, total real)";
        sqLiteDatabase.execSQL(order_details);
        String discount = "create table voucher (id INTEGER primary key autoincrement, name text, discount real)";
        sqLiteDatabase.execSQL(discount);
        String add5 = "Insert into voucher (name, discount)  values ( 'LYLY', 0.3)";
        sqLiteDatabase.execSQL(add5);
        String add0 = "Insert into voucher (name, discount)  values ( 'ORGANICBD', 0.4)";
        sqLiteDatabase.execSQL(add0);
        String add7 = "Insert into voucher (name, discount)  values ( 'TTT', 0.1)";
        sqLiteDatabase.execSQL(add7);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists Category");
        sqLiteDatabase.execSQL("Drop table if exists Productt");
        sqLiteDatabase.execSQL("Drop table if exists voucher");
        onCreate(sqLiteDatabase);
    }

}