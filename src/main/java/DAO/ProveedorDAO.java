package DAO;

import DBConnection.DBConnection;
import DTO.ProveedorDTO;
import java.sql.*;
import java.util.ArrayList;

public class ProveedorDAO {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public ProveedorDAO() {
        try {
            con = new DBConnection().getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    private ArrayList<ProveedorDTO> rsIntoArray(ResultSet rs){
        ArrayList<ProveedorDTO> proveedores = new ArrayList<>();
        try{
            while(rs.next()){
                proveedores.add(new ProveedorDTO(rs.getInt("id"),rs.getString("nombre"), rs.getInt("rut")
                        , rs.getString("email"), rs.getInt("telefono")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return proveedores;
    }

    public void updateProveedorById(int id, ProveedorDTO proveedor) {
        try {
            String query = "UPDATE proveedores SET nombre=?, rut=?, email=?, telefono=? WHERE id='" + id + "'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, proveedor.getNombre());
            pstmt.setInt(2, proveedor.getRut());
            pstmt.setString(3, proveedor.getEmail());
            pstmt.setInt(4, proveedor.getTelefono());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public ProveedorDTO getProveedorById(int id){
        ProveedorDTO proveedor = null;
        try{
            String query = "SELECT * FROM proveedores WHERE id='"+id+"'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            rs.next();
            proveedor = new ProveedorDTO(id, rs.getString("nombre"), rs.getInt("rut"),
                    rs.getString("email"), rs.getInt("telefono"));
        }catch(SQLException e){
            e.printStackTrace();
        }
        return proveedor;
    }

    public void deleteProveedorById(int id) {
        try {
            String query = "DELETE FROM proveedores WHERE id='" + id + "'";
            pstmt = con.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProveedor(ProveedorDTO proveedor) {
        boolean existe = checkProveedorExistence(proveedor);
        if (existe) {
            System.out.println("El proveedor ya existe");
        } else {
            saveProveedorDB(proveedor);
        }
    }

    private boolean checkProveedorExistence(ProveedorDTO proveedor) {

        try {
            String query = "SELECT rut FROM proveedores WHERE rut = '" + proveedor.getRut() + "'";
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

    private void saveProveedorDB(ProveedorDTO proveedor) {
        try {
            String query = "INSERT INTO proveedores VALUES (null, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, proveedor.getNombre());
            pstmt.setInt(2, proveedor.getRut());
            pstmt.setString(3, proveedor.getEmail());
            pstmt.setInt(4, proveedor.getTelefono());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
