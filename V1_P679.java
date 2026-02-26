import java.util.ArrayDeque;
import java.util.Deque;

public class V1_P679 {
    static java.util.Scanner in;

    static Deque<String> pila = new ArrayDeque<>();
    static Deque<String> pilaBorrado = new ArrayDeque<>();

    public static void casoDePrueba(){
        pila.clear();
        pilaBorrado.clear();

        String palabra = in.next();
        while(!palabra.equals(".")){
            if (palabra.equals("<")) {  // Pulsa Ctrl + Z
                if (!pila.isEmpty()){
                    pilaBorrado.push(pila.pop());
                }
            } else if (palabra.equals(">")) {   // Pulsa Ctrl + Y
                if (!pilaBorrado.isEmpty()){
                    pila.push(pilaBorrado.pop());
                } else {
                    if (!pila.isEmpty()){
                        pila.push(pila.peek());
                    }
                }
            } else {    // Escribe una palabra
                pila.push(palabra);
                pilaBorrado.clear();
            }
            palabra = in.next();
        }

        if (!pila.isEmpty()) {
            StringBuilder frase = new StringBuilder(pila.pop());
            while (!pila.isEmpty()) {
                frase.insert(0, pila.pop() + " ");
            }
            System.out.println(frase);
        } else {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        in = new java.util.Scanner(System.in);

        int n = in.nextInt();
        for (int i = 0; i < n; i++)
            casoDePrueba();
    }
}
