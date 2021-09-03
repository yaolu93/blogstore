package sort;

import java.util.Arrays;

public class DivideAndConquerSort {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Integer[] a = {8,2,3,1,4,6,7,5};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * At the begin of the Algorithm, we need to implement some function, which can
     * help us deal the method later. 1, less()==>compare two elements, get the
     * result which is smaller. 2, exch()==> exchange two elements position
     */

    /*
     * Conquer the assistant Array
     */
    private static Comparable[] assistant;

    /**
     * Compare two elements, whether v is smaller than w.
     */
    private static void exchange(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * Sort the order in Array a.
     */
    public static void sort(Comparable[] a) {

        // 1, initial the assistent Array.
        assistant = new Comparable[a.length];
        // 2, define two variable: low and high, which record the lowest and highest
        // index in the Array.
        int low = 0;
        int high = a.length - 1;

        // 3, reload the funtion sort(), which helps sort the Array from low to high.
        sort(a, low, high);

    }

    /**
     * Sort the Array from low to high
     * 
     * @param a
     * @param low
     * @param high
     */
    private static void sort(Comparable[] a, int low, int high) {

        // security check
        if (high <= low) {
            return;
        }

        // divide the elements from index low to high into different group.
        int mid = low + (high - low) / 2;

        // operate different groups, use the thinking of Recursion
        sort(a, low, mid);
        sort(a, mid + 1, high);

        // Conquer the two group of Array
        merge(a, low, mid, high);

    }

    /**
     * Allocate the groups, from low to mid as group 1, from mid+1 to high as group
     * 2. And Conquer two groups
     * 
     * @param a
     * @param low
     * @param mid
     * @param high
     */
    private static void merge(Comparable[] a, int low, int mid, int high) {
        // define three points.
        int i = low;
        int p1 = low;
        int p2 = mid + 1;

        // Traverse， move the point p1 and p2, compare the value of index p1 and p2, and
        // find out which is smaller, then put into the assistant Array
        while (p1 <= mid && p2 <= high) {
            // Compare the value of Index
            // if Value of Index p1 smaller than Value of Index p2,than put the assistant
            // Array value as a[p1++], else put as a[p2++]
            if (less(a[p1], a[p2])) {
                assistant[i++] = a[p1++];
            } else {
                assistant[i++] = a[p2++];
            }
        }

        //Traverse, if p1 is not at the end of the Array,than mapping the rest of the Value into the assistant Array
        while(p1 <= mid){
            assistant[i++] = a[p1++];
        }

        //Traverse, if p2 is not at the end of the Array, than mapping the rest of the value into the assistant Array
        while(p2 <= high){
            assistant[i++] = a[p2++];
        }

        //Copy the assistant Array to the original Array


        for(int index = low; index <= high; index++){
            a[index] = assistant[index];
        }
    }

    /**
     * Compare whether v is smaller than w
     * 
     * @param comparable
     * @param comparable2
     * @return
     */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

}
