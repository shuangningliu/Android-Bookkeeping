package com.example.accountmanager.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.accountmanager.Entity.PassST;

public class PassDao {
    private DataHelper da;
    private SQLiteDatabase db;

    public PassDao(Context context) {//构造方法，获取对象
        da = new DataHelper(context);
        db = da.getWritableDatabase();
    }

    public void add(PassST passST) {//添加信息
//        ContentValues value=new ContentValues();
//        value.put("password",passST.getPassword());
//        db.insert("tb_pwd",null,value);
        db.execSQL("insert into" + " tb_pwd (password)" + " values ('" + passST.getPassword() + "')");
    }

    public void update(PassST passST) {//更新
        db.execSQL("update tb_pwd set password = " + "'" + passST.getPassword() + "'");
    }

    public String find() {//查找
        Cursor cursor = db.query("tb_pwd", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                return cursor.getString(0);
            }
        }
        return null;
    }

    public void delete() {
        db.execSQL("delete from tb_pwd");//清空数据库
    }


    public long getCount() {//获取数据库记录数
        Cursor cursor = db.rawQuery("select count(*) from tb_pwd", null);// 获取信息的记录数
        if (cursor.moveToNext()) {
            return cursor.getLong(0);// 返回总记录数
        }
        cursor.close();
        return 0;
    }


}
