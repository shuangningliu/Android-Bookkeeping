package com.example.accountmanager.Entity;

import java.io.Serializable;

public class IncomeST implements Serializable {
    private int _id;
    private double money;
    private String date;
    private String time;
    private String type;
    private String mark;

    public IncomeST() {// 默认构造函数
        super();
    }

    // 定义有参构造函数，用来初始化收入信息实体类中的各个字段
    public IncomeST(int id, double money, String time, String type,
                    String date, String mark) {
        super();
        this._id = id;// 为收入编号赋值
        this.money = money;// 为收入金额赋值
        this.time = time;// 为收入日期赋值
        this.type = type;// 为收入时间赋值
        this.date = date;// 为收入类型赋值
        this.mark = mark;// 为收入备注赋值
    }

    public int getid() {// 设置收入编号的可读属性
        return _id;
    }

    public void setid(int id) {// 设置收入编号的可写属性
        this._id = id;
    }

    public double getMoney() {// 设置收入金额的可读属性
        return money;
    }

    public void setMoney(double money) {// 设置收入金额的可写属性
        this.money = money;
    }

    public String getTime() {// 设置收入时间的可读属性
        return time;
    }

    public void setTime(String time) {// 设置收入时间的可写属性
        this.time = time;
    }

    public String getType() {// 设置收入类别的可读属性
        return type;
    }

    public void setType(String type) {// 设置收入类别的可写属性
        this.type = type;
    }

    public String getDate() {// 设置收入付款方的可读属性
        return date;
    }

    public void setDate(String date) {// 设置收入付款方的可写属性
        this.date = date;
    }

    public String getMark() {// 设置收入备注的可读属性
        return mark;
    }

    public void setMark(String mark) {// 设置收入备注的可写属性
        this.mark = mark;
    }
}
