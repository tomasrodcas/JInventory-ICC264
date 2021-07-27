package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Maneja la conexion con la base de datos
 */
public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/jinventory";

    /**
     * Realiza la conexion a la base de datos.
     * @return Connection o null en error.
     */
    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL,"root", "");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
