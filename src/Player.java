import processing.core.PVector;

public class Player extends Cannon 
{
	//----------- Constructor ----------------------------------------------------

	public Player() 
	{
		setPosition(Globals.sketch.width / 2, Globals.sketch.height / 2 + 200);

		this.size = 8;
		isDead = false;

		addFCPattern(new FCPattern() {
			public void fire() 
			{
				Bullet bullet = new Bullet();

				float bulletSpeedX = (float) ((num % 5 - 2) * 1.4);

				bullet.setPosition(Globals.player.getPosition().add(0, -5));
				bullet.mPattern.setVelocity(bulletSpeedX, -30);
				bullet.mPattern.speed = 30;
				bullet.size = 8;

				bullets.add(bullet);
			}
			public void beforeShot(){}
			public void afterShot(){}
	    });
	    getFCPattern(0).shotCooldown = 4;
	    getFCPattern(0).bulletsPerShot = 5;

	    setMPattern(new MPattern() {
	    	public void move() 
	    	{
	    		if (Globals.sketch.mousePressed) 
	    		{
	    			final float SENSITIVITY = 1;
	    			PVector pos = gameObject.getPosition();
	    			
	    			pos.x += SENSITIVITY * (Globals.sketch.mouseX - Globals.sketch.pmouseX);
	         		pos.y += SENSITIVITY * (Globals.sketch.mouseY - Globals.sketch.pmouseY);
	          
	         		gameObject.setPosition(pos);
	    		}
	    	}
	    });
	}

	//--------- Multipurpose methods ---------------------------------------------

	@Override public void update() 
	{
		if (!isDead) 
		{
			move();
			fire();
			display();
			updateBullets();
		}
	}

	//--------- Main methods -----------------------------------------------------

	public void move() 
	{
		if (!isOnScreen()) stayOnTheScreen();
		super.move();
	}

	void display() 
	{
		Globals.sketch.noStroke();
		Globals.sketch.fill(255);
		Globals.ellipse(position, size);
	}

	//------- Private inner methods ----------------------------------------------

	private void stayOnTheScreen() // TODO: Optimize this method
	{
		if (position.x <= 0) position.x = 1;
		else if (position.x >= Globals.sketch.width) position.x = Globals.sketch.width - 1;
		if (position.y <= 0) position.y = 1;
		else if (position.y >= Globals.sketch.height) position.y = Globals.sketch.height - 1;
	}	
}
