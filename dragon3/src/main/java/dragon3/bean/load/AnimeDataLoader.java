package dragon3.bean.load;

import dragon3.bean.AnimeData;
import dragon3.common.DataList;

public class AnimeDataLoader {
	private static final String ANIME_DIR = "dragon3/data/anime/";

	private static final String[] ANIME_FILES = new String[]{"AnimeData.json", "SystemAnime.json"};

	public static DataList<AnimeData> loadAnimeList() {
		return new DataList<AnimeData>(ANIME_DIR, ANIME_FILES, AnimeData[].class);
	}
}
