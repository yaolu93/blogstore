---
title: "Bubble, Insertion and Selection Sort"
date: 2021-09-20
lastmod: 2021-09-20
draft: false
tags: ["Java", "Leetcode", "Sort"]
categories: ["Leetcode"]
author: "Achilles & Edward"
---


# Bubble Sort

Bubble Sort	is a simple sorting algorithm. It compares two adjacent elements and swap 
them until all elements are in order. It's not suitable for large data set as its time complexity
are of (n2). 

``` Java
	public class BubbleSort {
	    
	    public static void sort(Comparable[] a){
	
	        for(int i = a.length-1; i>0; i--){
	
	            for(int j = 0; j<i; j++){
	                if(greater(a[j], a[j+1])){
	                    exchange(a, j, j+1);
	                }
	            }
	         }
	    }
	
	    public static boolean greater(Comparable v, Comparable w){
	        return v.compareTo(w)>0;
	    }    
	
	    public static void exchange(Comparable[] a, int i, int j){
	        Comparable t = a[i];
	        a[i] = a[j];
	        a[j] = t;
	    }
	
	    public static void main(String[] args) {
	        
	        Integer [] a = {4,6,3,2,5,1};
	        BubbleSort.sort(a);
	        System.out.println(Arrays.toString(a));
	    }   
	    
	}
```

------------



# Insertion Sort

Insertion Sort is a simple sorting algorithm. It is not suitable for large data sets, 
as its complexity are on  O(n2).



 ``` Java



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
```
------

# Selection Sort	
	
The Selection sort algorithm repeatedly finds the minimum/maximum element from the unsorted part of the array and putting it at the end of the sorted part of the array. 	It's not suitable for large data set, as its time complexity are on O(n2).


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