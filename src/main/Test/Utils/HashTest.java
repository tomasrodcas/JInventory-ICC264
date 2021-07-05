package Utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HashTest {

    @ParameterizedTest
    @ValueSource(ints  = {0,1,2,3,4,5})
    void hashPassword(int index){
        String[] passwords = {"asd", "admin", "password", "123", "", "12 a 3"};
        String[] hashedPasswords = {"f10e2821bbbea527ea02200352313bc059445190","d033e22ae348aeb5660fc2140aec35850c4da997",
                "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", "40bd001563085fc35165329ea1ff5c5ecbdbbeef",
                "da39a3ee5e6b4b0d3255bfef95601890afd80709", "fd02cd6b64e2b4abea1ae26a4e2d531be0a1b65a"};

        assertEquals(hashedPasswords[index], new Hash().hashPassword(passwords[index]));
    }
}