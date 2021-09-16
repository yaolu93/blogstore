package linear;
import java.util.Scanner;


public class CircleArray {


    public static void main(final String[] args) {
	System.out.printf(" test");

	CircleArrayQueue queue = new CircleArrayQueue(4);

	char key = ' ';

	Scanner scanner = new Scanner(System.in);

	boolean loop = true;

	while(loop){

	    System.out.println("s(show): show the queue");
	    System.out.println("e(exit): exit the program");
	    System.out.println("a(add): add the data to the queue");
	    
	    System.out.println("g(get): get the data from the queue");
	    System.out.println("h(head): check the data at the head of the queue");



	    key = scanner.next().charAt(0); //get one char

	    switch(key){
		
	    case's':
		queue.showQueue();
		break;
	    case'a':
		System.out.printf("input one char:");
		int value = scanner.nextInt();
	    
		queue.addQueue(value);
		break;

				
	    case'g':

		try {
		    int res = queue.getQueue();
		    System.out.printf("the data that you pick out is %d\n",res);
		}
		catch (Exception e) {
		    System.out.println("Error " + e.getMessage());
		    e.printStackTrace();
		}
		break;

	    case'h':

		try {
		    int res = queue.headQueue();
		    System.out.printf("the data in the queue's head is %d \n",res);
		}
		catch (Exception e) {
		    System.out.println("Error " + e.getMessage());
		    e.printStackTrace();
		}
		break;

		
	    case'e':
		scanner.close();

		loop = false;

		break;
		
	    default:

		break;
	    }


	}

	System.out.printf("program exit");

	    
    }
}
    



class CircleArrayQueue {


    private int front;//head of the Array
    private int rear;//tail of the Array
    private int maxSize;//capacity of the Array
    private int[] arr;//Array put some data into this one

    // build a constructer

    public CircleArrayQueue(int arrMaxSize) {
	maxSize = arrMaxSize;
	arr = new int[maxSize];
    }

    // if the queue is full
    public boolean isFull() {
	return (rear + 1) % maxSize == front;
    }

    // if the queue is empty
    public boolean isEmpty() {
	return front == rear;
    }

    //  add data to the queue
    public void addQueue(int n) {
	//if the queue is not full

	if (isFull()) {
	    System.out.println("the queue is full you can not add the data");
	    return;
	}
	// put the n into the Array, then rear ++, should module the maxSize, because it is a CircleQueue
	arr[rear] = n;
	rear = (rear + 1) % maxSize;
	
    }

    //get data from CircleArray

    public int getQueue() {

	if (isEmpty()) {
	    throw new RuntimeException("Array is Empty can not get the data");
	}

	int value = arr[front];
	front = (front + 1) % maxSize;
	return value;
	 
    }

    // show all of the data in the Array

    public void showQueue() {

	// traversing all the elements

	if (isEmpty()) {
	    System.out.printf("queue is empty no any elements");
	    return;
	}

	for (int i = front ; i < front + size() ; i++) {
	    System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
	}

	
    }

    public int size() {
	return (rear + maxSize - front) % maxSize;
	 
    }

    //show the head data of the Array, not pick out the data

    public int  headQueue() {

	if (isEmpty()) {
	    throw new RuntimeException("Queue is Empty there is no data");
	}
	return arr[front];
    }
}

