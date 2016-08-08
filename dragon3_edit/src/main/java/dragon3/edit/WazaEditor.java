package dragon3.edit;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.ObjectGraph;
import dragon3.common.DataList;
import dragon3.common.constant.AttackEffect;
import dragon3.common.constant.DamageType;
import dragon3.common.constant.EnergyType;
import dragon3.common.constant.GameColor;
import dragon3.common.constant.TargetType;
import dragon3.data.AnimeData;
import dragon3.data.WazaData;
import dragon3.data.load.AnimeDataLoader;
import dragon3.image.BodyImageList;
import dragon3.image.ImageManager;
import mine.MineException;
import mine.edit.BeanEditor;
import mine.edit.EditListener;
import mine.edit.EditPanel;

@SuppressWarnings("serial")
public class WazaEditor extends EditPanel<WazaData> implements EditListener<WazaData> {

	public static void main(String[] args) throws MineException {
		ObjectGraph objectGraph = ObjectGraph.create(new EditorModule());

		new BeanEditor<>("WazaEditor", "wazas.txt", "data.json", objectGraph.get(WazaEditor.class));
	}

	@Inject
	WazaEditor(ImageManager im) {
		super(WazaData.class);

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
