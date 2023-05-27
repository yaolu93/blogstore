package linear;

import java.util.Iterator;

public class SequenceList<T> implements Iterable<T>{

    public static void main(String[] args){
        //System.out.println("Sequence, List!");

        SequenceList<String> s1 = new SequenceList<String>(10);

        s1.insert("Yao Ming");
        s1.insert("Kobe");
        s1.insert("MCgrady");
        s1.insert(1,"James");

        for (String single : s1) {
            System.out.println("name:"+single);
        }
        System.out.println("=============================");
        String getResult = s1.get(1);
        System.out.println("get the index 1 element:"+ getResult);

        String removeResult = s1.remove(0);
        System.out.println("get the remove element:"+ removeResult); 

        s1.clear();
        System.out.println("the left elements number is :"+ s1.length()); 
 

    }

    //The Array of store the elements.
    private T[] elements;

    //Recording the number of elements.
    private int N;

    //Construct function.
    public SequenceList(int capacity){
        elements = (T[]) new Object[capacity];
        N = 0;
    }

    //set a Linear as empty.
    public void clear(){
        N = 0;
    }

    //check is current linear is empty
    public boolean isEmpty(){
        return N == 0;
    }

    //get the length of the linear.
    public int length(){
        return N;
    }

    //get the position of element.
    public T get(int i){
        if(i < 0 || i > N){
            throw new RuntimeException("the element does not exist!");
        }

        return elements[i];
    }

    // add new element into the linear.
    public void insert(T t){

        if(N == elements.length){
            throw new RuntimeException("the linear is full!");
        }
        elements[N++] = t;

    }
    
    // insert the element at the i position
    public void insert(int i, T t){
        if(i == elements.length){
            throw new RuntimeException("the linear is full");
        }
        if(i<0 || i>N){
            throw new RuntimeException("the insert posistion is illegal!");
        }

        //put the all i elements position toward next step.
        for (int index = N; index > i; index--) {
           elements[index] = elements[index-1];
        }

        //put element t into i position
        elements[i] = t;
        N++;
    }

    //remove the i position element, then return the i element.
    public T remove(int i){
        //keep in mind this is not N! is N-1.
        if (i<0 || i>N-1) {
            throw new RuntimeException("the element is not exist!");
        }
        //recording the i element
        T result  = elements[i];

        //after remove, move all the elements back
        for (int index = i; index < N-1; index++) {
            elements[index] = elements[index++];
        }
        N--;
        return result;
    }

    //figure out where is the t element first time apprears.
    public int indexOf(T t){
        if (t == null) {
            throw new RuntimeException("the element is illegal");
        }

        for (int i = 0; i < N; i++) {
           if (elements[i].equals(t)) {
            return i;               
           } 
        }

        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return new SIterator();
    }

    private class SIterator implements Iterator{

        int cursor;
        public SIterator(){
            this.cursor = 0;
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return cursor < N;
        }

        @Override
        public Object next() {
            // TODO Auto-generated method stub
            return elements[cursor++];
        }

    }

}
