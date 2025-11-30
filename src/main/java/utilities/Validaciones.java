package utilities;

import java.util.regex.Pattern;

/**
 * @author porfi
 */

public class Validaciones {
    // Verifica que el texto no esté vacío
    public static boolean esTextoValido(String texto){
        return texto != null && !texto.trim().isEmpty();
    }

    // Verifica que el número sea mayor a cero
    public static boolean esNumeroPositivo(int numero){
        return numero > 0;
    }

    // Verifica que el número sea cero o mayor
    public static boolean esNumeroNoNegativo(int numero){
        return numero >= 0;
    }

    // Verifica que el nombre solo tenga letras y espacios
    public static boolean esNombreValido(String texto){
        if (!esTextoValido(texto)) {
            return false;
        }
        Pattern patron = Pattern.compile("^[a-zA-Z\\s]+$");
        return patron.matcher(texto).matches();
    }

    // Verifica que el texto sea un número entero válido
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
    
    // Convierte texto a número, retorna -1 si no es válido
    public static int convertirAEntero(String texto){
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Verifica que la edad esté entre 0 y 18 años
    public static boolean esEdadValida(int edad){
        return edad >= 0 && edad <= 18;
    }
}