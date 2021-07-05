package Utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation {
    // Clase para la validacion de datos tipo rut o email, debe poseer metodos que permitan
    //validar que estos tengan el formato correcto
    private final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public boolean validarRut(String rut) {

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

    private int[] invertirRutArray(int[] rutInt) {
        int[] rutInverso = new int[rutInt.length];
        int j = rutInt.length - 1;
        for (int i = 0; i < rutInt.length; i++) {
            rutInverso[i] = rutInt[j];
            j--;
        }
        return rutInverso;
    }

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

    private String replaceRutCharacters(String rut) {
        rut = rut.toUpperCase();
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        return rut;
    }

    public boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public boolean validarRutDB(int rut){
        return 1000000 <= rut && rut <= 100000000;
    }

    public String getCompleteRut(int rut){
        if(validarRutDB(rut)){
            char dv = calcularDV(Integer.toString(rut));
            return Integer.toString(rut)+"-"+dv;
        }else{
            return "";
        }
    }




}
