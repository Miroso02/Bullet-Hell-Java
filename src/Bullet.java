
public class Bullet extends GameObject 
{
	public Bullet() 
	{
		// Default values
		setPosition(2000, 0); // Offscreen
		size = 10;
		col = new Color(255);

		setMPattern(new MPattern() {
			public void move() 
			{
				moveWithConstSpeed();
		    }
		});
	}

	void display() 
	{
		Globals.sketch.fill(col.r, col.g, col.b);
		Globals.ellipse(position, size);
	}
}
