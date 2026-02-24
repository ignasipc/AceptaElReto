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
        for (int i = 0; i < n; i++) {
            resultado += ((float) 1 / n) * funcion((float) i / n, coeficientes);
        }

        if (0.4999 <= resultado && resultado <= 0.5001){
            System.out.println("JUSTO");
        } else if (0.4999 > resultado) {
            System.out.println("ABEL");
        } else {
            System.out.println("CAIN");
        }
        return true;
    }

    public static float funcion(float x, short [] coeficientes){
        float resultado = 0;
        for (short i = 0; i < coeficientes.length; i++) {
            resultado += (float) (coeficientes[i] * Math.pow(x,coeficientes.length-(i+1)));
        }
        if (resultado <= 0){
            return 0;
        }
        if (resultado >= 1){
            return 1;
        }
        return resultado;
    }

    public static void main(String [] args){
        in = new java.util.Scanner(System.in);
        while(casoDePrueba()){}
    }
}