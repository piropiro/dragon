package dragon3.stage;

import java.util.List;

import dragon3.Statics;
import dragon3.UnitWorks;
import dragon3.bean.StageData;
import dragon3.image.ImageManager;
import mine.event.MouseAllListener;
import mine.event.PaintComponent;
import mine.event.PaintListener;
import mine.paint.MineGraphics;
import mine.paint.MineImage;
import mine.paint.UnitMap;
import mine.util.Point;

public class StageSelectPanel implements MouseAllListener, PaintListener {

	private static final int UNIT_WIDTH = 128;
	private static final int UNIT_HEIGHT = 96;
	
	private PaintComponent panel;
	
	private UnitWorks uw;

	private int wx, wy, wxs, wys;

	private List<StageData> stageList;
	
	private UnitMap stageMap;

	/*** Constructer *****************************************************/

	public StageSelectPanel(PaintComponent panel, UnitWorks uw, List<StageData> stageList, ImageManager im) {
		super();
		this.panel = panel;
		this.uw = uw;
		this.stageList = stageList;

		int[][] stageMapData  = Statics.getStageMapData();
		MineImage[] stageImageList = im.loadStageImageList(stageList);

		stageMap = new UnitMap(2, 5, 5, im.getImageLoader());
		stageMap.setTile(0, stageImageList, -1);
		stageMap.setTile(1, im.getStageWaku(), 0);
		stageMap.setPage(0, stageMapData);
		stageMap.setVisible(0, true);
		stageMap.setVisible(1, true);
		
		panel.setPaintListener(this);
		panel.setMouseAllListener(this);
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
		stageMap.setData(1, wxs, wys, 0);
		stageMap.setData(1, wx, wy, 1);
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
		uw.stageStart(stageList.get(stageNum));
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
