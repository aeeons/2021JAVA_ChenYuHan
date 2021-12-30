package Functions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Register {
    Connection conn = null; //数据库链接对象
    Statement stmt = null;  //数据库操作对象
    ResultSet rs = null;    //返回结果集
    String sql;

    public Register() {

    }

    public Register(Connection conn) throws SQLException {
        this.conn = conn;
        stmt = conn.createStatement();
        rs = null;
    }

    //公司注册
    public void RegisterBoss(String name, String password, String company) {

        try {
            //sql 增加操作 insert into _table_(xxx,yyy,zzz) value(x,y,z)
            sql = getRegisterSQL(name, password, company, "Boss");
            stmt.executeUpdate(sql);

            sql = "insert into company(companyName) value('" + company + "')";
            stmt.executeUpdate(sql);

            //公司注册需要新建表格
            //sql 新建表格语句 create table _name_  (xxx _type_,....)
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
