package com.example.accountmanager.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.accountmanager.Dao.FlagDao;
import com.example.accountmanager.Entity.FlagST;
import com.example.accountmanager.R;

import java.util.List;
import java.util.Map;

public class MyNote extends AppCompatActivity {

    private ListView listView;
    private Button myno_cancel;
    private FlagDao flagDao;
    private List<FlagST> list;
    private FlagST flagtext;
    private Map map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_note);
        listView = (ListView) findViewById(R.id.myno_record);
        flagDao = new FlagDao(MyNote.this);
        list = flagDao.output();
        String[] data = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            flagtext = list.get(i);
            data[i] = "便签信息：" + flagtext.getFlag();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MyNote.this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
        myno_cancel = (Button) findViewById(R.id.myno_cancel);
        myno_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}