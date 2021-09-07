package sort;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        System.out.println("Insertion, Sort!!");

        Integer[] array = { 6, 1, 3, 2, 5, 4, 8, 7 };
        insertion(array);
        System.out.println(Arrays.toString(array));
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;

    }

    private static void insertion(Comparable[] a) {

        //Traverse,not Recursion

            for(int i=1; i< a.length; i++){

                for(int j = i; j>0; j--){
                    if(greater(a[j-1], a[j])){

                        exchange(a, j-1, j);
                    } else{
                        break;
                    }
                }
            }
        }

}

