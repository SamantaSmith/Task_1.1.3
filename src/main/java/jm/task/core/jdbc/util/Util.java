package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    static Connection dbConnection;

    public static Connection getDbConnection() {

        try {
            String userName = "root";
            String passWord = "12345";
            String connectionUrl = "jdbc:mysql://127.0.0.1:3306/itproger?useUnicode=true&serverTimezone=UTC&useSSL=false";
            Class.forName("com.mysql.jdbc.Driver");
            dbConnection = DriverManager.getConnection(connectionUrl, userName, passWord);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }
}
