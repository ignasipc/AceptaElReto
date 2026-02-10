public class V1_P103 {
    static java.util.Scanner in;

    public static boolean casoDePrueba(){
        byte gradoPolinomio = in.nextByte();
        if (gradoPolinomio == 20) return false;

        short [] polinomios = new short [gradoPolinomio];
    }

    public static void main(String [] args){
        in = new java.util.Scanner(System.in);
        while(casoDePrueba()){}
    }
}