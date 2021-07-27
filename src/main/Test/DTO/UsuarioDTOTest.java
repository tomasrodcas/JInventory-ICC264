package DTO;

import Utils.Hash;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDTOTest {

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void toArray(int i) {
        String[][] arrays = {{"Tomas","tomas", "asd", "-1","-1"},
                {"Antonia","anto", "asd", "-1","-1"},{"Alejandro","ale","asd", "-1","-1"},
                {"Maximiliano","max","asd","-1","-1"}};
        String[] usuario = new UsuarioDTO(arrays[i][0],arrays[i][1], arrays[i][2]).toArray();

        boolean sonIguales = true;
        for(int j = 0; j < usuario.length; j++){
            if(j == 2){
                if(!arrays[i][j].equals(new Hash().hashPassword(usuario[j])));
            }else{
                if(!arrays[i][j].equals(usuario[j])){
                    sonIguales = false;
                }
            }
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    void testEquals(int i) {

        String[] nombres1 = {"asd","asd2","asd3","asd4"};
        String[] usuarios1 = {"asd","asd2","asd3","asd4"};
        String[] password1 = {"asd","asd2","asd3","asd4"};

        String[] nombres2 = {"Asd","Asd2","Asd3","ad4"};
        String[] usuarios2 = {"asd","asd2","asd3","asd4"};
        String[] password2 = {"asd","asd2","asd3","asd4"};

        if(i < 3){
            assertEquals(new UsuarioDTO(nombres1[i],usuarios1[i], password1[i]),
                    new UsuarioDTO(nombres2[i],usuarios2[i], password2[i]));
        }else{
            assertNotEquals(new UsuarioDTO(nombres1[i],usuarios1[i], password1[i]),
                    new UsuarioDTO(nombres2[i],usuarios2[i], password2[i]));
        }

    }
}