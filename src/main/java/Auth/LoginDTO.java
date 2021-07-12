package Auth;
import Utils.Hash;
public class LoginDTO {
    protected final String username;
    protected final String password;

    public LoginDTO(String username, String password){
        this.username = username.toLowerCase();
        this.password = new Hash().hashPassword(password);
    }
}
