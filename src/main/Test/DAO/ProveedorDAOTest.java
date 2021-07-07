package DAO;

import DTO.ProveedorDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProveedorDAOTest {


    @Order(1)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2})
    void addProveedor(int i) {
        int id = i+4;
        String[] nombres = {"Antonia Millan", "Jaime Altozano", "Goku Lopez"};
        int[] ruts = {1000, 123123, 2124401};
        String[] emails = {"e@e.com", "a@a.com", "a@a.dev"};
        int[] telefonos = {102123, 123124, 123151};

        ProveedorDTO proveedor = new ProveedorDTO(nombres[i], ruts[i], emails[i], telefonos[i]);
        new ProveedorDAO().addProveedor(proveedor);

        assertEquals(proveedor, new ProveedorDAO().getProveedorById(id));
    }

    @Order(2)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5})
    void getProveedorById(int i) {
        int id = i+1;
        String[] nombres = {"Proveedor 1","Proveedor 2","Proveedor 3","Antonia Millan", "Jaime Altozano", "Goku Lopez"};
        int[] ruts = {20079829,20079853,7051267,1000, 123123, 2124401};
        String[] emails = {"tomasrodcas@gmail.com","asd@asd.com","asdasd@asdasd.com","e@e.com", "a@a.com", "a@a.dev"};
        int[] telefonos = {922222222,999999999,222222222,102123, 123124, 123151};

        ProveedorDTO proveedor = new ProveedorDTO(nombres[i], ruts[i], emails[i], telefonos[i]);

        assertEquals(proveedor, new ProveedorDAO().getProveedorById(id));
    }


    @Order(3)
    @Test
    void getProveedoresDB() {
        String[] nombres = {"Proveedor 1","Proveedor 2","Proveedor 3","Antonia Millan", "Jaime Altozano", "Goku Lopez"};
        int[] ruts = {20079829,20079853,7051267,1000, 123123, 2124401};
        String[] emails = {"tomasrodcas@gmail.com","asd@asd.com","asdasd@asdasd.com","e@e.com", "a@a.com", "a@a.dev"};
        int[] telefonos = {922222222,999999999,222222222,102123, 123124, 123151};

        ArrayList<ProveedorDTO> proveedoresDB = new ProveedorDAO().getProveedoresDB();

        for(int i = 0; i < nombres.length; i++){
            assertEquals(proveedoresDB.get(i), new ProveedorDTO(nombres[i], ruts[i], emails[i], telefonos[i]));
        }
    }

    @Order(4)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5})
    void updateProveedorById(int i) {
        int id = i+1;
        String[] nombres = {"ProvAedor 1","ProveAdor 2","ProveAdor 3","Antonia Aillan", "JaimA Altozano", "Goku Aopez"};
        int[] ruts = {20079329,20079353,7051267,1000, 3423123, 21324401};
        String[] emails = {"tomasrAdcas@gmail.com","asdDa@d.com","asdasd@asdasd.com","e@e.com", "a@a.com", "a@a.dev"};
        int[] telefonos = {92234222,99349999,2242222,1032123, 123124, 123451};

        ProveedorDTO proveedor = new ProveedorDTO(nombres[i], ruts[i], emails[i], telefonos[i]);
        new ProveedorDAO().updateProveedorById(id, proveedor);

        assertEquals(proveedor, new ProveedorDAO().getProveedorById(id));

    }

    @Order(5)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5})
    void deleteProveedorById(int i) {
        int id = i+1;
        String[] nombres = {"ProvAedor 1","ProveAdor 2","ProveAdor 3","Antonia Aillan", "JaimA Altozano", "Goku Aopez"};
        int[] ruts = {20079329,20079353,7051267,1000, 3423123, 21324401};
        String[] emails = {"tomasrAdcas@gmail.com","asdDa@d.com","asdasd@asdasd.com","e@e.com", "a@a.com", "a@a.dev"};
        int[] telefonos = {92234222,99349999,2242222,1032123, 123124, 123451};

        new ProveedorDAO().deleteProveedorById(id);

        ArrayList<ProveedorDTO> proveedoresDB = new ProveedorDAO().getProveedoresDB();
        for(int j = 0; j < proveedoresDB.size(); j++){
            System.out.println(proveedoresDB.get(j).getNombre());
        }

        assertNull(new ProveedorDAO().getProveedorById(id));

    }


}