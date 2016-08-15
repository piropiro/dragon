package dragon3.stage;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dragon3.Statics;
import dragon3.controller.UnitWorks;
import dragon3.data.StageData;
import dragon3.image.ImageManager;
import dragon3.save.SaveData;
import lombok.Getter;
import lombok.Setter;
import mine.event.MouseAllListener;
import mine.event.PaintComponent;
import mine.event.PaintListener;
import mine.paint.MineGraphics;
import mine.paint.MineImage;
import mine.paint.UnitMap;
import mine.util.Point;

public class StageSelectPanel implements StageManager, MouseAllListener, PaintListener {

	public static final int UNIT_WIDTH = 106;
	public static final int UNIT_HEIGHT = 96;
	
	private static final int P_STAGE = 0;
	private static final int P_STATUS = 1;
	private static final int P_WAKU = 2;
	
	private PaintComponent panel;
	
	@Setter UnitWorks uw;

	private ImageManager imageManager;
	
	
	private int wx, wy, wxs, wys;

	private List<StageData> stageList;
	
	private UnitMap selectMap;
	
	@Getter private StageData selectedStage;

	/*** Constructer *****************************************************/

	@Inject
	public StageSelectPanel(@Named("stageSelectC") PaintComponent panel, ImageManager imageManager, Statics statics) {
		super();
		this.panel = panel;
		this.imageManager = imageManager;
		this.stageList = statics.getStageList();

		int[][] stageMapData  = statics.getStageMapData();
		MineImage[] stageImageList = imageManager.loadStageImageList(stageList);

		selectMap = new UnitMap(3, stageMapData[0].length, stageMapData.length, imageManager.getImageLoader());
		selectMap.setTile(P_STAGE, stageImageList, -1);
		selectMap.setTile(P_STATUS, imageManager.createStageStatusImageList(stageList, new SaveData()), -1);
		selectMap.setTile(P_WAKU, imageManager.getStageWaku(), 0);
		selectMap.setPage(P_STAGE, stageMapData);
		selectMap.setPage(P_STATUS, stageMapData);
		selectMap.setVisible(P_STAGE, true);
		selectMap.setVisible(P_STATUS, true);
		selectMap.setVisible(P_WAKU, true);
		
		panel.setPaintListener(this);
	}
	
	public void updateStageStatus(SaveData saveData) {
		openStage(saveData);
		selectMap.setTile(P_STATUS, imageManager.createStageStatusImageList(stageList, saveData), -1);
		selectMap.repaint();
	}
	
	public void openStage(SaveData saveData) {
		for (int i = 0 ; i < stageList.size(); i++) {
			String stageId = stageList.get(i).getId();
			
			if (saveData.getStarNum(stageId) > 0) {
				Point p = selectMap.searchData(P_STAGE, i);

				openStage(saveData, p.x, p.y);
				openStage(saveData, p.x - 1, p.y);
				openStage(saveData, p.x + 1, p.y);
				openStage(saveData, p.x, p.y - 1);
				openStage(saveData, p.x, p.y + 1);
			}
		}
	}
	
	private void openStage(SaveData saveData, int x, int y) {
		int index = selectMap.getData(P_STAGE, x, y);
		if (index != UnitMap.FALSE) {
			String stageId = stageList.get(index).getId();
			saveData.setOpened(stageId, true);
		}
	}
		
	@Override
	public boolean isFinalStage() {
		return stageList.indexOf(selectedStage) == stageList.size() - 1;
	}


	/*** Get and Set Data ************************************************/
	
	private Point getWaku() {
		return new Point(wx, wy);
	}

	/*** Waku **************************************************************/

	private void wakuMove(int x, int y) {
		this.wx = x;
		this.wy = y;
//		Body b = uw.search(wx, wy);
//		if (b != null) {
//			el.setSelectBody(b);
//		} else {
//			el.setSelectPlace(x, y);
//		}
//		uw.getPanelManager().setHelpLocation(wx, wy);
	}

	public void wakuPaint(boolean flag) {
		selectMap.setData(P_WAKU, wxs, wys, 0);
		selectMap.setData(P_WAKU, wx, wy, 1);
		if (flag) {
			int x = Math.min(wx, wxs) * UNIT_WIDTH;
			int y = Math.min(wy, wys) * UNIT_HEIGHT;
			int xs = Math.abs(wx - wxs) * UNIT_WIDTH + UNIT_WIDTH;
			int ys = Math.abs(wy - wys) * UNIT_HEIGHT + UNIT_HEIGHT;
			panel.repaint(x, y, xs, ys);
		}
		wxs = wx;
		wys = wy;
	}

	public void setVisible(boolean flag) {
		panel.setVisible(flag);
	}

	/*** Paint *****************************************************/

	@Override
	public void paint(MineGraphics g) {
		selectMap.draw(g);
	}

	@Override
	public void leftPressed(int x, int y) {
		mouseMoved(x, y);
	}

	@Override
	public void rightPressed(int x, int y) {
	}

	@Override
	public void leftReleased(int x, int y) {
	}

	@Override
	public void rightReleased(int x, int y) {
	}

	@Override
	public void mouseMoved(int x, int y) {
		Point p = new Point(x / UNIT_WIDTH, y / UNIT_HEIGHT);
		Point ps = getWaku();
		if (p.x != ps.x || p.y != ps.y) {
			wakuMove(p.x, p.y);
			wakuPaint(true);
		}
	}


	@Override
	public void leftDragged(int x, int y) {
	}
	@Override
	public void rightDragged(int x, int y) {
	}
	@Override
	public void mouseEntered(int x, int y) {
	}
	
	@Override
	public void mouseExited(int x, int y) {
	}

	@Override
	public void accept() {
		int stageNum = selectMap.getData(P_STAGE, wx, wy);
		selectedStage = stageList.get(stageNum);
		uw.stageStart(selectedStage);
	}

	@Override
	public void cancel() {
		uw.campStart();
	}

}
