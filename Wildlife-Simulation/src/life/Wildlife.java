package life;

import java.io.FileNotFoundException;
import java.io.File; //I added this to fix an issue with the number 2 key case
import java.util.Scanner; 

/**
 *  
 * @author Christian Salazar
 *
 */

/**
 * 
 * The Wildlife class performs a simulation of a grid plain with
 * squares inhabited by badgers, foxes, rabbits, grass, or none. 
 *
 */
public class Wildlife 
{
	/**
	 * Update the new plain from the old plain in one cycle. 
	 * @param pOld  old plain
	 * @param pNew  new plain 
	 */
	public static void updatePlain(Plain pOld, Plain pNew)
	{
		
		//We iterate through our grid
		for (int i = 0; i < pOld.grid.length; i++) {
			for (int j = 0; j < pOld.grid[i].length; j++) {
				pNew.grid[i][j] = pOld.grid[i][j].next(pNew); //Calling next() checks living conditions for each Living cell in a grid.
				pNew.grid[i][j].plain = pNew;
			}
		}
	}
	
	/**
	 * Repeatedly generates plains either randomly or from reading files. 
	 * Over each plain, carries out an input number of cycles of evolution. 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{	
		int trialCount = 1; // Count the trials starting from 1
		Scanner scnr = new Scanner(System.in);
		System.out.println("Welcome to the simulation!");
		System.out.println("Keys: (1 - Random plain) (2 - File input (.txt) ) (3 - End simulation) ");
		
		while (true) {
			System.out.print("Trial " + trialCount + ": ");
			
			int userInput = scnr.nextInt();
			scnr.nextLine();
			//Break the simulation if input is 3
			if (userInput == 3) {
				System.out.println("Simulation Ended on Trial: " + trialCount);
				break;
			}
			
			Plain even;   // the plain after an even number of cycles 
			Plain odd;    // the plain after an odd number of cycles
			int cycles = 0;   // For how many cycles we want to run
			
			if (userInput == 1) { 
                System.out.println("Random plain");
                System.out.print("Enter grid width: ");
                int width = scnr.nextInt();
                scnr.nextLine(); //Clear the buffer 

                even = new Plain(width);  // Constructor for an empty plain
                even.randomInit();       // Fill with random life forms

                System.out.print("Enter the number of cycles: ");
                cycles = scnr.nextInt();
                scnr.nextLine(); //clear the buffer
                
			} else if (userInput == 2) {
				
				System.out.println("Plain input from a file");
				String fileName;
				File file;
				even = null;

				while (true) {
					System.out.print("File name: ");
					fileName = scnr.nextLine();
					file = new File(fileName);

					if (file.exists()) { //check if file exists to avoid any errors
						even = new Plain(fileName);
						break; 
					} else {
						System.out.println("ERROR: File not found. Please enter a valid filename.");
					}
				}
            	
            	System.out.print("Enter the number of cycles: ");
            	cycles = scnr.nextInt();
            	scnr.nextLine();
            	
            } else {
            	System.out.println("ERROR: Wrong input, I only accept 1, 2, or 3");
            	cycles = 1;
            	scnr.nextLine();
            	continue;
            }
			
			odd = new Plain(even.getWidth());
			
			System.out.println("Initial plain: ");
			System.out.println(even);
			
			//Now we run the cycles
			for (int i = 0; i < cycles; i++) {

				if (i % 2 == 0) {
					updatePlain(even, odd); //Odd
				} else {
					updatePlain(odd, even); //Even
				}
			}
			
			//Print the final Plain
			System.out.println("Final plain: ");
			if (cycles % 2 == 0) {
				System.out.println(even);
			} else {
				System.out.println(odd);
			}
			
			trialCount++; //Remembered to update to keep track of how many trials we've ran.
		}
		scnr.close();
	}
}
