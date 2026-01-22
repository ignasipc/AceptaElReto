import java.util.PriorityQueue;

public class V1_P101 {
    static java.util.Scanner in;

    public static boolean casoDePrueba() {
        int n = in.nextInt();
        if (n == 0)
            return false;
        else {
            boolean resultadoIndicado = false;
            int sqrn = n * n;
            PriorityQueue <Integer> colaPrioridades = new PriorityQueue <> (sqrn);
            int [][] matriz = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matriz[i][j] = in.nextInt();
                    colaPrioridades.add(matriz[i][j]);
                }
            }

            // Obtenemos la constante mágica
            int CM = 0;
            int cmFilas, cmColumnas, cmDiagonalPrin = 0, cmDiagonalSec = 0;
            for (int i = 0; i < n; i++) {
                cmFilas = 0;
                cmColumnas = 0;
                for (int j = 0; j < n; j++) {
                    if (i == 0){
                        CM+=matriz[0][j];
                    }

                    if (i==j){
                        cmDiagonalPrin += matriz[i][j];
                    }

                    if (i+j == n-1){
                        cmDiagonalSec += matriz[i][j];
                    }

                    cmFilas += matriz[i][j];
                    cmColumnas += matriz[j][i];
                }
                if ((CM != cmFilas || CM != cmColumnas) && !resultadoIndicado){
                    System.out.println("NO");
                    resultadoIndicado = true;
                }
            }
            if ((CM != cmDiagonalPrin || CM != cmDiagonalSec) && !resultadoIndicado){
                resultadoIndicado = true;
                System.out.println("NO");
            }

            // Comprobación 1
            for (int i = 1; i <= sqrn; i++) {
                if (i != colaPrioridades.poll()){
                    System.out.println("DIABOLICO");
                    resultadoIndicado = true;
                    break;
                }
            }

            // Comprobación 2
            if (!resultadoIndicado){
                int CM2 = 4*CM /n;
                int sumaEsquinas = matriz[0][0] + matriz[0][n-1] + matriz[n-1][0] + matriz[n-1][n-1];

                if (CM2 != sumaEsquinas){
                    System.out.println("DIABOLICO");
                    resultadoIndicado = true;
                }
                if (!resultadoIndicado){
                    if (n%2 != 0){ // impar

                        int mitad = n/2;
                        int sumaMitades = matriz[0][mitad] + matriz[mitad][0] + matriz[mitad][n-1] + matriz[n-1][mitad];

                        if (CM2 != sumaMitades){
                            System.out.println("DIABOLICO");
                            resultadoIndicado = true;
                        }

                        if(!resultadoIndicado){
                            if (matriz[mitad][mitad]*4 != CM2){
                                System.out.println("DIABOLICO");
                                resultadoIndicado = true;
                            }
                        }

                    }else{  //par

                        int mitad = n/2;
                        int sumaMitades = matriz[0][mitad] + matriz[mitad][0] + matriz[mitad][n-1] + matriz[n-1][mitad]+
                                matriz[0][mitad-1] + matriz[mitad-1][0] + matriz[mitad-1][n-1] + matriz[n-1][mitad-1];

                        if (2*CM2 != sumaMitades){
                            System.out.println("DIABOLICO");
                            resultadoIndicado = true;
                        }

                        if(!resultadoIndicado){
                            int centro = matriz[mitad][mitad] + matriz[mitad-1][mitad] + matriz[mitad][mitad-1] + matriz[mitad-1][mitad-1];
                            if (centro != CM2){
                                System.out.println("DIABOLICO");
                                resultadoIndicado = true;
                            }
                        }

                    }
                    if (!resultadoIndicado){
                        System.out.println("ESOTERICO");
                    }
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        in = new java.util.Scanner(System.in);
        while (casoDePrueba()) {}
    }
}
