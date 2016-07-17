package dragon3.common.constant;

import dragon3.common.Body;



public enum GameColors {
	

	BLACK("黒", new int[]{0, 0, 0}, new int[]{0, 0, 0, 200}, new int[]{255, 255, 255}),
	BLUE("青", new int[]{0, 0, 255}, new int[]{0, 0, 150, 200}, new int[]{255, 255, 255}),
	RED("赤", new int[]{255, 0, 0}, new int[]{150, 0, 0, 200}, new int[]{255, 255, 255}),
	GREEN("緑", new int[]{0, 255, 0}, new int[]{0, 100, 0, 200}, new int[]{0, 0, 0}),
	YELLOW("黄", new int[]{150, 150, 0}, new int[]{150, 150, 0, 200}, new int[]{0, 0, 0}),
	WHITE("白", new int[]{255, 255,255}, new int[]{255, 255, 25, 200}, new int[]{0, 0, 0}),
	SKY("空", new int[]{50, 100, 255}, new int[]{50, 100, 255, 200}, new int[]{255, 255, 255}),
	;
	
	private String text;
	
	private int[] bg;
	
	private int[] fg;
	
	private int[] alphaBg;
	
	private GameColors(String text, int[] bg, int[] alphaBg, int[] fg) {
		this.text = text;
		this.bg = bg;
		this.fg = fg;
		this.alphaBg = alphaBg;
	}
	
	public String getText() {
		return text;
	}
	public int[] getBg() {
		return bg;
	}
	public int[] getAlphaBg() {
		return alphaBg;
	}

	public int[] getFg() {
		return fg;
	}

	public static boolean isPlayer(Body b) {
		return b.getColor().equals(BLUE);
	}
	public static boolean isEnemy(Body b) {
		return b.getColor().equals(RED);
	}

}
