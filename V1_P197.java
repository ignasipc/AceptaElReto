import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class V1_P197 {
    static BufferedReader in;

    public static boolean casoDePrueba() throws IOException {
        String linea = in.readLine();
        if (linea == null) return false;
        int n = linea.length();

        System.out.print(linea + " => ");

        ArrayStack <Character> pila = new ArrayStack<Character>(n);
        ArrayQueue <Character> coa = new ArrayQueue<Character>(n);

        for (int i = 0; i < n; i++) {
            if ( i % 2 == 0){
                coa.put(linea.charAt(i));
            } else {
                pila.push(linea.charAt(i));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!coa.isEmpty()) {
            sb.append(coa.getFirst());
            coa.removeFirst();
        }
        while (!pila.isEmpty()) {
            sb.append(pila.top());
            pila.pop();
        }
        linea = sb.toString();

        StringBuilder decodificado = new StringBuilder();

        for (int i = 0; i < n; i++) {
            switch (linea.charAt(i)) {
                case 'A': case 'E': case 'I': case 'O': case 'U':
                case 'a': case 'e': case 'i': case 'o': case 'u':
                    while (!pila.isEmpty()){
                        decodificado.append(pila.top());
                        pila.pop();
                    }
                    decodificado.append(linea.charAt(i));
                    break;
                default:
                    pila.push(linea.charAt(i));
                    break;
            }
        }
        while (!pila.isEmpty()){
            decodificado.append(pila.top());
            pila.pop();
        }
        System.out.println(decodificado + "");

        return true;
    }

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        while (casoDePrueba());
    }

    public static class ArrayStack <E> {
        private E[] array;
        private int n;

        public ArrayStack(int max) {
            this.n = 0;
            this.array = (E[]) new Object[max];
        }

        public boolean push(E item) {
            if (n < array.length) {
                array[n] = item;
                n++;
                return true;
            } else {
                return false;
            }
        }

        public boolean pop() {
            if (!isEmpty()) {
                n--;
                return true;
            } else {
                return false;
            }
        }

        public E top(){
            if (!isEmpty()) {
                return array[n-1];
            } else {
                return null;
            }
        }

        public boolean isEmpty() {
            return n == 0;
        }

        public void empty(){
            this.n = 0;
        }
    }

    public static class ArrayQueue <E> {
        private E[] array;
        private int n, p, q;

        public ArrayQueue(int max){
            array = (E[]) new Object[max];
            n = 0;
            p = 0;
            q = 0;
        }

        public boolean put(E item) {
            if (n < array.length){
                array[p] = item;
                p = (p + 1) % array.length;
                n++;
                return true;
            }
            else return false;
        }

        public boolean removeFirst() {
            if (n != 0){
                q = (q + 1) % array.length;
                n--;
                return true;
            }
            else return false;
        }

        public E getFirst() {
            if (n != 0) return array[q];
            else return null;
        }

        public boolean isEmpty() {
            return n == 0;
        }

        public void empty() {
            n = 0;
            p = 0;
            q = 0;
        }
    }
}