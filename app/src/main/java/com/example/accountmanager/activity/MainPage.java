package com.example.accountmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.accountmanager.R;

public class MainPage extends AppCompatActivity {

    private ImageView addincome;
    private ImageView myincome;
    private ImageView help;
    private ImageView addspend;
    private ImageView myspend;
    private ImageView setting;
    private ImageView addnote;
    private ImageView mynote;
    private ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        addincome = (ImageView) findViewById(R.id.addincome);
        myincome = (ImageView) findViewById(R.id.myincome);
        help = (ImageView) findViewById(R.id.help);
        addspend = (ImageView) findViewById(R.id.addspend);
        myspend = (ImageView) findViewById(R.id.myspend);
        setting = (ImageView) findViewById(R.id.setting);
        addnote = (ImageView) findViewById(R.id.addnote);
        mynote = (ImageView) findViewById(R.id.mynote);
        exit = (ImageView) findViewById(R.id.exit);
        addincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addincomeit = new Intent(MainPage.this, AddIncome.class);
                startActivity(addincomeit);
            }
        });
        myincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myincomeit = new Intent(MainPage.this, MyIncome.class);
                startActivity(myincomeit);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent helpit = new Intent(MainPage.this, Help.class);
                startActivity(helpit);
            }
        });
        addspend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addspendit = new Intent(MainPage.this, AddSpend.class);
                startActivity(addspendit);
            }
        });
        myspend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myspendit = new Intent(MainPage.this, MySpend.class);
                startActivity(myspendit);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingit = new Intent(MainPage.this, Setting.class);
                startActivity(settingit);
            }
        });
        addnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnoteit = new Intent(MainPage.this, AddNote.class);
                startActivity(addnoteit);
            }
        });
        mynote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mynoteit = new Intent(MainPage.this, MyNote.class);
                startActivity(mynoteit);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}