package life;

/**
 *  
 * @author Christian Salazar
 *
 */

/*
 * A rabbit eats grass and lives no more than three years.
 */
public class Rabbit extends Animal 
{	
	/**
	 * Creates a Rabbit object.
	 * @param p: plain  
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Rabbit (Plain p, int r, int c, int a) 
	{
		super(p, r, c, a);
	}
		
	// Rabbit occupies the square.
	public State who()
	{
		return State.RABBIT; 
	}
	
	/**
	 * A rabbit dies of old age or hunger. It may also be eaten by a badger or a fox.  
	 * @param pNew     plain of the next cycle 
	 * @return Living  new life form occupying the same square
	 */
	public Living next(Plain pNew)
	{
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for a Rabbit. 
		
		//Gather a census of the surrounding area
		int[] population = new int[NUM_LIFE_FORMS];
		census(population);
		
		//a. Return new Empty when Rabbit dies of old age
		if (this.myAge() >= RABBIT_MAX_AGE) {
			return new Empty(pNew, this.row, this.column);
		
		//b. Return new Empty if no grass in the neighboring area.
		} else if (population[GRASS] <= 0) {
			return new Empty(pNew, this.row, this.column);
		
		//c. Return new Fox if Both Badgers AND Foxes out number Rabbits, AND Foxes out number Badgers
		} else if ((population[FOX] + population[BADGER] >= population[RABBIT]) && (population[FOX] > population[BADGER])) {
			return new Fox(pNew, this.row, this.column, 0);
		
		//d. Return new Badger when Rabbits are out numbered by Badger 
		} else if (population[BADGER] > population[RABBIT]) {
			return new Badger(pNew, this.row, this.column, 0);
		
		//e. Update Rabbit age when rabbit survives
		} else {
			return new Rabbit(pNew, this.row, this.column, this.myAge() + 1);
		}
	}
}
