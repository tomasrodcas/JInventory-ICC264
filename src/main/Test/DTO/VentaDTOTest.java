package DTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Date;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.*;

class VentaDTOTest {

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void toArray(int i) {
        Date fecha = new Date();
        String[][] arrays = {{"1","1","100","200012", fecha.toString(), "1231223"},
                {"1","1","100","200012", fecha.toString(), "1231223"},
                {"1","1","100","200012", fecha.toString(), "1231223"},
                {"1","1","100","200012", fecha.toString(), "1231223"}};

        String[][] arrays2 = {{"1","1","","100","1231223","200012", fecha.toString()},
                {"1","1","","100", "1231223","200012",fecha.toString()},
                {"1","1","","100", "1231223","200012",fecha.toString()},
                {"1","1","","100","1231223","200012",fecha.toString()}};

        String[] venta = new VentaDTO(parseInt(arrays[i][1]), parseInt(arrays[i][2]), arrays[i][3], fecha,
                parseInt(arrays[i][5]), parseInt(arrays[i][0]), "").toArray();

        boolean sonIguales = true;

        for(int j = 0; j < venta.length; j ++){
            if(!arrays2[i][j].equals(venta[j])){
                sonIguales = false;
            }
        }
        assertTrue(sonIguales);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void testEquals(int i) {
        int[] productos = {1,2,3,4};
        int[] cantidades = {100, 200, 300, 400};
        String[] cliente = {"111", "222", "333", "444"};
        Date fecha = new Date();

        int[] productos2 = {1,2,3,4};
        int[] cantidades2 = {100,200,300,401};
        String[] cliente2 = {"111", "22", "333", "44"};

        boolean[] resultado = {true, false, true, false};

        boolean resultadoObtenido = new VentaDTO(productos[i], cantidades[i], cliente[i], fecha).equals(
                new VentaDTO(productos2[i], cantidades2[i], cliente2[i], fecha)
            );
        assertEquals(resultado[i], resultadoObtenido);
    }
}