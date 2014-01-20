package dragon3.common.constant;

import dragon3.common.Body;



public class GameColors {

	public static final String BLUE = "blue";
	public static final String RED = "red";
	public static final String GREEN = "green";
	public static final String YELLOW = "yellow";
	public static final String SKY = "sky";
	public static final String WHITE = "white";

	public static boolean isPlayer(Body b) {
		return b.getColor().equals(BLUE);
	}
	public static boolean isEnemy(Body b) {
		return b.getColor().equals(RED);
	}
	
	public static int[] getColor(String color) {
		if (color == null) {
			return new int[]{0, 0, 0};
		}
		if (color.equals(BLUE)) {
			return new int[]{0, 0, 150};
		}
		if (color.equals(RED)) {
			return new int[]{150, 0, 0};
		}
		if (color.equals(GREEN)) {
			return new int[]{0, 100, 0};
		}
		return new int[]{0, 0, 0};
	}

	public static int[] getAlphaColor(String color) {
		if (color == null) {
			return new int[]{0, 0, 0, 200};
		}
		if (color.equals(BLUE)) {
			return new int[]{0, 0, 150, 200};
		}
		if (color.equals(RED)) {
			return new int[]{150, 0, 0, 200};
		}
		if (color.equals(GREEN)) {
			return new int[]{0, 100, 0, 200};
		}
		return new int[]{0, 0, 0, 200};
	}
}
