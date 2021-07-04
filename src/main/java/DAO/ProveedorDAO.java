package DAO;

import DBConnection.DBConnection;
import DTO.ProveedorDTO;

import java.sql.*;

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

    public ResultSet getProveedoresDB() {
        rs = null;
        try {
            String query = "SELECT * FROM proveedores";
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void updateProveedorById(int id, String nombre, int rut, String email, int telefono) {
        try {
            String query = "UPDATE proveedores SET nombre=?, rut=?, email=?, telefono=? WHERE id='" + id + "'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, nombre);
            pstmt.setInt(2, rut);
            pstmt.setString(3, email);
            pstmt.setInt(4, telefono);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void deleteProveedorById(int id) {
        try {
            String query = "DELETE FROM proveedores WHERE id='" + id + "'";
            pstmt.executeUpdate(query);
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
            rs = pstmt.executeQuery(query);
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
