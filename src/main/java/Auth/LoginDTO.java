package Auth;
import Utils.Hash;

/**
 * Clase encargada de almacenar la informacion del login
 */
public class LoginDTO {
    protected  final String username;
    protected final String password;

    /**
     * Constructor de login que se encarga de almacenar la informacion como atributo
     * @param username usuario ingresado
     * @param password password ingresada (Esta es hasheada con la clase Hash)
     */
    public LoginDTO(String username, String password){
        this.username = username.toLowerCase();
        this.password = new Hash().hashPassword(password);
    }
}
