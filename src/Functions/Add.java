package Functions;

import SwingGUI.indexFrame;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Add {
    indexFrame origin;
    Connection conn = null;
    Statement stmt = null;
    String company;

    Add() {

    }

    public Add(Connection conn, indexFrame origin, String company) {
        this.company = company;
        this.origin = origin;
        try {
            this.conn = conn;
            stmt = conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void Add(String id, String name, String price, String date, String type, String place, String mark) {
        if (!check(id, name)) {
            return;
        }
        goods newGoods = new goods(id, name, price, date, type, place, mark);
        origin.defaultTableModel.addRow(newGoods.getInfo());
        try {
            String sql = getSQl(id, name, price, date, type, place, mark);
            stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean check(String id, String name) {
        return !id.equals("") && !name.equals("");
    }

    String getSQl(String id, String name, String price, String date, String type, String place, String mark) {
        String SQL = "insert into " + company + "(id,name,price,date,type,place,mark) value(";
        SQL += "'" + id + "','" + name + "','" + price + "','" + date + "','" + type + "','" + place + "','" + mark + "')";
        return SQL;
    }
}
