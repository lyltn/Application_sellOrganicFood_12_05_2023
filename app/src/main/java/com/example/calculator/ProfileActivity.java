package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculator.Adaptor.OrderAdaptor;
import com.example.calculator.Hepler.CheckOutDao;
import com.example.calculator.Hepler.UserDao;
import com.example.calculator.Object.Order;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    TextView name, mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");

        initView();
        initList(CheckOutDao.getOrder(ProfileActivity.this, user));
        name.setText(user);
        mail.setText(UserDao.getUser(ProfileActivity.this, user));

    }
    private void initView(){
        recyclerViewList = findViewById(R.id.orderRecy);
        name = findViewById(R.id.nameuser);
        mail = findViewById(R.id.mailuser);
    }
    private void initList(ArrayList<Order> d ){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new OrderAdaptor(ProfileActivity.this, d );
        recyclerViewList.setAdapter(adapter);
    }

}