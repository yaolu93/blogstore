package linear;

public class CircleList {
    
    public static void main(String[] args){
        System.out.println("Circle, Link!");
        newNode();
    }

    public static void newNode(){

        Node<String> first = new Node<String>("aa", null);
        Node<String> second = new Node<String>("bb", null);
        Node<String> third = new Node<String>("cc", null);
        Node<String> forth = new Node<String>("dd", null);
        Node<String> fifth = new Node<String>("ee", null);
        Node<String> sixth = new Node<String>("ff", null);
        Node<String> seventh = new Node<String>("gg", null);
        // just wanna say sorry github
    
        first.next = second;
        second.next = third;
        third.next = forth;
        forth.next = fifth;
        fifth.next = sixth;
        sixth.next = seventh;

        //product the circle
        seventh.next = third;

        boolean circle = isCircle(first);
        System.out.println("the list created circle:"+ circle);

        //get Middle value.
        String middle = getMid(first);
        System.out.println("the middle value is:"+ middle);
    
    }

    private static boolean isCircle(Node<String> first) {

        Node<String> slow = first;
        Node<String> fast = first;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;

            if(fast.equals(slow)){
                return true;
            }
        }
        return false;
    }

    public static String getMid(Node<String> first){

        Node<String> fast = first;
        Node<String> slow = first;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow.item;
    }

    private static class Node<T>{
        public T item;
        public Node next;

        public Node(T item, Node next){
            this.item = item;
            this.next = next;
        }
    }


}
