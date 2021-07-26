package DAO;

import DTO.ItemDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemDAOTest {

   /* @Order(1)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2})
    void addItem(int i) {
        int id = i+4;
        String[] nombres = {"Microfono 1", "Audifonos 2", "Teclado 2"};
        int[] cantidades = {100, 200, 300};
        int[] precios = {1000, 200000, 12};
        int[] rut_proveedores = {29293, 23123, 412131};
        String[] marcas = {"Apple", "Microsoft", "Logitech"};

        ItemDTO item = new ItemDTO(nombres[i], cantidades[i], precios[i], rut_proveedores[i], marcas[i]);
        new ItemDAO().addItem(item);
        ItemDTO itemDB = new ItemDAO().getItemById(id);
        assertEquals(item, new ItemDAO().getItemById(id));
    }

    @Order(3)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5})
    void getItemById(int i) {

        String[] nombres = {"iphone","Teclado RK61","Audifonos G935","Microfono 1", "Audifonos 2", "Teclado 2"};
        int[] cantidades = {300,100, 10, 100, 200, 300};
        int[] precios = {1000, 69990, 150000, 1000, 200000, 12};
        int[] rut_proveedores = {20079829, 20079853, 7051267, 29293, 23123, 412131};
        String[] marcas = {"Apple","RoyalKludge","Logitech","Apple", "Microsoft", "Logitech"};

        ItemDTO item = new ItemDTO(nombres[i], cantidades[i], precios[i], rut_proveedores[i], marcas[i]);
        assertEquals(item, new ItemDAO().getItemById(i+1));
    }

    @Order(2)
    @Test
    void getItemsDB() {
        String[] nombres = {"iphone","Teclado RK61","Audifonos G935","Microfono 1", "Audifonos 2", "Teclado 2"};
        int[] cantidades = {300,100, 10, 100, 200, 300};
        int[] precios = {1000, 69990, 150000, 1000, 200000, 12};
        int[] rut_proveedores = {20079829, 20079853, 7051267, 29293, 23123, 412131};
        String[] marcas = {"Apple","RoyalKludge","Logitech","Apple", "Microsoft", "Logitech"};

        ArrayList<ItemDTO> items = new ItemDAO().getItemsDB();

        for(int i = 0; i < items.size(); i++){
            assertEquals(items.get(i), new ItemDTO(nombres[i], cantidades[i], precios[i], rut_proveedores[i], marcas[i]));
        }

    }

    @Order(5)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5})
    void updateItemById(int i) {
        String[] nombres = {"Aa","TecladoE RK61","AudifonSos G935","MicrofoEno 1", "Audifonos 2", "Teclado 2"};
        int[] cantidades = {3020,1020, 102, 1020, 2020, 3020};
        int[] precios = {10030, 699390, 1500300, 10300, 2030000, 123};
        int[] rut_proveedores = {20079829, 20079853, 7051267, 29293, 23123, 412131};
        String[] marcas = {"ApplAe","RoyaAlKludge","LogiAtech","AppAle", "MicrosoAft", "LogitecAh"};
        ItemDTO item = new ItemDTO(nombres[i], cantidades[i], precios[i], rut_proveedores[i], marcas[i]);
        new ItemDAO().updateItemById(i+1, item);

        assertEquals(item, new ItemDAO().getItemById(i+1));
    }

    @Order(4)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5})
    void updateStock(int i) {
        int[] cantidades = {300,100, 10, 100, 200, 300};
        int[] cambios = {100, -100, -100, 98, 0, 1000};
        int[] resultado = {400, 0, 0, 198, 200, 1300};
        new ItemDAO().updateStock(i+1, cambios[i]);
        assertEquals(resultado[i], new ItemDAO().getItemById(i+1).getCantidad());
    }

    @Order(6)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5})
    void deleteItemById(int i){
        int id = i+1;
        new ItemDAO().deleteItemById(id);
        ArrayList<ItemDTO> itemsDB = new ItemDAO().getItemsDB();

        for(int j = 0; j < itemsDB.size(); j++){
            System.out.println(itemsDB.get(j).getNombre());
        }
        assertNull(new ItemDAO().getItemById(id));

    }*/
}