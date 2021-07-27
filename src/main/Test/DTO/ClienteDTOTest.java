package DTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.*;

class ClienteDTOTest {

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void toArray(int i) {
        String[][] arrays = {{"1","Tomas", "email@email.com", "123124", "12231"}, {"1","Alejandro", "a@a.com", "123", "1232"},
                {"1","Antonia", "anto@a.com", "1232", "123"}, {"1","Max", "max@a.com", "123124", "123123"}};
        String[] cliente = new ClienteDTO(parseInt(arrays[i][0]), arrays[i][1], arrays[i][2], parseInt(arrays[i][3]), arrays[i][4]).toArray();
        boolean iguales = true;
        for(int j =0; j < cliente.length; j++){
            if(!arrays[i][j].equals(cliente[j])){
                iguales = false;
            }
        }
        assertTrue(iguales);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void testEquals(int i) {
        String[] nombres = {"Tomas", "Jaime", "Antonia", "Alejandro"};
        String[] emails = {"email@email.com", "ae@ae.com", "anto@a.com", "a@a.com"};
        int[] telefonos = {123, 1234, 12345, 123456};
        String[] ruts = {"123", "1234", "12345", "123456"};

        String[] nombres2 = {"TOMAS", "Jime", "Antonia", "AlejAndro"};
        String[] emails2 = {"email@email.com", "AE@AE.com", "anto@a.com", "a@A.cOm"};
        int[] telefonos2 = {123, 1234, 12345, 12356};
        String[] ruts2 = {"123", "1234", "12345", "123456"};

        boolean[] resultadosEsperados = {true, false, true, false};

        ClienteDTO cliente1 = new ClienteDTO(nombres[i], emails[i], telefonos[i], ruts[i]);
        ClienteDTO cliente2 = new ClienteDTO(nombres2[i], emails2[i], telefonos2[i], ruts2[i]);

        assertEquals(resultadosEsperados[i], cliente1.equals(cliente2));

    }
}