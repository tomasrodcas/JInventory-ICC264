package DTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.*;

class ItemDTOTest {

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void toArray(int i) {
        String[][] arrays = {{"1","Teasd","100","112312","2008213","Apple"}, {"1","Teasd","100","112312","2008213","Apple"},
                {"1","Teasd","100","112312","2008213","Apple"}, {"1","Teasd","100","112312","2008213","Apple"}};
        String[] items = new ItemDTO(parseInt(arrays[i][0]),arrays[i][1],parseInt(arrays[i][2]),parseInt(arrays[i][3]),parseInt(arrays[i][4]), arrays[i][5]).toArray();

        boolean sonIguales = true;
        for(int j = 0; j < items.length; j++){
            if(!arrays[i][j].equals(items[j])){
                sonIguales = false;
            }
        }
        assertTrue(sonIguales);

    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void testEquals(int i) {
        String[] nombres = {"TECLADO", "Bebida", "Bebida", "mouse"};
        int[] cantidades = {100, 200, 300, 400};
        int[] precios = {1000, 2000, 3000, 4000};
        int[] proveedores = {123, 1234, 12345, 123456};
        String[] marcas = {"Apple", "Logitech", "SteelSeries", "jbl"};

        String[] nombres2 = {"Teclado", "Audifonos", "Bebida", "Mouse"};
        int[] cantidades2 = {100, 200, 300, 400};
        int[] precios2 = {1000, 2000, 3001230, 4000};
        int[] proveedores2 = {123, 1234, 1223345, 123456};
        String[] marcas2 = {"Apple", "Logitech", "SteelSeries", "JBL"};

        boolean[] resultados = {true, false, false, true};

        ItemDTO item1 = new ItemDTO(nombres[i], cantidades[i], precios[i], proveedores[i], marcas[i]);
        ItemDTO item2 = new ItemDTO(nombres2[i], cantidades2[i], precios2[i], proveedores2[i], marcas2[i]);
        boolean resultadoObtenido = item1.equals(item2);

        assertEquals(resultados[i], resultadoObtenido);

    }
}