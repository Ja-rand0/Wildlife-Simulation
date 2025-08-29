package life;

/**
 *  
 * @author Christian Salazar
 *
 */

/**
 * A fox eats rabbits and competes against a badger. 
 */
public class Fox extends Animal 
{
	/**
	 * Constructor 
	 * @param p: plain
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Fox (Plain p, int r, int c, int a) 
	{
		super(p, r, c, a);
	}
		
	/**
	 * A fox occupies the square. 	 
	 */
	public State who()
	{
		return State.FOX; 
	}
	
	/**
	 * A fox dies of old age or hunger, or from attack by numerically superior badgers. 
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for a Fox. 
		
		//Gather a census of the surrounding area
		int[] population = new int[NUM_LIFE_FORMS];
		census(population); 
		
		//a. Return new Empty when Fox dies of old age
		if (this.myAge() >= FOX_MAX_AGE) {
			return new Empty(pNew, this.row, this.column);
		
		//b. Return new Badger when Fox is out numbered by Badgers
		} else if (population[FOX] < population[BADGER]) {
			return new Badger(pNew, this.row, this.column, 0);
		
		//c. Return new Empty when Foxes AND Badgers out number rabbits
		} else if ((population[FOX] + population[BADGER]) > population[RABBIT]) {
			return new Empty(pNew, this.row, this.column);
		
		//d. Update Fox age when Fox survives
		} else {
			return new Fox(pNew, this.row, this.column, this.myAge() + 1);
		}
	}
}
