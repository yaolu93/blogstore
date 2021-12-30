package sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        System.out.println("Quick, Sort!");
        Integer[] array = {6,1,3,2,5,4,8,7};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * Initial Sort function and some variable
     */
    private static void sort(Comparable[] a) {
        // initial Integer low and high which is represent the index of the Array
        int low = 0;
        int high = a.length - 1;
        sort(a, low, high);
    }

    private static void sort(Comparable[] a, int low, int high) {

        //security check when we are using the recursion
        if(high <= low){
            return;
        }
        int partition = partition(a, low, high);
        // using the partition to divide the Array into two different groups. which also
        // has connect thinking with divide-conquer-sort
        // Reload the function sort()
        sort(a, low, partition);
        sort(a, partition + 1, high);

    }

    private static int partition(Comparable[] a, int low, int high) {
        
        //Initial the key point. Remember the key point could always change, so it can not be 0, after allocated it can be any index from (low index)
        int key = low;

        // setting two point, Traverse the Array, and initial left can not set as 0, always consider about the dynamic divide, so it have to been set as low. Same as right point.
        //int left = 0;
        int left = low;
        int right = high; // From the initial it already +1 so at this moment, we need reduce(-1);
        while(true){

                if(right <= left){
                    break;
            }

            while(less(a[key],a[right])){
                right--;
                if(right <= low){
                    break;
                }
            }

            while(less(a[left], a[key])){
                left++;
                if(left >= high){
                    break;
                }
            }

            exchange(a, left, right);


        }

        exchange(a, low, right);

        //Considering if we change point right to left
        //returen left;
        return right;
    }

}
