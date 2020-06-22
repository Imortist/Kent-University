public class Npn {

    public static class MyStack<T>{
        private Node heap = null;


        class Node{
            T item;
            Node next;
        }


        public T pop(){
            if(isEmpty()){
                return null;
            }
            T item = heap.item;
            heap = heap.next;
            return item;
        }


        public void push(T item){
            Node oldtop = heap;
            heap = new Node();
            heap.item = item;
            heap.next = oldtop;
        }

        private boolean isEmpty(){
            return heap == null;
        }
    }

    public int calculate(String input) {
        MyStack<Integer> ints = new MyStack<>();
        String[] vals = input.split(" ");
        for(int i = 0; i< vals.length/2; i++){
            String temp = vals[i];
            vals[i] = vals[vals.length - i -1];
            vals[vals.length -i -1] = temp;
        }

        for(String v :vals){
            switch(v){
                case "+" :{
                    ints.push(ints.pop() + ints.pop());
                    break;
                }
                case "-":{
                    ints.push(ints.pop() - ints.pop());
                    break;
                }
                case"x":{
                    ints.push(ints.pop() * ints.pop());
                    break;
                }
                default: ints.push(Integer.parseInt(v));
            }
        }
        return ints.pop();
    }

}


