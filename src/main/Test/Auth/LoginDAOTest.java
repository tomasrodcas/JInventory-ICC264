package Auth;

import Window.Login;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class LoginDAOTest {

    @ParameterizedTest
    @ValueSource( ints = {0,1,2,3,4})
    void login(int i) {
        String[] usernames = {"admin", "tomas" ,"admin", "tomAS", "antonia"};
        String[] passwords = {"admin", "asd", "a", "asd", "asd"};
        boolean[] resultados = {true, true, false, true, false};

        assertEquals(resultados[i], new LoginDAO().login(new LoginDTO(usernames[i], passwords[i])));

    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1 ,2,3,4})
    void checkAdminCredentials(int i) {
        String[] usernames = {"admin", "tomas" ,"admin", "tomAS", "antonia"};
        String[] passwords = {"admin", "asd", "a", "asd", "asd"};
        boolean[] resultados = {true, false, false, false, false};

        assertEquals(resultados[i], new LoginDAO().checkAdminCredentials(new LoginDTO(usernames[i], passwords[i])));
    }
}