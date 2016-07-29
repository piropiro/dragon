package dragon3.stage;

import dragon3.data.StageData;

public interface StageManager {

	public boolean isTutorial();
	
	public boolean isFinalStage();
	
	public StageData getSelectedStage();
}
