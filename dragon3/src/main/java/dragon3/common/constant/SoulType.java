/*
 * Created on 2004/06/27
 */
package dragon3.common.constant;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author k-saito
 */
public enum SoulType {
	
	NONE("無", "none.png"),
	RED("赤", "soul_red.png"),
	BLUE("青", "soul_blue.png"),
	YELLOW("黄", "soul_yellow.png"),
	GREEN("緑", "soul_green.png"),
	PINK("桃", "soul_pink.png"),
	ORANGE("橙", "soul_orange.png"),
	
	;
	
	private String text;
	
	private String image;
	
	SoulType(String text, String image) {
		this.text = text;
		this.image = image;
	}
	
	public String getText() {
		return text;
	}
	
	public String getImage() {
		return image;
	}
	
	public static Map<String, String> createMap() {
		Map<String, String> idAndText = new LinkedHashMap<>();
		for (SoulType a : values()) {	
			idAndText.put(a.name(), a.getText());
		}
		return idAndText;
	}

}
