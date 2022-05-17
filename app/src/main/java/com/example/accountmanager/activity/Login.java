package com.example.accountmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.accountmanager.Dao.PassDao;
import com.example.accountmanager.R;

public class Login extends AppCompatActivity {

    private Button submit;
    private Button chanel;
    private EditText loginpass;
    private PassDao passDao;
    private String loginpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        passDao = new PassDao(this);
        loginpass = (EditText) findViewById(R.id.loginpass);
        submit = (Button) findViewById(R.id.loginsubmit);
        chanel = (Button) findViewById(R.id.loginchanel);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginpassword = loginpass.getText().toString();
                if (passDao.find().equals(loginpassword)) {
                    Intent intent = new Intent(Login.this, MainPage.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this, "密码输入错误，请重新输入！", Toast.LENGTH_SHORT).show();
                    loginpass.setText("");
                }
            }
        });
        chanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}