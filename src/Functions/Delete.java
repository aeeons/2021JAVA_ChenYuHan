package Functions;

import SwingGUI.indexFrame;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
    indexFrame origin;
    Connection conn = null; //数据库连接对象
    Statement stmt = null;  //数据库操作对象
    String company;         //要操作的表格

    public Delete() {

    }

    public Delete(Connection conn, indexFrame origin, String company) {
        this.company = company;
        this.origin = origin;
        try {
            this.conn = conn;
            stmt = conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void Delete(String id) {
        try {
            //sql 删除操作语句 delete from _table_ where id =
            String sql = getSQl(id);
            stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    String getSQl(String id) {
        String SQL = "DELETE FROM " + company;
        SQL += " WHERE id='" + id + "'";
        System.out.println(SQL);
        return SQL;
    }
}
