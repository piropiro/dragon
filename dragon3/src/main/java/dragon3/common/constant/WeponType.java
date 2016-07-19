/*
 * Created on 2004/06/27
 */
package dragon3.common.constant;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author k-saito
 */
public enum WeponType {
	
	NONE("無"),
	SWORD("剣"),
	AX("斧"),
	SPEAR("槍"),
	BOW("弓"),
	KNIFE("短剣"),
	MAGIC("魔法"),
	BODY("体術"),
	
	;
	
	private String text;
	
	WeponType(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public static Map<String, String> createMap() {
		Map<String, String> idAndText = new LinkedHashMap<>();
		for (WeponType a : values()) {	
			idAndText.put(a.name(), a.getText());
		}
		return idAndText;
	}

}
