package dragon2.common.constant;

import java.util.LinkedHashMap;
import java.util.Map;

import dragon2.common.Body;
import lombok.Getter;



public enum GameColor {
	

	NONE("無", new int[]{0, 0, 0}, new int[]{0, 0, 0, 200}, new int[]{255, 255, 255}),
	BLUE("青", new int[]{0, 0, 255}, new int[]{0, 0, 150, 200}, new int[]{255, 255, 255}),
	RED("赤", new int[]{255, 0, 0}, new int[]{150, 0, 0, 200}, new int[]{255, 255, 255}),
	GREEN("緑", new int[]{0, 255, 0}, new int[]{0, 100, 0, 200}, new int[]{0, 0, 0}),
	YELLOW("黄", new int[]{150, 150, 0}, new int[]{150, 150, 0, 200}, new int[]{0, 0, 0}),
	WHITE("白", new int[]{255, 255,255}, new int[]{255, 255, 25, 200}, new int[]{0, 0, 0}),
	SKY("空", new int[]{50, 100, 255}, new int[]{50, 100, 255, 200}, new int[]{255, 255, 255}),
	;
	
	@Getter private String text;
	
	@Getter private int[] bg;
	
	@Getter private int[] fg;
	
	@Getter private int[] alphaBg;
	
	private GameColor(String text, int[] bg, int[] alphaBg, int[] fg) {
		this.text = text;
		this.bg = bg;
		this.fg = fg;
		this.alphaBg = alphaBg;
	}

	public static boolean isPlayer(Body b) {
		return b.getColor().equals(BLUE);
	}
	public static boolean isEnemy(Body b) {
		return b.getColor().equals(RED);
	}

	public static GameColor convert(int n) {
		switch (n) {
		case 0: return NONE;
		case 1: return BLUE;
		case 2: return GREEN;
		case 3: return RED;
		default:
			throw new IllegalArgumentException("GameColor unmatch: " + n);
		}
	}
	public static Map<String, String> createMap() {
		Map<String, String> idAndText = new LinkedHashMap<>();
		for (GameColor a : values()) {	
			idAndText.put(a.name(), a.getText());
		}
		return idAndText;
	}
}
