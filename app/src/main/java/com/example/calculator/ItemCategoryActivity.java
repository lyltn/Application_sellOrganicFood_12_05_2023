package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculator.Adaptor.ItemListAdapter;
import com.example.calculator.Hepler.ProductDao;
import com.example.calculator.Object.Product;

import java.util.ArrayList;

public class ItemCategoryActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    String data, data1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_category);
        Intent intent = getIntent();
        data1 = intent.getStringExtra("cateId");
        data = intent.getStringExtra("name");
        initView();
        if(data1 == null){
            initList(ProductDao.findByName(ItemCategoryActivity.this, data) );
        }else{
            initList(ProductDao.getItemcategory(ItemCategoryActivity.this, data1));
        }

    }
    private void initView(){
        recyclerViewList = findViewById(R.id.itemRecy);
    }
    private void initList(ArrayList<Product> d ){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new ItemListAdapter(ItemCategoryActivity.this, d );
        recyclerViewList.setAdapter(adapter);
    }

}