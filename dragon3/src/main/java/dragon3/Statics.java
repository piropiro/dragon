package dragon3;

import java.util.List;
import java.util.Map;


import mine.MineException;
import mine.MineUtils;
import mine.io.BeanIO;
import mine.io.MatrixIO;
import dragon3.bean.BodyData;
import dragon3.bean.WazaData;
import dragon3.common.Body;
import dragon3.common.DataList;

public class Statics {

	public static final String STAGE_FILE = "StageData.xml";
	public static final String[] WAZA_FILES = new String[] { "WazaData.xml" };
	public static final String[] BODY_FILES = new String[] { "BodyData.xml" };
	public static final String[] DEPLOY_FILES = new String[] { "init.xml" };

	public static final String WAZA_DIR = "dragon3/data/waza/";

	public static final String BODY_DIR = "dragon3/data/body/";
	public static final String DEPLOY_DIR = "dragon3/data/deploy/";
	public static final String STAGE_DIR = "dragon3/data/stage/";

	public static final String MAP_DIR = "dragon3/data/map/";
	public static final String TEXT_DIR = "dragon3/text/";

	public static final int TYPE_MAX = 100;

	public static final Map<String, String> color;
	public static final Map<String, String> kind;
	public static final Map<String, String> soul;
	public static final Map<String, String> effect;
	public static final Map<String, String> weponType;
	public static final Map<String, String> armorType;
	public static final Map<String, String> moveType;
	public static final Map<String, String> targetType;
	public static final Map<String, String> damageType;
	public static final Map<String, String> animeRange;
	public static final Map<String, String> tokusei;
	public static final Map<String, String> deployType;

	public static final DataList<BodyData> bodyList;
	public static final DataList<WazaData> wazaList;

	public static final String[] idoType;

	static {
		try {
			bodyList = new DataList<BodyData>(BODY_DIR, BODY_FILES);
			wazaList = new DataList<WazaData>(WAZA_DIR, WAZA_FILES);

			kind = MineUtils.readIdAndTextMap(TEXT_DIR + "kind.txt");
			soul = MineUtils.readIdAndTextMap(TEXT_DIR + "soul.txt");
			armorType = MineUtils.readIdAndTextMap(TEXT_DIR + "armor.txt");
			weponType = MineUtils.readIdAndTextMap(TEXT_DIR + "wepon.txt");
			moveType = MineUtils.readIdAndTextMap(TEXT_DIR + "move.txt");
			color = MineUtils.readIdAndTextMap(TEXT_DIR + "color.txt");
			targetType = MineUtils.readIdAndTextMap(TEXT_DIR + "target.txt");
			damageType = MineUtils.readIdAndTextMap(TEXT_DIR + "damage.txt");
			animeRange = MineUtils
					.readIdAndTextMap(TEXT_DIR + "animeRange.txt");
			idoType = MineUtils.readStringArray(TEXT_DIR + "move.txt");
			tokusei = MineUtils.readIdAndTextMap(TEXT_DIR + "tokusei.txt");
			effect = MineUtils.readIdAndTextMap(TEXT_DIR + "effect.txt");
			deployType = MineUtils
					.readIdAndTextMap(TEXT_DIR + "deployType.txt");

		} catch (MineException e) {
			throw new RuntimeException(e);
		}
	}

	public static int getBukiType(int type) {
		switch (type) {
		case 1:
		case 2:
		case 3:
			return 1;
		case 4:
		case 5:
		case 6:
			return 2;
		default:
			return 3;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Body> getEnemyData(int n) {
		try {
			String file = String.format("E%02d.txt", n);
			return (List<Body>) BeanIO.read(BODY_DIR + file);
		} catch (MineException e) {
			throw new RuntimeException(e);
		}
	}

	public static int[][] getMapData(int n) {
		try {
			String file = String.format("D%02d.txt", n);
			return MatrixIO.read(MAP_DIR + file);
		} catch (MineException e) {
			throw new RuntimeException(e);
		}
	}
}
