package DAO;

import DTO.UsuarioDTO;
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
    @ValueSource(ints = {0,1,2})
    void addUser(int i) {
        int id = 3+i;
        String[] nombres = {"Antonia", "Max", "Felipe"};
        String[] usuarios = {"anto", "max", "felipe"};
        String[] passwords = {"anto123", "max123", "felipe123"};
        UsuarioDTO usuario = new UsuarioDTO(nombres[i], usuarios[i], passwords[i], false);
        new UsuarioDAO().addUser(usuario);
        assertEquals(usuario, new UsuarioDAO().getUserById(id));
    }

    @Order(3)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4})
    void editUserById(int i) {
        int id = i+1;
        String[] nombres = {"admain", "Tomas Rodariguez", "Antoania", "Maax", "Feliape"};
        String[] usuarios = {"admian", "tomaas", "anato", "maax", "feliape"};
        String[] passwords = {"asd", "asd","asd", "asd", "asd"};

        UsuarioDTO usuario = new UsuarioDTO(nombres[i], usuarios[i], passwords[i], false);

        new UsuarioDAO().editUserById(id, usuario);

        assertEquals(usuario, new UsuarioDAO().getUserById(id));
    }

    @Order(5)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4})
    void deleteUserById(int i) {
        int id = i+1;
        String[] nombres = {"admain", "Tomas Rodariguez", "Antoania", "Maax", "Feliape"};
        String[] usuarios = {"admian", "tomaas", "anato", "maax", "feliape"};
        String[] passwords = {"asd", "asd","asd", "asd", "asd"};

        new UsuarioDAO().deleteUserById(id);

        ArrayList<UsuarioDTO> usuariosDB = new UsuarioDAO().getUsersDB();
        for(int j = i; j < usuariosDB.size(); j++){
            System.out.println(usuariosDB.get(j).getNombre());
        }

        assertNull(new UsuarioDAO().getUserById(id));
    }

    @Order(2)
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4})
    void getUserById(int i) {
        int id = i+1;
        String[] nombres = {"admin", "Tomas Rodriguez", "Antonia", "Max", "Felipe"};
        String[] usuarios = {"admin", "tomas", "anto", "max", "felipe"};
        String[] passwords = {"admin", "asd","anto123", "max123", "felipe123"};

        UsuarioDTO usuario = new UsuarioDTO(nombres[i], usuarios[i], passwords[i], false);

        assertEquals(usuario, new UsuarioDAO().getUserById(id));

    }

    @Order(4)
    @Test
    void getUsersDB() {
        String[] nombres = {"admain", "Tomas Rodariguez", "Antoania", "Maax", "Feliape"};
        String[] usuarios = {"admian", "tomaas", "anato", "maax", "feliape"};
        String[] passwords = {"asd", "asd","asd", "asd", "asd"};

        ArrayList<UsuarioDTO> usuariosDB = new UsuarioDAO().getUsersDB();
        for(int i = 0; i < nombres.length; i++){
            UsuarioDTO usuario = new UsuarioDTO(nombres[i], usuarios[i], passwords[i], false);
            assertEquals(usuario, usuariosDB.get(i));
        }

    }
}