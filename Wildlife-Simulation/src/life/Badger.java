package life;

/**
 *  
 * @author Christian Salazar
 *
 */

/**
 * A badger eats a rabbit and competes against a fox. 
 */
public class Badger extends Animal
{
	/**
	 * Constructor 
	 * @param p: plain
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Badger (Plain p, int r, int c, int a) 
	{
		super(p, r, c, a);
	}
	
	/**
	 * A badger occupies the square. 	 
	 */
	public State who()
	{ 
		return State.BADGER; 
	}
	
	/**
	 * A badger dies of old age or hunger, or from isolation and attack by a group of foxes. 
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for a badger. 
		
		//Gather a census of the surrounding area
		int[] population = new int[NUM_LIFE_FORMS];
		census(population); 
		
		//a. Return new Empty when Badger dies of old age
		if (this.myAge() >= BADGER_MAX_AGE) {
			return new Empty(pNew, this.row, this.column);
		
		//b. Return new Fox when Badger out numbered by Fox
		} else if (population[BADGER] == 1 && population[FOX] > 1) {
			return new Fox(pNew, this.row, this.column, 0);
			
		//c. Return new Empty when Badger AND Foxes out number rabbit. AND when there are rabbits.
		} else if (population[RABBIT] < (population[BADGER] + population[FOX])) {
			return new Empty(pNew, this.row, this.column);
		
		//d. Update Badger age when it lives another year
		} else {
			return new Badger(pNew, this.row, this.column, this.myAge() + 1);
		}
		
	}
}
