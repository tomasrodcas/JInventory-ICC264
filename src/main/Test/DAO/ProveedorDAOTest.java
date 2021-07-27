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
    @ValueSource(ints = {0,1,2,3})
    void addProveedor(int i) {
        int id = i+6;
        String[] nombres = {"Proveedor 6", "Proveedor 7", "Proveedor 8", "Proveedor 8"};
        String[] ruts = {"1","2","3","4","4"};
        String[] emails = {"e@e.com", "a@a.com", "a@a.dev", "a@a.dev"};
        int[] telefonos = {102111123, 123111124, 123122251,123132251};

        ProveedorDTO proveedor = new ProveedorDTO(nombres[i], ruts[i], emails[i], telefonos[i]);

        if(i < 3){
            assertTrue(new ProveedorDAO().addProveedor(proveedor));
            assertEquals(proveedor,new ProveedorDAO().getProveedorById(id));
        }else{
            assertFalse(new ProveedorDAO().addProveedor(proveedor));
            assertNull(new ProveedorDAO().getProveedorById(id));
        }

    }
    @Order(2)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2})
    void editProveedorById(int i) {
        int id = i+6;
        String[] nombres = {"Provedor 6", "Proveeor 7", "Proveedor 7"};
        String[] ruts = {"6","7","7"};
        String[] emails = {"e@e.com", "a@a.com", "a@a.dev"};
        int[] telefonos = {102111123, 123622251, 123622251};

        ProveedorDTO proveedor = new ProveedorDTO(nombres[i], ruts[i], emails[i], telefonos[i]);

        if(i < 2){
            assertTrue(new ProveedorDAO().editProveedorById(id, proveedor));
            assertEquals(proveedor, new ProveedorDAO().getProveedorById(id));
        }else{
            assertFalse(new ProveedorDAO().editProveedorById(id, proveedor));
        }

    }

    @Order(3)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void deleteProveedorById(int i) {
        int id = i+6;

        if( i < 3){
            assertTrue(new ProveedorDAO().deleteProveedorById(id));
            assertNull(new ProveedorDAO().getProveedorById(id));
        }else{
            assertFalse(new ProveedorDAO().deleteProveedorById(id));
        }

    }

    @Order(4)
    @Test
    void getProveedoresDB() {
        String[] nombres = {"Proveedor 1","Proveedor 2","Proveedor 3","Proveedor 4", "Proveedor 5"};
        String[] ruts = {"20079829-5","20079853-4","7051267-k","15492706-9","5365610-2"};
        String[] emails = {"proveedor1@proveedores.com","proveedor2@proveedores.com",
                "proveedor3@proveedores.com","proveedor4@proveedores.com", "proveedor5@proveedores.com"};
        int[] telefonos = {111111112,111111122,111111222,111112222, 111122222};

        ArrayList<ProveedorDTO> proveedoresDB = new ProveedorDAO().getProveedoresDB();

        for(int i = 0; i < nombres.length; i++){

            assertEquals(proveedoresDB.get(i), new ProveedorDTO(nombres[i], ruts[i], emails[i], telefonos[i]));
        }
    }

    @Order(5)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5})
    void getProveedorById(int i) {
        int id = i+1;

        String[] nombres = {"Proveedor 1","Proveedor 2","Proveedor 3","Proveedor 4", "Proveedor 5"};
        String[] ruts = {"20079829-5","20079853-4","7051267-k","15492706-9","5365610-2"};
        String[] emails = {"proveedor1@proveedores.com","proveedor2@proveedores.com",
                "proveedor3@proveedores.com","proveedor4@proveedores.com", "proveedor5@proveedores.com"};
        int[] telefonos = {111111112,111111122,111111222,111112222, 111122222};



        if(i < 5){
            ProveedorDTO proveedor = new ProveedorDTO(nombres[i], ruts[i], emails[i], telefonos[i]);
            assertEquals(proveedor, new ProveedorDAO().getProveedorById(id));
        }else{
            assertNull(new ProveedorDAO().getProveedorById(id));
        }



    }









}