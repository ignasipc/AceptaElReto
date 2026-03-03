import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

public class V1_P141 {

    static BufferedReader in;

    public static boolean casoDePrueba() throws IOException {
        Stack<Character> pila = new Stack<>();
        String linea = in.readLine();
        if (linea == null) return false;
        int n = linea.length();

        for (int i = 0; i < n; i++) {
            char ch = linea.charAt(i);

            switch (ch) {
                case '(':
                case '[':
                case '{':
                    pila.push(ch);
                    break;

                case ')':
                    if (pila.isEmpty() || pila.pop() != '(') {
                        System.out.println("NO");
                        return true;
                    }
                    break;

                case ']':
                    if (pila.isEmpty() || pila.pop() != '[') {
                        System.out.println("NO");
                        return true;
                    }
                    break;

                case '}':
                    if (pila.isEmpty() || pila.pop() != '{') {
                        System.out.println("NO");
                        return true;
                    }
                    break;
            }
        }

        if (pila.isEmpty()) {
            System.out.println("YES");
        } else System.out.println("NO");

        return true;
    }

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        while (casoDePrueba());
    }
}