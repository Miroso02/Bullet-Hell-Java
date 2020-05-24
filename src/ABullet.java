public class ABullet extends Bullet // Aggressive bullet
{
	public ABullet() 
	{
		super();
	}

	@Override public void update() 
	{
		display();
		move();
		killPlayer(); // Comment this in test mode
	}

	private void killPlayer() 
	{
		if (this.isTouching(Globals.player))	
			Globals.player.isDead = true;
	}
}
