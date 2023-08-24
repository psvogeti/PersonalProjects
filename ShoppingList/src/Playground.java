import java.util.Random;
public class Playground {
	
	
	private static int[] generateArrayOfInts(int arraySize, int upperBound) {
		Random random = new Random();
		int[] generatedIntArray = new int[arraySize];
		
		for (int i = 0; i < arraySize; i++) {
			generatedIntArray[i] = random.nextInt(upperBound);
		}
		
		return generatedIntArray;
	}
	
	private static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
	
	private static void mergeSort(int[] array) {
		int originalLength = array.length;
		
		//BASE CASE: only one element, array is already sorted
		if (originalLength == 1) {
			return;
		} 
		
		//1: Divide the array into smaller halves until base case is reached
		int middleIndex = originalLength / 2;
		
		int[] leftHalfArray = new int[middleIndex];
		int[] rightHalfArray = new int[originalLength - middleIndex];
		
		//2: fill in the halved arrays with their corresponding elements
		for (int i = 0; i < middleIndex; i++) {
			leftHalfArray[i] = array[i];
		} 
		
		
		for (int j = 0; j < rightHalfArray.length; j++) {	
			rightHalfArray[j] = array[middleIndex];
			middleIndex++;
		}
		
		System.out.println("--LEFT PRE MERGE--");
		printArray(leftHalfArray);
		System.out.println("---RIGHT PRE MERGE---");
		printArray(rightHalfArray);
		System.out.println("----------");
		
		//3: Recursive call to divide the array down into sorted unit elements
		 mergeSort(leftHalfArray);
		 mergeSort(rightHalfArray);
		//4: Upon each return, merge the returned halves into our array 
		 mergeArrays(leftHalfArray, rightHalfArray, array);	
	}
	
	private static int[] mergeArrays(int[] leftHalf, int[] rightHalf, int[] arrayCopy) {		
		int leftIndex = 0;
		int rightIndex = 0;
		
		System.out.println("------HALF ARRAY PRINT BEGINS---------");
		printArray(leftHalf);
		System.out.println("--------LEFT ABOVE, RIGHT BELOW (PRE-MERGE)-----------");
		printArray(rightHalf);
		System.out.println("------HALF ARRAY PRINT ENDS---------");
		
		for (int arrayIndex = 0; arrayIndex < arrayCopy.length; arrayIndex++) {
			//CASE: we reached the end of the left half so just add the remaining right element	
			if (leftIndex == leftHalf.length) {
				arrayCopy[arrayIndex] = rightHalf[rightIndex];
				rightIndex++;
		
			//CASE: we reached the end of the right half so just add the remaining left element		
			} else if (rightIndex == rightHalf.length) {
				arrayCopy[arrayIndex] = leftHalf[leftIndex];
				leftIndex++;
				
			//CASE: left element is smaller than corresponding right element	
			} else if (leftHalf[leftIndex] < rightHalf[rightIndex]) {
				arrayCopy[arrayIndex] = leftHalf[leftIndex];
				leftIndex++;
					
			//CASE: left element is larger than corresponding right element	
			} else if (leftHalf[leftIndex] > rightHalf[rightIndex]) {
				arrayCopy[arrayIndex] = rightHalf[rightIndex];
				rightIndex++;
				
			//CASE: elements are equal to one another	
			} else {
				arrayCopy[arrayIndex] = leftHalf[leftIndex];
				leftIndex++;
			}
		}
		
		return arrayCopy;
	}
	
	
	public static void main(String[] args) {
		int[] unsortedArray = generateArrayOfInts(10, 1500);
		System.out.println("---BEFORE---");
		printArray(unsortedArray);
		System.out.println("--CALL TO SORT--");
		mergeSort(unsortedArray);
		System.out.println("----AFTER----");
		printArray(unsortedArray);
		
		

	}

}
