package Others;

import java.sql.*;
import java.util.Scanner;

public class Venta {
    public Connection con;
    public double total;


    public  Venta(Connection con) throws SQLException {
        this.con = con;
        viewTable();
        pedirSeleccionProducto();
        int id = leerNumero();
        Item item = cargarProducto(con, id);
        pedirCantidadProducto();
        int cantidad = leerNumero();

        updateItemById(id, cantidad, item.getStock());
        this.total = cantidad*item.getPrecio();
        System.out.println("Total venta: "+this.total);
        System.out.println("Tabla Actualizada");
        System.out.println(" ");
        viewTable();


    }
    public Item cargarProducto(Connection con, int id){
        String query = "select NOMBRE, STOCK, PRECIO, TIPO, MARCA from ITEMS WHERE ID = '" + id + "'";
        Item item = null;
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                String nombre = rs.getString("NOMBRE");
                int stock = rs.getInt("STOCK");
                float precio = rs.getFloat("PRECIO");
                String tipo = rs.getString("TIPO");
                String marca = rs.getString("MARCA");
                item = new Item( id, nombre, stock, precio, tipo, marca);
            }
        } catch (SQLException e) {
            System.out.println("Error con la base de datos");

        }
        return item;
    }

    public void viewTable() throws SQLException {
        String query = "select NOMBRE, ID, STOCK, PRECIO, TIPO, MARCA from ITEMS";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String nombre = rs.getString("NOMBRE");
                int id = rs.getInt("ID");
                int stock = rs.getInt("STOCK");
                float precio = rs.getFloat("PRECIO");
                String tipo = rs.getString("TIPO");
                String marca = rs.getString("MARCA");
                System.out.println("Nombre: "+nombre + ", ID: " + id + ", Stock: " + stock +
                        ", Precio " + precio + ", Tipo " + tipo+", Marca "+marca);
            }
        } catch (SQLException e) {
            System.out.println("Error con la base de datos");
        }
    }

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
        System.out.println(" ");
        System.out.println("Ingrese el ID del item");
    }

    public void pedirCantidadProducto(){
        System.out.println("Ingrese la cantidad a vender");
    }
    public int leerNumero(){
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }


}
