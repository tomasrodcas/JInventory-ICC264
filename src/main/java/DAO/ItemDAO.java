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
                    "cantidad='"+item.getCantidad()+"' AND marca='"+item.getMarca()+"' AND proveedor='"+item.getProveedor()+"'";
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
}
