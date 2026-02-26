public class V1_P104 {
    static java.util.Scanner in;

    public static boolean casoDePrueba(){
        int [] movil = llegirMovil();

        if (movil[0] == 0 && movil[1] == 0 && movil[2] == 0 && movil[3] == 0) return false;

        if (estaBalancejat(movil)) System.out.println("SI");
        else System.out.println("NO");

        return true;
    }

    public static int[] llegirMovil(){
        int [] movil = new int[4];
        for (byte i = 0; i < 4; i++) movil[i] = in.nextInt();
        return movil;
    }

    public static int calcularPes(int [] movil){
        if (movil[0] == 0) movil[0] = calcularPes(llegirMovil());
        if (movil[2] == 0) movil[2] = calcularPes(llegirMovil());
        return movil[0] + movil[2];
    }

    public static boolean estaBalancejat(int [] movil){
        calcularPes(movil);
        // [pi, di, pd, dd]
        return movil[0] * movil[1] == movil[2] * movil[3];
    }

    public static void main(String [] args){
        in = new java.util.Scanner(System.in);
        while(casoDePrueba());
    }
}
