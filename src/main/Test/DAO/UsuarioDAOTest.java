package DAO;

import DTO.UsuarioDTO;
import Utils.Hash;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsuarioDAOTest {

    @Order(1)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3 })
    void addUser(int i) {
        int id = 6+i;
        String[] nombres = {"usuario 6", "usuario 7","usuario 8", "usuario 8"};
        String[] usuarios = {"usuario6", "usuario7","usuario8", "usuario8"};
        String[] passwords = {"anto123", "max123", "felipe123", "felipe123"};
        UsuarioDTO usuario = new UsuarioDTO(id, nombres[i], usuarios[i], passwords[i], 0, 0);

        if(i < 3){
            assertTrue(new UsuarioDAO().addUser(usuario));
            assertEquals(usuario, new UsuarioDAO().getUserById(id));
        }else{
            assertFalse(new UsuarioDAO().addUser(usuario));
        }

    }

    @Order(2)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4})
    void editUserById(int i) {
        int id = 6+i;
        String[] nombres = {"usuaio 6", "usuario 7","usuario 7", "usuario 8","usuasdario 8"};
        String[] usuarios = {"usuaro6", "usuario7","usuario7", "usuario8","usuari2asdo8"};
        String[] passwords = {"anto23", "max1234", "max1234", "felipe123","felipasde123"};
        int[] tipoUsuario = {0,0,0,0,0};

        UsuarioDTO usuario = new UsuarioDTO(id, nombres[i], usuarios[i],
                new Hash().hashPassword(passwords[i]), 0, tipoUsuario[i]);

        if(i < 2){
            assertTrue(new UsuarioDAO().editUserById(id, usuario));
            assertEquals(usuario, new UsuarioDAO().getUserById(id));
        }else{
            assertFalse(new UsuarioDAO().editUserById(id, usuario));
        }
    }

    @Order(3)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2})
    void deleteUserById(int i) {
        int id = i+6;

        assertTrue(new UsuarioDAO().deleteUserById(id));

        ArrayList<UsuarioDTO> usuariosDB = new UsuarioDAO().getUsersDB();
        for(int j = i; j < usuariosDB.size(); j++){
            System.out.println(usuariosDB.get(j).getNombre());
        }

        assertNull(new UsuarioDAO().getUserById(id));
    }

    @Order(4)
    @Test
    void getUsersDB() {
        String[] nombres = {"admin", "Tomas Rodriguez","Antonia Millan",
                "Maximiliano Divin", "Felipe Castro"};
        String[] usuarios = {"admin", "tomas","anto", "max","felipe"};
        String[] passwords = {"admin", "asd", "asd", "asd","asd"};
        int[] tipoUsuario = {1,0,0,0,0};

        ArrayList<UsuarioDTO> usuariosDB = new UsuarioDAO().getUsersDB();
        for(int i = 0; i < nombres.length; i++){
            UsuarioDTO usuario = new UsuarioDTO(i+1, nombres[i], usuarios[i],
                    new Hash().hashPassword(passwords[i]), 0, tipoUsuario[i]);
            assertEquals(usuario, usuariosDB.get(i));
        }
    }

    @Order(5)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4})
    void getUserById(int i) {
        int id = i+1;
        String[] nombres = {"admin", "Tomas Rodriguez","Antonia Millan",
                "Maximiliano Divin", "Felipe Castro"};
        String[] usuarios = {"admin", "tomas","anto", "max","felipe"};
        String[] passwords = {"admin", "asd", "asd", "asd","asd"};
        int[] tipoUsuario = {1,0,0,0,0};

        if(i < 5){
            UsuarioDTO usuario = new UsuarioDTO(id, nombres[i], usuarios[i],
                    new Hash().hashPassword(passwords[i]),0, tipoUsuario[i]);
            assertEquals(usuario, new UsuarioDAO().getUserById(id));
        }else{
            assertNull(new UsuarioDAO().getUserById(id));
        }

    }

}