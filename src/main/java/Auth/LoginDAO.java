package Auth;

import DBConnection.DBConnection;
import java.sql.*;

/**
 * Clase encargada de realizar el login comparando con los datos de la BD
 */
public class LoginDAO {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    /**
     * Constructor encargado de realizar la conexion con la base de datos
     */
    public LoginDAO(){
        try {
            con = new DBConnection().getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Realiza el login, comparando la informacion recibida con la base de datos, retorna un booleano dependiendo
     * si los datos coinciden o no
     * @param loginInfo objeto LoginDTO con la informacion proporcionada para el login
     * @return booleano que describe si los datos coinciden o no
     */
    public boolean login(LoginDTO loginInfo) {
        boolean loggedIn = false;
        String passwordDB = retrieveUsernamePassword(loginInfo);
        if(!passwordDB.equals("")){
            if(passwordDB.equals(loginInfo.password)){
                loggedIn = true;
            }
        }
        return loggedIn;
    }

    /**
     * Revisa la existencia del username en la base de datos
     * @param loginInfo Objeto LoginDTO con la informacion del login
     * @return booleano si existe o no
     */
    private boolean checkUsernameExistence(LoginDTO loginInfo){
        boolean existe = false;
        try{
            String query = "SELECT nombre FROM usuarios WHERE usuario='"+loginInfo.username+"'";
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
     * Obtiene la password asociada al username en la base de datos
     * @param loginInfo Objeto LoginDTO con la informacion del login
     * @return String con la password del usuario
     */
    private String retrieveUsernamePassword(LoginDTO loginInfo){
        if(checkUsernameExistence(loginInfo)){
            try{
                String query = "SELECT password FROM usuarios WHERE usuario='"+loginInfo.username+"'";
                pstmt = con.prepareStatement(query);
                rs = pstmt.executeQuery();
                rs.next();
                return rs.getString("password");

            }catch(SQLException e){
                e.printStackTrace();
                return "";
            }
        }
        else{
            System.out.println("El usuario no existe");
            return "";
        }

    }

    /**
     * Revisa si las credenciales otorgadas para realizar el login son de administrador o usuario normal
     * @param loginInfo objeto LoginDTO con la informacion del login
     * @return booleano que describe si las credenciales son de administrador o no
     */
    public boolean checkAdminCredentials(LoginDTO loginInfo){
        if(login(loginInfo) && getUserType(loginInfo) == 1){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Obtiene el tipo de usuario del usuario logueado de la base de datos
     * @param loginInfo Objeto LoginDTO con la informacion del login
     * @return int tipo de usuario asignado al username
     */
    private int getUserType(LoginDTO loginInfo){
        int tipo_usuario = 0;
        if(checkUsernameExistence(loginInfo)){
            try{
                String query = "SELECT tipo_usuario FROM usuarios WHERE usuario='"+loginInfo.username+"'";
                pstmt = con.prepareStatement(query);
                rs = pstmt.executeQuery();
                rs.next();
                tipo_usuario = rs.getInt("tipo_usuario");
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return tipo_usuario;
    }

}
