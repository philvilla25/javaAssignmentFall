import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

/**
 * This class contains the dynamically allocated array and it's processing
 * Student Name: Philogene Villanueva
 * Student Number: 
 * Course: CST8130 - Data Structures
 * CET-CS-Level 3
 * @author/Professor James Mwangi PhD. 
 * 
  */
public class Numbers {
	/**
	 * Stores Float values.
	 */
	private Float[] numbers;

	private int flag = 0;
	/**
	 * Store the number of items currently in the array.
	 */
	private int numItems;

	private int size;

	/**
	 * Default Constructor
	 */
	public Numbers() {

		this.size = 10;
		this.numbers = new Float[size];
		this.numItems = 0;
	}

	/**
	 * Constructor that initializes the numbers array.
	 * 
	 * @param size - Max size of the numbers array
	 */
	public Numbers(int size) {

		this.size = size;
		numbers = new Float[size];
		this.numItems = 0;
	}

	/**
	 * Adds a value in the array
	 * 
	 * @param keyboard - Scanner object to use for input
	 */
	public void addValue(Scanner keyboard) {
		boolean flag = true;
		
		if (numItems > size - 1) { // check if number of items is more than array size
			System.out.println("Array Full"); // print's array full and exit method
			return;
		}
		
		
		try {
		
		System.out.print("Enter Value: "); //ask for user input
		float val = keyboard.nextFloat();
		
		numbers[numItems] = val;
		numItems++;
		flag = false;
		
		}catch(InputMismatchException e) {
			System.out.println("Please input an integer or a float value");
			keyboard.nextLine();
		}
	}
	
	/**
	 * Add Value to the current array from a file
	 * @param line
	 */
	public void addValue(Float line) {
	
		if (numItems > size - 1) { // check if number of items is more than array size
			System.out.println("Array Full"); // print's array full and exit method
			return;
		}
		
		numbers[numItems] = line;
		numItems++;
	}
	
	public boolean checkSize(int countNum) {
		boolean flag = false;
		int counter = numItems+countNum;
		
		if(counter > size) {
			return flag = false;
		}
	 
			return flag = true;	
		
	}
	/**
	 * Calculates the average of all the values in the numbers array.
	 * 
	 * @return float value that represents the average
	 */
	public float calcAverage() {
		BigInteger factor = BigInteger.ONE;
		float totalAverage = 0;
		float min = numbers[0]; //sets the minimum to the first value of the array
		float max = numbers[0]; //sets the maximum to the first value of  the array

		for (int i = 0; i <= numItems - 1; i++) {
			System.out.println(numbers[i]); 

			min = calcMin(numbers[i]);
			max = calcMax(numbers[i]);
			totalAverage += numbers[i];
		}
		
		factor = totalFactorial(max);

		
		totalAverage = totalAverage / numItems; //computes average of all the values inside the array
		float mod = max % min; //computes modulo between minimum and maximum value
		
		System.out.printf("Average: %.2f ,", totalAverage);
		System.out.printf("Minimum value is: %.2f ,", min);
		System.out.printf("Maximum value is: %.2f ,", max);
		System.out.printf("Max mod Min is: ,", mod);
		System.out.println("TotalFactorial " + factor);

		return totalAverage;
	}

	/**
	 * calculates minimum number inside the array
	 * @param num
	 * @return the least value inside the array in a float type
	 */
	public float calcMin(float num) {
		float resultMin = numbers[0];
		
		if (num < resultMin) { 
			resultMin = num; //assigns the minimum value inside the array
		}
		
		return resultMin;
	}
	
	
	/**
	 * 
	 * @param num
	 * @return the largest value inside the array in a float type
	 */
	public float calcMax(float num) {
		float resultMax = numbers[0];
		
		if(num > resultMax) { //checks if the current value is larger than the current max
			resultMax = num;
		}
		
		return resultMax;
	}
	
	/**
	 * 
	 * @param num
	 * @return a BigInteger output to compute totalFactorial of the max value
	 */
	public BigInteger totalFactorial(float num) {
		BigInteger factor = BigInteger.ONE;
		
		for(int i = 1; i <= num; i++) {
			factor = factor.multiply(BigInteger.valueOf(i)) ;	//computes totalfactorial for the max value, we are using BigInteger to accommodate the size of the output
		}
		
		
		
		return factor;
	}
	
	@Override
	public String toString() {
		// TODO Write code for an appropriate toString method
		return "";
	}
	
	/**
	 * Description: reads value from a file then store or add the value inside the array
	 * @return void
	 */
	public void readFile(){
		String textFile;
		Scanner input = new Scanner(System.in);
		System.out.println("Name of the file to read from : ");
		textFile = input.nextLine();
	
		String filePath = "C:/AlgonquinSubjects/CST8130_DataStructures/lab2/"+textFile; //concatenates filePath with the inputted text file name from the user
		
		
		try {
		
		BufferedReader br = new BufferedReader(new FileReader(filePath)); //Initialize buffered reader class
		String line; 
		Float textVal;

		while((line = br.readLine()) != null) { //loops to read each value inside the file
			textVal = Float.parseFloat(line);//converts value inside the file to a float type
			addValue(textVal);//call addValue to add the value to the array
		}
		br.close();
		}catch(IOException  e) {
			System.out.println("No file found");
		}
		
	}
	
	/**
	 * Description: ask the user the file name to save values and saves the values inside the array to a file
	 */
	public void saveFile() {
		String textFile;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Name of the file to save to: ");
		textFile = input.nextLine();
		String filePath = "C:/AlgonquinSubjects/CST8130_DataStructures/lab2/"+textFile;//concatenates user's desired filename to the base file location
		
		try {
		PrintWriter writeOut = new PrintWriter(filePath);//initialize PrintWriter class with the filePath parameter
		
		for (int i = 0; i <= numItems - 1; i++) { //traverses the array and print's the values per index			
			writeOut.print(numbers[i]);//writes the current value of the array to the file
			writeOut.println();//next line
		}
		
		writeOut.close();
		
		}catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	
	/**
	 * Displays all the values inside the array
	 */
	public void displayArray() {
		System.out.println("Numbers are: ");
		for (int i = 0; i <= numItems - 1; i++) { //traverses the array and print's the values per index
			System.out.println(numbers[i]);
		}
	}
}

