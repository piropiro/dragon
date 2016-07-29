package dragon3.image;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dragon3.data.StageData;
import lombok.Getter;
import mine.MineException;
import mine.MineUtils;
import mine.paint.MineGraphics;
import mine.paint.MineImage;
import mine.paint.MineImageLoader;


public class ImageManager {

	public static final String IMAGE_DIR = "dragon3/image/";
	public static final String BODY_IMAGE_DIR = "dragon3/image/body/";
	public static final String ANIME_IMAGE_DIR = "dragon3/image/anime/";
	public static final String STAGE_IMAGE_DIR = "dragon3/image/stage/";
	
	@Getter private MineImageLoader imageLoader;
	@Getter private AnimeImageList animeImageList;
	@Getter private BodyImageList bodyImageList;

	@Getter private MineImage[][] waku;
	@Getter private MineImage[] back;
	@Getter private MineImage[] text;
	@Getter private MineImage[] status;
	@Getter private MineImage[] num;
	
	@Getter private MineImage[] stageWaku;
	@Getter private MineImage stageStar;

	
	public ImageManager(MineImageLoader imageLoader) throws MineException {
		this.imageLoader = imageLoader;

		waku = imageLoader.loadTile(IMAGE_DIR + "waku.png", 32, 32);
		text = imageLoader.loadTile(IMAGE_DIR + "text.png", 32, 12)[0];
		status = imageLoader.loadTile(IMAGE_DIR + "status.png", 32, 32)[0];
		num = imageLoader.loadTile(IMAGE_DIR + "num.png", 10, 12)[0];
		back =
			(MineImage[]) MineUtils.linerize(
					imageLoader.loadTile(IMAGE_DIR + "back.png", 32, 32),
				new MineImage[0]);
		animeImageList = new AnimeImageList(ANIME_IMAGE_DIR, imageLoader);
		bodyImageList = new BodyImageList(BODY_IMAGE_DIR, imageLoader);
		
		stageWaku = imageLoader.loadTile(IMAGE_DIR + "stageWaku.png", 128, 96)[0];
		stageStar = imageLoader.load(IMAGE_DIR + "stageStar.png");
	}

	public MineImage getImage(String name) {
		try {
			return imageLoader.load(IMAGE_DIR + name);
		} catch (MineException e) {
			throw new RuntimeException(e);
		}
	}
	
	public MineImage[] loadStageImageList(List<StageData> stageList) {
		List<MineImage> list = new ArrayList<>();
		for (StageData stage : stageList) {
			try {
				MineImage img = imageLoader.load(STAGE_IMAGE_DIR + stage.getId() + ".png");
				list.add(img);
			} catch (MineException e) {
				throw new RuntimeException(e);
			}
		}
		return list.toArray(new MineImage[0]);
	}
	
	public MineImage[] createStageStatusImageList(List<StageData> stageList, Map<String, Integer> starList) {
		List<MineImage> list = new ArrayList<>();
		for (StageData stage : stageList) {		
			MineImage img = imageLoader.getBuffer(128, 96);
			MineGraphics g = img.getGraphics();
			
			int starNum = 0;
			if (starList.containsKey(stage.getId())) {
				starNum = starList.get(stage.getId());
			}
			int level = stage.getLevel() + starNum * 10;
			g.drawString("Lv." + level, 60, 70);
			
			for (int i = 1; i <= starNum; i++) {
				g.drawImage(stageStar, 128 - 30 * i, 60);
			}
			list.add(img);
		}
		return list.toArray(new MineImage[0]);
	}
}
