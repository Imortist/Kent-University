public class submission1 {

    public static class MyStack<T> {
        private Node heap = null;

        class Node {
            T item;
            Node next;
        }

        public T pop() {
            if (isEmpty()) {
                return null;
            }
            T item = heap.item;
            heap = heap.next;
            return item;
        }

        public void push(T item) {
            Node oldtop = heap;
            heap = new Node();
            heap.item = item;
            heap.next = oldtop;
        }

        private boolean isEmpty() {
            return heap == null;
        }
    }

    public int calculate(String input) {
        MyStack<Integer> stack = new MyStack<>();
        String[] vals = input.split(" ");
        for (String v : vals) {
            switch (v) {
                case "+": {
                    stack.push(stack.pop() + stack.pop());
                    break;
                }
                case "-": {
                    stack.push(stack.pop() - stack.pop());
                    break;
                }
                case "x": {
                    stack.push(stack.pop() * stack.pop());
                    break;
                }
                default:
                    stack.push(Integer.parseInt(v));
            }
        }
        return stack.pop();
    }
}



