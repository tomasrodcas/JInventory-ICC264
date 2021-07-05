package Utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class DataValidationTest {

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6})
    void validarRut(int i) {
        String[] ruts = {"20079829-5", "7.051.267-k", "asd23", "20.079.853-8", "76523553-7", "", "20a079829-5", "20...079-829-5"};
        boolean[] resultados = {true, true, false, true, true, false, false, true};

        assertEquals(resultados[i], new DataValidation().validarRut(ruts[i]));
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6})
    void validateEmail(int i) {
        String[] emails = {"t.rodriguez07@ufromail.cl", "1..23@asd.com", "", "123\\s@.com", ".@.com", "tomasrodcas@gmail.com", "a@a..com"};
        boolean[] resultados = {true, false, false, false, false, true, false};

        assertEquals(resultados[i], new DataValidation().validateEmail(emails[i]));
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6})
    void validarRutDB(int i){
        int[] ruts = {1, 1000200, 20079829, 7051267, -20, 1232102302, 76523553};
        boolean[] resultados = {false, true, true, true, false, false, true};

        assertEquals(resultados[i], new DataValidation().validarRutDB(ruts[i]));
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6})
    void getCompleteRut(int i){
        int[] ruts ={1, 20079853, 20079829, 7051267, -20, 1232102302, 76523553};
        String[] rutsCompletos = {"","20079853-8", "20079829-5", "7051267-K", "","", "76523553-7" };
        assertEquals(rutsCompletos[i], new DataValidation().getCompleteRut(ruts[i]));

    }
}