package DAO;

import DBConnection.DBConnection;
import DTO.ProveedorDTO;
import java.sql.*;
import java.util.ArrayList;

/**
 * Clase encargada de realziar el CRUD de proveedores en la base de datos
 */
public class ProveedorDAO {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    /**
     * Constructor de la clase encargado de obtener la conexion con la base de datos
     */
    public ProveedorDAO() {
        try {
            con = new DBConnection().getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene todos los proveedores almacenados en la base de datos
     * @return ArrayList de ProveedorDTO que contiene la informacion de cada proveedor
     */
    public ArrayList<ProveedorDTO> getProveedoresDB() {
        ArrayList<ProveedorDTO> proveedores = new ArrayList<>();
        try {
            String query = "SELECT * FROM proveedores";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            proveedores = rsIntoArray(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedores;
    }

    /**
     * Transforma el ResultSet de todos los proveedores de la base de datos
     * en un ArrayList de ProveedorDTO que contiene la informacion de todos los proveedores
     * de la BD
     * @param rs ResultSet de los proveedores en la base de datos
     * @return ArrayLIst de ProveedorDTO con todos los proveedores
     */
    private ArrayList<ProveedorDTO> rsIntoArray(ResultSet rs){
        ArrayList<ProveedorDTO> proveedores = new ArrayList<>();
        try{
            while(rs.next()){
                proveedores.add(new ProveedorDTO(rs.getInt("id"),rs.getString("nombre"),
                        rs.getString("rut"), rs.getString("email"),
                        rs.getInt("telefono")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return proveedores;
    }

    /**
     * Actualiza la informacion de un proveedor en la BD mediante su identificador
     * @param id identificador del proveedor
     * @param proveedor Objeto ProveedorDTO con la informacion nueva del proveedor
     * @return boolean si fue exitoso o no
     */
    public boolean editProveedorById(int id, ProveedorDTO proveedor) {
        try {
            String query = "UPDATE proveedores SET nombre=?, rut=?, email=?, telefono=? WHERE id='" + id + "'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, proveedor.getNombre());
            pstmt.setString(2, proveedor.getRut());
            pstmt.setString(3, proveedor.getEmail());
            pstmt.setInt(4, proveedor.getTelefono());
            pstmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    /**
     * Obtiene un proveedor de la base de datos mediante su identificador
     * @param id identificador del proveedor
     * @return Objeto ProveedorDTO conteniendo la informacion del proveedor
     */
    public ProveedorDTO getProveedorById(int id){
        ProveedorDTO proveedor = null;
        try{
            String query = "SELECT * FROM proveedores WHERE id='"+id+"'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            rs.next();
            proveedor = new ProveedorDTO(id, rs.getString("nombre"),
                    rs.getString("rut"), rs.getString("email"),
                    rs.getInt("telefono"));

        }catch(SQLException e){
            e.printStackTrace();
        }
        return proveedor;
    }

    /**
     * Revisa que el proveedor exista en la base de datos mediante el id
     * @param id identificador del proveedor
     * @return booleano si existe o no
     */
    private boolean checkProveedorExistenceById(int id){
        try{
            String query = "SELECT nombre FROM proveedores WHERE id='"+id+"'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Elimina un proveedor de la base de datos mediante su identificador
     * @param id identificador del proveedor
     * @return boolean si fue exitoso o no
     */
    public boolean deleteProveedorById(int id) {
        if(checkProveedorExistenceById(id)){
            try {
                String query = "DELETE FROM proveedores WHERE id='"+id+"'";
                pstmt = con.prepareStatement(query);
                pstmt.executeUpdate();

                return true;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     * Añade un proveedor a la base de datos
     * @param proveedor Objeto ProveedorDTO que contiene la informacion del proveedor
     * @return boolean si fue exitoso o no
     */
    public boolean addProveedor(ProveedorDTO proveedor) {
        boolean existe = checkProveedorExistence(proveedor);
        if (!existe) {
            return saveProveedorDB(proveedor);
        }
        return false;
    }

    /**
     * Valida la existencia de un proveedor en la base de datos con la mismas entradas que el proveedor
     * proporcionado
     * @param proveedor ProveedorDTO con la informacion del proveedor
     * @return booleano si existe o no en la BD
     */
    private boolean checkProveedorExistence(ProveedorDTO proveedor) {

        try {
            String query = "SELECT rut FROM proveedores WHERE rut = '"+proveedor.getRut()+"'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Se encarga de guardar el nuevo proveedor en la base de datos
     * @param proveedor Objeto ProveedorDTO con la informacion del proveedor
     * @return boolean si fue exitoso o no
     */
    private boolean saveProveedorDB(ProveedorDTO proveedor) {
        try {
            String query = "INSERT INTO proveedores VALUES (null, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, proveedor.getNombre());
            pstmt.setString(2, proveedor.getRut());
            pstmt.setString(3, proveedor.getEmail());
            pstmt.setInt(4, proveedor.getTelefono());

            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
