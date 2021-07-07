package DAO;

import DBConnection.DBConnection;
import DTO.ItemDTO;
import DTO.VentaDTO;
import java.sql.*;
import java.util.ArrayList;


public class VentaDAO {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ItemDTO item = null;
    private VentaDTO venta = null;

    public VentaDAO(VentaDTO venta){
        try {
            con = new DBConnection().getConnection();
            stmt = con.createStatement();
            this.venta = venta;
            this.item = new ItemDAO().getItemById(venta.getIdProducto());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public VentaDAO(){
        try {
            con = new DBConnection().getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeSale(boolean saleMaxStock){
        if(this.item !=  null){
            if(checkStockSufficiency()){
                    saveSaleDB();
                    new ItemDAO().updateStock(venta.getIdProducto(), -venta.getCantidadVendida());
            }
            else{
                if(saleMaxStock){
                    venta.setCantidadVendida(item.getCantidad());
                    saveSaleDB();
                    new ItemDAO().updateStock(venta.getIdProducto(), -venta.getCantidadVendida());
                }else{
                    System.out.println("No existe stock o no hay suficiente");
                }
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
            java.sql.Date sqlDate = new java.sql.Date(this.venta.getFecha().getTime());
            pstmt.setDate(6, sqlDate);

            pstmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();

        }
    }
    public void editSaleById(int id, VentaDTO infoVenta){
        this.item = new ItemDAO().getItemById(infoVenta.getIdProducto());
        try{
            String query = "UPDATE ventas SET producto=?, id_producto=?, cantidad=?, total=?, rut_cliente=?, fecha=? " +
                    "WHERE id='"+id+"'";
            pstmt = (PreparedStatement) con.prepareStatement(query);
            int total = this.item.getPrecio()*infoVenta.getCantidadVendida();
            pstmt.setString(1, new VentaDAO(infoVenta).getItem().getNombre());
            pstmt.setInt(2, infoVenta.getIdProducto());
            pstmt.setInt(3, infoVenta.getCantidadVendida());
            pstmt.setInt(4, total);
            pstmt.setInt(5, infoVenta.getRutCliente());
            java.sql.Date sqlDate = new java.sql.Date(infoVenta.getFecha().getTime());
            pstmt.setDate(6, sqlDate);

            pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private boolean checkStockSufficiency(){
        boolean existsStock = false;
        if(this.venta.getCantidadVendida() <= this.item.getCantidad()){
            existsStock = true;
        }
        return existsStock;
    }

    public ArrayList<VentaDTO> getVentasDB(){
        ResultSet rs;
        try{
            String query = "SELECT * FROM ventas";
            rs=stmt.executeQuery(query);
        }catch(SQLException e){
            rs = null;
            e.printStackTrace();
        }
        return rsIntoArrayList(rs);
    }
    private ArrayList<VentaDTO> rsIntoArrayList(ResultSet rs){
        ArrayList<VentaDTO> array = new ArrayList<>();
        try{
            while(rs.next()){
                array.add(new VentaDTO(rs.getInt("id_producto"),
                        rs.getInt("cantidad"), rs.getInt("rut_cliente"), rs.getDate("fecha"),
                        rs.getInt("total")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return array;
    }
    public VentaDTO getVentaById(int id){
        VentaDTO venta = null;
        try{
            String query = "SELECT * FROM ventas WHERE id='"+id+"'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            rs.next();
            venta = new VentaDTO(rs.getInt("id_producto"), rs.getInt("cantidad"), rs.getInt("rut_cliente"),
                    rs.getDate("fecha"), rs.getInt("total"));
        }catch(SQLException e){
            e.printStackTrace();
        }
        return venta;
    }
    public void deleteSaleById(int id){
        if(checkSaleExistenceById(id)){
            try{
                String query = "DELETE FROM ventas WHERE id='"+id+"'";
                pstmt = con.prepareStatement(query);
                pstmt.executeUpdate();

                new ItemDAO().updateStock(this.venta.getIdProducto(), this.venta.getCantidadVendida());

            }catch(SQLException e){
                e.printStackTrace();
            }

        }else{
            System.out.println("La venta no existe en los registros");
        }
    }
    private boolean checkSaleExistenceById(int id){
        boolean exists = false;
        try{
            String query = "SELECT producto FROM ventas WHERE id='"+id+"'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if(rs.next()){
                exists = true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return exists;
    }

    public ItemDTO getItem(){
        return this.item;
    }


}
