public class V6_P624 {
    static java.util.Scanner in;

    public static boolean casoDePrueba(){
        short n = in.nextShort();

        if (n==0) return false;

        boolean [] calcetinesMesa = new boolean[100];
        byte idCalcetin, calcetinesSinPareja = 0, maxCalcetSinPareja = 0;
        for (int i = 0; i < n; i++) {
            idCalcetin = in.nextByte();

            // Busco si ya tengo éste código del calcetín en la mesa
            if (calcetinesMesa[idCalcetin-1]){      // Si ya lo tengo
                calcetinesMesa[idCalcetin-1] = false;   // Lo emparejo y dejo el espacio vacío
                calcetinesSinPareja--;                  // Decremento el número de calcetines sin pareja actualmente
            } else {
                calcetinesMesa[idCalcetin-1] = true;    // Lo dejo en la mesa
                calcetinesSinPareja++;                  // Aumento el número de calcetines sin pareja actualmente

                if (maxCalcetSinPareja < calcetinesSinPareja){  // Si el número maximo de calcetines sin pareja es menor que los actuales, hemos encontrado un nuevo máximo
                    maxCalcetSinPareja = calcetinesSinPareja;
                }
            }
        }
        System.out.println(maxCalcetSinPareja);

        return true;
    }

    public static void main(String [] args){
        in = new java.util.Scanner(System.in);
        while(casoDePrueba()){}
    }
}
