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

    @Order(1)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void addItem(int i) {
        int id = i+7;
        String[] nombres = {"Microfono 1", "Audifonos 2", "Teclado 3", "Teclado 3", "Teclado 4"};
        int[] cantidades = {100, 200, 300, 300, 500};
        int[] precios = {1000, 200000, 12, 12, 20};
        int[] id_proveedores = {1, 2, 3, 3 , 10};
        String[] marcas = {"Apple", "Microsoft", "Logitech", "Logitech", "RoyalKludge"};

        ItemDTO item = new ItemDTO(nombres[i], cantidades[i], precios[i], id_proveedores[i], marcas[i]);
        if(i < 3){
            assertTrue(new ItemDAO().addItem(item));
            ItemDTO itemDB = new ItemDAO().getItemById(id);
            assertEquals(item, new ItemDAO().getItemById(id));
        }else{
            assertFalse(new ItemDAO().addItem(item));
            assertNull(new ItemDAO().getItemById(id));
        }
    }

    @Order(2)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void editItemById(int i) {
        int id = i+7;
        String[] nombres = {"MicrOfono 1", "AUdifonos 2", "TeClado 3", "TeClado 3", "Teclado 4"};
        int[] cantidades = {100, 200, 300, 300, 500};
        int[] precios = {1000, 2000, 12, 12, 20};
        int[] id_proveedores = {2, 2, 3, 3 , 10};
        String[] marcas = {"Apple", "Microsoft", "LogItech", "LogItech", "RoyAlKludge"};
        ItemDTO item = new ItemDTO(nombres[i], cantidades[i], precios[i], id_proveedores[i], marcas[i]);

        if(i < 3){
            assertTrue(new ItemDAO().editItemById(id, item));
            ItemDTO itemDB = new ItemDAO().getItemById(id);
            assertEquals(item, new ItemDAO().getItemById(id));
        }else if (i == 3){
            assertFalse(new ItemDAO().editItemById(id, item));
        }else{
            assertFalse(new ItemDAO().editItemById(id, item));
        }
    }

    @Order(3)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void deleteItemById(int i){
        int id = i+7;

        if(i < 3){
            assertTrue(new ItemDAO().deleteItemById(id));
            assertNull(new ItemDAO().getItemById(id));
        }else{
            assertFalse(new ItemDAO().deleteItemById(id));
            assertNull(new ItemDAO().getItemById(id));
        }
    }

    @Order(4)
    @Test
    void getItemsDB() {

        String[] nombres = {"Iphone XR", "Teclado RK61", "Audifonos G935",
                "Ryzen 5600X", "8 Port Desktop Switch"};
        int[] cantidades = {295, 86, 0, 99, 90};
        int[] precios = {499990, 69990, 150000, 276000, 15990};
        int[] id_proveedores = {1, 2, 3, 1 , 4};
        String[] marcas = {"Apple", "RoyalKludge", "Logitech", "Ryzen", "Mercusys"};

        ArrayList<ItemDTO> items = new ItemDAO().getItemsDB();

        for(int i = 0; i < items.size(); i++){
            assertEquals(items.get(i), new ItemDTO(nombres[i], cantidades[i], precios[i],
                    id_proveedores[i], marcas[i]));
        }
    }

    @Order(5)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void getItemById(int i) {

        String[] nombres = {"Iphone XR", "Teclado RK61", "Audifonos G935",
                "Ryzen 5600X", "8 Port Desktop Switch"};
        int[] cantidades = {295, 86, 0, 99, 90};
        int[] precios = {499990, 69990, 150000, 276000, 15990};
        int[] id_proveedores = {1, 2, 3, 1 , 4};
        String[] marcas = {"Apple", "RoyalKludge", "Logitech", "Ryzen", "Mercusys"};

        ItemDTO item = new ItemDTO(nombres[i], cantidades[i], precios[i], id_proveedores[i], marcas[i]);
        assertEquals(item, new ItemDAO().getItemById(i+1));
    }

    @Order(6)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void updateStock(int i) {
        int[] cantidades = {295,86, 0, 99};
        int[] cambios = {100, -100, 100, 98};
        int[] resultado = {395, 0, 100, 197};
        new ItemDAO().updateStock(i+1, cambios[i]);
        assertEquals(resultado[i], new ItemDAO().getItemById(i+1).getCantidad());
    }

}