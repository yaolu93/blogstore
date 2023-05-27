package linear;

import java.util.Iterator;

public class NewQueue<T>  implements Iterable<T>{

    private Node head;
    private Node last;

    private int N;

    private class Node {
        public T item;
        public Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public NewQueue(){
	this.head = new Node(null, null);
	this.last = null;
	this.N = 0;
	
    }

    // check the Queue is Empty
    public boolean isEmpty() {
        return N == 0;
    }

    // return the size of the Queue
    public int size() {
        return N;
    }

    // insert the Element into the Queue
    public void addQueue(T t) {
        // if the last Node is Null
        if (last == null) {
            last = new Node(t, null);
            head.next = last; // it means head's first paraments always be null

        } else {
            // if the last Node is not Null
            Node oldLast = last;
            last = new Node(t, null);
            oldLast.next = last;
        }

        N++;
    }

    // take out one element from the Queue

    public T deQueue() {
        if (isEmpty()) {
            return null;
        }

        Node oldFirst = head.next;
        head.next = oldFirst.next;
        N--;

         if (isEmpty()) {
            last = null;
         }

        return oldFirst.item;
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return new QIterator();
    }

    private class QIterator implements Iterator{
        private Node temp;

        public QIterator(){
            this.temp = head;
        }
        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return temp.next != null;
        }

        @Override
        public Object next() {
            // TODO Auto-generated method stub
            temp = temp.next;
            return temp.item;
        }

    }
    public static void main(String[] args) {
       NewQueue<String> queue = new NewQueue<>();
       
       queue.addQueue("a");
       queue.addQueue("b");
    //    queue.addQueue("c");
    //    queue.addQueue("d");


       for (String string : queue) {
           System.out.println(string);
       }
       
       System.out.println("====================");
       String result = queue.deQueue();
       System.out.println("the element removed from the Queue is :" + result);
       System.out.println("the number of left Queue is :" + queue.size());
    }
}
