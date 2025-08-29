package life;

/**
 *  
 * @author Christian Salazar
 *
 */

/** 
 * Empty squares are competed by various forms of life.
 */
public class Empty extends Living 
{
	public Empty (Plain p, int r, int c) 
	{
		super(p, r, c);
	}
	
	public State who()
	{
		return State.EMPTY; 
	}
	
	/**
	 * An empty square will be occupied by a neighboring Badger, Fox, Rabbit, or Grass, or remain empty. 
	 * @param pNew     plain of the next life cycle.
	 * @return Living  life form in the next cycle.   
	 */
	public Living next(Plain pNew)
	{
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for Empty. 
		
		//Gather a census of the surrounding area
		int[] population = new int[NUM_LIFE_FORMS];
		census(population); 

		
		//Return new Rabbit if more than one neighboring Rabbit
		if (population[RABBIT] > 1) {
			return new Rabbit(pNew, this.row, this.column, 0);
		
		//Return new Fox if more than one neighboring Fox
		} else if (population[FOX] > 1) {
			return new Fox(pNew, this.row, this.column, 0);
		
		//Return new Badger if more than one neighboring Badger
		} else if (population[BADGER] > 1) {
			return new Badger(pNew, this.row, this.column, 0);
		
		//Return new Grass if AT LEAST one neighboring Grass
		} else if (population[GRASS] >= 1) {
			return new Grass(pNew, this.row, this.column);
			
		//Return Empty if nothing applies
		} else {
			return new Empty(pNew, this.row, this.column);
		}
	}
}
