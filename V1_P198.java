import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class V1_P198 {
    static BufferedReader in;

    private static boolean casoDePrueba() throws IOException {
        String linea = in.readLine();
        if (linea == null) return false;

        int n = linea.length();
        V1_P198.ArrayStack<Integer> pila = new V1_P198.ArrayStack<>(n);
        V1_P198.ArrayQueue<Integer> cola = new V1_P198.ArrayQueue<>(n);

        boolean errorPila = false, errorCola = false;
        int primerOperando, segundoOperando;
        for (int i = 0; i < n; i++) {
            int c = linea.charAt(i);
            switch (c) {
                // Al encontrar un operador obtenemos los operandos, operamos y guardamos en la estructura
                // (Teniendo en cuenta que el primer valor que se extrae es el segundo operando)
                case '+':
                case '-':
                case '*':
                case '/':
                    // Caso Pila
                    if (!errorPila) {
                        segundoOperando = pila.top(); pila.pop();   // Obtenemos y quitamos de la pila

                        // Validamos la división por cero antes de sacar el primer operando
                        if (c == '/' && segundoOperando == 0) {
                            errorPila = true;
                        } else {
                            primerOperando = pila.top(); pila.pop();
                            int resultado = 0;
                            // Switch tradicional para el cálculo
                            switch (c) {
                                case '+': resultado = primerOperando + segundoOperando; break;
                                case '-': resultado = primerOperando - segundoOperando; break;
                                case '*': resultado = primerOperando * segundoOperando; break;
                                case '/': resultado = primerOperando / segundoOperando; break;
                            }
                            pila.push(resultado);
                        }
                    }

                    // Caso cola
                    if (!errorCola) {
                        segundoOperando = cola.getFirst(); cola.removeFirst();  // Obtenemos y quitamos de la cola

                        if (c == '/' && segundoOperando == 0) {
                            errorCola = true;
                        } else {
                            primerOperando = cola.getFirst(); cola.removeFirst();
                            int resultado = 0;
                            switch (c) {
                                case '+': resultado = primerOperando + segundoOperando; break;
                                case '-': resultado = primerOperando - segundoOperando; break;
                                case '*': resultado = primerOperando * segundoOperando; break;
                                case '/': resultado = primerOperando / segundoOperando; break;
                            }
                            cola.put(resultado);
                        }
                    }
                    break;

                // Añadimos los operandos que nos vamos encontrando
                default:
                    if (!errorPila) pila.push(c - '0');
                    if (!errorCola) cola.put(c - '0');
                    break;
            }
        }

        if (errorPila && errorCola) {
            System.out.println("ERROR = ERROR");
        } else if (!errorPila && !errorCola) {
            if (Objects.equals(pila.top(), cola.getFirst())) {
                System.out.println(pila.top() + " = " + cola.getFirst());
            } else {
                System.out.println(pila.top() + " != " + cola.getFirst());
            }
        } else {
            if (errorPila) {
                System.out.println("ERROR != " + cola.getFirst());
            } else {
                System.out.println(pila.top() + " != ERROR");
            }
        }

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