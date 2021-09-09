package linear;


public class LinkList<T>{

    public static void main(String[] args){
        System.out.println("LinkList!");
    }

    //this is the first Node
    private Node head;

    //this is the length of the LinkList
    private int N;

    //construct function    
    public LinkList(){
        //initial the head Node
        head = new Node(null, null);
        N = 0;
    }

    //this is the InnerClass of Node
    private class Node<T>{
        public T item;
        public Node next;

        public Node(T item, Node next){
            this.item = item;
            this.next = next;
        }
    }
    // clear the LinkList
    public void clear(){
        head.next = null;
        head.item = null;
        N = 0;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int length(){
        return N;
    }

    public T get(int i){

        if (i < 0 || i >N) {
            throw new RuntimeException("position is illegal!");
        }
        Node n = head.next;
        
        for (int index = 0; index < i; index++) {
                n = n.next;
        }

        return (T) n.item;

    }

    public void insert(T t){
        //add the Node to the end of the list.
        Node n = head;        
        while(n.next != null){
            n = n.next;
        }
        
        Node newNode = new Node(t, null);

        n.next = newNode;

        N++;
        


    }


    public void insert(int t, T t){
        return;
    }

    public T remove(int i){
        return null;
    }

    public int indexOf(){
        return 0;
    }


}