package demoCode;
public class dfsDemo {
    public static void main(String[] args) {

    int[] count = new int[255]; 
    count['b']++;
    System.out.println(count['b']);
    int counter=0;
    for(int i=0;i<count.length;i++) {
        System.out.println(counter++ + " " + count[i]);
        }
    }

}
