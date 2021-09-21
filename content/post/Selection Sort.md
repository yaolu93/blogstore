# Selection Sort	
	
The Selection sort algorithm repeatedly finds the minimum/maximum element from the unsorted part of the array and putting it at the end of the sorted part of the array. 	It's not suitable for large data set,
as its time complexity are on O(n2).
	
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