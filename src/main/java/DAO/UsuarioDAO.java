package DAO;

import DBConnection.DBConnection;
import DTO.UsuarioDTO;
import java.sql.*;
import java.util.ArrayList;

/**
 * Clase encargada de realizar el CRUD de usuarios en la base de datos
 */
public class UsuarioDAO {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    /**
     * Constructor de la clase que obtiene la conexion a la base de datos
     */
    public UsuarioDAO(){
        try {
            con = new DBConnection().getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Añade un usuario a la base de datos
     * @param usuario Objeto UsuarioDTO que contiene la informacion del usuario
     * @return boolean si fue exitoso o no
     */
    public boolean addUser(UsuarioDTO usuario){
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

                int id = getLastUserId();
                boolean loginAttemptsAdded = addLoginAttemptsEntry(id);
                
                if(!loginAttemptsAdded){
                    deleteUserById(id);
                }else{
                    return true;
                }

            }catch(SQLException e){
                e.printStackTrace();
            }

        }
        return false;
    }

    /**
     * Añade una nueva entrada a la tabla login_attemps con el id del nuevo usuario
     * @param id identificador del usuario
     * @return booleano si fue exitoso o no
     */
    private boolean addLoginAttemptsEntry(int id){
        try{
            String query = "INSERT INTO login_attempts VALUES ('"+id+"','"+0+"')";
            pstmt = con.prepareStatement(query);
            pstmt.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();

        }
        return false;
    }

    /**
     * Obtiene el identificador del ultimo usurio existente en la base de datos
     * @return int identifcador del ultimo usuario
     */
    private int getLastUserId(){
        ArrayList<UsuarioDTO> usuarios = getUsersDB();
        return usuarios.get(usuarios.size()-1).getId();
    }

    /**
     * Edita un usuario en la base de datos mediante su identificador
     * @param id identificador del usuario
     * @param usuario Objeto UsuarioDTO que contiene la informacion del usuario
     * @return boolean si fue exitoso o no
     */
    public boolean editUserById(int id, UsuarioDTO usuario){
        if(checkUserExistenceById(id)) {
            try {
                String query = "UPDATE usuarios SET nombre=?, usuario=?, password=? WHERE id='" + id + "'";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, usuario.getNombre());
                pstmt.setString(2, usuario.getUsuario());
                pstmt.setString(3, usuario.getPassword());


                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Elimina un usuario de la base de datos mediante su identificador
     * @param id identificador del usuario
     * @return boolean si fue exitoso o no
     */
    public boolean deleteUserById(int id){
        if(checkUserExistenceById(id)){
            try{
                String query = "DELETE FROM usuarios WHERE id='"+id+"'";
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
     * Valida que no exista un usuario con las mismas entradas en la base de datos
     * @param usuario Objeto UsuarioDTO que contiene la informacion del usuario
     * @return booleano si existe o no
     */
    private boolean checkUserExistence(UsuarioDTO usuario){
        boolean existe = false;
        try{
            String query = "SELECT nombre FROM usuarios WHERE usuario='"+usuario.getUsuario()
                    + "' AND nombre='"+usuario.getNombre()+"' ";
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

    /**
     * Checkea si existe una entrada en usuarios mediante el identificador
     * @param id identificador del usuario
     * @return boolean si existe o no
     */
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

    /**
     * Obtiene un usuario de la base de datos mediante su identificador
     * @param id identificador del usuario
     * @return Objeto UsuarioDTO que contiene la informacion del usuario
     */
    public UsuarioDTO getUserById(int id){
        UsuarioDTO usuario = null;
        if(checkUserExistenceById(id)){
            try{
                String query = "SELECT * FROM usuarios u INNER JOIN login_attempts l" +
                        "ON u.id = l.id_usuario WHERE id= '"+id+"'";
                pstmt = con.prepareStatement(query);
                rs = pstmt.executeQuery();
                rs.next();
                String user = rs.getString("usuario");
                String  nombre = rs.getString("nombre");
                String password = rs.getString("password");
                int login_attempts = rs.getInt("l.login_attempts");
                int tipo_usuario =  rs.getInt("tipo_usuario");

                usuario = new UsuarioDTO(id, nombre, user, password, login_attempts, tipo_usuario);

            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return usuario;
    }

    /**
     * Obtiene todos los usuarios almacenados en la base de datos
     * @return ArrayList de UsuarioDTO conteniendo toda la informacion de los usuarios
     */
    public ArrayList<UsuarioDTO> getUsersDB(){
        ArrayList<UsuarioDTO> usuarios = new ArrayList<>();
        try{
            String query = "SELECT * FROM usuarios u INNER JOIN login_attempts l" +
                    "ON u.id = l.id_usuario";

            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            usuarios = rsIntoArray(rs);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return usuarios;
    }

    /**
     * Transforma el ResultSet obtenido de los usuarios de la base de datos en un ArrayList de UsuarioDTO que contiene
     * la informacion de todos los usuarios en la DB
     * @param rs ResultSet con todos los usuarios de la DB
     * @return ArrayList de UsuarioDTO con todos los usuarios
     */
    private ArrayList<UsuarioDTO> rsIntoArray(ResultSet rs){
        ArrayList<UsuarioDTO> usuarios = new ArrayList<>();
        try{
            while(rs.next()){
                usuarios.add(new UsuarioDTO(rs.getInt("id"),rs.getString("nombre"),
                        rs.getString("usuario"), rs.getString("password"),
                        rs.getInt("l.login_attempts"), rs.getInt("tipo_usuario")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return usuarios;
    }



}
