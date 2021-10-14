package code.graph;


public class demo02 {
    public static void main(String[] args) {


       
        int number = 29;
        getPrimeNumber(number);

    }

    private static void getPrimeNumber(int number) {


      
        boolean flag = false;
        for (int i = 2; i <= number / 2; ++i) {
          // condition for nonprime number
          if (number % i == 0) {
            flag = true;
            System.out.println(number + " is not a prime number."); 
       
            break;
          }
        }
        System.out.println(number + " is a prime number.");
      
         
      

       





    }
}
