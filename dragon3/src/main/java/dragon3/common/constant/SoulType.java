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
	
	NONE("無"),
	RED("赤"),
	BLUE("青"),
	YELLOW("黄"),
	GREEN("緑"),
	PINK("桃"),
	ORANGE("橙"),
	
	;
	
	private String text;
	
	SoulType(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public static Map<String, String> createMap() {
		Map<String, String> idAndText = new LinkedHashMap<>();
		for (SoulType a : values()) {	
			idAndText.put(a.name(), a.getText());
		}
		return idAndText;
	}

}
