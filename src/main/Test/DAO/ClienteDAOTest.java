package DAO;

import DTO.ClienteDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClienteDAOTest {


    @Order(1)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2})
    void addCliente(int i) {
         int id = i+3; //Ya existen el id 1 y 2
        String[] nombres = {"Tomas", "Alejandro", "Max"};
        String[] emails = {"asd@asd2.com", "a@a.com", "e@e.com"};
        int[] telefonos = {222, 22, 2};
        int[] ruts = {2007982, 20000000, 2000000};
        ClienteDTO cliente = new ClienteDTO(nombres[i], emails[i], telefonos[i], ruts[i]);
        new ClienteDAO().addCliente(cliente);
        ClienteDTO clienteDB = new ClienteDAO().getClienteById(id);

        assertEquals(clienteDB, cliente);

    }

    @Order(2)
    @ParameterizedTest
    @ValueSource(ints = {1,2})
    void editClienteById(int i) {
        String[] nombres = {"Tomas", "Aa", "Ee"};
        String[] emails = {"asd@asd22.com", "a@a2.com", "e@e2.com"};
        int[] telefonos = {2223, 2223, 23};
        int[] ruts = {2007983, 20000300, 2002000};
        ClienteDTO cliente = new ClienteDTO(nombres[i], emails[i], telefonos[i], ruts[i]);

        new ClienteDAO().editClienteById(cliente, i);

        ClienteDTO clienteDB = new ClienteDAO().getClienteById(i);


        assertEquals(clienteDB,cliente);
    }

    @Order(3)
    @ParameterizedTest
    @ValueSource(ints = {3,4,5})
    void deleteUserById(int id) {
        new ClienteDAO().deleteUserById(id);
        assertNull(new ClienteDAO().getClienteById(id));
    }


    @Order(4)
    @Test
    void getClientesDB() {
        String[] nombres = {"Aa", "Ee"};
        String[] emails = {"a@a2.com", "e@e2.com"};
        int[] telefonos = { 2223, 23};
        int[] ruts = { 20000300, 2002000};

        ClienteDTO cliente1 = new ClienteDTO(nombres[0], emails[0], telefonos[0], ruts[0]);
        ClienteDTO cliente2 = new ClienteDTO(nombres[1], emails[1], telefonos[1], ruts[1]);

        ClienteDTO[] clientesDB = {cliente1, cliente2};
        ArrayList<ClienteDTO> clientesObtenidos = new ClienteDAO().getClientesDB();

        boolean extraidosCorrectamente = clientesDB[0].equals(clientesObtenidos.get(0))
                && clientesDB[1].equals(clientesObtenidos.get(1));

        assertTrue(extraidosCorrectamente);


    }

    @Order(5)
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void getClienteById(int i) {
        String[] nombres = {"Aa", "Ee"};
        String[] emails = {"a@a2.com", "e@e2.com"};
        int[] telefonos = { 2223, 23};
        int[] ruts = { 20000300, 2002000};
        ClienteDTO cliente1 = new ClienteDTO(nombres[0], emails[0], telefonos[0], ruts[0]);
        ClienteDTO cliente2 = new ClienteDTO(nombres[1], emails[1], telefonos[1], ruts[1]);

        ClienteDTO[] clientesDB = {cliente1, cliente2};

        assertEquals(clientesDB[i-1], new ClienteDAO().getClienteById(i));

    }
}