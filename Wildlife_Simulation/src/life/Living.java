package life;

/**
 *  
 * @author Christian Salazar
 *
 */

/**
 * 
 * Living refers to the life form occupying a square in a plain grid. It is a 
 * superclass of Empty, Grass, and Animal, the latter of which is in turn a superclass
 * of Badger, Fox, and Rabbit. Living has two abstract methods awaiting implementation. 
 *
 */
public abstract class Living 
{
	protected Plain plain; // the plain in which the life form resides
	protected int row;     // location of the square on which 
	protected int column;  // the life form resides
	
	// constants to be used as indices. 
	protected static final int BADGER = 0; 
	protected static final int EMPTY = 1; 
	protected static final int FOX = 2; 
	protected static final int GRASS = 3; 
	protected static final int RABBIT = 4; 
	
	public static final int NUM_LIFE_FORMS = 5; 
	
	// life expectancies 
	public static final int BADGER_MAX_AGE = 4; 
	public static final int FOX_MAX_AGE = 6; 
	public static final int RABBIT_MAX_AGE = 3; 
	
	public Living(Plain p, int r, int c) {
		this.plain = p;
		this.row = r;
		this.column = c;
	}
	
	/**
	 * Censuses all life forms in the 3 X 3 neighborhood in a plain. 
	 * @param population  counts of all life forms
	 */
	protected void census(int population[]) {
		// Count the numbers of Badgers, Empties, Foxes, Grasses, and Rabbits
		// in the 3x3 neighborhood centered at this Living object. Store the
		// counts in the array population[] at indices 0, 1, 2, 3, 4, respectively.

		// Reset population counts
	    for (int i = 0; i < NUM_LIFE_FORMS; i++) {
	        population[i] = 0;
	    }

	    // Define our search bounds
	    int startRow = Math.max(0, this.row - 1);
	    int endRow = Math.min(this.plain.grid.length - 1, this.row + 1);
	    int startCol = Math.max(0, this.column - 1);
	    int endCol = Math.min(this.plain.grid[0].length - 1, this.column + 1);

	    // Iterate through the valid neighborhood
	    for (int i = startRow; i <= endRow; i++) {
	        for (int j = startCol; j <= endCol; j++) {
	            Living cell = this.plain.grid[i][j]; // Get the living object
	            if (cell != null) { // Ensure it's a valid object
	                population[cell.who().ordinal()]++; // .ordinal() assigns the indices from the enum State class
	                									//cleaner than using if-else statements 
	            }
	        }
	    }
	}

	/**
	 * Gets the identity of the life form on the square.
	 * @return State
	 */
	public abstract State who();
	// To be implemented in each class of Badger, Empty, Fox, Grass, and Rabbit. 
	// 
	// There are five states given in State.java. Include the prefix State in   
	// the return value, e.g., return State.Fox instead of Fox.  
	
	/**
	 * Determines the life form on the square in the next cycle.
	 * @param  pNew  plain of the next cycle
	 * @return Living 
	 */
	public abstract Living next(Plain pNew); 
	// To be implemented in the classes Badger, Empty, Fox, Grass, and Rabbit. 
	// 
	// For each class (life form), carry out the following: 
	// 
	// 1. Obtains counts of life forms in the 3x3 neighborhood of the class object. //navigate through array in cell grid around the object

	// 2. Applies the survival rules for the life form to determine the life form  
	//    (on the same square) in the next cycle.  These rules are given in the  
	//    project description. 
	// 
	// 3. Generate this new life form at the same location in the plain pNew.    
	

}
