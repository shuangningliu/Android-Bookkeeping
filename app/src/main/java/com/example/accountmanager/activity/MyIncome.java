package com.example.accountmanager.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.accountmanager.Dao.IncomeDao;
import com.example.accountmanager.Entity.IncomeST;
import com.example.accountmanager.PieChartManagger;
import com.example.accountmanager.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyIncome extends AppCompatActivity {
    PieChart pie_chart2;//实例化对象
    private ListView listView;
    private Button myin_cancel;
    private IncomeDao incomeDao;
    private List<IncomeST> list;
    private IncomeST incometext;
    private Map map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_income);
        //饼状图实心
        pie_chart2 = findViewById(R.id.myincome_pie);
        listView = (ListView) findViewById(R.id.myin_record);
        incomeDao = new IncomeDao(MyIncome.this);
        map = incomeDao.groupby();
        percent(map);
        list = incomeDao.output();
        String[] data = new String[list.size()];
        int[] id = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            incometext = list.get(i);
            data[i] = "金额：" + incometext.getMoney() + " 日期：" + incometext.getTime() + " 时间：" + incometext.getType() + " 类型：" + incometext.getDate() + " 备注：" + incometext.getMark();
            id[i] = incometext.getid();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MyIncome.this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MyIncome.this, Modify.class);
                Bundle bundle = new Bundle();
                bundle.putString("type", "income");
                bundle.putSerializable("incomeST", list.get(i));
                bundle.putInt("id", id[i]);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
        myin_cancel = (Button) findViewById(R.id.myin_cancel);
        myin_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void show_pie_chart_2(float gxj, float sjk, float lcj, float other) {
        // 设置每份所占数量
        List<PieEntry> yvals = new ArrayList<>();
        yvals.add(new PieEntry(gxj, "工薪金"));
        yvals.add(new PieEntry(sjk, "所借款"));
        yvals.add(new PieEntry(lcj, "理财金"));
        yvals.add(new PieEntry(other, "其它"));
        //设置每份的颜色
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#6785f2"));
        colors.add(Color.parseColor("#675cf2"));
        colors.add(Color.parseColor("#496cef"));
        colors.add(Color.parseColor("#aa63fa"));
        PieChartManagger pieChartManagger = new PieChartManagger(pie_chart2);
        pieChartManagger.showSolidPieChart(yvals, colors);
    }

    private void percent(Map map) {
        float sum = 0.0f;
        sum = (float) map.get("工薪金") + (float) map.get("所借款") + (float) map.get("理财金") + (float) map.get("其它");
        if (sum == 0) {
            show_pie_chart_2(1.0f, 1.0f, 1.0f, 1.0f);
        } else {
            show_pie_chart_2((float) map.get("工薪金"), (float) map.get("所借款"), (float) map.get("理财金"), (float) map.get("其它"));
        }
    }


}