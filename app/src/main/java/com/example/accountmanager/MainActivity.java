package com.example.accountmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.accountmanager.Dao.FlagDao;
import com.example.accountmanager.Dao.IncomeDao;
import com.example.accountmanager.Dao.PassDao;
import com.example.accountmanager.Dao.SpendDao;
import com.example.accountmanager.Entity.PassST;
import com.example.accountmanager.activity.Login;
import com.example.accountmanager.activity.MainPage;

public class MainActivity extends AppCompatActivity {
    private Button submit;
    private Button cancel;
    private EditText passinput;
    private EditText passaffirm;
    private String input;
    private String affirm;
    private PassST passST;
    private PassDao passDao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passDao = new PassDao(this);
//        passDao.delete();
        if (passDao.getCount() != 0) {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        }

        IncomeDao incomeDao = new IncomeDao(MainActivity.this);
//        incomeDao.groupby();
//        incomeDao.remake();
        SpendDao spendDao = new SpendDao(MainActivity.this);
//        spendDao.remake();
        FlagDao flagDao = new FlagDao(MainActivity.this);
//        flagDao.remake();
//        flagDao.look();
        submit = (Button) findViewById(R.id.submit);
        cancel = (Button) findViewById(R.id.cancel);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passinput = (EditText) findViewById(R.id.passwordinput);
                passaffirm = (EditText) findViewById(R.id.passwordaffirm);
                input = passinput.getText().toString();
                affirm = passaffirm.getText().toString();
                if (input.equals(affirm) && (!input.equals(""))) {
                    passST = new PassST(input);
                    passDao.add(passST);
                    Toast.makeText(MainActivity.this, "设置成功，下次可通过密码进入！", Toast.LENGTH_SHORT).show();
                    Intent mainintent = new Intent(MainActivity.this, MainPage.class);
                    startActivity(mainintent);
                } else {
                    Toast.makeText(MainActivity.this, "密码输入不一致或未输入！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}