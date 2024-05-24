package org.achilles;

public class testPrefix {
    public static void main(String[] args) {
        // 后缀递增运算符 num++
        System.out.println("Postfix increment:");
        for (int num = 1; num < 5; num++) {
            int result = num + 1;
            System.out.println("Current value: " + num + ", Next value: " + result);
        }

        System.out.println();

        // 前缀递增运算符 ++num
        System.out.println("Prefix increment:");
        for (int num = 1; num < 5; ++num) {
            int result = num + 1;
            System.out.println("Current value: " + num + ", Next value: " + result);

        }
    }
}
