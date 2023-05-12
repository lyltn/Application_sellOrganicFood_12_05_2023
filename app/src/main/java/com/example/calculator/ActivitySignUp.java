package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.calculator.Hepler.UserDao;

import java.util.Random;

public class ActivitySignUp extends AppCompatActivity {
    EditText name, phone, user, pass, mail;

//           EditText codebtn;
    AppCompatButton btnsignup;
    String formattedNumber;
//    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = findViewById(R.id.NameU);
        phone = findViewById(R.id.PhoneU);
        user = findViewById(R.id.UserU);
        pass = findViewById(R.id.PassU);
        mail = findViewById(R.id.MailU);
        btnsignup = findViewById(R.id.btnLog);
        Random random = new Random();
        int randomNumber = random.nextInt(100000); // tạo số ngẫu nhiên trong phạm vi từ 0 đến 99999
        formattedNumber = String.format("%05d", randomNumber);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                JavaMailAPI javaMailAPI = new JavaMailAPI(ActivitySignUp.this, mail.getText().toString(),  "MÃ XÁC THẬT", "Mã xác thật của bạn là: "+formattedNumber);
//                javaMailAPI.execute();
//                showDialogDes();
                if(UserDao.insert(ActivitySignUp.this, name.getText().toString(), phone.getText().toString(), user.getText().toString(), pass.getText().toString(), mail.getText().toString())){
                    Toast.makeText(ActivitySignUp.this, "Insert success!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ActivitySignUp.this, MainActivity2.class));
                }else{
                    Toast.makeText(ActivitySignUp.this, "Insert Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
//    private void showDialogDes(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(ActivitySignUp.this);
//        LayoutInflater inflater = getLayoutInflater();
//        View view = inflater.inflate(R.layout.dialog_comfirm, null);
//        builder.setView(view);
////        Dialog dialog = builder.create();
//        Dialog dialog = new Dialog(ActivitySignUp.this);
//        dialog.setContentView(R.layout.dialog_comfirm);
//        codebtn = dialog.findViewById(R.id.codeBtn);
//        entercode = dialog.findViewById(R.id.entercode);
//        dialog.show();
//        if(formattedNumber.equals(entercode.getText().toString())){
//            codebtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(UserDao.insert(ActivitySignUp.this, name.getText().toString(), phone.getText().toString(), user.getText().toString(), pass.getText().toString(), mail.getText().toString())){
//                        Toast.makeText(ActivitySignUp.this, "Insert success!", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(ActivitySignUp.this, MainActivity2.class));
//                    }else{
//                        Toast.makeText(ActivitySignUp.this, "Insert Failed", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//            dialog.dismiss();
//        }else{
//
//            Toast.makeText(ActivitySignUp.this, "Mã xác thật không đúng!!!", Toast.LENGTH_SHORT).show();
//        }
//
//
//    }
}