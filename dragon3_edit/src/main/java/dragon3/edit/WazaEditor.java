package dragon3.edit;

import mine.MineException;
import mine.edit.BeanEditor;
import mine.edit.EditListener;
import mine.edit.EditPanel;

import java.util.LinkedHashMap;
import java.util.Map;

import dragon3.Statics;
import dragon3.bean.AnimeData;
import dragon3.bean.WazaData;
import dragon3.bean.load.AnimeDataLoader;
import dragon3.common.DataList;
import dragon3.common.constant.GameColors;

public class WazaEditor extends EditPanel<WazaData> implements EditListener<WazaData> {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws MineException {
		new BeanEditor<>("WazaEditor", "wazas.txt", "data.json", new WazaEditor());
	}

	WazaEditor() throws MineException {
		super(WazaData.class);

		setField(CENTER, "id", "ID");
		setField(CENTER, "name", "名前");
		setField(LEFT, "label", "ラベル");
		setEnumCombo(RIGHT, "labelColor", "カラー", GameColors.class);
		
		Map<String, String> idAndText = new LinkedHashMap<>();
		for (GameColors gc : GameColors.values()) {	
			idAndText.put(gc.name(), gc.getText());
		}
		initCombo("labelColor", idAndText);
		setSlider(CENTER, "star", "Star", 5);
		setTextCombo(CENTER, "targetType", "範囲タイプ");
		initCombo("targetType", Statics.targetType);
		setTextCombo(CENTER, "damageType", "攻撃タイプ");
		initCombo("damageType", Statics.damageType);
		setTextCombo(CENTER, "weponType", "武器タイプ");
		initCombo("weponType", Statics.weponType);

		DataList<AnimeData> animeList = AnimeDataLoader.loadAnimeList();
		setTextCombo(CENTER, "animeId", "動画タイプ");
		initCombo("animeId", animeList.getIdAndName());

		for (int i=0; i<5; i++) {
			setTextCombo(CENTER, "effect", i, "効果" + i);
			initCombo("effect", i, Statics.effect);
		}
	}
}
