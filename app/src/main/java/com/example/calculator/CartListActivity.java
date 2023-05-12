package com.example.calculator;

import static com.example.calculator.R.id.CartBtn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculator.Adaptor.CartListAdapter;
import com.example.calculator.Hepler.CheckOutDao;
import com.example.calculator.Hepler.ManagementCart;
import com.example.calculator.Interface.ChangeNumberItemsListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView totalFeeTxt, disCountTxt, deliveryServicesTxt, totalTxt, emptyTxt, count, checkOutBtn;
    private RecyclerView.LayoutManager mLayoutManager;
    private double discount, percentDiscount= 0;
    private ScrollView scrollView;
    String data;
    private EditText enterdis;
    double total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        Intent intent = getIntent();
        data = intent.getStringExtra("user");
        managementCart = new ManagementCart(this);
        initView();
        initList();
        CalculateCart();
        bottomNavigation();
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                percentDiscount = CheckOutDao.getDiscount(CartListActivity.this, enterdis.getText().toString());
                discount = (double) (managementCart.getTotalFee()*percentDiscount) ;
                total =(double) (managementCart.getTotalFee()- discount + 30) ;
                disCountTxt.setText(Double.toString(discount).substring(0, Double.toString(discount).indexOf(".") + 2)+"kđ");
                totalTxt.setText( Double.toString(total).substring(0, Double.toString(total).indexOf(".") + 2)+"kđ");
            }
        });
        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                data = intent.getStringExtra("user");
                Intent a = new Intent(CartListActivity.this, CheckOutActivity.class);
                a.putExtra("user", data);
                a.putExtra("quan", data);
                a.putExtra("total", Double.toString(total).substring(0, Double.toString(total).indexOf(".") + 2));
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
                Intent a = new Intent(CartListActivity.this, CartListActivity.class);
                a.putExtra("user", data);
                startActivity(a);

            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                Intent a = new Intent(CartListActivity.this, HomeActivity.class);
                a.putExtra("user", data);
                startActivity(a);
            }
        });
        ProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(CartListActivity.this, ProfileActivity.class);
                a.putExtra("user", data);
                startActivity(a);
            }
        });
        SettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(CartListActivity.this, MainActivity2.class);
                a.putExtra("user", data);
                startActivity(a);
            }
        });
        SupportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(CartListActivity.this, MailActivity.class);
                a.putExtra("user", data);
                startActivity(a);
            }
        });

    }

    private void initView(){
        recyclerViewList = findViewById(R.id.recyclerView);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        disCountTxt = findViewById(R.id.discountTxt);
        deliveryServicesTxt = findViewById(R.id.deliveryServicesTxt);
        totalTxt = findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView3);
        recyclerViewList = findViewById(R.id.CartView);
        enterdis = findViewById(R.id.enterdis);
        count = findViewById(R.id.discount);
        checkOutBtn = findViewById(R.id.checkOutBtn);
    }
    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerViewList.setLayoutManager(linearLayoutManager);
//        recyclerViewList.setHasFixedSize(true);
//
//        mLayoutManager = new GridLayoutManager(this, 3);
//        recyclerViewList.setLayoutManager(mLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if(managementCart.getListCart().isEmpty()){
//            emptyTxt.setVisibility(View.VISIBLE);
//            scrollView.setVisibility(View.GONE);
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private void CalculateCart(){
        double delivery = 30;
        discount = (double) (managementCart.getTotalFee()*percentDiscount) ;
         total =(double) (managementCart.getTotalFee()- discount + 30) ;
        double iTermTotal = (double) managementCart.getTotalFee() ;
//        double num = (double)object.getPrice()*numberOrder;

        totalFeeTxt.setText(Double.toString(iTermTotal).substring(0, Double.toString(iTermTotal).indexOf(".") + 2)+"kđ");
        disCountTxt.setText(Double.toString(discount).substring(0, Double.toString(discount).indexOf(".") + 2)+"kđ");
        deliveryServicesTxt.setText(+delivery+"kđ");
        totalTxt.setText( Double.toString(total).substring(0, Double.toString(total).indexOf(".") + 2)+"kđ");

    }
}