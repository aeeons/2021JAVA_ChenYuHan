package Functions;

import SwingGUI.indexFrame;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Add {

    indexFrame origin;
    Connection conn = null; //数据库连接对象
    Statement stmt = null;  //数据库操作对象
    ResultSet rs = null;    //查询结果集
    String company;

    Add() {

    }

    public Add(Connection conn, indexFrame origin, String company) {
        this.company = company;
        this.origin = origin;
        rs = null;
        try {
            this.conn = conn;
            stmt = conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean Add(String id, String name, String price, String date, String type, String place, String mark) {

        //如果id或者name为空则失败
        if (!check(id, name)) {
            return false;
        }

        //如果id已存在则失败
        String check = "select id from " + company + " where id='" + id + "'";
        try {
            rs = stmt.executeQuery(check);

            //如果编号已存在，进行的是修改操作
            if (rs.next()) {
                //sql 修改语句 update _table_ set _yyy_ = , _zzz_ =  where _xxx_ =
                String sql = getUpdate(id, name, price, date, type, place, mark);
                stmt.executeUpdate(sql);

                //重新写入表格中
                origin.tableLoad();
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //把新信息写回表格中
        goods newGoods = new goods(id, name, price, date, type, place, mark);
        origin.defaultTableModel.addRow(newGoods.getInfo());
        try {
            //在数据库中添加信息
            //sql 增加语句 insert into _table_ (xxx,yyy,zzz) value(x,y,z)
            String sql = getSQl(id, name, price, date, type, place, mark);
            stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean check(String id, String name) {
        return !id.equals("") && !name.equals("");
    }

    String getSQl(String id, String name, String price, String date, String type, String place, String mark) {
        String SQL = "insert into " + company + "(id,name,price,date,type,place,mark) value(";
        SQL += "'" + id + "','" + name + "','" + price + "','" + date + "','" + type + "','" + place + "','" + mark + "')";
        return SQL;
    }

    String getUpdate(String id, String name, String price, String date, String type, String place, String mark) {
        String SQL = "update " + company + " set name='" + name + "',";
        SQL += "price='" + price + "',date='" + date + "',type='" + type + "',";
        SQL += "place='" + place + "',mark='" + mark + "' where id='" + id + "'";
        return SQL;
    }
}