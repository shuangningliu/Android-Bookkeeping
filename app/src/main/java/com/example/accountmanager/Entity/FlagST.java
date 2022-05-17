package com.example.accountmanager.Entity;

public class FlagST {
    private int _id;// 存储便签编号
    private String flag;// 存储便签信息

    public FlagST() {// 默认构造函数
        super();
    }

    // 定义有参构造函数，用来初始化便签信息实体类中的各个字段
    public FlagST(int id, String flag) {
        super();
        this._id = id;
        this.flag = flag;
    }

    public int getid() {// 设置便签编号的可读属性
        return _id;
    }

    public void setid(int id) {// 设置便签编号的可写属性
        this._id = id;
    }

    public String getFlag() {// 设置便签信息的可读属性
        return flag;
    }

    public void setFlag(String flag) {// 设置便签信息的可写属性
        this.flag = flag;
    }
}
