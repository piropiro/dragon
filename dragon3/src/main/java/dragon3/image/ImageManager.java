package dragon3.image;

import mine.MineException;
import mine.MineUtils;
import mine.paint.MineImage;
import mine.paint.MineImageLoader;


public class ImageManager {

	public static final String IMAGE_DIR = "dragon3/image/";
	public static final String BODY_IMAGE_DIR = "dragon3/image/body/";
	public static final String ANIME_IMAGE_DIR = "dragon3/image/anime/";

	private MineImageLoader mil;
	private AnimeImageList animeList;
	private BodyImageList bodyList;
	private MineImage[][] waku;
	private MineImage[] back;
	private MineImage[] text;
	private MineImage[] status;
	private MineImage[] num;
	
	public ImageManager(MineImageLoader mil) throws MineException {
		this.mil = mil;

		waku = mil.loadTile(IMAGE_DIR + "waku.png", 32, 32);
		text = mil.loadTile(IMAGE_DIR + "text.png", 32, 12)[0];
		status = mil.loadTile(IMAGE_DIR + "status.png", 32, 32)[0];
		num = mil.loadTile(IMAGE_DIR + "num.png", 10, 12)[0];
		back =
			(MineImage[]) MineUtils.linerize(
				mil.loadTile(IMAGE_DIR + "back.png", 32, 32),
				new MineImage[0]);
		animeList = new AnimeImageList(ANIME_IMAGE_DIR, mil);
		bodyList = new BodyImageList(BODY_IMAGE_DIR, mil);
	}

	public MineImage getImage(String name) {
		try {
			return mil.load(IMAGE_DIR + name);
		} catch (MineException e) {
			throw new RuntimeException(e);
		}
	}

	public AnimeImageList getAnimeList() {
		return animeList;
	}

	public MineImage[] getBack() {
		return back;
	}

	public BodyImageList getBodyList() {
		return bodyList;
	}

	public MineImage[] getNum() {
		return num;
	}

	public MineImage[] getStatus() {
		return status;
	}

	public MineImage[] getText() {
		return text;
	}

	public MineImage[][] getWaku() {
		return waku;
	}
}
