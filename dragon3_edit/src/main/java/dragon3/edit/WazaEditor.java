package dragon3.edit;

import java.util.LinkedHashMap;
import java.util.Map;

import dragon3.bean.AnimeData;
import dragon3.bean.WazaData;
import dragon3.bean.load.AnimeDataLoader;
import dragon3.common.DataList;
import dragon3.common.constant.AttackEffect;
import dragon3.common.constant.DamageType;
import dragon3.common.constant.EnergyType;
import dragon3.common.constant.GameColor;
import dragon3.common.constant.TargetType;
import dragon3.image.BodyImageList;
import dragon3.image.ImageManager;
import mine.MineException;
import mine.awt.ImageLoaderAWT;
import mine.edit.BeanEditor;
import mine.edit.EditListener;
import mine.edit.EditPanel;
import mine.paint.MineImageLoader;

public class WazaEditor extends EditPanel<WazaData> implements EditListener<WazaData> {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws MineException {
		new BeanEditor<>("WazaEditor", "wazas.txt", "data.json", new WazaEditor());
	}

	WazaEditor() throws MineException {
		super(WazaData.class);

		MineImageLoader mil = new ImageLoaderAWT();
		ImageManager im = new ImageManager(mil);
		BodyImageList bil = im.getBodyImageList();

		setField(CENTER, "id", "ID");
		setField(CENTER, "name", "名前");
		setField(LEFT, "label", "ラベル");
		setEnumCombo(RIGHT, "labelColor", "カラー", GameColor.class);
		setImageCombo(CENTER, "image", "画像");
		initCombo("image", bil.getPathList(), bil.getImageList());

		Map<String, String> idAndText = new LinkedHashMap<>();
		for (GameColor gc : GameColor.values()) {	
			idAndText.put(gc.name(), gc.getText());
		}
		initCombo("labelColor", idAndText);
		setEnumCombo(CENTER, "targetType", "範囲タイプ", TargetType.class);
		initCombo("targetType", TargetType.createMap());
		setEnumCombo(CENTER, "damageType", "攻撃タイプ", DamageType.class);
		initCombo("damageType", DamageType.createMap());

		DataList<AnimeData> animeList = AnimeDataLoader.loadAnimeList();
		setTextCombo(CENTER, "animeId", "動画タイプ");
		initCombo("animeId", animeList.getIdAndName());
		
		setEnumCombo(LEFT, "energyType", "消費タイプ", EnergyType.class);
		initCombo("energyType", EnergyType.createMap());
		setIntCombo(RIGHT, "energyCost", "消費量", 8);

		for (int i=0; i<5; i++) {
			setEnumCombo(CENTER, "effect", i, "効果" + i, AttackEffect.class);
			initCombo("effect", i, AttackEffect.createMap());
		}
	}
}
