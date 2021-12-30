package linear;
public class SparseArray {
    //create init 2D array
    // 0 means not 1 black 2 blue

    public static void main(String[] args) {

	System.out.printf("Hello, World");


	int chessArr1[][] = new int[11][11];

	chessArr1[1][2] = 1;
	chessArr1[2][3] = 2;

	//print initial 2D arraylist
	System.out.println("print initial 2D arraylist");

	for (int [] row : chessArr1) {

	    for (int data : row) {

		System.out.printf("%d\t", data);
	    }
	    System.out.println(" ");

	    //lets have a try in advence okay?
	    
	}
	//transform from 2D to sparsearray
	// 1 step, count unzero element.

	int sum = 0;

	for (int i = 0; i < 11; i++) {

	    for (int j = 0; j < 11; j++) {

		if (chessArr1[i][j] != 0) {

		    sum++;
		}	
	    }
	}
	
	// 2nd step create sharseArray, put value into sharseArry.
	int sharseArray[][] = new int[sum+1][3]; // 3 is not index, it means how many lines Array have

	sharseArray[0][0] = 11;
	sharseArray[0][1] = 11;
	sharseArray[0][2] = sum;
	
	//scan the 2D Array put unzero element into sparseArray
	int count = 0; //use to caclulate the unzero number

	for (int i = 0; i < 11; i++) {

	    for (int j = 0; j < 11; j++) {

		if (chessArr1[i][j] != 0){

		    count++;
		    sharseArray[count][0] = i;
		    sharseArray[count][1] = j;
		    sharseArray[count][2] = chessArr1[i][j];
		    
		}
		
	    }
	    
	}

	//output

	System.out.println();
	System.out.println("the sharsearry that you get is");

	for (int i = 0; i < sharseArray.length; i++) {

	    System.out.printf("%d\t%d\t%d\t\n", sharseArray[i][0], sharseArray[i][1], sharseArray[i][2]);
	    
	}
	System.out.println();

	//recovery sharseArray to 2D

	int chessArr2[][] = new int[sharseArray[0][0]][sharseArray[0][1]]; //right one is put how many row and how many line in it, it is value.
	

	for (int i = 1; i < sharseArray.length; i++) {
	    
	    chessArr2[sharseArray[i][0]][sharseArray[i][1]] = sharseArray[i][2];
	    
	}

	System.out.println();
	System.out.println("recovery Array");

	for (int [] row : chessArr2) {
	    for (int data : row) {
		System.out.printf("%d\t", data);
	    }
	    System.out.println();
	}
    }
}
