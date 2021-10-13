package linear;

import java.util.Iterator;

public class Stack<T> implements Iterable<T> {

    private class Node{
        public T item;
        public int N;

        private class Node{
            public T item;
            public Node next;

            public Node(T item, Node next){
                this.item = item;
                this.next = next;
            }
        }
        
    }
    // recording the link size;
    private int N;
    // recording the first Node
    private Node head;


    public Stack(){
        this.head = new Node(null, null);
        this.N = 0;
    }

    //check is the elements in the stack is empty
    public boolean isEmpty(){
        return N == 0;
    }

    //get the size of the elements
    public int size(){
        return N;
    }

    //push the T into the stack
    public void push(T t){

        Node top = head.next;

        Node newNode = new Node(t, null);

        head.next = newNode;

        newNode.next = top;

        N++;

    }

    public T pop(){
        Node top = head.next;
        if(top == null){
            return null;
        }

        head.next = top.next;
        N--;

        return top.item;
    }



    public static void main(String[] args) {
        System.out.println("Stack");
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return new SIterator();
    }

    private class SIterator implements Iterator{
        
        private Node n;

        public SIterator(){
            this.n = head;
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return n.next != null;
        }

        @Override
        public Object next() {
            // TODO Auto-generated method stu

            n = n.next;

            return n.item;
        }

    }
}
