package DAO;

import DBConnection.DBConnection;
import DTO.ItemDTO;
import java.sql.*;
import java.util.ArrayList;

/**
 * Clase encargada de manejar el CRUD de items en la BD
 */
public class ItemDAO {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    /**
     * Constructor que se encarga de obtener la conexion con la base de datos
     */
    public ItemDAO(){
        try {
            con = new DBConnection().getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Se encarga de añadir un item a la base de datos
     * @param item Objeto ItemDTO que contiene la informacion del item
     */
    public boolean addItem(ItemDTO item){
        boolean existe = checkItemExistence(item);

        if(!existe){

            try{
                String query = "INSERT INTO items VALUES(NULL, ?,?,?,?,?)";
                pstmt = (PreparedStatement) con.prepareStatement(query);
                pstmt.setString(1, item.getNombre());
                pstmt.setInt(2, item.getCantidad());
                pstmt.setInt(3, item.getPrecio());
                pstmt.setString(4, item.getMarca());
                pstmt.setInt(5, item.getIdProveedor());
                pstmt.executeUpdate();
                return true;
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Valida la existencia del item a crear en la base de datos
     * @param item Objeto ItemDTO con la informacion del item
     * @return booleano si existe o no
     */
    private boolean checkItemExistence(ItemDTO item){
        boolean existe = false;
        try{
            String query = "SELECT * FROM items WHERE nombre='"+item.getNombre()+"' AND precio='"+item.getPrecio()+"' AND " +
                    "cantidad='"+item.getCantidad()+"' AND marca='"+item.getMarca()+"' AND id_proveedor='"+item.getIdProveedor()+"'";
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

    /**
     * Obtiene un item de la BD mediante su identificador
     * @param id Identificador del cliente
     * @return Objeto ItemDTO que contiene la informacion del item
     */
    public ItemDTO getItemById(int id){
        ItemDTO item = null;
        try{
            String query = "SELECT * FROM items i INNER JOIN proveedores p ON i.id_proveedor" +
                    "=p.id WHERE i.id='"+id+"'";
            rs=stmt.executeQuery(query);
            rs.next();
            String nombre = rs.getString("nombre");
            int precio = rs.getInt("precio");
            int cantidad = rs.getInt("cantidad");
            String marca = rs.getString("marca");
            String rutProveedor =  rs.getString("p.rut");
            int idProveedor = rs.getInt("p.id");
            item = new ItemDTO(id, nombre, cantidad, precio, idProveedor, rutProveedor, marca);

        }catch(Exception e){
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Obtiene todos los items almacenados en la base de datos.
     * @return ArrayList de ItemDTO que contienen la informacion de cada item
     */
    public ArrayList<ItemDTO> getItemsDB(){
        rs = null;
        try{
            String query = "SELECT * FROM items i INNER JOIN proveedores p ON i.id_proveedor = p.id";
            rs = stmt.executeQuery(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return rsIntoArrayList(rs);
    }

    /**
     * Transforma el ResultSet de items de la base de datos en un ArrayList de
     * ItemDTO con la informacion de todos los items
     * @param rs ResultSet con los items de la BD
     * @return ArrayList de ItemDTO con la informacion de los items
     */
    private ArrayList<ItemDTO> rsIntoArrayList(ResultSet rs){
        ArrayList<ItemDTO> array = new ArrayList<>();
        try{
            while(rs.next()){
                array.add(new ItemDTO(rs.getInt("id"), rs.getString("nombre"),
                        rs.getInt("cantidad"), rs.getInt("precio"),
                        rs.getInt("p.id"), rs.getString("p.rut") ,
                        rs.getString("marca")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return array;
    }

    /**
     * Actualiza la informacion de un item mediante su identificador
     * @param id Identificador del item
     * @param item Objeto ItemDTO que contiene la informacion del item
     */
    public boolean editItemById(int id, ItemDTO item){
        if(checkItemExistenceById(id)){
            try{
                String query = "UPDATE items SET nombre=?, cantidad=?, precio=?, rut_proveedor=?, marca=? WHERE id='"+id+"'";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, item.getNombre());
                pstmt.setInt(2, item.getCantidad());
                pstmt.setInt(3, item.getPrecio());
                pstmt.setInt(4, item.getIdProveedor());
                pstmt.setString(5, item.getMarca());
                pstmt.executeUpdate();

                return true;
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     * Revisa la existencia de un item mediante su identificador
     * @param id identificador del item
     * @return booleano si existe o no
     */
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

    /**
     * Actualiza el stock de un item una cantidad definida (En caso de no existir suficiente
     * este queda en 0)
     * @param id identificador del item
     * @param cantidad cantidad a añadir o substraer del stock
     */
    public boolean updateStock(int id, int cantidad){

        if(checkItemExistenceById(id)){

            try{
                ItemDTO item = getItemById(id);
                int stockResultante = Math.max(0, item.getCantidad() + cantidad);
                String query = "update items set cantidad='"+stockResultante+"' where id='"+id+"'";
                pstmt = con.prepareStatement(query);
                pstmt.executeUpdate();

                return true;
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Elimina un item mediante su identificador id
     * @param id identificador del item
     */
    public boolean deleteItemById(int id){
        if(checkItemExistenceById(id)){
            try{
                String query = "DELETE FROM items WHERE id='"+id+"'";
                pstmt = con.prepareStatement(query);
                pstmt.executeUpdate();

                return true;

            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public ArrayList<ItemDTO> getItemsSinStock(){
        ArrayList<ItemDTO> itemsSinStock = new ArrayList<>();
        ArrayList<ItemDTO> items = getItemsDB();

        for(ItemDTO item : items){
            if(item.getCantidad() == 0){
                itemsSinStock.add(item);
            }
        }
        return itemsSinStock;
    }



}
