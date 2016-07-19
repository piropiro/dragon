package dragon3.edit;

import dragon3.bean.AnimeData;
import dragon3.common.constant.AnimeType;
import dragon3.image.AnimeImageList;
import dragon3.image.ImageManager;
import mine.awt.ImageLoaderAWT;
import mine.edit.BeanEditor;
import mine.edit.EditPanel;
import mine.paint.MineImage;
import mine.paint.MineImageLoader;

public class AnimeEditor extends EditPanel<AnimeData> {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws Exception {
		new BeanEditor<>("AnimeEditor", "animes.txt", "data.json", new AnimeEditor());
	}

	AnimeEditor() throws Exception {
		super(AnimeData.class);

		MineImageLoader mil = new ImageLoaderAWT();
		ImageManager im = new ImageManager(mil);
		AnimeImageList ail = im.getAnimeList();

		String[] pathList = ail.getPathList();
		MineImage[][] imageList = ail.getImageList();
		MineImage[] firstImageList = new MineImage[imageList.length];
		for (int i=0; i<imageList.length; i++) {
			firstImageList[i] = imageList[i][imageList[i].length / 2];
		}

		setField(CENTER, "id", "ID");
		setField(CENTER, "name", "名前");
		setImageCombo(CENTER, "image", "画像");
		setEnumCombo(CENTER, "type", "タイプ", AnimeType.class);
		setSlider(CENTER, "sleep", "ウエイト", 100);

		initCombo("image", pathList, firstImageList);
		initCombo("type", AnimeType.createMap());
	}
}
