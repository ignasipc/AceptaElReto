import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class V1_P197 {
    static BufferedReader in;

    public static boolean casoDePrueba() throws IOException {
        String linea = in.readLine();
        if (linea == null) return false;
        int n = linea.length() - 1;

        Deque<Character> pila = new ArrayDeque<>();
        Deque <Character> coa = new ArrayDeque<>();

        System.out.print(linea + " => ");

        boolean esParell = true;
        if (n % 2 == 0){
            for (int i = n; i >= 0; i--) {
                if(esParell){
                    coa.addFirst(linea.charAt(i));
                    esParell = false;
                } else {
                    coa.addLast(linea.charAt(i));
                    esParell = true;
                }
            }
        } else {
            for (int i = n; i >= 0; i--) {
                if(esParell){
                    coa.addLast(linea.charAt(i));
                    esParell = false;
                } else {
                    coa.addFirst(linea.charAt(i));
                    esParell = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!coa.isEmpty()) {
            sb.append(coa.remove());
        }
        linea = sb.toString();

        n++;
        StringBuilder decodificado = new StringBuilder();

        for (int i = 0; i < n; i++) {
            switch (linea.charAt(i)) {
                case 'A': case 'E': case 'I': case 'O': case 'U':
                case 'a': case 'e': case 'i': case 'o': case 'u':
                    while (!pila.isEmpty()){
                        decodificado.append(pila.removeLast());
                    }
                    decodificado.append(linea.charAt(i));
                    break;
                default:
                    pila.addLast(linea.charAt(i));
                    break;
            }
        }
        while (!pila.isEmpty()){
            decodificado.append(pila.removeLast());
        }
        System.out.println(decodificado);

        return true;
    }

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        while (casoDePrueba());
    }
}