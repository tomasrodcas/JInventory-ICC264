package DAO;

import DBConnection.DBConnection;
import DTO.ClienteDTO;

import java.sql.*;

public class ClienteDAO {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public ClienteDAO(){
        try {
            con = new DBConnection().getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addCliente(ClienteDTO cliente){
        if(checkClientExistence(cliente)){
            System.out.println("El usuario ya existe");
        }
        else{
            try{
                String query = "INSERT INTO clientes VALUES (null, ?,?,?,?) ";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, cliente.getNombre());
                pstmt.setString(2, cliente.getEmail());
                pstmt.setInt(3, cliente.getTelefono());
                pstmt.setInt(4, cliente.getRut());
                pstmt.executeUpdate();

            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    public void editClienteById(ClienteDTO cliente, int id){
        if(checkClientExistenceById(id)){
            try{
                String query = "UPDATE clientes SET nombre=?, email=?, telefono=?, rut=? WHERE id='"+id+"'";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, cliente.getNombre());
                pstmt.setString(2, cliente.getEmail());
                pstmt.setInt(3, cliente.getTelefono());
                pstmt.setInt(4, cliente.getRut());
                pstmt.executeUpdate();

            }catch(SQLException e){
                e.printStackTrace();
                System.out.println("El cliente no existe");
            }
        }
    }
    public void deleteUserById(int id){
        if(checkClientExistenceById(id)){
            try{
                String query = "DELETE FROM clientes WHERE id='"+id+"'";
                pstmt = con.prepareStatement(query);
                pstmt.executeUpdate();
            }catch(SQLException e){
                System.out.println("El usuario no existe");
                e.printStackTrace();
            }
        }

    }
    public ResultSet getClientesDB(){
        rs = null;
        try{
            String query = "SELECT * FROM clientes";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return rs;
    }
    public ClienteDTO getClienteById(int id ){
        ClienteDTO cliente = null;
        if(checkClientExistenceById(id)){
            try{
                String query = "SELECT * FROM clientes WHERE id='"+id+"'";
                pstmt = con.prepareStatement(query);
                rs = pstmt.executeQuery();
                rs.next();
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                int rut = rs.getInt("rut");
                int telefono = rs.getInt("telefono");
                cliente =  new ClienteDTO(nombre, email, telefono, rut);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return cliente;

    }
    private boolean checkClientExistenceById(int id){
        boolean existe =  false;
        try{
            String query = "SELECT nombre FROM clientes WHERE id='"+id+"'";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            if(rs.next()){
                existe = true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return existe;
    }
    private boolean checkClientExistence(ClienteDTO cliente){
        boolean existe = false;
        try{
            String query = "SELECT id FROM clientes WHERE nombre=? AND email=? AND telefono=? AND rut=?";
            pstmt =  con.prepareStatement(query);
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setInt(3, cliente.getTelefono());
            pstmt.setInt(4, cliente.getRut());

            rs = pstmt.executeQuery();
            if(rs.next()){
                existe = true;
            }
        }catch(SQLException e ){
            e.printStackTrace();
        }
        return existe;
    }


  
}
