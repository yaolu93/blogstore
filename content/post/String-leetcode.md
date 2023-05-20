---
title: "String--leetcode"
date: 2022-05-29
lastmod: 2022-05-29
draft: false
tags: ["Java", "Leetcode", "String"]
categories: ["Leetcode"]
author: "Yao Lu"
---


# Leetcode Questions about String

[14.longest-common-prefix.java](https://leetcode.com/problems/longest-common-prefix/description/)

``` Java
class Solution {
    public String longestCommonPrefix(String[] strs) {
    // flower,flow,fly
        if (strs.length == 0) {
            return null;
        }

        String prefix = strs[0];
        int lengths = strs.length;

        for (int i = 1; i < lengths; i++) {
            
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length()-1);
            }
        }
        return prefix;
    }
}
```

------------



[125.valid-palindrome.java](https://leetcode.com/problems/valid-palindrome/description/)


 ``` Java

 class Solution {
    public boolean isPalindrome(String s) {

        String fixed_string = "";
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c) || Character.isLetter(c)) {
                fixed_string += c;
            }

            fixed_string = fixed_string.toLowerCase();
        }
        
        int a_pointer = 0;
        int b_pointer = fixed_string.length() - 1;

        while (a_pointer <= b_pointer) {
            if (fixed_string.charAt(a_pointer) != fixed_string.charAt(b_pointer)) {
                return false;
            }

            a_pointer += 1;
            b_pointer -= 1;
        }
        return true;

    }
}
```
------

[242.valid-anagram.java](https://leetcode.com/problems/valid-anagram/description/)

``` Java

public class SelectionSort {
	
    public static void main(String[] args) {
        System.out.println("Selection,Sort!");
        Integer[] a = { 8, 2, 3, 1, 4, 6, 7, 5 };
        selection(a);

        System.out.println(Arrays.toString(a));
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

    private static void selection(Comparable[] a) {

        for (int i = 0; i < a.length-1; i++) {
            int minIndex = i;

            for (int j = i+1; j < a.length; j++) {
                if (greater(a[minIndex], a[j])) {
                   // exchange(a, minIndex, j); we can not exchange here,see i as assistant point every time need to move forward.
                    minIndex = j;
                }
            }
            exchange(a, i, minIndex);
        }
    }
	
	}

````````

-----------


[344.reverse-string.java](https://leetcode.com/problems/reverse-string/description/)


 ``` Java
 class Solution {
    public void reverseString(char[] s) {
        int a_pointer = 0;
        int b_pointer = s.length - 1;

        while(a_pointer <= b_pointer){
            char temp = s[a_pointer];
            s[a_pointer] = s[b_pointer];
            s[b_pointer] = temp;
            a_pointer += 1;
            b_pointer -= 1;
        }
    }
}
```
------