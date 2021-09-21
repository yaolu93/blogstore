# Bubble Sort

Bubble Sort	is a simple sorting algorithm. It compares two adjacent elements and swap 
them until all elements are in order. It's not suitable for large data set as its time complexity
are of ¦¯(n2). 
	
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
