package Others;

import java.sql.*;
import java.util.Scanner;

public class Venta {
    public Connection con;
























































    public int getStockActual(int id) throws SQLException {
        int stock = 0;
        PreparedStatement myStmt = con.prepareStatement("SELECT STOCK FROM items WHERE ID = '" + id + "'");
        ResultSet rs = myStmt.executeQuery();
        if (rs.next()) {
            stock = rs.getInt("STOCK");

        }
        return stock;
    }

    public void updateItemById(int id, int cantidad, int stockActual){
        int stock = stockActual - cantidad;
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate("update ITEMS set STOCK ='" + stock+ "' where ID = '"+ id +"'"  );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public  void pedirSeleccionProducto(){
        System.out.println("Ingrese el id del item");
    }

    public void pedirCantidadProducto(){
        System.out.println("Ingrese la cantidad a vender");
    }
    public int leerNumero(){
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }


}
