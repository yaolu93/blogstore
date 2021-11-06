package linear;

import java.util.Iterator;

public class LinkList<T> implements Iterable<T>{

    public static void main(String[] args){
        System.out.println("LinkList!");

        LinkList<String> link = new LinkList<>();
        link.insert(0, "number01");
        link.insert(1, "number02");
        link.insert(2, "number03");
        link.insert(3, "number04");
    
        for (String string : link) {
            System.out.println(string);
        }

        System.out.println(link.length());
        
        System.out.println("======================="); 
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


    public void insert(int i, T t){

        if (i<0 || i> N) {
            throw new RuntimeException("the position is not correct!");
        }        
        //find the front Node.
        Node pre = head;

        for(int index = 0; index <= i-1; index++) {
            pre = pre.next;
        }

        //find current i position
        Node cursor = pre.next;

        //contruct new Node then make the new pointer.
        Node newNode = new Node(t,cursor);
        //make the front Node point newNode
        pre.next = newNode;

        N++;
 
    }

    public T remove(int i){
        

        //check Boundary conditions
        if (i<0 || i>N) {
            throw new RuntimeException("this is wrong position");
        }
        //find the front position
        Node pre = head;
        for (int index = 0; index < i-1; index++) {
            pre = pre.next;
        }

        //find the current i position
        Node cursor = pre.next;

        //make front point toward next point, the current element will remove automactically
        pre.next = cursor.next;

        //Important Notice N--.
        N--;

        return (T) cursor.item;
    }

    public int indexOf(T t){

        Node n = head;

        for (int i = 0; n.next != null; i++) {
            n = n.next;
            if (n.item.equals(t)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return new LIterator();
    }

    private class LIterator implements Iterator<T>{

        private Node n;
        public LIterator(){
            n = head;
        }
        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return n.next != null;
        }

        @Override
        public T next() {
            // TODO Auto-generated method stub
            n = n.next;
            return (T)n.item;
        }


    }

        //用来反转整个链表
    public void reverse(){

        //判断当前链表是否为空链表，如果是空链表，则结束运行，如果不是，则调用重载的reverse方法完成反转
        if (isEmpty()){
            return;
        }

        reverse(head.next);
    }

    //反转指定的结点curr，并把反转后的结点返回
    public Node reverse(Node curr){
	//what we  really need to knwo is
	// type variable = value;
        if (curr.next==null){
            head.next=curr;
            System.out.println("Curr="+ curr.item.toString());
            return curr;
        }
        System.out.println("curr1="+ curr.item.toString());
        //递归的反转当前结点curr的下一个结点；返回值就是链表反转后，当前结点的上一个结点
        Node pre = reverse(curr.next);
        System.out.println("pre1="+ pre.item.toString());
        System.out.println("curr2="+ curr.item.toString()); 
        System.out.println("curr.next1="+ curr.next.item.toString());

        //让返回的结点的下一个结点变为当前结点curr；
        pre.next=curr;
        System.out.println("curr3="+ curr.item.toString());
        System.out.println("pre2="+ pre.item.toString());
        System.out.println("pre.next="+ pre.next.item.toString());
        //把当前结点的下一个结点变为null
        System.out.println("curr.next2="+ curr.next.item.toString());
        curr.next=null;
        System.out.println("curr4="+ curr.item.toString());
        return curr;
    }



}
