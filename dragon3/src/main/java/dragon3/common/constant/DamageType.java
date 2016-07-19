/*
 * Created on 2004/06/27
 */
package dragon3.common.constant;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author k-saito
 */
public enum DamageType {
	
	NONE("なし"),
	SWORD("物理攻撃"),
	MAGIC("魔法攻撃"),
	SWORD_ALL("無視物理"),
	MAGIC_ALL("無視魔法"),
	
	;
	
	private String text;
	
	DamageType(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public static Map<String, String> createMap() {
		Map<String, String> idAndText = new LinkedHashMap<>();
		for (DamageType a : values()) {	
			idAndText.put(a.name(), a.getText());
		}
		return idAndText;
	}

}