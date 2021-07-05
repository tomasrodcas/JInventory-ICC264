import java.sql.SQLException;
import DAO.*;
import DTO.*;

public class JInventory {

    public static void main(String[] args) throws SQLException {

        new VentaDAO(new VentaDTO(1, 4, 200798245)).executeSale();

    }

}








