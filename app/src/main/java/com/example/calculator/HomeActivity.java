package com.example.calculator;

import static com.example.calculator.R.id.CartBtn;
import static com.example.calculator.R.id.recyclerView;
import static com.example.calculator.R.id.recyclerView2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculator.Adaptor.CategoryAdaptor;
import com.example.calculator.Adaptor.PopularAdaptor;
import com.example.calculator.Domain.FoodDomain;
import com.example.calculator.Hepler.ProductDao;
import com.example.calculator.Object.Category;
import com.example.calculator.Object.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    private RecyclerView.LayoutManager mLayoutManager;
    private EditText findTxt;
     TextView usernn;
    ArrayList<Product> a= new ArrayList<>();
//    boolean addPro;
    Product a1;
//    static DBHelper helper ;
//    static SQLiteDatabase db ;
    String data;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        data = intent.getStringExtra("user");
        usernn = findViewById(R.id.usernn);
        usernn.setText(data);
        findTxt = findViewById(R.id.findTxt);

//        Toast.makeText(HomeActivity.this, data, Toast.LENGTH_SHORT).show();
        a = ProductDao.getPro(HomeActivity.this);
//        helper = new DBHelper(HomeActivity.this);
//        db = helper.getReadableDatabase();
        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
        findTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(HomeActivity.this, ItemCategoryActivity.class);
                a.putExtra("name", findTxt.getText().toString());
                startActivity(a);
            }
        });


    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(CartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeB);
        LinearLayout ProfileBtn = findViewById(R.id.ProfileBtn);
        LinearLayout SettingsBtn = findViewById(R.id.SettingsBtn);
        LinearLayout SupportBtn = findViewById(R.id.SupportBtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(HomeActivity.this, CartListActivity.class));
                Intent a = new Intent(HomeActivity.this, CartListActivity.class);
                a.putExtra("user", data);
                startActivity(a);

            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                Intent a = new Intent(HomeActivity.this, HomeActivity.class);
                a.putExtra("user", data);
                startActivity(a);
            }
        });
        ProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(HomeActivity.this, ProfileActivity.class);
                a.putExtra("user", data);
                startActivity(a);
            }
        });
        SettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(HomeActivity.this, MainActivity2.class);
                a.putExtra("user", data);
                startActivity(a);
            }
        });
        SupportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(HomeActivity.this, MailActivity.class);
                a.putExtra("user", data);
                startActivity(a);
            }
        });


    }
    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(recyclerView);

        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<Category> categoryDomains = new ArrayList<>();
        categoryDomains = ProductDao.getCate(HomeActivity.this);
//        for(Category c : categoryDomains){
//            categoryDomains.add(new Category(1, "Eggplant", "cat_1", "cat_background1"));
//        }
//        categoryDomains.add(new Category(1, "Eggplant", "cat_1", "cat_background1"));


        adapter = new CategoryAdaptor(HomeActivity.this, categoryDomains);
        recyclerViewCategoryList.setAdapter(adapter);
    }





    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(recyclerView2);
//        recyclerViewPopularList.setLayoutManager(linearLayoutManager);
        recyclerViewCategoryList.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(this, 2);
        recyclerViewPopularList.setLayoutManager(mLayoutManager);

        ArrayList<FoodDomain> foodDomains = new ArrayList<>();




        for(Product p :a){

            foodDomains.add(new FoodDomain(p.getName()+"", p.getPic()+"", p.getDescrip(), p.getPrice()*100/100));
        }


        adapter2 = new PopularAdaptor(HomeActivity.this,a);
        recyclerViewPopularList.setAdapter(adapter2);
    }

//    public static ArrayList<Category> getCate(){
//        ArrayList<Category> arrShare = new ArrayList<>();
//
//        Cursor cs = db.rawQuery("select * from Category", null);
//        cs.moveToFirst();
//        while (!cs.isAfterLast()){
//            Category cate = new Category(cs.getInt(0), cs.getString(1)+"",cs.getDouble(2)+"",cs.getDouble(3)+"");
//            arrShare.add(cate);
//            cs.moveToNext();
//
//        }
//        cs.close();
//        db.close();
//        return arrShare;
//    }
}