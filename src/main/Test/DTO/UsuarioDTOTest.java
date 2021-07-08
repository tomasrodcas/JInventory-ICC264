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
        String[][] arrays = {{"Tomas","tomas", "asd"},{"Antonia","anto", "asd"},{"Alejandro","ale","asd"},{"Maximiliano","max","asd"}};
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
    void testEquals() {
    }
}