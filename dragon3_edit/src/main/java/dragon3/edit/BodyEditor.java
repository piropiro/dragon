package dragon3.edit;

import java.util.Map;

import mine.awt.ImageLoaderAWT;
import mine.edit.BeanEditor;
import mine.edit.EditListener;
import mine.edit.EditPanel;
import mine.paint.MineImageLoader;
import dragon3.Statics;
import dragon3.bean.BodyData;
import dragon3.image.BodyImageList;
import dragon3.image.ImageManager;


public class BodyEditor extends EditPanel<BodyData> implements EditListener<BodyData> {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws Exception {
		new BeanEditor<>("BodyEditor", "bodys.txt", "data.xml", new BodyEditor());
	}

	public BodyEditor() throws Exception {
		super(BodyData.class);

		MineImageLoader mil = new ImageLoaderAWT();
		ImageManager im = new ImageManager(mil);
		BodyImageList bil = im.getBodyList();

		setField(CENTER, "id", "ID");
		setField(CENTER, "name", "名前");
		setImageCombo(CENTER, "image", "画像");
		initCombo("image", bil.getPathList(), bil.getImageList());
		setTextCombo(LEFT, "kind", "種別");
		initCombo("kind", Statics.kind);
		setTextCombo(RIGHT, "soulType", "魂色");
		initCombo("soulType", Statics.soul);
		setSlider(CENTER, "hp", "HP", 99);
		setSlider(CENTER, "str", "攻撃", 30);
		setSlider(CENTER, "def", "防御", 30);
		setSlider(CENTER, "mst", "魔法", 30);
		setSlider(CENTER, "mdf", "抵抗", 30);
		setSlider(CENTER, "hit", "命中", 30);
		setSlider(CENTER, "mis", "回避", 30);
		setTextCombo(LEFT, "moveType", "移動");
		initCombo("moveType", Statics.moveType);
		setIntCombo(RIGHT, "moveStep", "歩数", 20);
		setTextCombo(LEFT, "weponType", "武器");
		initCombo("weponType", Statics.weponType);
		setTextCombo(RIGHT, "armorType", "防具");
		initCombo("armorType", Statics.armorType);

		Map<String, String> wazaIdAndName = Statics.wazaList.getIdAndName();
		for (int i=0; i<3; i++) {
			setTextCombo(CENTER, "wazaList", i, "特技" + i);
			initCombo("wazaList", i, wazaIdAndName);
		}

		for (int i=0; i<5; i++) {
			setTextCombo(CENTER, "typeList", i, "特性" + i);
			initCombo("typeList", i, Statics.tokusei);
		}
	}
}
