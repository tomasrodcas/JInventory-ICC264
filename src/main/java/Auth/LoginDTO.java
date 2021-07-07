package Auth;
import Utils.Hash;
public class LoginDTO {
    private final String username;
    private final String password;

    public LoginDTO(String username, String password){
        this.username = username.toLowerCase();
        this.password = new Hash().hashPassword(password);
    }

    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }

}
