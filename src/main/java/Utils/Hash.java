package Utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Clase encargada de Hashear un String con el algoritmo SHA1
 */
public class Hash {
    /**
     * Retorna el String ingresado hasheado con el algoritmo SHA1 de encriptacion
     * @param password password ingresada
     * @return String password hasheada con SHA1
     */
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
