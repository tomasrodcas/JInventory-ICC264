package DAO;

import DBConnection.DBConnection;
import DTO.ClienteDTO;
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
            java.sql.Date sqlDate = new java.sql.Date(this.venta.getFecha().getTime());
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
                        rs.getInt("cantidad"), rs.getInt("rut_cliente"), rs.getDate("fecha")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return array;
    }
    public void deleteSaleById(int id){
        if(checkSaleExistence(id)){
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
    private boolean checkSaleExistence(int id){
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




}
