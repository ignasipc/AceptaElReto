import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class V1_P100 {
    public static void numIteracionesKaprekar() {
        int resultado = in.nextInt();

        for (int i = 0; i < 8; i++) {
            if (resultado == 6174) {
                System.out.println(i);
                break;
            }

            char[] cadenaNumeros = ("" + resultado).toCharArray();
            int numerosLenght = cadenaNumeros.length;

            if (--numerosLenght < 3) {
                char[] aux = {'0', '0', '0', '0'};
                for (int j = 3; j > 0 && numerosLenght >= 0; j--) {
                    aux[j] = cadenaNumeros[numerosLenght--];
                }
                cadenaNumeros = aux;
            }

            int repeticiones = 0;
            for (int j = 1; j < 4; j++) {
                if (cadenaNumeros[0] != cadenaNumeros[j]) break;
                repeticiones++;
            }
            if (repeticiones == 3) {
                System.out.println(8);
                break;
            }

            Arrays.sort(cadenaNumeros);
            char[] numPequeArr = cadenaNumeros;

            Character[] numGrandeArr = new Character[4];
            for (int j = 0; j < 4; j++) {
                numGrandeArr[j] = cadenaNumeros[j];
            }
            Collections.reverse(Arrays.asList(numGrandeArr));

            int numGrande = 0, numPeque = 0, k = 1000;
            for (int j = 0; j < 4; j++) {
                numGrande += (numGrandeArr[j] - '0') * k;
                numPeque += (numPequeArr[j] - '0') * k;
                k /= 10;
            }
            resultado = numGrande - numPeque;
        }
    }

    static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(System.in);

        int n = in.nextInt();
        for (int i = 0; i < n; i++)
            numIteracionesKaprekar();
    }
}