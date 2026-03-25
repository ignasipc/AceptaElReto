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
        V1_P198.ArrayStack<Integer> pila = new V1_P198.ArrayStack<>(n);
        V1_P198.ArrayQueue<Integer> cola = new V1_P198.ArrayQueue<>(n);

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
     * Implementación de pila vista en clase.
     * @param <E> tipo de dato que manejará la pila
     */
    public static class ArrayStack <E> {
        private E[] array;
        private int n;

        /**
         * Prepara el array para almacenar los elementos en la pila
         * @param max Tamaño máximo de elementos de la pila
         */
        public ArrayStack(int max) {
            this.n = 0;
            this.array = (E[]) new Object[max];
        }

        /**
         * Inserta el elemento en la pila
         * @param item El elemento que se guardará en la pila
         * @return <code>True</code> si se inserta correctamente
         */
        public boolean push(E item) {
            if (n < array.length) {
                array[n] = item;
                n++;
                return true;
            } else {
                return false;
            }
        }

        /**
         * Elimina un elemento de la pila
         * @return <code>True</code> si se elimina el elemento exitosamente
         */
        public boolean pop() {
            if (!isEmpty()) {
                n--;
                return true;
            } else {
                return false;
            }
        }

        /**
         * @return El elemento en la cima de la pila, <code>Null</code> si no hay
         */
        public E top(){
            if (!isEmpty()) {
                return array[n-1];
            } else {
                return null;
            }
        }

        /**
         * @return <code>True</code> si la pila está vacía
         */
        public boolean isEmpty() {
            return n == 0;
        }

        /**
         * Vacía la pila
         */
        public void empty(){
            this.n = 0;
        }
    }

    /**
     * Implementación de cola vista en clase.
     * @param <E> tipo de dato que manejará la pila
     */
    public static class ArrayQueue <E> {
        private E[] array;
        private int n, p, q;

        /**
         * Prepara el array para almacenar los elementos en la cola
         * @param max Tamaño máximo de elementos en la cola
         */
        public ArrayQueue(int max){
            array = (E[]) new Object[max];
            n = 0;
            p = 0;
            q = 0;
        }

        /**
         * Inserta el elemento en la cola
         * @param item Elemento insertado en la cola
         * @return <code>True</code> si se ha insertado exitosamente
         */
        public boolean put(E item) {
            if (n < array.length){
                array[p] = item;
                p = (p + 1) % array.length;
                n++;
                return true;
            }
            else return false;
        }

        /**
         * Eliminamos de la cola el primer elemento
         * @return <code>True</code> si se ha eliminado correctamente
         */
        public boolean removeFirst() {
            if (n != 0){
                q = (q + 1) % array.length;
                n--;
                return true;
            }
            else return false;
        }

        /**
         * Obtenemos el primer elemento de la cola
         * @return El primer elemento insertado y no eliminado de la cola, <code>null</code> si no quedan elementos
         */
        public E getFirst() {
            if (n != 0) return array[q];
            else return null;
        }

        /**
         * @return <code>True</code> si la cola está vacía
         */
        public boolean isEmpty() {
            return n == 0;
        }

        /**
         * Vacía la cola
         */
        public void empty() {
            n = 0;
            p = 0;
            q = 0;
        }
    }
}