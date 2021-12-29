package Functions;

import SwingGUI.indexFrame;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Add {
    indexFrame origin;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
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
        System.out.println(check);
        try {
            rs = stmt.executeQuery(check);
            if (rs.next()) {
                String sql = getUpdate(id, name, price, date, type, place, mark);
                stmt.executeUpdate(sql);
                System.out.println(sql);
                origin.tableLoad();
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        goods newGoods = new goods(id, name, price, date, type, place, mark);
        origin.defaultTableModel.addRow(newGoods.getInfo());
        try {
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
        String SQL = "update " + company + " set name='" + name + "',price='" + price + "',date='" + date + "',type='" + type + "',place='" + place + "',mark='" + mark + "' where id='" + id + "'";
        return SQL;
    }
}
