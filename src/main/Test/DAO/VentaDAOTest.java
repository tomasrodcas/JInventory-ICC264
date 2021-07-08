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
    @ValueSource(ints = {0,1,2})
    void executeSale(int i) {
        int id = 7+i;
        int[] productos = {1, 2, 3};
        int[] cantidades = {400, 1, 300};
        int[] ruts = {20079, 123123, 12123};
        Date[] fechas = {new Date(),new Date(),new Date()};
        int[] totales = {300000, 69990, 0};
        boolean[] saleMaxStock = {true, false, false};
        int[] cantidadVendida = {300, 1};
        int[] cantidadesResultantes = {0, 99, 10};
        int[] ids = {5,6,7,8};

        VentaDTO venta = new VentaDTO(productos[i], cantidades[i], ruts[i], fechas[i], totales[i], ids[i]);
        new VentaDAO(venta).executeSale(saleMaxStock[i]);
        VentaDTO ventaDB = new VentaDAO().getVentaById(id);

        if(i < 2){
            venta = new VentaDTO(productos[i], cantidadVendida[i], ruts[i], ventaDB.getFecha(), totales[i], ids[i]);
            assertEquals(venta, ventaDB);
            assertEquals(cantidadesResultantes[i], new ItemDAO().getItemById(ventaDB.getIdProducto()).getCantidad());
        }
        else{
            assertNull(ventaDB);
            assertEquals(cantidadesResultantes[i], new ItemDAO().getItemById(3).getCantidad());
        }

    }

    @Order(4)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void editSaleById(int i) {
        int id = 5+i;
        int[] productos = {2,3,1, 2};
        int[] cantidades = {200, 20, 3000, 10};
        int[] ruts = {200790829, 200079853 ,200709, 1230123};
        int[] totales = {13998000, 3000000, 3000000, 699900};
        int[] ids = {5,6,7,8};
        VentaDTO ventaDB = new VentaDAO().getVentaById(id);
        VentaDTO venta = new VentaDTO(productos[i], cantidades[i], ruts[i], ventaDB.getFecha() , totales[i], ids[i]);
        new VentaDAO().editSaleById(id, venta);
        ventaDB = new VentaDAO().getVentaById(id);
        System.out.println(ventaDB.getTotal()+" "+venta.getTotal());
        assertEquals(venta, ventaDB);

    }

    @Order(3)
    @Test
    void getVentasDB() {
        int[] productos = {2,3,1, 2};
        int[] cantidades = {20, 2, 300, 1};
        int[] ruts = {20079829, 20079853 ,20079, 123123};
        int[] totales = {100000, 300000, 300000, 69990};
        int[] ids = {5,6,7,8};

        ArrayList<VentaDTO> ventasDB = new VentaDAO().getVentasDB();
        for(int i = 0; i < ventasDB.size(); i++){
            assertEquals(ventasDB.get(i), new VentaDTO(productos[i], cantidades[i], ruts[i], ventasDB.get(i).getFecha(), totales[i], ids[i]));
        }
    }

    @Order(2)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void getVentaById(int i) {
        int id = i+5;
        int[] productos = {2,3,1, 2};
        int[] cantidades = {20, 2, 300, 1};
        int[] ruts = {20079829, 20079853 ,20079, 123123};
        int[] totales = {100000, 300000, 300000, 69990};
        int[] ids = {5,6,7,8};
        VentaDTO ventaDB = new VentaDAO().getVentaById(id);
        VentaDTO venta = new VentaDTO(productos[i], cantidades[i], ruts[i], ventaDB.getFecha() , totales[i], ids[i]);

        assertEquals(venta, ventaDB);
    }

    @Order(5)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void deleteSaleById(int i) {
        int id = 5+i;
        ArrayList<VentaDTO> ventasDB = new VentaDAO().getVentasDB();

        for(int j = 0; j < ventasDB.size(); j++){
            System.out.println(ventasDB.get(j).getIdProducto());
        }
        new VentaDAO(ventasDB.get(0)).deleteSaleById(id);
        assertNull(new VentaDAO().getVentaById(id));

    }
}