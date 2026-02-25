public class V1_P103 {
    static java.util.Scanner in;

    public static boolean casoDePrueba(){
        // Obtenemos el grado del polinomio
        byte gradoPolinomio = in.nextByte();
        if (gradoPolinomio == 20) return false;

        // Obtenemos los coeficientes en orden decreciente respecto al grado
        short [] coeficientes = new short [gradoPolinomio+1];
        for (short i = 0; i < coeficientes.length; i++) {
            coeficientes[i] = in.nextShort();   // El coeficiente es el número que precede a X. Ej: el coef. de 5x² es 5
        }

        // Obtenemos el número de rectángulos que queremos crear
        int n = in.nextInt();

        float resultado = 0;
        float invN = 1.0f / n;
        for (float i = 0; i < n; i++) {
            resultado += invN * funcion(i / n, coeficientes);
        }

        if (0.4999 <= resultado && resultado <= 0.5001) System.out.println("JUSTO");
        else if (0.4999 > resultado) System.out.println("ABEL");
        else System.out.println("CAIN");
        return true;
    }

    public static float funcion(float x, short [] coeficientes){
        float resultado = 0;
        float xPower = 1.0f; // x^0 = 1
        for (int i = coeficientes.length-1; i >= 0; i--) {
            resultado += coeficientes[i] * xPower;
            xPower *= x; // Multiplicamos por x para obtener la siguiente potencia
        }
        if (resultado <= 0) return 0;
        if (resultado >= 1) return 1;
        return resultado;
    }

    public static void main(String [] args){
        in = new java.util.Scanner(System.in);
        while(casoDePrueba()){}
    }
}