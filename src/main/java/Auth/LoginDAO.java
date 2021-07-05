package Auth;

import DBConnection.DBConnection;
import java.sql.*;

public class LoginDAO {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public LoginDAO(){
        try {
            con = new DBConnection().getConnection();
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean login(LoginDTO loginInfo) {
        boolean loggedIn = false;
        String passwordDB = retrieveUsernamePassword(loginInfo);
        if(!passwordDB.equals("")){
            if(passwordDB.equals(loginInfo.getPassword())){
                loggedIn = true;
            }
        }
        return loggedIn;
    }
    private boolean checkUsernameExistence(LoginDTO loginInfo){
        boolean existe = false;
        try{
            String query = "SELECT nombre FROM usuarios WHERE usuario='"+loginInfo.getUsername()+"'";
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
    private String retrieveUsernamePassword(LoginDTO loginInfo){
        if(checkUsernameExistence(loginInfo)){
            try{
                String query = "SELECT password FROM usuarios WHERE usuario='"+loginInfo.getUsername()+"'";
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
    public boolean checkAdminCredentials(LoginDTO loginInfo){
        if(login(loginInfo) && getUserType(loginInfo) == 1){
            return true;
        }
        else{
            return false;
        }
    }
    private int getUserType(LoginDTO loginInfo){
        if(checkUsernameExistence(loginInfo)){
            try{
                String query = "SELECT tipo_usuario FROM usuarios WHERE usuario='"+loginInfo.getUsername()+"'";
                pstmt = con.prepareStatement(query);
                rs = pstmt.executeQuery();
                rs.next();
                return rs.getInt("tipo_usuario");
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return 0;
    }

}
