import java.util.ArrayList;

import processing.core.PVector;

public class Globals
{	
	static Program sketch;
	static Player player;
	static Button restartButton;
	static ArrayList<Cannon> cannons;

	// On this cannon I'll test new patterns
	static Cannon test;
	
	static PVector pressPos;
	static PVector releasePos;
	static boolean mouseReleased = false;
	
	static void rect(PVector position, int size) {
		sketch.rect(position.x, position.y, size, size);
	}

	static void ellipse(PVector position, int size) {
		sketch.ellipse(position.x, position.y, size, size);
	}
}
