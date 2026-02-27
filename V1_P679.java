import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class V1_P679 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void casoDePrueba() throws IOException {
        Deque<String> pila = new ArrayDeque<>();
        Deque<String> pilaBorrado = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        String palabra = st.nextToken();
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
            palabra = st.nextToken();
        }

        StringBuilder frase = new StringBuilder();
        for (java.util.Iterator<String> it = pila.descendingIterator(); it.hasNext();) {
            frase.append(it.next());
            if (it.hasNext()) frase.append(" ");
        }
        System.out.println(frase);
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) casoDePrueba();
    }
}
