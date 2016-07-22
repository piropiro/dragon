/*
 * Created on 2004/06/27
 */
package dragon2.common.constant;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;

/**
 * @author k-saito
 */
public enum DamageType {
	
	NONE("なし"), // 0
	SWORD("物理攻撃"), // 1
	MAGIC("魔法攻撃"), // 2
	SWORD_ALL("無視物理"), // 5
	MAGIC_ALL("無視魔法"), // 6
	
	;
	
	@Getter private String text;
	
	DamageType(String text) {
		this.text = text;
	}
	
	public static Map<String, String> createMap() {
		Map<String, String> idAndText = new LinkedHashMap<>();
		for (DamageType a : values()) {	
			idAndText.put(a.name(), a.getText());
		}
		return idAndText;
	}

	public static DamageType convert(int n) {
		switch (n) {
		case 1: return SWORD;
		case 2: return MAGIC;
		case 5: return SWORD_ALL;
		case 6: return MAGIC_ALL;
		
		default:
			throw new IllegalArgumentException("DamageType unmatch: " + n);
		}
	}
}
