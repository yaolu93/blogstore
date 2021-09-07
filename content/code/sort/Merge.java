package sort;

import java.security.Principal;
import java.util.Arrays;

public class Merge {
    public static void main(String[] args) {
        System.out.println("Hello,Merge!");
        Integer[] a = { 1, 3, 2, 1 };
        divide(a);
        System.out.println(Arrays.toString(a));
    }

    private static Comparable[] assistant;

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void divide(Comparable[] a) {
        assistant = new Comparable[a.length];
        int low = 0;
        int high = a.length - 1;
        divide(a, low, high);
    }

    // reload
    private static void divide(Comparable[] a, int low, int high) {
        if (high <= low) {
            return;
        }

        // low = 0;
        // high = a.length - 1;//这里每次的长度都有问题，不是low的问题。所以重在避免掉了变量初始化的问题。
        // int mid = (high - low)/2; 如果仅仅是 -0，那么不能起到递归，中间值左右两边的效果。
        // 所以下面来更改mid的表达式，以便不同index的变化。
        int mid = low + (high - low) / 2; // 5 + (9 - 5 )/2 = 5 + 4/2 =7

        // 一下两步实现分步递归。
        divide(a, low, mid);
        System.out.println("low=>" + low + "+mid=>" + mid + "+high=>" + high);

        divide(a, mid + 1, high);
        conquer(a, low, mid, high);

    }

    // todo
    private static void conquer(Comparable[] a, int low, int mid, int high) {
        // 从这里开始就需要引入辅助数组了。i为其中的辅助指针
        int i = low;
        int p1 = low;
        int p2 = mid + 1;

        while (p1 <= mid && p2 <= high) {

            if (less(a[p1], a[p2])) {
                assistant[i++] = a[p1++];
                // i++ 是在该行语句执行过后+1，所以，现在可以用 assistant[i++] 来代替。
                // i++;
                // p1++;
            } else {
                assistant[i++] = a[p2++];
            }

        }
        // while 循环， 什么时候跳出？
        while (p1 <= mid) {
            // assistant 这个对象在使用的时候有报出空指针，所以需要初始化对象，而不是使用null

            assistant[i++] = a[p1++];
            // p2++;

            // if(less(a[p1], a[p2])){
            // p2++;
            // }else{
            // exchange(a, p1, p2);
            // p2++;
            // }

        }

        while (p2 <= high) {
            assistant[i++] = a[p2++];
            // p1++;

            // if(less(a[p1], a[p2])){
            // p1++;
            // } else{
            // exchange(a, p1, p2);
            // p1++;
            // }
        }

        for (int index = low; index <= high; index++) {
            a[index] = assistant[index];
        }
    }

}
