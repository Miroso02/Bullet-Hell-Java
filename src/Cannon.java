import java.util.ArrayList;

public class Cannon extends GameObject 
{
	int health;
	boolean isDead;

	protected ArrayList<FCPattern> fcPatterns = new ArrayList<FCPattern>();

	//----------- Constructor --------------------------------------

	public Cannon() {
		// Default values
		setPosition(Globals.sketch.width / 2, Globals.sketch.height / 2);
		this.size = 40;
		this.col = new Color(200, 0, 255);
		health = 100;

		isDead = false;

		// Default move pattern realization does nothing
		setMPattern(new MPattern() {
			public void move() {}
		});
	}

	//--------- Multipurpose methods --------------------------

	@Override public void update() 
	{
		if (!isDead) 
		{
			takeDamage(); // Comment this in test mode
			move();
			fire();
			display();
		}
		updateBullets();
	}

	//--------- Main methods --------------------------------

	protected void updateBullets() 
	{
		Globals.sketch.noStroke();
		for (FCPattern fc: fcPatterns)
			fc.updateBullets();
	}

	void fire() 
	{
		for (FCPattern fc: fcPatterns)
			fc.fireAndColorize();
	}

	void display() 
	{
		Globals.sketch.noFill();
		Globals.sketch.strokeWeight(1);
		Globals.sketch.stroke(col.r, col.g, col.b);
		Globals.rect(position, size);

		Globals.sketch.fill(col.r, col.g, col.b);
		Globals.sketch.textSize(20);
		Globals.sketch.text(health, position.x, position.y); // TODO: Fix text display
	}

	void takeDamage() 
	{
		ArrayList<Bullet> playerBullets = new ArrayList<Bullet>();
		for (FCPattern playerFCP: Globals.player.fcPatterns) {
			playerBullets.addAll(playerFCP.bullets);
		}

		for (Bullet bullet: playerBullets) {
			if (isTouching(bullet)) {
				health--;
				bullet.position.x = 2000;
			}
		}

		if (health <= 0) {
			isDead = true;
		}
	}

	//--------- GETters / SETters ----------------------

	public void addFCPattern(FCPattern newPattern) 
	{
		newPattern.gameObject = this;
		fcPatterns.add(newPattern);
	}
	public FCPattern getFCPattern(int index) 
	{
		return fcPatterns.get(index);
	}
}
