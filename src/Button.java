import processing.core.PApplet;
import processing.core.PVector;

class Button extends GameObject
{
	PVector dimensions = new PVector(0, 0);
  
	String text;

	Color pcol;

	public Button(float x, float y)
	{
		setPosition(x, y);

		col = new Color(180, 255, 0);
		pcol = new Color(255, 100, 0);

		Globals.sketch.textSize(30);
		setText("");
		setPadding(10, 10);

		Globals.pressPos = new PVector(0, 0);
		Globals.releasePos = new PVector(0, 0);
	}

	void display() {
		Color fillCol = (onButton(Globals.sketch.mouseX, Globals.sketch.mouseY)) ? pcol : col;
		Globals.sketch.stroke(fillCol.r, fillCol.g, fillCol.b);

		Globals.sketch.textSize(30);
		Globals.sketch.textAlign(PApplet.CENTER, PApplet.CENTER);
		Globals.sketch.fill(fillCol.r, fillCol.g, fillCol.b);
		Globals.sketch.text(text, position.x, position.y);
		Globals.sketch.textAlign(PApplet.CENTER);

		Globals.sketch.fill(0, 0);
		Globals.sketch.rect(position.x, position.y, dimensions.x, dimensions.y);
	}

	boolean pressed() {
		return Globals.mouseReleased && onButton(Globals.pressPos) && onButton(Globals.releasePos);
	}

	boolean onButton(PVector v)  {
		return onButton(v.x, v.y);
	}

	boolean onButton(float mx, float my) {
		return  Math.abs(mx - position.x) < dimensions.x / 2 &&
				Math.abs(my - position.y) < dimensions.y / 2;
	}
  
	private void setText(String text) {
		this.text = text;
	}

	private void setPadding(PVector padding) {
		dimensions.x = 2 * padding.x + Globals.sketch.textWidth(text);
		dimensions.y = 2 * padding.y + Globals.sketch.textAscent() + Globals.sketch.textDescent();
	}
  
	private void setPadding(float pw, float ph) {
		setPadding(new PVector(pw, ph));
	}
  
	void setOptions(String text, float pw, float ph) {
		setText(text);
		setPadding(pw, ph);
	}
  
	void setOptions(String text, PVector padding) {
		setText(text);
		setPadding(padding);
	}
	
	void setOptions(String text) {
		setOptions(text, 10, 10);
	}
}
