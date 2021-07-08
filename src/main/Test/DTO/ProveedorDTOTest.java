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

        String[] proveedor = new ProveedorDTO(parseInt(arrays[i][0]), arrays[i][1], parseInt(arrays[i][2]), arrays[i][3], parseInt(arrays[i][4])).toArray();
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
    void testEquals() {
        String[] nombres = {};
        int ruts[] = {};
        String[] emails = {};
        int[] telefonos = {};
    }
}