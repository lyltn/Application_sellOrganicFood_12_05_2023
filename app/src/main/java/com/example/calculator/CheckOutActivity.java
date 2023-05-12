package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculator.Hepler.CheckOutDao;
import com.example.calculator.Hepler.ManagementCart;
import com.example.calculator.Hepler.UserDao;
import com.example.calculator.Mail.JavaMailAPI;
import com.example.calculator.Object.Product;

import java.util.ArrayList;

public class CheckOutActivity extends AppCompatActivity {
    EditText name, phone, address, note;
    TextView comfirm;
    String user, data;
    double total;
    private ManagementCart managementCart;
    private ArrayList<Product> listCart;
    String[] items =  {"Đà Nẵng",
            "Thành phố Hồ Chí Minh",
            "Hà Nội",
            "An Giang","Bà Rịa – Vũng Tàu",
            "Bạc Liêu","Bắc Giang",
            "Bắc Kạn","Bắc Ninh","Bến Tre","Bình Dương",
            "Bình Định",
            "Cà Mau",
            "Cần Thơ",
            "Hà Giang",
            "Khánh Hòa",
            "Long An",
            "Nghệ An",
            "Yên Bái",
            "Vĩnh Phúc",
            "Tuyên Quang",
            "Tiền Giang","Thừa Thiên Huế",
            "Sơn La",
            "Sóc Trăng",
            "Quảng Trị",
            "Quảng Ninh","Bến Tre",
            "Quảng Ngãi",
            "Quảng Nam",
             "Quảng Bình",
             "Ninh Bình",
             "Nghệ An",
            "Hà Tĩnh",};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        managementCart = new ManagementCart(this);
        listCart = managementCart.getListCart();
        innit ();
        Intent intent = getIntent();
        user = intent.getStringExtra("user");
        total = Double.parseDouble(intent.getStringExtra("total"));
        autoCompleteTxt = findViewById(R.id.auto_complete_txt);

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });
        comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckOutDao.insertOrders(CheckOutActivity.this,user, name.getText().toString(), phone.getText().toString(), address.getText().toString(), note.getText().toString(), total)){
                    int a = CheckOutDao.getIdOrder(CheckOutActivity.this,user,address.getText().toString());
                   for (Product p : listCart){
                       if(!CheckOutDao.insertOrdetail(CheckOutActivity.this, a, p.getId(), p.getNumberInCart(), (double) p.getPrice()*p.getNumberInCart())){
                           Toast.makeText(CheckOutActivity.this, " Failed", Toast.LENGTH_SHORT).show();
                       }
                   }
                    JavaMailAPI javaMailAPI = new JavaMailAPI(CheckOutActivity.this, UserDao.getUser(CheckOutActivity.this, user), "XÁC NHẬN ĐƠN HÀNG", "Bạn đã dặt hàng thành công");
                    javaMailAPI.execute();
                    Intent a1 = new Intent(CheckOutActivity.this, HomeActivity.class);
                    a1.putExtra("user", user);
                    Toast.makeText(CheckOutActivity.this, " Đặt Hàng Thành Công", Toast.LENGTH_SHORT).show();
                    startActivity(a1);

                }else{
                    Toast.makeText(CheckOutActivity.this, " Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public  void innit (){
        name = findViewById(R.id.nameEditT);
        phone = findViewById(R.id.phoneEditT);
        address = findViewById(R.id.addressEditT);
        note  = findViewById(R.id.noteEditT);
        comfirm = findViewById(R.id.confirmBtn);
    }
}