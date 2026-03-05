public class V7_P798 {
    static java.util.Scanner in;

    public static boolean casoDePrueba(){
        byte primeraEntrada = in.nextByte();
        if(primeraEntrada == 0) return false;

        byte segundaEntrada;
        StackWithQueues<Byte> stack = new StackWithQueues<Byte>();

        for (int i = 0; i < primeraEntrada; i++) {
            segundaEntrada = in.nextByte();
            stack.push(segundaEntrada);
        }

        StackWithQueues<Byte> stackGuardados = new StackWithQueues<Byte>();
        primeraEntrada++;

        for (int i = 1; i < primeraEntrada; i++) {
            byte vagon = stack.top();
            stack.pop();

            if (vagon != i){
                stackGuardados.push(vagon);
            }
        }

        return true;
    }



    public static void main(String[] args) {
        in = new java.util.Scanner(System.in);
        while(casoDePrueba());
    }

    public static class StackWithQueues<E> {
        private LinkedListQueue<E> q1, q2;

        public StackWithQueues() {
            q1 = new LinkedListQueue<>();
            q2 = new LinkedListQueue<>();
        }

        /**
         * Adds an item into the stack. O(1) efficient
         * @param item item to introduce into the stack
         * @return item introduced successfully
         */
        public boolean push(E item) {
            return q1.put(item);
        }

        /**
         * Removes the item at the top of the stack. Very inefficient O(elements-1)
         * @return Remove is successful
         */
        public boolean pop() {
            int n = q1.getNumElements()-1;
            for (int i = 0; i < n; i++) {
                E item = q1.getFirst();
                boolean success = q1.removeFirst();
                if (!success || item == null) return false;
                q1.put(item);
            }
            q1.removeFirst();
            return true;
        }

        /**
         * Obtain the item at the top of the stack. Very inefficient
         * @return item at the top of the stack
         */
        public E top() {
            int n = q1.getNumElements()-1;
            for (int i = 0; i < n; i++) {
                E item = q1.getFirst();
                q1.removeFirst();
                q2.put(item);
            }
            E itemToReturn = q1.getFirst();
            q1.removeFirst();
            q2.put(itemToReturn);
            while (!q2.isEmpty()) {
                q1.put(q2.getFirst());
                q2.removeFirst();
            }
            return itemToReturn;
        }

        public boolean isEmpty() {
            return q1.isEmpty();
        }

        public void empty() {
            q1.empty();
        }
    }

    public static class LinkedListQueue<E> {
        private class Node {
            private E elem;
            private Node next;

            public Node(E item, Node n) {
                elem = item;
                next = n;
            }
        }

        private Node p, q;
        private int numElements;

        public LinkedListQueue(){
            p = q = null;
            numElements = 0;
        }

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
                numElements++;
                return true;
            } catch (OutOfMemoryError e) {
                return false;
            }
        }

        public boolean removeFirst() {
            if (q == null) {
                return false;
            } else {
                q = q.next;
                if (q == null) {
                    p = null;
                }
                numElements--;
                return true;
            }
        }

        public E getFirst() {
            if (q == null) {
                return null;
            } else {
                return q.elem;
            }
        }

        public int getNumElements() {
            return numElements;
        }

        public boolean isEmpty() {
            return q == null;
        }

        public void empty() {
            p = null;
            q = null;
            numElements = 0;
        }
    }
}
