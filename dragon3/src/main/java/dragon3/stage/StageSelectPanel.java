package dragon3.stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dragon3.Statics;
import dragon3.UnitWorks;
import dragon3.bean.StageData;
import dragon3.image.ImageManager;
import lombok.Getter;
import mine.event.MouseAllListener;
import mine.event.PaintComponent;
import mine.event.PaintListener;
import mine.paint.MineGraphics;
import mine.paint.MineImage;
import mine.paint.UnitMap;
import mine.util.Point;

public class StageSelectPanel implements StageManager, MouseAllListener, PaintListener {

	private static final int UNIT_WIDTH = 128;
	private static final int UNIT_HEIGHT = 96;
	
	private static final int P_STAGE = 0;
	private static final int P_STATUS = 1;
	private static final int P_WAKU = 2;
	
	private PaintComponent panel;
	
	private UnitWorks uw;

	private ImageManager imageManager;
	
	private int wx, wy, wxs, wys;

	private List<StageData> stageList;
	
	private UnitMap stageMap;
	
	@Getter private StageData selectedStage;

	/*** Constructer *****************************************************/

	public StageSelectPanel(PaintComponent panel, UnitWorks uw, List<StageData> stageList, ImageManager imageManager) {
		super();
		this.panel = panel;
		this.uw = uw;
		this.stageList = stageList;
		this.imageManager = imageManager;

		int[][] stageMapData  = Statics.getStageMapData();
		MineImage[] stageImageList = imageManager.loadStageImageList(stageList);

		stageMap = new UnitMap(3, 5, 5, imageManager.getImageLoader());
		stageMap.setTile(P_STAGE, stageImageList, -1);
		stageMap.setTile(P_STATUS, imageManager.createStageStatusImageList(stageList, new HashMap<>()), -1);
		stageMap.setTile(P_WAKU, imageManager.getStageWaku(), 0);
		stageMap.setPage(P_STAGE, stageMapData);
		stageMap.setPage(P_STATUS, stageMapData);
		stageMap.setVisible(P_STAGE, true);
		stageMap.setVisible(P_STATUS, true);
		stageMap.setVisible(P_WAKU, true);
		
		panel.setPaintListener(this);
		panel.setMouseAllListener(this);
	}
	
	public void updateStageStatus(Map<String, Integer> starList) {
		stageMap.setTile(P_STATUS, imageManager.createStageStatusImageList(stageList, starList), -1);
	}
	
	@Override
	public boolean isTutorial() {
		return stageList.indexOf(selectedStage.getId()) == 0;
	}
	
	@Override
	public boolean isFinalStage() {
		return stageList.indexOf(selectedStage.getId()) == stageList.size() - 1;
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
		stageMap.setData(P_WAKU, wxs, wys, 0);
		stageMap.setData(P_WAKU, wx, wy, 1);
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
		stageMap.draw(g);
	}

	@Override
	public void leftPressed(int x, int y) {
		mouseMoved(x, y);
		int stageNum = wx + wy * 5;
		selectedStage = stageList.get(stageNum);
		uw.stageStart(selectedStage);
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

}
