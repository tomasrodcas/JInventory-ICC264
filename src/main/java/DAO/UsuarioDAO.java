package DAO;

import DBConnection.DBConnection;
import DTO.UsuarioDTO;
import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public UsuarioDAO(){
        try {
            con = new DBConnection().getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser(UsuarioDTO usuario){
        if(checkUserExistence(usuario)){
            System.out.println("El usuario ya existe");
        }else{
            try{
                String query = "INSERT INTO usuarios VALUES (null, ?,?,?,?)";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, usuario.getNombre());
                pstmt.setString(2, usuario.getUsuario());
                pstmt.setString(3, usuario.getPassword());
                pstmt.setInt(4, 0);
                pstmt.executeUpdate();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void editUserById(int id, UsuarioDTO usuario){
        if(checkUserExistenceById(id)){
            try{
                String query = "UPDATE usuarios SET nombre=?, usuario=?, password=? WHERE id='"+id+"'";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, usuario.getNombre());
                pstmt.setString(2, usuario.getUsuario());
                pstmt.setString(3, usuario.getPassword());
                pstmt.executeUpdate();

            }catch(SQLException e){
                e.printStackTrace();
            }
        }else{
            System.out.println("El usuario no existe");
        }
    }

    public void deleteUserById(int id){
        if(checkUserExistenceById(id)){
            try{
                String query = "DELETE FROM usuarios WHERE id='"+id+"'";
                pstmt = con.prepareStatement(query);
                pstmt.executeUpdate();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("El usuario no existe");
        }
    }

    private boolean checkUserExistence(UsuarioDTO usuario){
        boolean existe = false;
        try{
            String query = "SELECT nombre FROM usuarios WHERE usuario='"+usuario.getUsuario()+"' AND nombre='"+usuario.getNombre()+"' ";
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

    private boolean checkUserExistenceById(int id){
        boolean existe = false;
        try{
            String query = "SELECT usuario FROM usuarios WHERE id='"+id+"'";
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

    public UsuarioDTO getUserById(int id){
        UsuarioDTO usuario = null;
        if(checkUserExistenceById(id)){
            try{
                String query = "SELECT * FROM usuarios WHERE id= '"+id+"'";
                pstmt = con.prepareStatement(query);
                rs = pstmt.executeQuery();
                rs.next();
                String user = rs.getString("usuario");
                String  nombre = rs.getString("nombre");
                String password = rs.getString("password");

                usuario = new UsuarioDTO(id, nombre, user, password);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return usuario;
    }

    public ArrayList<UsuarioDTO> getUsersDB(){
        ArrayList<UsuarioDTO> usuarios = new ArrayList<>();
        try{
            String query = "SELECT * FROM usuarios";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            usuarios = rsIntoArray(rs);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return usuarios;
    }

    private ArrayList<UsuarioDTO> rsIntoArray(ResultSet rs){
        ArrayList<UsuarioDTO> usuarios = new ArrayList<>();
        try{
            while(rs.next()){
                usuarios.add(new UsuarioDTO(rs.getInt("id"),rs.getString("nombre"), rs.getString("usuario")
                        , rs.getString("password")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return usuarios;
    }



}
