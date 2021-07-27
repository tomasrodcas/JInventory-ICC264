package DTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.*;

class ProveedorDTOTest {

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void toArray(int i) {
        String[][] arrays = {{"1","Tomas", "123124", "email@email.com","12231"}, {"1","Alejandro", "123", "a@a.com", "1232"},
                {"1","Antonia", "1232","anto@a.com", "123"}, {"1","Max", "123124", "max@a.com", "123123"}};

        String[] proveedor = new ProveedorDTO(parseInt(arrays[i][0]), arrays[i][1], arrays[i][2], arrays[i][3], parseInt(arrays[i][4])).toArray();
        boolean sonIguales = true;
        for(int j = 0; j < proveedor.length; j++){
            if(!arrays[i][j].equals(proveedor[j])){
                sonIguales = false;
            }
        }
        assertTrue(sonIguales);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void testEquals(int i) {
        String[] nombres = {"asd", "asdasd", "asdasdasd", "asdasdasdasd"};
        String ruts[] = {"123","1234", "1234-K","123"};
        String[] emails = {"a1@a.com","a2@a.com","a3@a.com","a4@a.com"};
        int[] telefonos = {123,1234,12345,123456};

        String[] nombres2 = {"asd", "asdasd", "asdasdasd", "asda213sdasd"};
        String ruts2[] = {"123","1234", "1234-k","123"};
        String[] emails2 = {"a1@a.com","a2@a.com","a3@a.com","a4@a123.com"};
        int[] telefonos2 = {123,1234,12345,123456};

        if(i < 3){
            assertEquals(new ProveedorDTO(nombres[i], ruts[i], emails[i], telefonos[i]),
                    new ProveedorDTO(nombres2[i], ruts2[i], emails2[i], telefonos2[i]));
        }else{
            assertNotEquals(new ProveedorDTO(nombres[i], ruts[i], emails[i], telefonos[i]),
                    new ProveedorDTO(nombres2[i], ruts2[i], emails2[i], telefonos2[i]));
        }
    }
}