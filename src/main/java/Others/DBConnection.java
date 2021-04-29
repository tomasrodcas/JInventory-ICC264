package Others;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/jinventory";

    public static Connection getConnection() {
        Connection con;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL,"root", "root");
            System.out.println("Connection is Successful to the database" + URL);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return con;
    }
}