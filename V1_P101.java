import java.util.PriorityQueue;

public class V1_P101 {
    static java.util.Scanner in;

    public static boolean casoDePrueba() {
        int n = in.nextInt();
        if (n == 0)
            return false;
        else {
            boolean esDiabolico = true;
            int sqrn = n * n;
            PriorityQueue <Integer> colaPrioridades = new PriorityQueue <> (sqrn);
            int [][] matriz = new int[n][n];

            int numero = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    numero = in.nextInt();
                    matriz[i][j] = numero;
                    colaPrioridades.add(numero);
                }
            }

            // Obtenemos la constante mágica
            int CM = 0;
            for (int i = 0; i < n; i++) {
                CM += matriz[0][i];
            }

            int cmFilas, cmColumnas, cmDiagonalPrin = 0, cmDiagonalSec = 0;

            // Comprobamos las filas y columnas
            for (int i = 0; i < n; i++) {
                cmFilas = 0;
                cmColumnas = 0;
                for (int j = 0; j < n; j++) {
                    cmFilas += matriz[i][j];
                    cmColumnas += matriz[j][i];
                }
                if (CM != cmFilas || CM != cmColumnas){
                    esDiabolico = false;
                    break;
                }
            }

            // Seguimos comprobando que es mágico
            if (esDiabolico){
                for (int i = 0; i < n; i++) {
                    cmDiagonalPrin += matriz[i][i];
                    cmDiagonalSec += matriz[i][n - 1 - i];
                }
                if (CM != cmDiagonalPrin || CM != cmDiagonalSec){
                    esDiabolico = false;
                }
            }
            
            if (!esDiabolico){
                System.out.println("NO");
                return true;
            }

            // Comprobación 1
            for (int i = 1; i <= sqrn; i++) {
                if (i != colaPrioridades.poll()){
                    System.out.println("DIABOLICO");
                    return true;
                }
            }

            // Comprobación 2
            int CM2 = (4*CM) / n;
            int sumaEsquinas = matriz[0][0] + matriz[0][n-1] + matriz[n-1][0] + matriz[n-1][n-1];

            if (CM2 != sumaEsquinas){
                System.out.println("DIABOLICO");
                return true;
            }

            // División entre par e impar
            int mitad = n / 2;
            if ((n % 2) != 0){ // impar
                int sumaMitades = matriz[0][mitad] + matriz[mitad][0] + matriz[mitad][n-1] + matriz[n-1][mitad];

                if (CM2 != sumaMitades || (matriz[mitad][mitad] * 4) != CM2){
                    System.out.println("DIABOLICO");
                    return true;
                }
            }else{ // par
                int sumaMitades = matriz[0][mitad] + matriz[mitad][0] + matriz[mitad][n-1] + matriz[n-1][mitad]+
                        matriz[0][mitad-1] + matriz[mitad-1][0] + matriz[mitad-1][n-1] + matriz[n-1][mitad-1];
                int centro = matriz[mitad][mitad] + matriz[mitad-1][mitad] + matriz[mitad][mitad-1] + matriz[mitad-1][mitad-1];

                if (2*CM2 != sumaMitades || centro != CM2){
                    System.out.println("DIABOLICO");
                    return true;
                }
            }

            System.out.println("ESOTERICO");
            return true;
        }
    }

    public static void main(String[] args) {
        in = new java.util.Scanner(System.in);
        while (casoDePrueba()) {}
    }
}
