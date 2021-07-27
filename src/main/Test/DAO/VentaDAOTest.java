package DAO;

import DTO.VentaDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VentaDAOTest {

    @Order(1)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void executeSale(int i) {
        int id = 23+i;
        int[] productos = {1, 2, 4,6};
        int[] cantidades = {1, 1,1, 100};
        String[] ruts = {"20079829-5", "20079829-5", "20079829-5", "20079829-5"};
        Date[] fechas = {new Date(),new Date(),new Date(), new Date()};
        int[] totales = {499990, 69990, 276000, 1439100};
        int[] cantidadVendida = {1,1,1, 90};


        VentaDTO venta = new VentaDTO(productos[i], cantidades[i], ruts[i], fechas[i], totales[i], id, "");
        new VentaDAO(venta).executeSale(true);

        VentaDTO ventaDB = new VentaDAO().getVentaById(id);


        venta = new VentaDTO(productos[i], cantidadVendida[i], ruts[i], ventaDB.getFecha(), totales[i], id,"");
        assertEquals(venta, ventaDB);


    }

    @Order(2)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void editSaleById(int i) {
        int id = 23+i;
        int[] productos = {1, 2, 4,6};
        int[] cantidades = {2, 2,2, 90};
        String[] ruts = {"20079829-5", "20079829-5", "20079829-5", "20079829-5"};
        int[] totales = {2*499990, 2*69990, 2*276000, 1439100};


        VentaDTO ventaDB = new VentaDAO().getVentaById(id);
        VentaDTO venta = new VentaDTO(productos[i], cantidades[i], ruts[i], ventaDB.getFecha() , totales[i], id, "");

        new VentaDAO().editSaleById(id, venta);

        ventaDB = new VentaDAO().getVentaById(id);

        assertEquals(venta, ventaDB);

    }

    @Order(5)
    @Test
    void getVentasDB() {

        int[] productos = {1, 4, 6,2,2};
        int[] cantidades = {2, 1,10, 1, 2};
        String[] ruts = {"20079829-5", "20079829-5", "76523553-7", "7051267-k","20079853-8"};
        Date[] fechas = {new Date(),new Date(),new Date(), new Date(), new Date()};
        int[] totales = {999980, 276000, 159900, 69990, 139980};

        int[] ids = {18,19,20,21, 22};

        ArrayList<VentaDTO> ventasDB = new VentaDAO().getVentasDB();
        for(int i = 0; i < productos.length; i++){
            assertEquals(ventasDB.get(i), new VentaDTO(productos[i], cantidades[i], ruts[i], ventasDB.get(i).getFecha(), totales[i], ids[i], ""));
        }
    }

    @Order(3)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void getVentaById(int i) {
        int id = i+23;
        int[] productos = {1, 2, 4,6};
        int[] cantidades = {2, 2,2, 90};
        String[] ruts = {"20079829-5", "20079829-5", "20079829-5", "20079829-5"};
        Date[] fechas = {new Date(),new Date(),new Date(), new Date()};
        int[] totales = {2*499990, 2*69990, 2*276000, 1439100};

        VentaDTO ventaDB = new VentaDAO().getVentaById(id);
        VentaDTO venta = new VentaDTO(productos[i], cantidades[i], ruts[i], ventaDB.getFecha() , totales[i], id,"");

        assertEquals(venta, ventaDB);
    }

    @Order(4)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void deleteSaleById(int i) {
        int id = 23+i;
        ArrayList<VentaDTO> ventasDB = new VentaDAO().getVentasDB();

        for(int j = 0; j < ventasDB.size(); j++){
            System.out.println(ventasDB.get(j).getIdProducto());
        }
        new VentaDAO(ventasDB.get(0)).deleteSaleById(id);
        assertNull(new VentaDAO().getVentaById(id));

    }
}