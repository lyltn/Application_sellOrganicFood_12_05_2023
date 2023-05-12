package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculator.Mail.JavaMailAPI;

public class MailActivity extends AppCompatActivity {
    public EditText mEmail;
    public EditText mSubject;
    public EditText mMess;
    public Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        mEmail = (EditText) findViewById(R.id.mail);
        mSubject = (EditText) findViewById(R.id.sub);
        mMess = (EditText) findViewById(R.id.mess);
        btn = (Button)findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });
    }

    private void sendMail() {
        String mail = mEmail.getText().toString().trim();
        String message = mMess.getText().toString();
        String sub = mSubject.getText().toString().trim();
        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mail, sub, message);
        javaMailAPI.execute();
    }
}