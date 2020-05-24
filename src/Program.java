import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Program extends PApplet
{	
	public void settings()
	{
		size(720, 1280);
		noSmooth();	
	}
	
	public void setup() 
	{	
		Globals.cannons = new ArrayList<Cannon>();

		Globals.test = new Cannon();
		Globals.test.setPosition(width / 2, height / 2 - 200);
		Globals.test.health = 500;
		Globals.test.setColor(0, 255, 200);

		Globals.test.addFCPattern(new FCPattern() {
			public void fire() 
			{
				Bullet bullet = new ABullet();
				bullets.add(bullet);

				bullet.setPosition(((Cannon)gameObject).getPosition());
				bullet.size = 20;
				bullet.setColor(255, 0, 0);

				bullet.setMPattern(new MPattern() {
					public void move() 
					{
						velocity.y += 0.03;
						super.moveWithConstSpeedAndRicochet();
					}
				});
				bullet.mPattern.setVelocity(super.shootToAllSides());
				bullet.mPattern.speed = 5;
		     	bullet.mPattern.ricochetModule.setOptions(2, true, true, true);
		    }
		    public void beforeShot(){}
		    public void afterShot(){}
		});
		Globals.test.getFCPattern(0).setOptions(160, 6);

		Globals.test.addFCPattern(new FCPattern() {
			public void beforeShot() 
			{
				this.bulletsPerShot = ((Cannon)gameObject).getFCPattern(0).bullets.size();
			}

		    public void fire() 
		    {
		    	Bullet bullet = new ABullet();
		    	bullets.add(bullet);
		    	int num = this.num % bulletsPerShot;

		    	Bullet prevBul = ((Cannon)gameObject).getFCPattern(0).getBullet(num);
		    	bullet.setPosition(prevBul.getPosition());

		    	bullet.setMPattern(new MPattern() {
		    		public void move() 
		    		{
		    			velocity.y += 0.03;
		    			super.moveWithConstSpeed();
		    		}
		    	});
		    	bullet.mPattern.setVelocity(0, -1);
		    	bullet.mPattern.speed = 4;

		    	super.changeBulletColorHSB(150);
		    }
		    public void afterShot(){}
		});
		Globals.test.getFCPattern(1).setOptions(8, 6);
		Globals.test.getFCPattern(1).setTimeCounter(-5);

		  //----------------------------------------------------------------------------

		  Globals.player = new Player();
		  Globals.restartButton = new Button(width / 2 - 150, height / 2 + 50);
		  Globals.restartButton.setOptions("Restart");

		  rectMode(CENTER);
		  textAlign(CENTER);
		  
		  mouseX = -1;
		  pmouseX = -1;
		  mouseY = -1;
		  pmouseY = -1;
		}
	
	public void draw() 
	{
		background(0); // Black background
		
		Globals.test.update();
	
		Globals.player.update();
		if (Globals.player.isDead) 
		{
			Globals.restartButton.display();
			if (Globals.restartButton.pressed()) setup();
		}

		fill(255);
	  	text(frameRate, 100, 100);

	  	// mouseReleased = false;
	}
	
	public void mousePressed() 
	{
		Globals.pressPos = new PVector(mouseX, mouseY);
		Globals.releasePos = new PVector(0, 0);
	}

	public void mouseReleased() 
	{
		Globals.releasePos = new PVector(mouseX, mouseY);
		Globals.mouseReleased = true;
	}
	
	//------------------------------------------------------------------------------
	
	public static void main(String[] args)
	{
		Globals.sketch = new Program();
		String[] pargs = {""};
		PApplet.runSketch(pargs, Globals.sketch);
	}
}
