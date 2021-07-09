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

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6})
    void clientDTOValidation(int i){
        String[] nombre = { "Tomas", "Camilo", "Esteba123", "Cristian", "", "Felipe", "Agusto"};
        String[] emails = {"t.rodriguez07@ufromail.cl", "1..23@asd.com", "", "123\\s@.com", ".@.com", "tomasrodcas@gmail.com", "a@a..com"};
        int[] telefono = {978364782, 923465123, 45612377, 1, 0323232,657882982,456789234 };
        String[] ruts = {"20079829-5", "7.051.267-k", "asd23", "20.079.853-8", "76523552-7", "76523553-7", "", "20...079-829-5"};
        boolean[] resultados = {true, false, false, false, false, true, false };
        assertEquals(resultados[i],new DataValidation().clienteDTOValidation(nombre[i],emails[i],telefono[i],ruts[i]));
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6})
    void itemDTOValidation(int i){
        String[] nombres = { "Tomas", "Camilo", "Esteba123", "Cristian", "", "Felipe", "Agusto"};
        int[] cantidades = {13,12,-1,30,0,7,20};
        int[] precios= {1900,200,-345,10990,0,199,1599};
        int[] proveedores = {0,2,-3,5,6,1,9000};
        String[] marcas = {"Elite", "", "Asus", "Hipoglos", "Carac@l", "nivea","Corintios"};
        boolean[] resultados = {false, false, false, true, false, true, true};
        assertEquals(resultados[i], new DataValidation().itemDTOValidation(nombres[i], cantidades[i], precios[i], proveedores[i], marcas[i]));
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6})
    void proveedorDTOValidation(int i){
        String[] nombres = { "Tomas", "Camilo", "Esteba123", "Cristian", "", "Felipe", "Agusto"};
        String[] emails = {"t.rodriguez07@ufromail.cl", "1..23@asd.com", "", "123\\s@.com", ".@.com", "tomasrodcas@gmail.com", "a@a..com"};
        int[] telefono = {978364782, 923465123, 45612375, 1, 0323232,65788982,456789234 };
        String[] ruts = {"20079829-5", "7.051.267-k", "asd23", "20.079.853-8", "76523552-7", "", "76523553-7", "20...079-829-5"};
        boolean[] resultados = {true, false, false, false, false, false, false};
        assertEquals(resultados[i], new DataValidation().proveedorDTOValidation(nombres[i], ruts[i] ,emails[i], telefono[i]));
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6})
    void usuarioDTOValidation(int i){
        String[] nombres = {"Tomas", "Camilo", "Esteba123", "Cristian", "", "Felipe", "Agusto"};
        String[] usuarios = {"Editor1", "", "123321", "123", "Elite203", "Serpiente15", "Esm" };
        String[] passwords = {"Panel1357", "contrasena1", "contra12-1", "Trq2Qfan2", "xExpre3xxxF", "f1F2,-/2///", "a--L^^^^^%%%41"};
        boolean[] resultados = {true, false,false, false, false, true, false };
        assertEquals(resultados[i], new DataValidation().usuarioDTOValidation(nombres[i], usuarios[i], passwords[i]));
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6})
    void ventaDTOValidation(int i){
        int[] idproductos = {2032, 131, 1111, 0, 3231, -134, 2332};
        int[] cantidadVendidas = {2,3,-1,3,0,123,10};
        String[] ruts = {"20079829-5", "7.051.267-k", "asd23", "20.079.853-8", "76523552-7", "", "76523553-7", "20...079-829-5"};
        int[] total = {20009000, 200, -132, 3021, 0 , 1000, 1};
        boolean[] resultados = {true, true, false, false, false, false, true};
        assertEquals(resultados[i], new DataValidation().ventaDTOValidation(idproductos[i],cantidadVendidas[i],ruts[i],total[i]));
    }
}