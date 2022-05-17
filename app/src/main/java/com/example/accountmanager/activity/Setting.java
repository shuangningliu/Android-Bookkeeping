package com.example.accountmanager.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.accountmanager.Dao.PassDao;
import com.example.accountmanager.Entity.PassST;
import com.example.accountmanager.R;

public class Setting extends AppCompatActivity {
    private Button settingsubmit;
    private Button settingcancel;
    private EditText oldpass_ed;
    private EditText newpass_ed;
    private EditText confirmpass_ed;
    private PassDao passDao;
    private PassST passST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        settingsubmit = (Button) findViewById(R.id.settingsubmit);
        settingcancel = (Button) findViewById(R.id.settingcancel);
        oldpass_ed = (EditText) findViewById(R.id.oldpass);
        newpass_ed = (EditText) findViewById(R.id.newpass);
        confirmpass_ed = (EditText) findViewById(R.id.confirmpass);
        passDao = new PassDao(this);
        settingsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((oldpass_ed.getText().toString()).equals(passDao.find()))) {
                    if ((newpass_ed.getText().toString()).equals(confirmpass_ed.getText().toString())) {
                        passST = new PassST(newpass_ed.getText().toString());
                        passDao.update(passST);
                        Toast.makeText(Setting.this, "修改成功！", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(Setting.this, "两次密码输入不一致，请重新输入！", Toast.LENGTH_SHORT).show();
                        newpass_ed.setText("");
                        confirmpass_ed.setText("");
                    }
                } else {
                    Toast.makeText(Setting.this, "原密码输入错误，请重新输入！", Toast.LENGTH_SHORT).show();
                    oldpass_ed.setText("");
                }
            }
        });
        settingcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}