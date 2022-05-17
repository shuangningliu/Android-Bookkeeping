package com.example.accountmanager.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.accountmanager.Dao.FlagDao;
import com.example.accountmanager.Entity.FlagST;
import com.example.accountmanager.R;

public class AddNote extends AppCompatActivity {

    private Button addno_submit;
    private Button addno_cancel;
    private EditText addno_note;
    private String addno_noin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        addno_submit = (Button) findViewById(R.id.addno_submit);
        addno_cancel = (Button) findViewById(R.id.addno_cancel);
        addno_note = (EditText) findViewById(R.id.addno_note);
        FlagDao flagDao = new FlagDao(AddNote.this);
        addno_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addno_noin = addno_note.getText().toString();
                if (!addno_noin.equals("")) {
                    System.out.println(addno_noin);
                    FlagST flagST = new FlagST(flagDao.getMaxId() + 1, addno_noin);
                    flagDao.add(flagST);
                    Toast.makeText(AddNote.this, "添加成功！", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddNote.this, "输入框为空，请输入信息！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        addno_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}