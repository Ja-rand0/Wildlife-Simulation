package life;

/**
 *  
 * @author Christian Salazar
 *
 */

/**
 * Grass remains if more than rabbits in the neighborhood; otherwise, it is eaten. 
 *
 */
public class Grass extends Living 
{
	public Grass (Plain p, int r, int c) 
	{
		super(p, r, c);
	}
	
	public State who()
	{
		return State.GRASS; 
	}
	
	/**
	 * Grass can be eaten out by too many rabbits. Rabbits may also multiply fast enough to take over Grass.
	 */
	public Living next(Plain pNew)
	{
		// See Living.java for an outline of the function.
		// See the project description for the survival rules for grass.
		
		// Gather a census of the surrounding area
		int[] population = new int[NUM_LIFE_FORMS];
		census(population);


		// a. Return new Empty when Rabbit is at least 3x more than grass.
		if (population[RABBIT] >= (population[GRASS] * 3)) {
			return new Empty(pNew, this.row, this.column);
		
		// b. Otherwise return new Rabbit if at least more than three Rabbits
		} else if (population[RABBIT] >= 3) {
			return new Rabbit(pNew, this.row, this.column, 0);
		
		//c. Return Grass if nothing applies.
		} else {
			return new Grass(pNew, this.row, this.column);
		}
	}
}
