package linear;

public class JosephCircle {
    public static void main(String[] args) {
        System.out.println("Joseph, Circle!");

        //make the cirlce
        Node<Integer> first = null;
        Node<Integer> pre = null;

        for(int i=1; i<= 41; i++){

            if (i == 1) {
                first = new Node (i, null);
                pre = first;
                continue;
            }
            
            Node newNode = new Node(i, null);
            pre.next = newNode;
            pre = newNode;

            if (i ==41) {
             pre.next = first;   
            }

        }
        
        Node begin = first;
        Node current = null;

        //creat count register
        int count = 0;
        
        //check and remove the number.
        while(begin != begin.next){
            count++;

            //when the count =3, when the count != 3
            if (count ==3) {
                current.next = begin.next;
                begin = begin.next;
                count = 0;
                System.out.println(begin.item +",");
            } else {
                current = begin;
                begin = begin.next;

            }
        }

        System.out.println("current and exist number is :" + current.item.toString());
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
