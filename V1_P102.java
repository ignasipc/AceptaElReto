public class V1_P102 {
    static java.util.Scanner in;

    public static boolean casoDePrueba() {
        String cadenaInput = in.nextLine();
        char [] cadena = cadenaInput.toCharArray();

        // Caso cadena sin codificar
        if (cadena[0] == 'p'){
            // Caso final del programa
            if (cadena.length == 4 && cadena[1] == 'F' && cadena[2] == 'I' && cadena[3] == 'N'){
                return false;
            }

            // Contamos el número de vocales y lo mostramos por pantalla
            short numVocales = 0;
            for (int i = 1; i < cadena.length; i++) {
                if (esVocal(cadena[i])) numVocales++;
            }
            System.out.println(numVocales);
            return true;
        }

        // Caso cadena codificada
        byte desplazamiento = (byte) (cadena[0] - (byte)'p');

        // Caso final del programa codificado
        if (cadena.length == 4
                && descifrarCaracter(desplazamiento, (short)cadena[1]) == (short) 'F'
                && descifrarCaracter(desplazamiento, (short)cadena[2]) == (short) 'I'
                && descifrarCaracter(desplazamiento, (short)cadena[3]) == (short) 'N'){
            return false;
        }

        // Contamos el número de vocales y lo mostramos por pantalla
        short numVocales = 0;
        for (int i = 1; i < cadena.length; i++) {
            if (esVocal(descifrarCaracter(desplazamiento, (short)cadena[i]))) numVocales++;
        }
        System.out.println(numVocales);
        return true;
    }

    /// Dado un código ascii, retorna True si es una vocal y False si no.
    public static boolean esVocal(int ascii){
        return ascii == 97 || ascii == 101 || ascii == 105 || ascii == 111 || ascii == 117
        || ascii == 65 || ascii == 69 || ascii == 73 || ascii == 79 || ascii == 85;
    }

    public static short descifrarCaracter(byte desplazamiento, short caracter){
        short resultado;
        // Caso minúscula
        if (97 <= caracter && caracter <= 122){
            resultado = (short) (caracter - desplazamiento);
            if (resultado > 122) return (short) (resultado-26);
            if (resultado < 97) return (short) (resultado+26);
            return resultado;
        }
        // Caso mayúscula
        if (65 <= caracter && caracter <= 90){
            resultado = (short) (caracter - desplazamiento);
            if (resultado > 90) return (short) (resultado-26);
            if (resultado < 65) return (short) (resultado+26);
            return resultado;
        }
        // Caso distinto, nos da igual, no será una vocal
        return 0;
    }

    public static void main (String[]args){
        in = new java.util.Scanner(System.in);
        while (casoDePrueba()){}
    }
}