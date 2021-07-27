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
    @ValueSource(ints = {0,1,2,3})
    void addCliente(int i) {
        int id = i+7; //Ya existen el id 1-6
        String[] nombres = {"Cliente 6", "Cliente 7", "Cliente 8", "Cliete 8"};
        String[] emails = {"cliente6@clientes.com", "cliente7@clientes.com",
                "cliente8@clientes.com", "cliente8@clentes.com"};
        int[] telefonos = {222111111, 221111111, 211111111, 213111111};
        String[] ruts = {"17028243-4","8517843-1","8885126-9", "8885126-9"};

        ClienteDTO cliente = new ClienteDTO(nombres[i], emails[i], telefonos[i], ruts[i]);

        if( i < 3){

            assertTrue(new ClienteDAO().addCliente(cliente));
            ClienteDTO clienteDB = new ClienteDAO().getClienteById(id);
            assertEquals(clienteDB, cliente);
        }else{
            new ClienteDAO().addCliente(cliente);
            ClienteDTO clienteDB = new ClienteDAO().getClienteById(id);
            assertNull(clienteDB);
        }
    }

    @Order(2)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void editClienteById(int i) {
        int id = i+7;

        String[] nombres = {"CliEnte 6", "CliEnte 7", "CliEnte 8","CliEnte 8"};
        String[] emails = {"cliente6@clientes.com", "cliente7@clientes.com",
                "cliente8@clientes.com", "cliente8@clientes.com"};
        int[] telefonos = {222111111, 221111111, 211111111,211111111};
        String[] ruts = {"17028243-4","8517843-1","8885126-9","8885126-9"};

        ClienteDTO cliente = new ClienteDTO(nombres[i], emails[i], telefonos[i], ruts[i]);

        if(i < 3){
            assertTrue(new ClienteDAO().editClienteById(cliente, id));
            ClienteDTO clienteDB = new ClienteDAO().getClienteById(id);
            assertEquals(clienteDB,cliente);
        }else{
            assertFalse(new ClienteDAO().editClienteById(cliente, id));
        }
    }

    @Order(3)
    @ParameterizedTest
    @ValueSource(ints = {7,8,9,10})
    void deleteUserById(int id) {
        if(id < 10){
            assertTrue(new ClienteDAO().deleteClienteById(id));
            assertNull(new ClienteDAO().getClienteById(id));
        }
        else{
            assertFalse(new ClienteDAO().deleteClienteById(id));
        }
    }

    @Order(4)
    @Test
    void getClientesDB() {
        String[] nombres = {"Cliente 1", "Cliente 2", "Cliente 3", "Cliente 4", "Cliente 5"};
        String[] emails = {"cliente1@clientes.com", "cliente2@clientes.com",
                "cliente3@clientes.com", "cliente4@clientes.com", "cliente5@clientes.com"};
        int[] telefonos = {222222221, 222222211, 222222111, 222221111, 222211111};
        String[] ruts = {"20079853-8","20079829-5","7051267-k", "76523553-7","5365610-2"};


        ClienteDTO cliente1 = new ClienteDTO(nombres[0], emails[0], telefonos[0], ruts[0]);
        ClienteDTO cliente2 = new ClienteDTO(nombres[1], emails[1], telefonos[1], ruts[1]);
        ClienteDTO cliente3 = new ClienteDTO(nombres[2], emails[2], telefonos[2], ruts[2]);
        ClienteDTO cliente4 = new ClienteDTO(nombres[3], emails[3], telefonos[3], ruts[3]);
        ClienteDTO cliente5 = new ClienteDTO(nombres[4], emails[4], telefonos[4], ruts[4]);

        ClienteDTO[] clientesDB = {cliente1, cliente2, cliente3, cliente4, cliente5};
        ArrayList<ClienteDTO> clientesObtenidos = new ClienteDAO().getClientesDB();

        for(int j = 0; j < clientesDB.length; j++){
            assertEquals(clientesDB[j], clientesObtenidos.get(j));
        }


    }

    @Order(5)
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,5,6})
    void getClienteById(int i) {
        String[] nombres = {"Cliente 1", "Cliente 2", "Cliente 3", "Cliente 4", "Cliente 5"};
        String[] emails = {"cliente1@clientes.com", "cliente2@clientes.com",
                "cliente3@clientes.com", "cliente4@clientes.com", "cliente5@clientes.com"};
        int[] telefonos = {222222221, 222222211, 222222111, 222221111, 222211111};
        String[] ruts = {"20079853-8","20079829-5","7051267-k", "76523553-7","5365610-2"};

        ClienteDTO cliente1 = new ClienteDTO(nombres[0], emails[0], telefonos[0], ruts[0]);
        ClienteDTO cliente2 = new ClienteDTO(nombres[1], emails[1], telefonos[1], ruts[1]);
        ClienteDTO cliente3 = new ClienteDTO(nombres[2], emails[2], telefonos[2], ruts[2]);
        ClienteDTO cliente4 = new ClienteDTO(nombres[3], emails[3], telefonos[3], ruts[3]);
        ClienteDTO cliente5 = new ClienteDTO(nombres[4], emails[4], telefonos[4], ruts[4]);

        ClienteDTO[] clientesDB = {cliente1, cliente2, cliente3, null, cliente4, cliente5};

        assertEquals(clientesDB[i-1], new ClienteDAO().getClienteById(i));

    }
}