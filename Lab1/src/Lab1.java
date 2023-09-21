import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * CET - CS Academic Level 3
 * This class contains the dynamically allocated array and it's processing
 * Student Name: Philogene Villanueva
 * Student Number:  041063813
 * Course: CST8130 - Data Structures
 * @author/Professor: James Mwangi PhD. 
 * 
  */
public class Lab1 {

	/** Description: displayMain method will display the menu to the user, check's user input is correct and within range
	 *  determines what case would it enter according the the user's choice, display output for some of the cases
	 * @return void
	 */
	public static void displayMainMenu() {
		boolean flag = true;
		Scanner input = new Scanner(System.in);
		Numbers num = null;
		int userInput;
		
		while (flag) {

			try {

				System.out.println("Please select one of the following: ");
				System.out.println("1: Initialize a default array");
				System.out.println("2: To specify the max size of the array");
				System.out.println("3: Add value to the array");
				System.out.println("4: Display values in the array");
				System.out.println("5: Display average of the values, minimum value, maximum value, max mod min, TotalFactorial");
				System.out.println("6: Enter Multiple values");
				System.out.println("7: Read values from file");
				System.out.println("8: Save values to file");
				System.out.println("9: To exit");
				System.out.print(">");

				userInput = input.nextInt();

				if (userInput >= 1 && userInput <= 9) { //checking if user's input is within bounds

					switch (userInput) {

					case 1:
						num = new Numbers(); //Initialize new instance of Numbers Class with default constructor
						break;
					case 2:
						int max = 0;
						System.out.print("Enter new size of the array: ");
						max = input.nextInt();
						num = new Numbers(max); //Initialize new instance of Numbers Class with parameterized constructor to set array size
						break;
					case 3:
						if (num == null) { //checking if Number class instance is null
							num = new Numbers(); //creates instance of Number class
						}
						num.addValue(input); //call addValue method to store user's input to the array in Number class
						break;
					case 4:
						if (num == null) { //checking if Number class instance is null;
							System.out.println("Numbers are: ");
							break;
						} else {
							num.displayArray(); //calls displayArray method to display the value of the array per index
						}
						break;

					case 5:
						if (num == null) { //checks if number class instance is null
							System.out.println(
									"Average is: 0.0, Minimum value is 0.0, Maximum value is 0.0 , max mod min is _______ factorial of Max is ____________");
						} else {
							float ave = 0;
							ave = num.calcAverage(); //calls calcAverage method of Numbers class to compute all the values inside the array for it's average, lowest value for minimum, largest value for maximum and modulo for min and max
						}
						break;
					case 6:
						
						if (num == null) { //checking if Number class instance is null
							num = new Numbers(); //creates instance of Number class
						}
						int inputCount = 0;
						System.out.print("Enter how many values you wish to add? ");
						inputCount = input.nextInt();
						input.nextLine();						
						
						if(num.checkSize(inputCount) == true) {
							for(int i = 1; i <= inputCount; i++) {
								num.addValue(input);		
							}
						}else {
							System.out.println("No room in array to add all values");
						}
					
						break;
					case 7: 
						
						if (num == null) { //checking if Number class instance is null
							num = new Numbers(); //creates instance of Number class
						}
						
						num.readFile();
						
						break;
					case 8:
						if (num == null) { //checking if Number class instance is null
							num = new Numbers(); //creates instance of Number class
						}
						num.saveFile();
						break;
					case 9:
						flag = false;
						System.out.println("Exiting..."); //exit's case and exit's loop
						input.close();
						break;
					}			
					

				} else {
					System.out.println("Please input within range!"); 
					input.nextLine();
				}

			} catch (InputMismatchException e) { //catch input Exceptions and directs back to the main menu
				System.out.println("Input a valid number from the menu");
				input.nextLine();
			}
	
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		displayMainMenu(); //calls displayMainMenu method for user Menu and corresponding response

	}

}
