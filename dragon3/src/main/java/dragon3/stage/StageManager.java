package dragon3.stage;

import dragon3.controller.UnitWorks;
import dragon3.data.StageData;

public interface StageManager {
	
	public boolean isFinalStage();
	
	public StageData getSelectedStage();
	
	public void setUw(UnitWorks uw);
}
