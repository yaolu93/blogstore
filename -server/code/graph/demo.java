package code.graph;

public class demo{
    public static void main(String[] args) {
        System.out.println("Hello , World!");
        int a = (int) (1.8/2) ;
        System.out.println(a);

        int b = 5;
        int c = 5;

        b %= c;

        System.out.println("the result is :" + b);
        

        int option = 2;
        switch (option) {
        case 1:
            System.out.println("Selected 1");
            break;
        case 2:
            System.out.println("Selected 2");
            break;
        case 3:
            System.out.println("Selected 3");
            break;
        }
    }
}
