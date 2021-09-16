package linear;

import java.util.Iterator;

public class DoublyLinklist<T> implements Iterable<T>{

    public static void main(String[] args){

        System.out.println("DoublyLinklist!");
        DoublyLinklist<String> doublelist = new DoublyLinklist<>(); 
        doublelist.insert("Nash");
        doublelist.insert("Yao");
        doublelist.insert("James");

        doublelist.insert("Macgrady",1);

        //doublelist.insert("who",8);
        doublelist.insert("right One",3);
        

        for (String string : doublelist) {
            System.out.println(string);
        }

        System.out.println("=========================");
        System.out.println("How long list we have :"+ doublelist.length());
        String two = doublelist.get(2);
        System.out.println(two);
        System.out.println("=======================");
        String remove = doublelist.remove(3);
        System.out.println(remove);
        System.out.println("new length :"+ doublelist.length());
    }

    private class Node{

        public Node(T item, Node pre, Node next){
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
        //store the data
        public T item;
        //point toward preview Node
        public Node pre;
        //point toward next Node
        public Node next;
    }
    //first Node
    private Node head;
    //last Node
    private Node last;
    //the length of link
    private int N;

    //constuct initial the paramater
    public DoublyLinklist(){
        last = null;
        head = new Node(null,null,null);
        N=0;
    }

    //clear the link
    public void clear(){
        N = 0;
        last = null;
        head.item = null;
        head.pre = null;
        head.next = null;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    //get the length of the linklist
    public int length(){
        return N;
    }

    public T get(int i){

        if (i<0 || i>N) {
            throw new RuntimeException("the position is illegal");
        }

        Node current = head.next;
        for (int index = 0; index < i; index++) {
            current = current.next;
        }
        return current.item;
    }

    public void insert(T t){

        if (last == null) {
            last = new Node(t,head, null);
            head.next = last;
        }else{
            Node oldLast = last;
            Node newLast = new Node(t, oldLast, null);
            oldLast.next = newLast;
            // this is important make the newLast become the last
            last =  newLast;

        }
        N++;
    }

    public void insert(T t, int i){

        if (i<0 || i>N) {
            throw new RuntimeException("The position is not correct!");
        }

        //find the preview Node
        Node preview = head;
        for (int index = 0; index < i; index++) {
            preview = preview.next;
        }

        //current Node
        Node current = preview.next;

        Node newNode = new Node(t, preview, current);
        

        //this area must have good debugging test.
        preview.next = newNode;
        current.pre = newNode;
        N++;


    }

    //the function remove is similar with the function insert(t,i);
    public T remove(int i){

        if (i < 0 || i> N) {
            throw new RuntimeException("The position is not currect!");
        }

        // find the preview Node
        Node preview = head;
        for (int index = 0; index < i; index++) {
            preview = preview.next;
        }

        //find current (i) Node
        Node current = preview.next;

        // find the i position next
        Node current_next = current.next;

        preview.next = current_next;
        current_next.pre = preview;

        N--;
        return current.item;
    }

    public int indexOf(T t){

        Node current = head;

        for (int i = 0; current.next != null; i++) {
            current = current.next;
            if (current.next.equals(t)) {
                return i;
            }
        }
        return -1;
    }

    public T getFirst(){

        if(isEmpty()){
           return null;
        }
        return head.next.item;
    }

    public T getLast(){

        if (isEmpty()) {
            return null;
        }
        return last.item;
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return new LIterator();
    }

    private class LIterator implements Iterator{    

        private Node node = head;

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return node.next != null;
        }

        @Override
        public Object next() {
            // TODO Auto-generated method stub
            node = node.next;
            return node.item;
        }

    }
    
   
    
}
