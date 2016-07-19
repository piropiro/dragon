package dragon3;

import java.util.Arrays;
import java.util.List;

import dragon3.bean.BodyData;
import dragon3.bean.DeployData;
import dragon3.bean.StageData;
import dragon3.bean.WazaData;
import dragon3.common.DataList;
import mine.MineException;
import mine.io.JsonIO;
import mine.io.MatrixIO;

public class Statics {

	public static final String[] STAGE_FILES = { "StageData.json" };
	public static final String[] WAZA_FILES =  { "WazaData.json" };
	public static final String[] BODY_FILES =  { "BodyData.json" };

	public static final String WAZA_DIR = "dragon3/data/waza/";

	public static final String BODY_DIR = "dragon3/data/body/";
	public static final String DEPLOY_DIR = "dragon3/data/deploy/";
	public static final String STAGE_DIR = "dragon3/data/stage/";

	public static final String MAP_DIR = "dragon3/data/map/";
	public static final String TEXT_DIR = "dragon3/text/";

	public static final int TYPE_MAX = 100;


	public static final DataList<BodyData> bodyList = new DataList<BodyData>(BODY_DIR, BODY_FILES, BodyData[].class);
	public static final DataList<WazaData> wazaList = new DataList<WazaData>(WAZA_DIR, WAZA_FILES, WazaData[].class);
	public static final DataList<StageData> stageList = new DataList<StageData>(STAGE_DIR, STAGE_FILES, StageData[].class);


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


	
	public static List<DeployData> getDeployData(String stageId) {
		try {
			return Arrays.asList(JsonIO.read(DEPLOY_DIR + stageId + ".json", DeployData[].class));
		} catch (MineException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static StageData getStageData(String stageId) {
		return stageList.getData(stageId);
	}

	public static int[][] getMapData(String stageId) {
		try {
			return MatrixIO.read(MAP_DIR + stageId + ".txt");
		} catch (MineException e) {
			throw new RuntimeException(e);
		}
	}
}
