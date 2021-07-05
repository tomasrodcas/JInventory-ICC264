package Utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Hash {
    public String hashPassword(String password){

        String hashedPassword = "";

        // With the java libraries
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes("utf8"));
            hashedPassword = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (Exception e){
            e.printStackTrace();
        }
        return hashedPassword;
    }

}
