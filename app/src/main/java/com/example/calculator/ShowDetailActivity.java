package com.example.calculator;

import static com.example.calculator.R.id.cartB;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.calculator.Adaptor.PopularAdaptor1;
import com.example.calculator.Domain.FoodDomain;
import com.example.calculator.Hepler.ManagementCart;
import com.example.calculator.Hepler.ProductDao;
import com.example.calculator.Object.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView addToCartBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt;
    private ImageView plusBtn, minusBtn, picFood;
    private Product object;
    private RecyclerView recyclerViewGoiy;
    private RecyclerView.Adapter  adapter2;
    int numberOrder = 1;
    TextView dismissTxt, desTxt;
    String data, cateId;
    ArrayList<Product> a= new ArrayList<>();

    private ManagementCart managementCart;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart = new ManagementCart(this);
        initView();
        descriptionTxt = findViewById(R.id.descriptionTxt);
        Intent intent = getIntent();
        data = intent.getStringExtra("user");
        cateId = intent.getStringExtra("cateId");
        a = ProductDao.getItemcategory(ShowDetailActivity.this, cateId);

        getBundle();
        recyclerViewGoiY();
        bottomNavigation();
    }

    private void getBundle(){
       object = (Product) getIntent().getSerializableExtra("object");
       int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText("   "+object.getName());
        feeTxt.setText(object.getPrice()+"kđ");

//        descriptionTxt.setText(object.getDescription());

        if (descriptionTxt != null) {
        descriptionTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogDes();
            }
        });}
        numberOrderTxt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOrder = numberOrder +1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
                double num = (double)object.getPrice()*numberOrder;
                String numString = Double.toString(num);
                String decimalPart = numString.substring(0, numString.indexOf(".") + 2);
                feeTxt.setText(decimalPart+"kđ");
            }
        });
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numberOrder>1){
                    numberOrder = numberOrder -1;
                    double num = (double)object.getPrice()*numberOrder;
                    String numString = Double.toString(num);
                    String decimalPart = numString.substring(0, numString.indexOf(".") + 2);
                    feeTxt.setText(decimalPart+"kđ");
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);
            }
        });


    }

    private void initView(){
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberOrder);
        plusBtn  = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picFood = findViewById(R.id.picFood);
    }
    private void recyclerViewGoiY(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewGoiy = (RecyclerView) findViewById(R.id.recyclergoiy);
        recyclerViewGoiy.setLayoutManager(linearLayoutManager);


        ArrayList<FoodDomain> foodDomains = new ArrayList<>();




        for(Product p :a){

            foodDomains.add(new FoodDomain(p.getName()+"", p.getPic()+"", p.getDescrip(), p.getPrice()*100/100));
        }
        if(a.size()>1){
            adapter2 = new PopularAdaptor1(ShowDetailActivity.this,a);
        }else{
            adapter2 = new PopularAdaptor1(ShowDetailActivity.this,ProductDao.getPro(ShowDetailActivity.this));
        }



        recyclerViewGoiy.setAdapter(adapter2);
    }
    private void showDialogDes(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ShowDetailActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_description, null);
        builder.setView(view);
//        Dialog dialog = builder.create();
        Dialog dialog = new Dialog(ShowDetailActivity.this);
        dialog.setContentView(R.layout.dialog_description);
        dismissTxt = dialog.findViewById(R.id.dismissTxt);
        desTxt = dialog.findViewById(R.id.desTxt);
        dialog.show();


        if (dismissTxt != null) {
        dismissTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });}

        if (desTxt != null) {
        desTxt.setText(object.getDescrip());}

    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(cartB);
        LinearLayout homeBtn = findViewById(R.id.homeB);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(ShowDetailActivity.this, CartListActivity.class));
                Intent a = new Intent(ShowDetailActivity.this, CartListActivity.class);
                a.putExtra("user", data);
                startActivity(a);

            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(ShowDetailActivity.this, HomeActivity.class));

                Intent a = new Intent(ShowDetailActivity.this, HomeActivity.class);
                a.putExtra("user", data);
                startActivity(a);
            }
        });

    }
}