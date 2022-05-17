package com.example.accountmanager.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.accountmanager.Entity.SpendST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpendDao {
    private DataHelper helper;// 创建DBOpenHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象

    public SpendDao(Context context) {// 定义构造函数
        helper = new DataHelper(context);// 初始化DBOpenHelper对象
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
    }

    /**
     * 添加支出信息
     *
     * @param tb_outaccount
     */
    public void add(SpendST tb_outaccount) {
        // 执行添加支出信息操作
        db.execSQL(
                "insert into tb_outaccount (_id,money,time,type,address,mark) values (?,?,?,?,?,?)",
                new Object[]{tb_outaccount.getid(), tb_outaccount.getMoney(),
                        tb_outaccount.getTime(), tb_outaccount.getType(),
                        tb_outaccount.getAddress(), tb_outaccount.getMark()});
    }

    /**
     * 更新支出信息
     *
     * @param tb_outaccount
     */
    public void update(SpendST tb_outaccount) {
//		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        // 执行修改支出信息操作
        db.execSQL(
                "update tb_outaccount set money = ?,time = ?,type = ?,address = ?,mark = ? where _id = ?",
                new Object[]{tb_outaccount.getMoney(),
                        tb_outaccount.getTime(), tb_outaccount.getType(),
                        tb_outaccount.getAddress(), tb_outaccount.getMark(),
                        tb_outaccount.getid()});
    }


    /**
     * 获取总记录数
     *
     * @return
     */
    public long getCount() {//获取总记录数
//		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select count(_id) from tb_outaccount",
                null);// 获取支出信息的记录数
        if (cursor.moveToNext()) {// 判断Cursor中是否有数据
            return cursor.getLong(0);// 返回总记录数
        }
        cursor.close();// 关闭游标
        return 0;// 如果没有数据，则返回0
    }


    public int getMaxId() {//获取最大编号
        Cursor cursor = db.rawQuery("select max(_id) from tb_outaccount", null);// 获取支出信息表中的最大编号
        while (cursor.moveToLast()) {// 访问Cursor中的最后一条数据
            return cursor.getInt(0);// 获取访问到的数据，即最大编号
        }
        cursor.close();// 关闭游标
        return 0;// 如果没有数据，则返回0
    }

    public void look() {//打印数据库数据
        Cursor cursor = db.query("tb_outaccount", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                System.out.println(cursor.getString(0));
            }
        }
    }

    public void remake() {
        db.execSQL("delete from tb_outaccount");//清空数据库
    }

    @SuppressLint("Range")
    public List<SpendST> output() {
        List<SpendST> list = new ArrayList<SpendST>();//创建列表对象，用于保存数据库元素，数据类型为实体对象
        Cursor cursor = db.query("tb_outaccount", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {

                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                Double money = cursor.getDouble(cursor.getColumnIndex("money"));
                String date = cursor.getString(cursor.getColumnIndex("time"));
                String time = cursor.getString(cursor.getColumnIndex("type"));
                String type = cursor.getString(cursor.getColumnIndex("address"));
                String mark = cursor.getString(cursor.getColumnIndex("mark"));
                list.add(new SpendST(id, money, date, time, type, mark));
            }
            return list;
        }
        cursor.close();
        return null;
    }

    @SuppressLint("Range")
    public Map groupby() {
        Map percentage = new HashMap<>();//建立一个map对象，用于储存各项收入的总金额
        percentage.put("餐饮费", 0.0f);
        percentage.put("生活费", 0.0f);
        percentage.put("行程费", 0.0f);
        percentage.put("借出款", 0.0f);
        percentage.put("其它", 0.0f);
        float sum = 0;
        Cursor cursor = db.rawQuery("select address,sum(money) from tb_outaccount group by address", null);
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
        db.execSQL("delete from tb_outaccount where _id=?", new Object[]{id});
    }
}
