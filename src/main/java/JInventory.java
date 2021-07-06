import java.sql.SQLException;

import Auth.LoginDAO;
import Auth.LoginDTO;
import DAO.*;
import DTO.*;
import Utils.DataValidation;
import Window.Login;

import javax.xml.crypto.Data;

public class JInventory {

    public static void main(String[] args) throws SQLException {
        new ReporteDAO();
        Login interfaz = new Login();
        interfaz.setVisible(true);
    }

}








