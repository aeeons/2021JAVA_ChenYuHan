import SwingGUI.loginFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class start {
    //数据库操作对象
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        Class.forName(driver);

        Connection conn = DriverManager.getConnection(url, user, password);
        new loginFrame(conn);
    }
}
