package com.example.accountmanager.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.accountmanager.Entity.FlagST;

import java.util.ArrayList;
import java.util.List;

public class FlagDao {
    private DataHelper helper;// 创建DBOpenHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象

    public FlagDao(Context context) {// 定义构造函数
        helper = new DataHelper(context);// 初始化DBOpenHelper对象
        db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
    }

    /**
     * 添加便签信息
     *
     * @param tb_flag
     */
    public void add(FlagST tb_flag) {
        db.execSQL("insert into tb_flag (_id,flag) values (?,?)", new Object[]{
                tb_flag.getid(), tb_flag.getFlag()});// 执行添加便签信息操作
    }

    /**
     * 更新便签信息
     *
     * @param tb_flag
     */
    public void update(FlagST tb_flag) {
//		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        db.execSQL("update tb_flag set flag = ? where _id = ?", new Object[]{
                tb_flag.getFlag(), tb_flag.getid()});// 执行修改便签信息操作
    }


    /**
     * 获取总记录数
     *
     * @return
     */
    public long getCount() {
        Cursor cursor = db.rawQuery("select count(_id) from tb_flag", null);// 获取便签信息的记录数
        if (cursor.moveToNext()) {// 判断Cursor中是否有数据
            return cursor.getLong(0);// 返回总记录数
        }
        cursor.close();// 关闭游标
        return 0;// 如果没有数据，则返回0
    }

    /**
     * 获取便签最大编号
     *
     * @return
     */
    public int getMaxId() {
        Cursor cursor = db.rawQuery("select max(_id) from tb_flag", null);// 获取便签信息表中的最大编号
        while (cursor.moveToLast()) {// 访问Cursor中的最后一条数据
            return cursor.getInt(0);// 获取访问到的数据，即最大编号
        }
        cursor.close();// 关闭游标
        return 0;// 如果没有数据，则返回0
    }

    public void remake() {
        db.execSQL("delete from tb_flag");//清空数据库
    }

    public void look() {//打印数据库数据
        Cursor cursor = db.query("tb_flag", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                System.out.println(cursor.getString(1));
            }
        }
    }

    @SuppressLint("Range")
    public List<FlagST> output() {
        List<FlagST> list = new ArrayList<FlagST>();//创建列表对象，用于保存数据库元素，数据类型为实体对象
        Cursor cursor = db.query("tb_flag", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String mark = cursor.getString(cursor.getColumnIndex("flag"));
                list.add(new FlagST(id, mark));
            }
            return list;
        }
        cursor.close();
        return null;
    }
}
