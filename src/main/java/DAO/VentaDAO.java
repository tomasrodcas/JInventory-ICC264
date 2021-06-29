package DAO;

import DBConnection.DBConnection;
import DTO.ItemDTO;
import DTO.VentaDTO;
import DAO.VentaDAO;



import java.sql.*;

public class VentaDAO {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ItemDTO item;
    private VentaDTO venta;

    public VentaDAO(VentaDTO venta){
        try {
            con = new DBConnection().getConnection();
            stmt = con.createStatement();
            this.venta = venta;
            this.item = getItemById();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void executeSale(){
        if(this.item !=  null){
            if(checkStockSufficiency()){
                int cantidadRestante = this.item.getCantidad() - this.venta.getCantidadVendida();
                String query = "UPDATE items SET cantidad = '"+cantidadRestante+"' WHERE id='"+this.venta.getIdProducto()+"'";
                try{
                    pstmt = (PreparedStatement) con.prepareStatement(query);
                    pstmt.executeUpdate();
                    saveSaleDB();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("No existe stock o no hay suficiente");
            }
        }else{
            System.out.println("El item no existe");
        }

    }
    private void saveSaleDB(){
        try{
            String query = "INSERT INTO ventas VALUES (null, ?,?,?,?,?,?)";
            pstmt = (PreparedStatement) con.prepareStatement(query);
            int total = this.item.getPrecio()*this.venta.getCantidadVendida();
            pstmt.setString(1, this.item.getNombre());
            pstmt.setInt(2,this.venta.getIdProducto());
            pstmt.setInt(3, this.venta.getCantidadVendida());
            pstmt.setInt(4, total);
            pstmt.setInt(5, this.venta.getRutCliente());
            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
            pstmt.setDate(6, sqlDate);

            pstmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();

        }
    }

    private ItemDTO getItemById(){
        ItemDTO item = null;
        try{
            rs = new ItemDAO().getItemById(this.venta.getIdProducto());
            if(rs.next()){
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                int precio = rs.getInt("precio");
                String marca = rs.getString("marca");
                String proveedor = rs.getString("proveedor");

                item = new ItemDTO(nombre, cantidad, precio, proveedor, marca);
            }
            else{
                item = null;
            }
        }catch(Exception e){
            item = null;
            e.printStackTrace();
        }
        return item;
    }

    private boolean checkStockSufficiency(){
        boolean existsStock = false;
        if(this.venta.getCantidadVendida() <= this.item.getCantidad()){
            existsStock = true;
        }
        return existsStock;
    }


}
