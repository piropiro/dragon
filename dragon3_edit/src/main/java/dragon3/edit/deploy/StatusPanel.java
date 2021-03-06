package dragon3.edit.deploy;

import java.util.List;

import mine.MineException;
import mine.awt.ImageLoaderAWT;
import mine.edit.EditListener;
import mine.edit.EditPanel;
import mine.paint.MineImage;
import mine.paint.MineImageLoader;
import dragon3.Statics;
import dragon3.bean.BodyData;
import dragon3.bean.DeployData;
import dragon3.image.BodyImageList;
import dragon3.image.ImageManager;

public class StatusPanel extends EditPanel<DeployData> implements EditListener<DeployData> {

	private static final long serialVersionUID = 1L;

	StatusPanel() {
		super(DeployData.class);

		setImageCombo(CENTER, "bodyId", "BODY");
		setTextCombo(CENTER, "type", "種類");
		initCombo("type", Statics.deployType);
		setSlider(CENTER, "level", "Level", 99);
		setTextCombo(LEFT, "color", "色");
		initCombo("color", Statics.color);
		setIntCombo(RIGHT, "limitTurn", "制限時間", 20);
		setIntCombo(LEFT, "scope", "射程", 5);
		setIntCombo(RIGHT, "range", "領域", 20);
		setIntCombo(LEFT, "x", "配置X", 20);
		setIntCombo(RIGHT, "y", "配置Y", 15);
		setIntCombo(LEFT, "goalX", "目標X", 20);
		setIntCombo(RIGHT, "goalY", "目標Y", 15);

		List<BodyData> bodyList = Statics.bodyList.getList();
		String[] idList = new String[bodyList.size()];
		MineImage[] imageList = new MineImage[bodyList.size()];

		try {
			MineImageLoader mil = new ImageLoaderAWT();
			ImageManager im = new ImageManager(mil);
			BodyImageList bil = im.getBodyList();

			for (int i=0; i<bodyList.size(); i++) {
				BodyData body = bodyList.get(i);
				idList[i] = body.getId();
				imageList[i] = bil.getImage(body.getImage());
			}
		} catch (MineException e) {
			throw new RuntimeException(e);
		}
		initCombo("bodyId", idList, imageList);
	}
}
