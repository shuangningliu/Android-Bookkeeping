package com.example.accountmanager.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.accountmanager.Entity.IncomeST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IncomeDao {
    private DataHelper helper;// 创建DBOpenHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象

    public IncomeDao(Context context) {// 定义构造函数
        helper = new DataHelper(context);// 初始化DBOpenHelper对象
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
    }

    /**
     * 添加收入信息
     *
     * @param tb_inaccount
     */
    public void add(IncomeST tb_inaccount) {
        // 执行添加收入信息操作
        db.execSQL(
                "insert into tb_inaccount (_id,money,time,type,handler,mark) "
                        + "values (?,?,?,?,?,?)",
                new Object[]{tb_inaccount.getid(), tb_inaccount.getMoney(),
                        tb_inaccount.getTime(), tb_inaccount.getType(),
                        tb_inaccount.getDate(), tb_inaccount.getMark()});
    }

    /**
     * 更新收入信息
     *
     * @param tb_inaccount
     */
    public void update(IncomeST tb_inaccount) {
//		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        // 执行修改收入信息操作
        db.execSQL(
                "update tb_inaccount set money = ?,time = ?,type = ?,handler = ?,"
                        + "mark = ? where _id = ?",
                new Object[]{tb_inaccount.getMoney(), tb_inaccount.getTime(),
                        tb_inaccount.getType(), tb_inaccount.getDate(),
                        tb_inaccount.getMark(), tb_inaccount.getid()});
    }


    public long getCount() {//获取总记录数
//		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db
                .rawQuery("select count(_id) from tb_inaccount", null);// 获取收入信息的记录数
        if (cursor.moveToNext()) {// 判断Cursor中是否有数据
            return cursor.getLong(0);// 返回总记录数
        }
        cursor.close();// 关闭游标
        return 0;// 如果没有数据，则返回0
    }

    public int getMaxId() {//获取最大编号
//		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select max(_id) from tb_inaccount", null);// 获取收入信息表中的最大编号
        while (cursor.moveToLast()) {// 访问Cursor中的最后一条数据
            return cursor.getInt(0);// 获取访问到的数据，即最大编号
        }
        cursor.close();// 关闭游标
        return 0;// 如果没有数据，则返回0
    }

    public void look() {//打印数据库数据
        Cursor cursor = db.query("tb_inaccount", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                System.out.println(cursor.getString(0));
            }
        }
    }

    public void remake() {
        db.execSQL("delete from tb_inaccount");//清空数据库
    }

    //查找数据库，遍历数据库中元素
    @SuppressLint("Range")
    public List<IncomeST> output() {
        List<IncomeST> list = new ArrayList<IncomeST>();//创建列表对象，用于保存数据库元素，数据类型为实体对象
        Cursor cursor = db.query("tb_inaccount", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {

                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                Double money = cursor.getDouble(cursor.getColumnIndex("money"));
                String date = cursor.getString(cursor.getColumnIndex("time"));
                String time = cursor.getString(cursor.getColumnIndex("type"));
                String type = cursor.getString(cursor.getColumnIndex("handler"));
                String mark = cursor.getString(cursor.getColumnIndex("mark"));
                list.add(new IncomeST(id, money, date, time, type, mark));
            }
            return list;
        }
        cursor.close();
        return null;
    }

    @SuppressLint("Range")
    public Map groupby() {
        Map percentage = new HashMap<>();//建立一个map对象，用于储存各项收入的总金额
        percentage.put("工薪金", 0.0f);
        percentage.put("所借款", 0.0f);
        percentage.put("理财金", 0.0f);
        percentage.put("其它", 0.0f);
        float sum = 0;
//        Cursor cursor=db.query("tb_inaccount","sum(money)","handler",null,null,null,nu)
        Cursor cursor = db.rawQuery("select handler,sum(money) from tb_inaccount group by handler", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String key = cursor.getString(0);
                Float value = cursor.getFloat(1);
                percentage.put(key, value);//更新字典值
            }
        }
        cursor.close();
        return percentage;

    }

    public void delete(int id) {//删除数据
        db.execSQL("delete from tb_inaccount where _id=?", new Object[]{id});
    }


}
