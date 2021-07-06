package DAO;
import DBConnection.DBConnection;
import DTO.ClienteDTO;
import DTO.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

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

    public ItemDTO getItemById(int id){
        ItemDTO item = null;
        try{
            String query = "SELECT * FROM items WHERE id='"+id+"'";
            rs=stmt.executeQuery(query);
            rs.next();
            String nombre = rs.getString("nombre");
            int precio = rs.getInt("precio");
            int cantidad = rs.getInt("cantidad");
            String marca = rs.getString("marca");
            int rut_proveedor = rs.getInt("rut_proveedor");
            item = new ItemDTO(nombre, precio, cantidad, rut_proveedor, marca);

        }catch(Exception e){
            e.printStackTrace();
        }
        return item;
    }
    public ArrayList<ItemDTO> getItemsDB(){
        rs = null;
        try{
            String query = "SELECT * FROM items";
            rs = stmt.executeQuery(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return rsIntoArrayList(rs);
    }
    private ArrayList<ItemDTO> rsIntoArrayList(ResultSet rs){
        ArrayList<ItemDTO> array = new ArrayList<>();
        try{
            while(rs.next()){
                array.add(new ItemDTO(rs.getString("nombre"), rs.getInt("precio"),
                        rs.getInt("cantidad"), rs.getInt("rut_proveedor"), rs.getString("marca")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return array;
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
                ItemDTO item = getItemById(id);
                int stockresultante = item.getCantidad() + cantidad;
                String query = "update items set cantidad='"+stockresultante+"' where id='"+id+"'";
                pstmt = con.prepareStatement(query);
                pstmt.executeUpdate();

            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("El item no existe en los registros");
        }
    }



}
