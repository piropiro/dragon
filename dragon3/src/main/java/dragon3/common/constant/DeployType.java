/*
 * Created on 2004/06/27
 */
package dragon3.common.constant;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;

/**
 * @author k-saito
 */
public enum DeployType {

	NONE("無", GameColors.NONE),
	ENEMY_CHARA("敵キャラ", GameColors.RED),
	PLAYER_CHARA("味方キャラ", GameColors.BLUE),
	NEUTRAL_CHARA("中立キャラ", GameColors.GREEN),
	SUMMON("召喚キャラ", GameColors.RED),
	BOX_ITEM("宝箱アイテム", GameColors.GREEN),
	CLEAR_ITEM("クリアアイテム", GameColors.GREEN),
	ENEMY_ITEM("敵持ちアイテム", GameColors.GREEN),
	SECRET_ITEM("隠しアイテム", GameColors.GREEN),
	
	;
	
	@Getter private String text;
	@Getter private GameColors color;
	
	DeployType(String text, GameColors color) {
		this.text = text;
		this.color = color;
	}
	
	public static Map<String, String> createMap() {
		Map<String, String> idAndText = new LinkedHashMap<>();
		for (DeployType a : values()) {	
			idAndText.put(a.name(), a.getText());
		}
		return idAndText;
	}

}
