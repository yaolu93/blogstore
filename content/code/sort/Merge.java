package sort;

import java.security.Principal;
import java.util.Arrays;

public class Merge{
    public static void main(String[] args){
        System.out.println("Hello,Merge!");
        Integer[] a = {4,3,2,1};
        divide(a, 0, a.length);
    }

    
    private static Comparable[] assistant;

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w)<0;
    }

    private static void exchange(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void divide(Comparable[] a){
        int low = 0;
        int high = a.length - 1;
        divide(a,low, high);
    }

    //reload
    private static void divide(Comparable[] a, int low, int high){
        if(high <= low){
            return;
        }

        //low = 0;
        //high = a.length - 1;//这里每次的长度都有问题，不是low的问题。所以重在避免掉了变量初始化的问题。
        //int mid = (high - low)/2; 如果仅仅是 -0，那么不能起到递归，中间值左右两边的效果。
        //所以下面来更改mid的表达式，以便不同index的变化。
        int mid = low +(high - low)/2;  //5 + (9 - 5 )/2 = 5 + 4/2 =7

        //一下两步实现分步递归。
        divide(a, low, mid);

        divide(a, mid+1, high);

        conquer(a, low, mid, high);

    }

    //todo
    private static void conquer(Comparable[] a, int low, int mid, int high) {
    }



}


