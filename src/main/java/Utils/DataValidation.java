package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase encargada de validar los datos ingresados para los diferentes procesos
 */
public class DataValidation {
    /**
     * regex pattern para la validacion del email
     */
    private static final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    /**
     * regex pattern para la validacion de la contraseña
     */
    private static final String passRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    /**
     * regex pattern para la validacion del rut
     */
    private static final String rutRegex = "^(\\d{1,3}(?:\\.\\d{3}){2}-[\\dkK])$";
    /**
     * Valida un rut ingresado como String con puntos guion y digito verificador
     * @param rut rut ingresado como String
     * @return boolean si es valido o no
     */
    private boolean validarRut(String rut) {

        boolean validacion = false;
        if(rut.length() == 0){
            return false;
        }
        rut = replaceRutCharacters(rut);
        char dv = rut.charAt(rut.length() - 1);
        if (checkCompleteRut(rut.substring(0, rut.length() - 1))) {
            char dvCorrespondiente = calcularDV(rut.substring(0, rut.length() - 1));

            if (dv == dvCorrespondiente) {
                validacion = true;
            }
            return validacion;
        } else {
            return false;
        }

    }

    /**
     * Transforma el rut como String a un array de enteros
     * @param rut rut como String
     * @return int array con los digitos del rut
     */
    private int[] rutToArray(String rut) {

        String[] rutChar = rut.split("");
        int[] rutArray = new int[rutChar.length];
        try {
            for (int i = 0; i < rutChar.length; i++) {
                rutArray[i] = Integer.valueOf(rutChar[i]);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return rutArray;
    }

    /**
     * Invierte el array de enteros del rut
     * @param rutInt array de enteros con los digitos del rut
     * @return array de enteros inverso con los digitos del rut
     */
    private int[] invertirRutArray(int[] rutInt) {
        int[] rutInverso = new int[rutInt.length];
        int j = rutInt.length - 1;
        for (int i = 0; i < rutInt.length; i++) {
            rutInverso[i] = rutInt[j];
            j--;
        }
        return rutInverso;
    }

    /**
     * Valida que el rut completo con digito verificador, sin puntos ni guion sea valido
     * @param rut String rut con digito verificador y sin puntuaciones
     * @return boolean si es valido o no
     */
    private boolean checkCompleteRut(String rut) {
        String[] rutArray = rut.split("");
        int[] rutIntArray = new int[rutArray.length];
        for (int i = 0; i < rutArray.length; i++) {
            try {
                rutIntArray[i] = Integer.valueOf(rutArray[i]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * Calcula el digito verificador que corresponde al rut
     * @param rut String rut sin digito verificador
     * @return  char que corresponde al digito verificador
     */
    private char calcularDV(String rut) {

        int[] rutInverso = invertirRutArray(rutToArray(rut));
        int a = 2;
        int rutAcumulado = 0;
        for (int i = 0; i < rutInverso.length; i++) {
            rutInverso[i] = rutInverso[i] * a;
            rutAcumulado += Integer.parseInt(String.valueOf(rutInverso[i]));
            if (a == 7) {
                a = 1;
            }
            a++;
        }
        int resto = rutAcumulado % 11;
        String dv = String.valueOf(11 - resto);
        if (dv.equals("11")) {
            dv = "0";
        }
        if (dv.equals("10")) {
            dv = "K";
        }

        return dv.toCharArray()[0];
    }

    /**
     * Elimina las puntaciones del rut y devuelve el String sin guion ni puntos
     * @param rut String rut completo con puntos y guion
     * @return String rut sin puntos y sin guion
     */
    private String replaceRutCharacters(String rut) {
        rut = rut.toUpperCase();
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        return rut;
    }

    /**
     * Valida el formato del email
     * @param email String email ingresado
     * @return boolean si es valido o no
     */
    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    /**
     * Revisa que los datos ingresados para crear o editar un cliente sean validos
     * @param nombre nombre del cliente
     * @param email email del cliente
     * @param telefono telefono del cliente
     * @param rut rut del cliente
     * @return booleano que describe si todos los datos son validos o no
     */
    public boolean clienteDTOValidation(String nombre, String email, String telefono, String rut){
        if(nombreValidation(nombre) && validateEmail(email) && telefonoValidation(telefono)
                && validarRut(rut) && validateRutRegex(rut) )
            return true;
        else
            return false;
    }

    /**
     * Valida que el nombre sea un nombre valido y no numeros
     * @param nombre String nombre a verificar
     * @return booleano si es valido o no
     */
    private boolean nombreValidation(String nombre){
        try {
            int isNro = Integer.parseInt(nombre);
            return false;
        }catch (NumberFormatException ignored){
        }

        return !nombre.equals("");
    }

    /**
     * Valida que el numero de telefono proveido sea valido
     * @param telefono int telefono a verificar
     * @return booleano si es valido o no
     */
    private boolean telefonoValidation(String telefono){
        try{
            int telefonoInt = Integer.parseInt(telefono);

            if( telefonoInt > 100000000 && telefonoInt < 1000000000 ){
                return true;
            }
        }catch(NumberFormatException ignore){
        }

        return false;
    }

    /**
     * Valida que todos los datos ingresados para crear o editar un item son validos
     * @param nombre nombre del item
     * @param cantidad cantidad del item
     * @param precio precio del item
     * @param proveedor rut del proveedor
     * @param marca marca del item
     * @return booleano que describe si todos los datos son validos o no
     */
    public boolean itemDTOValidation(String nombre, String cantidad, String precio, String proveedor, String marca){
        if(nombreValidation(nombre) && intPositiveValidation(precio) && idValidation(proveedor) && marcaValidation(marca))
            return true;
        else
            return false;
    }

    /**
     * Valida que el parametro entregado corresponda  a un entero mayor que 0
     * @param numero numero entero a validar
     * @return booleano si es mayor que 0 o no
     */
    private boolean intPositiveValidation(String numero){
        try{
            int entero = Integer.parseInt(numero);
            if(entero > 0) {
                return true;
            }
        }catch(NumberFormatException ignored){
        }
        return false;
    }
    public boolean idValidation(String id){
        try{
            int entero = Integer.parseInt(id);
            if(entero >= 0) {
                return true;
            }
        }catch(NumberFormatException ignored){
        }
        return false;
    }

    /**
     * Valida que la marca entregada posea contenido y no sea un numero entero
     * @param marca String marca a validar
     * @return booleano si es valida o no
     */
    private boolean marcaValidation(String marca){
        if(marca.equals(""))
            return false;
        else
            try{
                int marcaInt = Integer.parseInt(marca);
                return false;
            }catch(NumberFormatException e){
                return true;
            }


    }

    /**
     * Valida que todos los datos ingresados para editar o crear un proveedor sean validos
     * @param nombre nombre proveedor
     * @param rut rut proveedor
     * @param email email proveedor
     * @param telefono telefono proveedor
     * @return booleano que describe si todos los datos son validos o no
     */
    public boolean proveedorDTOValidation(String nombre, String rut, String email, String telefono){
        if(nombreValidation(nombre) && validarRut(rut) && validateRutRegex(rut)
                && validateEmail(email) && telefonoValidation(telefono))
            return true;
        else
            return false;
    }

    /**
     * Valida que todos los datos ingresados para editar o crear un usuario sean validos
     * @param nombre nombre del usuario
     * @param usuario usuario
     * @param password contraseña
     * @return booleano que describe si todos los datos son validos o no
     */
    public boolean usuarioDTOValidation(String nombre, String usuario, String password){
        if(nombreValidation(nombre) && usuarioValidation(usuario) && passwordValidation(password))
            return true;
        else
            return false;
    }

    /**
     * Valida que el usuario se encuentre entre 4 y 20 caracteres de largo
     * @param usuario String usuario a validar
     * @return booleano si es valido o no
     */
    private boolean usuarioValidation(String usuario){
        if(usuario.length() > 4 && usuario.length() < 20)
            return true;
        else
            return false;
    }

    /**
     * Valida que la password entregada contenga los requisitos de seguridad minimos
     * @param password String password a verificar
     * @return booleano si es valida o no
     */
    private boolean passwordValidation(String password){
        return password.matches(passRegex);
    }

    /**
     * Valida que todos los datos ingresados para editar o crear una venta sean validos
     * @param idProducto id del producto
     * @param cantidadVendida cantidad a vender
     * @param rutCliente rut del cliente
     * @return booleano que describe si todos los datos son validos o no
     */
    public boolean ventaDTOValidation(String idProducto, String cantidadVendida, String rutCliente){
      if(idValidation(idProducto) && intPositiveValidation(cantidadVendida) && validarRut(rutCliente))
          return true;
      else
          return false;
    }

    /**
     * Valida mediante regex la composicion del rut, que contenga los puntos y guiones que corresponden
     * y donde corresponden
     * @param rut String rut a revisar
     * @return boolean si cumple o no las condiciones
     */
    private boolean validateRutRegex(String rut){
        Pattern pattern = Pattern.compile(rutRegex);
        Matcher matcher = pattern.matcher(rut);

        return matcher.matches();
    }


}

