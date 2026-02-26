public class V1_P679 {
    static java.util.Scanner in;

    public static void casoDePrueba(){
        java.util.Stack <String> pila = new java.util.Stack<>();
        java.util.Stack <String> pilaBorrado = new java.util.Stack<>();

        String palabra = in.next();
        while(!palabra.equals(".")){
            if (palabra.equals("<")) {
                if (!pila.empty()){
                    pilaBorrado.push(pila.pop());
                }
            } else if (palabra.equals(">")) {
                if (!pilaBorrado.empty()){
                    pila.push(pilaBorrado.pop());
                }
            } else {
                pila.push(palabra);
            }
            palabra = in.next();
        }

        String frase = pila.pop();
        while (!pila.empty()) {
            frase = pila.pop() + " " + frase;
        }
        System.out.println(frase);
    }

    public static void main(String[] args) {
        in = new java.util.Scanner(System.in);

        int n = in.nextInt();
        for (int i = 0; i < n; i++)
            casoDePrueba();
    }
}
