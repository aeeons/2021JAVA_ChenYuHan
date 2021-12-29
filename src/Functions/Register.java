package Functions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Register {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    String sql;

    public Register() {

    }

    public Register(Connection conn) throws SQLException {
        this.conn = conn;
        stmt = conn.createStatement();
        rs = null;
    }

    public void RegisterBoss(String name, String password, String company) {

        try {
            sql = getRegisterSQL(name, password, company, "Boss");
            stmt.executeUpdate(sql);

            sql = "insert into company(companyName) value('" + company + "')";
            stmt.executeUpdate(sql);

            sql = createTable(company);
            stmt.executeUpdate(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void RegisterStaff(String name, String password, String company) {
        try {
            sql = getRegisterSQL(name, password, company, "Staff");
            stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    String getRegisterSQL(String name, String password, String company, String position) {
        String SQL = "insert into account(name,password,company,position) value(";
        SQL += "'" + name + "','" + password + "','" + company + "','" + position + "')";
        System.out.println(SQL);
        return SQL;
    }

    String createTable(String companyName) {
        String SQL = "CREATE TABLE " + companyName + "(";
        SQL += "id varchar(255),";
        SQL += "name varchar(255),";
        SQL += "price varchar(255),";
        SQL += "date varchar(255),";
        SQL += "type varchar(255),";
        SQL += "place varchar(255),";
        SQL += "mark varchar(255)";
        SQL += ")";
        System.out.println(SQL);
        return SQL;
    }
}
