import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class V1_P198 {
    static BufferedReader in;

    /**
     * Método principal donde se leerá la entrada entera para dar una salida por línea. Cabe destacar que se utiliza el
     * BufferedReader, ya que es mucho más eficiente que no el Scanner.
     */
    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        while (casoDePrueba());
    }

    /**
     * Método que lee una sola línea de la entrada e imprime en la salida estándar la comparación de los cálculos de las
     * notaciones dadas.
     * @return <code>True</code> en el caso de existir más líneas que leer, <code>False</code> por el contrario
     */
    private static boolean casoDePrueba() throws IOException {
        // Leemos la línea entera
        String linea = in.readLine();

        // Si no existe, hemos acabado todas las entradas, por tanto, devolvemos False y termina el programa
        if (linea == null) return false;

        // Como máximo, tendremos tantos objetos en la pila/cola como número de caracteres tengamos en la línea
        int n = linea.length();

        // Creamos la pila y la cola, ambos de tamaño n
        LinkedListStack<Integer> pila = new LinkedListStack<>();
        LinkedListQueue<Integer> cola = new LinkedListQueue<>();

        // Creamos booleanos para guardar si ya ha realizado una división por 0, para no malgastar recursos de cálculo
        // y evitar posibles errores
        boolean errorPila = false, errorCola = false;

        // Creamos dos enteros para guardar los operandos que extraeremos de la pila y la cola cuando tengamos que operar
        int primerOperando, segundoOperando;

        // Iteramos por todos los carácteres de la línea leida anteriormente
        for (int i = 0; i < n; i++) {
            int c = linea.charAt(i);
            switch (c) {
                // Al encontrar un operador obtenemos los operandos, operamos y guardamos en la estructura de datos pertinente
                // (Teniendo en cuenta que el primer valor que se extrae es el segundo operando)
                case '+':
                case '-':
                case '*':
                case '/':
                    // Caso Pila (notación postfija)
                    if (!errorPila) {   // Si ya hemos obtenido un error (división por 0), no hace falta calcular nada más
                        segundoOperando = pila.top(); pila.pop();   // Obtenemos y quitamos de la pila el segundo operando

                        // Validamos la división por cero antes de sacar el primer operando
                        if (c == '/' && segundoOperando == 0) {
                            errorPila = true;
                        } else {
                            primerOperando = pila.top(); pila.pop();   // Obtenemos y quitamos de la pila el primer operando
                            int resultado = 0;
                            // Realizamos el cálculo dependiendo del operador
                            switch (c) {
                                case '+': resultado = primerOperando + segundoOperando; break;
                                case '-': resultado = primerOperando - segundoOperando; break;
                                case '*': resultado = primerOperando * segundoOperando; break;
                                case '/': resultado = primerOperando / segundoOperando; break;
                            }
                            // Guardamos el resultado en la pila
                            pila.push(resultado);
                        }
                    }

                    // Caso cola
                    if (!errorCola) {   // Si ya hemos obtenido un error (división por 0), no hace falta calcular nada más
                        segundoOperando = cola.getFirst(); cola.removeFirst();  // Obtenemos y quitamos de la cola el segundo operando

                        // Validamos la división por cero antes de sacar el primer operando
                        if (c == '/' && segundoOperando == 0) {
                            errorCola = true;
                        } else {
                            primerOperando = cola.getFirst(); cola.removeFirst();   // Obtenemos y quitamos de la pila el primer operando
                            int resultado = 0;
                            // Realizamos el cálculo dependiendo del operador
                            switch (c) {
                                case '+': resultado = primerOperando + segundoOperando; break;
                                case '-': resultado = primerOperando - segundoOperando; break;
                                case '*': resultado = primerOperando * segundoOperando; break;
                                case '/': resultado = primerOperando / segundoOperando; break;
                            }
                            // Guardamos el resultado en la cola
                            cola.put(resultado);
                        }
                    }
                    break;

                // En el caso de que no sea un operador, añadimos los operandos que nos vamos encontrando en la pila y la cola
                default:
                    if (!errorPila) pila.push(c - '0');
                    if (!errorCola) cola.put(c - '0');
                    break;
            }
        }

        // Obtenemos la salida deseada dependiendo del resultado calculado y lo mostramos en la salida estándar
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

        // Fin de la operación de la línea leída, vamos a buscar la siguiente línea.
        return true;
    }

    /**
     * Pila implementada mediante una lista enlazada
     * @param <E> Tipo de dato que guardará la pila
     */
    public static class LinkedListStack<E> {
        private class Node {
            private E elem;
            private Node next;

            public Node(E elem, Node next) {
                this.elem = elem;
                this.next = next;
            }
        }

        private Node top;

        /**
         * Prepara la lista para almacenar elementos
         */
        public LinkedListStack() {
            top = null;
        }

        /**
         * Añade el elemento a la pila
         *
         * @param item elemento a insertar en la pila
         * @return <code>True</code> si se ha insertado con éxito
         */
        public boolean push(E item) {
            top = new Node(item, top);
            return true;
        }

        /**
         * Elimina un elemento de la pila
         *
         * @return <code>True</code> si se ha eliminado con éxito
         */
        public boolean pop() {
            if (!isEmpty()) {
                top = top.next;
                return true;
            } else return false;
        }

        /**
         * @return el elemento de la cima de la pila, si había, sino <code>null</code>
         */
        public E top() {
            if (!isEmpty()) return top.elem;
            else return null;
        }

        /**
         * @return <code>True</code> si la pila está vacía
         */
        public boolean isEmpty() {
            return top == null;
        }

        /**
         * Vacía la pila
         */
        public void empty() {
            top = null;
        }
    }

    /**
     * Cola implementada mediante una lista enlazada
     *
     * @param <E> Tipo de dato que guardará la cola
     */
    public static class LinkedListQueue<E> {
        private class Node {
            private E elem;
            Node next;

            public Node(E elem, Node next) {
                this.elem = elem;
                this.next = next;
            }
        }
        private Node p, q;

        /**
         * Prepara la lista para almacenar elementos
         */
        public LinkedListQueue(){
            p = null;
            q = null;
        }

        /**
         * Añade el elemento a la cola
         *
         * @param item elemento a insertar en la cola
         * @return <code>True</code> si se ha insertado con éxito
         */
        public boolean put(E item) {
            try {
                Node r = new Node(item, null);
                if (p == null) {
                    p = r;
                    q = r;
                } else {
                    p.next = r;
                    p = r;
                }
                return true;
            } catch (OutOfMemoryError e) {
                return false;
            }
        }

        /**
         * Elimina el primer elemento de la cola
         *
         * @return <code>True</code> si se ha eliminado con éxito
         */
        public boolean removeFirst() {
            if (q == null) {
                return false;
            } else {
                q = q.next;
                if (q == null) {
                    p = null;
                }
                return true;
            }
        }

        /**
         * @return el primer elemento de la cola, si había, sino <code>null</code>
         */
        public E getFirst() {
            if (q == null) {
                return null;
            } else {
                return q.elem;
            }
        }

        /**
         * @return <code>True</code> si la cola está vacía
         */
        public boolean isEmpty() {
            return q == null;
        }

        /**
         * Vacía la cola
         */
        public void empty() {
            p = null;
            q = null;
        }
    }
}