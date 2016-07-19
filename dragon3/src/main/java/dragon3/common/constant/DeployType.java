/*
 * Created on 2004/06/27
 */
package dragon3.common.constant;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author k-saito
 */
public enum DeployType {
	
	CHARA("普通キャラ"),
	SUMMON("召喚キャラ"),
	BOX_ITEM("宝箱アイテム"),
	CLEAR_ITEM("クリアアイテム"),
	ENEMY_ITEM("敵持ちアイテム"),
	SECRET_ITEM("隠しアイテム"),
	
	;
	
	private String text;
	
	DeployType(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public static Map<String, String> createMap() {
		Map<String, String> idAndText = new LinkedHashMap<>();
		for (DeployType a : values()) {	
			idAndText.put(a.name(), a.getText());
		}
		return idAndText;
	}

}
