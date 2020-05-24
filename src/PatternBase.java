import java.util.HashMap;

// Base for:
// 1) Bullet mPattern;
// 2) Cannon mPattern and fcPattern;

public class PatternBase 
{
	GameObject gameObject;
	HashMap<String, Object> data = new HashMap<String, Object>();

	int time = 0; // Time passed since creating this pattern
	int num = 0; // Number of bullet shot by this pattern
}