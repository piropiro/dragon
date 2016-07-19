package dragon3.common.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public enum BodyKind {

	CHARA("キャラ"),
	CLASS("職業"), // 1
	SOUL("魂玉"),
	WEPON("武器"), // 2
	ARMOR("防具"), // 3
	ITEM("小物"), // 4
	DOLL("ドール"), // 39
	WAZA("技"), // 52
	;
	
	private String text;
	
	BodyKind(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public static Map<String, String> createMap() {
		Map<String, String> idAndText = new LinkedHashMap<>();
		for (BodyKind a : values()) {	
			idAndText.put(a.name(), a.getText());
		}
		return idAndText;
	}
}