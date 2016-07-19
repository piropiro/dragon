/*
 * Created on 2004/06/27
 */
package dragon3.common.constant;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author k-saito
 */
public enum ArmorType {
	
	NONE("無"),
	LITE("軽量"),
	HEAVY("重量"),
	;
	
	private String text;
	
	ArmorType(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public static Map<String, String> createMap() {
		Map<String, String> idAndText = new LinkedHashMap<>();
		for (ArmorType a : values()) {	
			idAndText.put(a.name(), a.getText());
		}
		return idAndText;
	}

}
