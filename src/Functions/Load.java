package Functions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class Load {
    Connection conn = null; //数据库链接对象
    Statement stmt = null;  //数据库操作对象
    ResultSet rs = null;    //查询结果集

    public Load() {

    }

    public Load(Connection conn) {
        try {
            this.conn = conn;
            stmt = conn.createStatement();
            rs = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<goods> getTableList(String company) {
        LinkedList<goods> list = new LinkedList<goods>();
        try {
            //sql 查询操作 select xxx from _table_
            String sql = "select * from " + company;
            rs = stmt.executeQuery(sql);
            //将查询结果写入表格
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String price = rs.getString("price");
                String data = rs.getString("date");
                String type = rs.getString("type");
                String place = rs.getString("place");
                String mark = rs.getString("mark");
                list.add(new goods(id, name, price, data, type, place, mark));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
