package com.example.accountmanager.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.accountmanager.Dao.IncomeDao;
import com.example.accountmanager.Dao.SpendDao;
import com.example.accountmanager.Entity.IncomeST;
import com.example.accountmanager.Entity.SpendST;
import com.example.accountmanager.R;

import java.util.Calendar;

public class Modify extends AppCompatActivity {
    private Button modify_submit;
    private Button modify_delete;
    private Button modify_cancel;
    private Button modify_groupbutton;
    private EditText modify_number;
    private EditText modify_date;
    private EditText modify_time;
    private EditText modify_beizhu;

    private String modify_class = "";
    private String dateselect = "";
    private String timeselect = "";
    private Double numbergold;
    private String mark;

    private IncomeDao incomeDao;
    private SpendDao spendDao;
    private IncomeST incomeST;
    private SpendST spendST;

    private int index = 0;
    private String[] out_group = {"餐饮费", "生活费", "行程费", "借出款", "其它"};//对话框选项
    private String[] add_group = {"工薪资", "所借款", "理财金", "其它"};
    private int mYear;        // 年
    private int mMonth;        // 月
    private int mDay;        // 日
    private String data;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
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
        incomeDao = new IncomeDao(Modify.this);
        spendDao = new SpendDao(Modify.this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        modify_date = (EditText) findViewById(R.id.modify_date);
        modify_time = (EditText) findViewById(R.id.modify_time);
        modify_number = (EditText) findViewById(R.id.modify_number);
        modify_beizhu = (EditText) findViewById(R.id.modify_beizhu);
        modify_groupbutton = (Button) findViewById(R.id.modify_group);

        if (bundle.getString("type").equals("income")) {
            incomeST = (IncomeST) bundle.getSerializable("incomeST");
            modify_date.setText(incomeST.getTime());
            modify_time.setText(incomeST.getType());
            modify_number.setText(Double.toString(incomeST.getMoney()));//setText不能接受浮点数类型，所以要转成字符串类型
            modify_beizhu.setText(incomeST.getMark());
            modify_groupbutton.setText(incomeST.getDate());
        } else if (bundle.getString("type").equals("myspend")) {
            spendST = (SpendST) bundle.getSerializable("spendST");
            modify_date.setText(spendST.getTime());
            modify_time.setText(spendST.getType());
            modify_number.setText(Double.toString(spendST.getMoney()));//setText不能接受浮点数类型，所以要转成字符串类型
            modify_beizhu.setText(spendST.getMark());
            modify_groupbutton.setText(spendST.getAddress());
        }

        modify_submit = (Button) findViewById(R.id.modify_submit);
        modify_delete = (Button) findViewById(R.id.modify_delete);
        modify_cancel = (Button) findViewById(R.id.modify_cancel);
        modify_groupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Modify.this);//创建弹出式对话框对象
                if (bundle.getString("type").equals("income")) {
                    builder.setIcon(R.mipmap.ic_launcher).setTitle("选择类别").setSingleChoiceItems(add_group, 0, new DialogInterface.OnClickListener() {//设置标题、选项
                        @Override
                        //setMessage设置简单文本、setSingChoiceItems设置单选按钮、setMultiChoiceItems设置多选按钮
                        public void onClick(DialogInterface dialogInterface, int i) {
                            index = i;//获取选取的选项的索引值
                            modify_class = add_group[i];
                        }
                    }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            modify_groupbutton.setText(modify_class);
                            dialogInterface.dismiss();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                } else if (bundle.getString("type").equals("myspend")) {
                    builder.setIcon(R.mipmap.ic_launcher).setTitle("选择类别").setSingleChoiceItems(out_group, 0, new DialogInterface.OnClickListener() {//设置标题、选项
                        @Override
                        //setMessage设置简单文本、setSingChoiceItems设置单选按钮、setMultiChoiceItems设置多选按钮
                        public void onClick(DialogInterface dialogInterface, int i) {
                            index = i;//获取选取的选项的索引值
                            modify_class = out_group[i];
                        }
                    }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            modify_groupbutton.setText(modify_class);
                            dialogInterface.dismiss();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                }
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        modify_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Modify.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                mYear[0] = year;
                                mMonth[0] = month;
                                mDay[0] = dayOfMonth;
                                data = year + "年-" + (month + 1) + "月-" + dayOfMonth + "日 ";
                                modify_date.setText(data);
                                dateselect = data;
                            }
                        },
                        mYear[0], mMonth[0], mDay[0]);
                datePickerDialog.setTitle("选择日期");
                datePickerDialog.show();
            }
        });
        modify_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Modify.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourread, int minuteread) {
                                hour[0] = hourread;
                                minute[0] = minuteread;
                                time = hourread + "时：" + minuteread + "分";
                                modify_time.setText(time);
                                timeselect = time;
                            }
                        }, hour[0], minute[0], true);//true为24小时制，false为12小时制
                timePickerDialog.setTitle("选择时间");
                timePickerDialog.show();
            }
        });
        modify_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mark = modify_beizhu.getText().toString();
                String numberString = modify_number.getText().toString();
                numbergold = Double.parseDouble(numberString);
                if (bundle.getString("type").equals("income")) {
                    incomeST.setMark(mark);
                    incomeST.setDate(modify_class);
                    incomeST.setMoney(numbergold);
                    incomeST.setType(timeselect);
                    incomeST.setTime(dateselect);
                    incomeDao.update(incomeST);
                    Toast.makeText(Modify.this, "修改成功！", Toast.LENGTH_SHORT).show();
                    finish();
                } else if (bundle.getString("type").equals("myspend")) {
                    spendST.setMark(mark);
                    spendST.setTime(dateselect);
                    spendST.setMoney(numbergold);
                    spendST.setType(timeselect);
                    spendST.setAddress(modify_class);
                    spendDao.update(spendST);
                    Toast.makeText(Modify.this, "修改成功！", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        modify_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bundle.getString("type").equals("income")) {

                    incomeDao.delete(bundle.getInt("id"));
                    Toast.makeText(Modify.this, "删除成功！", Toast.LENGTH_SHORT).show();
                    finish();
                } else if (bundle.getString("type").equals("myspend")) {

                    spendDao.delete(bundle.getInt("id"));
                    Toast.makeText(Modify.this, "删除成功！", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        modify_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}