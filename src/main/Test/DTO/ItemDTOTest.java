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
        String[][] arrayConstructor = {{"1","Teasd","100","112312","2008213", "2008213","Apple"},
                {"1","Tea2sd","100","1122312","2008213","2008213","Apple"},
                {"1","Teasd","100","112312","20082213","2008213","Apple"},
                {"1","Tea2sd","100","112312","2008213","20082213","Apple"}};

       String[][] arrayCheck = {{"1","Teasd","100","112312", "2008213","Apple"},
               {"1","Tea2sd","100","1122312","2008213","Apple"},
               {"1","Teasd","100","112312","2008213","Apple"},
               {"1","Tea2sd","100","112312","20082213","Apple"}};

        String[] item = new ItemDTO(parseInt(arrayConstructor[i][0]),
                arrayConstructor[i][1],parseInt(arrayConstructor[i][2]), parseInt(arrayConstructor[i][3]),
                parseInt(arrayConstructor[i][4]), arrayConstructor[i][5], arrayConstructor[i][6]).toArray();

        boolean iguales = true;
        for(int j = 0; j < arrayCheck[i].length; j++){
            if(!arrayCheck[i][j].equals(item[j])){
                iguales = false;

            }        }

        assertTrue(iguales);
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