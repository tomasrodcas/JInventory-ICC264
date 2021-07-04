package DAO;
import DBConnection.DBConnection;
import DTO.ItemDTO;

import java.sql.*;

public class ItemDAO {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public ItemDAO(){
        try {
            con = new DBConnection().getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addItem(ItemDTO item){
        boolean existe = checkItemExistence(item);
        if(existe){
            System.out.println("El item ya existe!");
        }else{
            saveItemDB(item);
        }
    }
    private boolean checkItemExistence(ItemDTO item){
        boolean existe = false;
        try{
            String query = "SELECT * FROM items WHERE nombre='"+item.getNombre()+"' AND precio='"+item.getPrecio()+"' AND " +
                    "cantidad='"+item.getCantidad()+"' AND marca='"+item.getMarca()+"' AND rut_proveedor='"+item.getProveedor()+"'";
            rs=stmt.executeQuery(query);
            if(rs.next()){
                existe = true;
            }else{
                existe = false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return existe;
    }
    private void saveItemDB(ItemDTO item){
        try{
            String query = "INSERT INTO items VALUES(NULL, ?,?,?,?,?)";
            pstmt = (PreparedStatement) con.prepareStatement(query);
            pstmt.setString(1, item.getNombre());
            pstmt.setInt(2, item.getCantidad());
            pstmt.setInt(3, item.getPrecio());
            pstmt.setString(4, item.getMarca());
            pstmt.setInt(5, item.getProveedor());
            pstmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ResultSet getItemById(int id){
        rs = null;
        try{
            String query = "SELECT * FROM items WHERE id='"+id+"'";
            rs=stmt.executeQuery(query);

        }catch(Exception e){
            e.printStackTrace();
        }
        return rs;
    }
    public ResultSet getItemsDB(){
        rs = null;
        try{
            String query = "SELECT * FROM items";
            rs = stmt.executeQuery(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return rs;
    }
    public void updateItemById(int id,String nombre, int cantidad, int precio, int proveedor, String marca){
        try{
            String query = "UPDATE items SET nombre=?, cantidad=?, precio=?, rut_proveedor=?, marca=? WHERE id='"+id+"'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, nombre);
            pstmt.setInt(2, cantidad);
            pstmt.setInt(3, precio);
            pstmt.setInt(4, proveedor);
            pstmt.setString(5, marca);
            pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    private boolean checkItemExistenceById(int id ){
        boolean exists = false;
        try{
            String query = "SELECT nombre FROM items WHERE id='"+id+"'";
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

    public void updateStock(int id, int cantidad){
        if(checkItemExistenceById(id)){
            try{
                rs = getItemById(id);
                if(rs.next()){
                    int actualStock = rs.getInt("cantidad");
                    int stockResultante = actualStock + cantidad;

                    String query = "UPDATE items SET cantidad='"+stockResultante+"' WHERE id='"+id+"'";
                    pstmt = con.prepareStatement(query);
                    pstmt.executeUpdate();
                }


            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("El item no existe en los registros");
        }
    }



}
