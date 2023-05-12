package com.example.calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.calculator.Hepler.ManagementCart;
import com.example.calculator.Hepler.UserDao;

public class MainActivity2 extends AppCompatActivity {
    private TextView tvsignup;
    AppCompatButton log;
    EditText user, pass;
    private ManagementCart managementCart;
    boolean a;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        user = findViewById(R.id.UserI);
        pass = findViewById(R.id.PassI);
        tvsignup = findViewById(R.id.tvsignup);
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, ActivitySignUp.class));
            }
        });
       log = findViewById(R.id.btnLog);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (UserDao.getUser(MainActivity2.this, user.getText().toString(), pass.getText().toString())){
                    managementCart = new ManagementCart(MainActivity2.this);
                    managementCart.deleteListCart();
                    Intent a = new Intent(MainActivity2.this, HomeActivity.class);
                    a.putExtra("user", user.getText().toString());
                    startActivity(a);
                }else{
                    Toast.makeText(MainActivity2.this, "SignUp Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}