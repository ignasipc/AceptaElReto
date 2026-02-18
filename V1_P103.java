public class V1_P103 {
    static java.util.Scanner in;

    public static boolean casoDePrueba(){
        byte gradoPolinomio = in.nextByte();
        if (gradoPolinomio == 20) return false;

        short [] coeficientes = new short [gradoPolinomio+1];

        for (byte i = 0; i < gradoPolinomio+1; i++) {
            coeficientes[i] = in.nextShort();   // El coeficiente es el número que precede a X. Ej: el coef. de 5x² es 5
        }

        short n = in.nextShort();

        float resultado = 0;
        for (byte i = 0; i < gradoPolinomio; i++) {
            resultado += (1 / (float) n) * funcion( i * (1 / (float) n), coeficientes );
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
        for (byte i = 0; i < coeficientes.length; i++) {
            resultado += coeficientes[i] * (float)Math.pow(x,i);
        }
        if (resultado < 0){
            return 0;
        }
        if (resultado > 1){
            return 1;
        }
        return resultado;
    }

    public static void main(String [] args){
        in = new java.util.Scanner(System.in);
        while(casoDePrueba()){}
    }
}