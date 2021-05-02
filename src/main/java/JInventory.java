
import Others.DBConnection;
import Others.Venta;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JInventory {

    public static void main(String[] args) throws SQLException {


        Connection con = DBConnection.getConnection();
        // create a Statement from the connection
        while(true){
            menu();
            ejecutar(leerNumero(), con);

        }
// insert the data
    }
    public static void menu(){
        System.out.println("Ingrese si desea realizar una venta");
        System.out.println("[1] Si");
        System.out.println("[2] No");
    }

    public static int leerNumero(){
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    public static void ejecutar(int eleccion, Connection con) throws SQLException {
        switch(eleccion){
            case 1:
                Venta venta = new Venta(con);
                break;
            case 2:
                System.exit(1);
                System.out.println("Finalizando...");
        }
    }
}








