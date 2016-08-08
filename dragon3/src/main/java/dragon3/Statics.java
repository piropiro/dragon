package dragon3;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dragon3.common.DataList;
import dragon3.data.BodyData;
import dragon3.data.DeployData;
import dragon3.data.StageData;
import dragon3.data.WazaData;
import lombok.Getter;
import mine.MineException;
import mine.io.JsonIO;

@Singleton
public class Statics {

	public static final List<String> STAGE_FILES = Arrays.asList("StageData.json");
	public static final List<String> WAZA_FILES = Arrays.asList("WazaData.json" );
	public static final List<String> BODY_FILES = Arrays.asList("CharaData.json", "ClassData.json", "WeponData.json", "ArmorData.json", "ItemData.json");

	public static final String WAZA_DIR = "dragon3/data/waza/";

	public static final String BODY_DIR = "dragon3/data/body/";
	public static final String DEPLOY_DIR = "dragon3/data/deploy/";
	public static final String STAGE_DIR = "dragon3/data/stage/";

	public static final String MAP_DIR = "dragon3/data/map/";
	public static final String TEXT_DIR = "dragon3/text/";

	public static final int TYPE_MAX = 100;


	@Getter private DataList<BodyData> bodyList;
	@Getter private DataList<WazaData> wazaList;
	private DataList<StageData> stageList;

	@Inject public Statics() {
		bodyList = new DataList<BodyData>(BODY_DIR, BODY_FILES, BodyData[].class);
		wazaList = new DataList<WazaData>(WAZA_DIR, WAZA_FILES, WazaData[].class);
		stageList = new DataList<StageData>(STAGE_DIR, STAGE_FILES, StageData[].class);
	}
	
	public List<DeployData> getDeployData(String stageId) {
		try {
			return Arrays.asList(JsonIO.read(DEPLOY_DIR + "deploy_" + stageId + ".json", DeployData[].class));
		} catch (MineException e) {
			throw new RuntimeException(e);
		}
	}
	
	public StageData getStageData(String stageId) {
		return stageList.getData(stageId);
	}

	public int[][] getMapData(String stageId) {
		try {
			return JsonIO.read(MAP_DIR + "map_" + stageId + ".json", int[][].class);
		} catch (MineException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int[][] getStageMapData() {
        try {
            int[][] stages = JsonIO.read(Statics.STAGE_DIR + "map_stage.json", int[][].class);
            return stages;
        } catch (MineException e) {
            throw new RuntimeException(e);
        }
	}
	
    /*** DataLoad ******************************/
 
    public int[][] getCampMap() {
        return getMapData("camp");
    }

    public int[][] getCollectionMap() {
        return getMapData("collection");
    }

    public int[][] getWazalistMap() {
        return getMapData("wazalist");
    }
    
    public WazaData getWazaData(String wazaId) {
    	return wazaList.getData(wazaId);
    }
    
    public BodyData getBodyData(String bodyId) {
    	return bodyList.getData(bodyId);
    }
    
    public List<StageData> getStageList() {
    	return stageList.getList();
    }
}
