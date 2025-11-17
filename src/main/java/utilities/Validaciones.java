package utilities;

import java.util.regex.Pattern;

/**
 * @author porfi
 */

public class Validaciones {
    public static boolean esTextoValido(String texto){
        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean esNumeroPositivo(int numero){
        return numero > 0;
    }

    public static boolean esNumeroNoNegativo(int numero){
        return numero >= 0;
    }

    public static boolean esNombreValido(String texto){
        if (!esTextoValido(texto)) {
            return false;
        }
        Pattern patron = Pattern.compile("^[a-zA-Z\\s]+$");
        return patron.matcher(texto).matches();
    }

    public static boolean esNumeroEnteroValido(String texto){
        if (!esTextoValido(texto)) {
            return false;
        }
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static int convertirAEntero(String texto){
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static boolean esEdadValida(int edad){
        return edad >= 0 && edad <= 18;
    }
}