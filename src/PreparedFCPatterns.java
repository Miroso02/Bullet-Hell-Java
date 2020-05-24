import processing.core.PApplet;
import processing.core.PVector;

public class PreparedFCPatterns extends FCPatternBase 
{
	// TODO: Add some patterns

	//-------------- Fire patterns --------------------------

	public PVector targetPlayerFrom(PVector startPoint) 
	{
		PVector axisDist = Globals.player.getPosition().sub(startPoint);
		float absDist = axisDist.mag();

		float speedX = axisDist.x / absDist;
		float speedY = axisDist.y / absDist;

		return new PVector(speedX, speedY);
	}

	public PVector shootToAllSides() 
	{
		return shootToAllSides(this.bulletsPerShot);
	}
	public PVector shootToAllSides(int numOfDirections) 
	{
		float angle = (float) Math.PI * 2 * num / numOfDirections;
		return new PVector((float) Math.cos(angle), (float) Math.sin(angle));
	}

	//------------ Color Patterns --------------------------

	public void changeBulletColorHSB(int bulletsPerCycle) 
	{
		Bullet bullet = getCurBullet();

		Globals.sketch.colorMode(PApplet.HSB);
		float colK = (num * 255 / bulletsPerCycle) % 255;
		bullet.col = new Color(colK, 255, 255);
		Globals.sketch.colorMode(PApplet.RGB);
	}

	public void setColorOfAllShot(Color col) 
	{
		Bullet bullet = getCurBullet();
		int bulletsPerShot = this.bulletsPerShot;

		if (num % bulletsPerShot == 0) {
			bullet.col = col;
		}
		else {
			Bullet prevBullet = this.getPrevBullet();
			bullet.col = prevBullet.col;
		}
	}

	public Color randomColor() {
		return new Color((float) Math.random() * 255, (float) Math.random() * 255, (float) Math.random() * 255);
	}
}