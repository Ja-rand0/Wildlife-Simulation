package life;

/**
 *  
 * @author Christian Salazar
 *
 */

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner; 
import java.util.Random; 

/**
 * 
 * The plain is represented as a square grid of size width x width. 
 *
 */
public class Plain 
{
	private int width; // grid size: width X width 
	
	public Living[][] grid;   

	public Plain(String inputFileName) throws FileNotFoundException {
		File file = new File(inputFileName);

		try (Scanner scnr = new Scanner(file)) {

			//1. Read the first line to find the width of our grid
			if (scnr.hasNextLine()) {
				String firstLine = scnr.nextLine().trim();
				String[] firstRow = firstLine.split("\\s+"); //Ignore whitespace
				this.width = firstRow.length; //Square grid
				

				//2. Create the grid object
				grid = new Living[width][width];

				//3. Read input file and fill up our grid with living objects.
				for (int i = 0; i < width; i++) {
					String line;
					if (i == 0) {
						line = firstLine;
					} else {
						line = scnr.nextLine().trim();
					}
					
					//For if there are unnecessary whitespaces in a .txt file. Safety guard.
					String[] cells = line.split("\\s+");
					
					//Guarding if width doesn't match length.
					if (cells.length != width) {
		                throw new IllegalArgumentException("ERROR: Row " + i + " does not match expected width.");
					}

					for (int j = 0; j < width; j++) {
						String cell = cells[j];
						
						if (cell.isEmpty()) {
						    throw new IllegalArgumentException("ERROR: Empty cell at position (" + i + ", " + j + ")");
						}
						
						char stateChar = cell.charAt(0);
						int age = 0;
						
						//Grabs the Age of the living object "if" it applies
						if (cell.length() > 1) {
		                    char ageChar = cell.charAt(1);
		                    if (Character.isDigit(ageChar)) {
		                        age = Character.getNumericValue(ageChar);
		                    } else {
		                        throw new IllegalArgumentException("ERROR: Invalid age format in " + cell);
		                    }
		                }
						//Translate the files contents to living objects.
						if (stateChar == 'B') {
							grid[i][j] = new Badger(this, i, j, age);
						} else if (stateChar == 'F') {
							grid[i][j] = new Fox(this, i, j, age);
						} else if (stateChar == 'R') {
							grid[i][j] = new Rabbit(this, i, j, age);
						} else if (stateChar == 'G') {
							grid[i][j] = new Grass(this, i, j);
						} else if (stateChar == 'E') {
							grid[i][j] = new Empty(this, i, j);
						} else {
							throw new IllegalArgumentException("ERROR: Wrong animal/type: " + cell);
						}
					}
				}
				 if (scnr.hasNextLine()) {
			            throw new IllegalArgumentException("ERROR: More rows than expected in the input file.");
			        }
			}
		}
	}

	
	/**
	 * Constructor that builds a w x w grid without initializing it. 
	 * @param width  the grid 
	 */
	public Plain(int w)
	{
		grid = new Living[w][w];
	}
	
	
	public int getWidth()
	{
		return grid.length;  
	}
	
	/**
	 * Initialize the plain by randomly assigning to every square of the grid  
	 * one of BADGER, FOX, RABBIT, GRASS, or EMPTY.  
	 * 
	 * Every animal starts at age 0.
	 */
	public void randomInit()
	{
		Random generator = new Random(); 
		 //Busting out the nested loop 
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				int rand = generator.nextInt(5); //5 living types
				
				//Using the indices from Living class 
				if (rand == 0) {
					grid[i][j] = new Badger(this, i, j, 0);
				} else if (rand == 1) {
					grid[i][j] = new Empty(this, i, j);
				} else if (rand == 2) {
					grid[i][j] = new Fox(this, i, j, 0);
				} else if (rand == 3) {
					grid[i][j] = new Grass(this, i, j);
				} else if (rand == 4) {
					grid[i][j] = new Rabbit(this, i, j, 0);
				}
			}
		}
	}
	
	
	/**
	 * Output the plain grid. For each square, output the first letter of the living form
	 * occupying the square. If the living form is an animal, then output the age of the animal 
	 * followed by a blank space; otherwise, output two blanks.  
	 */
	public String toString()
	{
		String result = "";
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				Living cell = grid[i][j]; //Grab the Living Object, EX: "B0"
				State cellState = cell.who(); //"converting" to State. "B0" is now known as a Badger, Age 0.
				
				//Match them up. Sorting by indices order (though not necessary)
				if (cellState == State.BADGER) {
					result += "B" + ((Animal) cell).myAge() + " "; //Busting out the typecast. 
				} else if (cellState == State.EMPTY) {
					result += "E  " ;
				} else if (cellState == State.FOX) {
					result += "F" + ((Animal) cell).myAge() + " ";
				} else if (cellState == State.GRASS) {
					result += "G  ";
				} else if (cellState == State.RABBIT) {
					result += "R" + ((Animal) cell).myAge() + " "; 
				}
			}
			result += "\n"; //Busting out the newline for each row.
		}
		return result; 
	}
	

	/**
	 * Write the plain grid to an output file.  Also useful for saving a randomly 
	 * generated plain for debugging purpose. 
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException {
		
		PrintWriter write = new PrintWriter(outputFileName); //Opens the file in writing mode
			write.print(this.toString()); //Busting out the custom function to print output
			write.close();
	}			
}
