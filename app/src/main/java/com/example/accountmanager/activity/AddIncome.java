package com.example.accountmanager.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.accountmanager.Dao.IncomeDao;
import com.example.accountmanager.Entity.IncomeST;
import com.example.accountmanager.R;

import java.util.Calendar;

public class AddIncome extends AppCompatActivity {


    private Button addin_submit;
    private Button addin_cancel;
    private Button addin_groupbutton;
    private EditText addin_number;
    private EditText addin_date;
    private EditText addin_time;
    private EditText addin_beizhu;

    private String addin_class = "";
    private String dateselect = "";
    private String timeselect = "";
    private Double numbergold;
    private String mark;
    private int index = 0;
    private String[] add_group = {"工薪资", "所借款", "理财金", "其它"};//对话框选项
    private int mYear;        // 年
    private int mMonth;        // 月
    private int mDay;        // 日
    private String data;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        Calendar ca = Calendar.getInstance();
        final int[] mYear = {ca.get(Calendar.YEAR)};
        final int[] mMonth = {ca.get(Calendar.MONTH)};
        final int[] mDay = {ca.get(Calendar.DAY_OF_MONTH)};
        data = mYear[0] + "年-" + (mMonth[0] + 1) + "月-" + mDay[0] + "日 ";
        final int[] hour = {ca.get(Calendar.HOUR_OF_DAY)};
        final int[] minute = {ca.get(Calendar.MINUTE)};
        time = hour[0] + "时：" + minute[0] + "分";
        dateselect = data;
        timeselect = time;


        addin_submit = (Button) findViewById(R.id.addin_submit);
        addin_cancel = (Button) findViewById(R.id.addin_cancel);
        addin_groupbutton = (Button) findViewById(R.id.addin_group);
        addin_date = (EditText) findViewById(R.id.addin_date);
        addin_time = (EditText) findViewById(R.id.addin_time);
        addin_date.setHint(data);
        addin_time = (EditText) findViewById(R.id.addin_time);
        addin_time.setHint(time);
        addin_groupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddIncome.this);//创建弹出式对话框对象
                builder.setIcon(R.mipmap.ic_launcher).setTitle("选择收入类别").setSingleChoiceItems(add_group, 0, new DialogInterface.OnClickListener() {//设置标题、选项
                    @Override
                    //setMessage设置简单文本、setSingChoiceItems设置单选按钮、setMultiChoiceItems设置多选按钮
                    public void onClick(DialogInterface dialogInterface, int i) {
                        index = i;//获取选取的选项的索引值
                        addin_class = add_group[i];
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addin_groupbutton.setText(addin_class);
                        dialogInterface.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        addin_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddIncome.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                mYear[0] = year;
                                mMonth[0] = month;
                                mDay[0] = dayOfMonth;
                                data = year + "年-" + (month + 1) + "月-" + dayOfMonth + "日 ";
                                addin_date.setText(data);
                                dateselect = data;
                            }
                        },
                        mYear[0], mMonth[0], mDay[0]);
                datePickerDialog.setTitle("选择日期");
                datePickerDialog.show();
            }
        });
        addin_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddIncome.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourread, int minuteread) {
                                hour[0] = hourread;
                                minute[0] = minuteread;
                                time = hourread + "时：" + minuteread + "分";
                                addin_time.setText(time);
                                timeselect = time;
                            }
                        }, hour[0], minute[0], true);//true为24小时制，false为12小时制
                timePickerDialog.setTitle("选择时间");
                timePickerDialog.show();
            }
        });
        addin_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addin_number = (EditText) findViewById(R.id.addin_number);
                addin_beizhu = (EditText) findViewById(R.id.addin_beizhu);
                mark = addin_beizhu.getText().toString();
                String numberString = addin_number.getText().toString();
                if (!numberString.equals("")) {
                    numbergold = Double.parseDouble(numberString);
                    IncomeDao incomeDao = new IncomeDao(AddIncome.this);
                    IncomeST incomeST = new IncomeST(incomeDao.getMaxId() + 1, numbergold, dateselect, timeselect, addin_class, mark);
                    incomeDao.add(incomeST);
                    Toast.makeText(AddIncome.this, "添加成功！", Toast.LENGTH_SHORT).show();
                    finish();
                }


            }
        });
        addin_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

}