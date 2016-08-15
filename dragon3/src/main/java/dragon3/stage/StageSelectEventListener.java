package dragon3.stage;

import dragon3.controller.UnitWorks;
import mine.event.MouseAllListener;
import mine.util.Point;

public class StageSelectEventListener implements MouseAllListener {

	public static final int UNIT_WIDTH = 106;
	public static final int UNIT_HEIGHT = 96;
	
	private int wx, wy;
	
	private StageManager stageManager;
	
	private UnitWorks uw;
	
	private Point getWaku() {
		return new Point(wx, wy);
	}
	
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
			stageManager.wakuPaint(true);
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
		stageManager.selectStage(wx, wy);	
		uw.stageStart(stageManager.getSelectedStage());
	}

	@Override
	public void cancel() {
		uw.campStart();
	}
}
