package Functions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class Load {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

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
            String sql = "select * from " + company;
            rs = stmt.executeQuery(sql);
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
