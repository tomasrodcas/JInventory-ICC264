import java.sql.SQLException;

import Reporte.Reporte;
import Window.Login;

public class JInventory {

    public static void main(String[] args) throws SQLException {
        new Reporte();
        Login interfaz = new Login();
        interfaz.setVisible(true);
    }

}








