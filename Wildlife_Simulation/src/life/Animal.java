package life;

/**
 *  
 * @author
 *
 */

/*
 * This class is to be extended by the Badger, Fox, and Rabbit classes. 
 */
public abstract class Animal extends Living implements MyAge
{
	protected int age;   // age of the animal 
	public Plain p;
	public int c;
	public int r;
	public int a;
	public Living[][] grid;
	
	//Without the constructor, my main was causing issues.
	public Animal(Plain p, int r, int c, int a) {
		super(p, r, c);
		this.age = a;
	}

	@Override
	/**
	 * 
	 * @return age of the animal 
	 */
	public int myAge()
	{
		return this.age; 
	}
}
