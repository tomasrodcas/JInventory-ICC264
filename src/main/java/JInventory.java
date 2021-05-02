
import Others.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JInventory {

    public static void main(String[] args) throws SQLException {


        Connection con = DBConnection.getConnection();
        // create a Statement from the connection

        viewTable(con);
        updateItemById(1, 120, 2000, con);
        viewTable(con);

// insert the data
    }

    public static void viewTable(Connection con) throws SQLException {
        String query = "select NOMBRE, ID, STOCK, PRECIO, TIPO, MARCA from ITEMS";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String coffeeName = rs.getString("NOMBRE");
                int supplierID = rs.getInt("ID");
                int price = rs.getInt("STOCK");
                float sales = rs.getFloat("PRECIO");
                String total = rs.getString("TIPO");
                String marca = rs.getString("MARCA");
                System.out.println(coffeeName + ", " + supplierID + ", " + price +
                        ", " + sales + ", " + total+", "+marca);
            }
        } catch (SQLException e) {
            System.out.println("Error con la base de datos");
        }
    }

    public static void updateItemById(int id, int stock, float precio, Connection con){

        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate("update ITEMS set STOCK ='" + stock + "' where ID = '"+ id +"'"  );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }





    }


