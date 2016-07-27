package dragon3.map;

import mine.util.Point;

import dragon3.FrameWorks;
import dragon3.UnitWorks;
import dragon3.common.Body;
import dragon3.common.constant.Page;
import dragon3.paint.EventListener;
import mine.event.MouseAllListener;
import mine.event.PaintComponent;
import mine.event.PaintListener;
import mine.paint.MineGraphics;
import mine.paint.UnitMap;

public class MapPanel implements MapWorks, MouseAllListener, PaintListener {

	private UnitWorks uw;
	private FrameWorks fw;
	
	private EventListener el;

	private int wx, wy, wxs, wys;

	private UnitMap map;

	/*** Constructer *****************************************************/

	public MapPanel(PaintComponent panel, UnitWorks uw, FrameWorks fw) {
		super();
		this.uw = uw;
		this.fw = fw;
		this.map = uw.getUnitMap();
//		MineAwtUtils.setSize(this, 640, 480);
//
//		MapKeyManager mkm = new MapKeyManager(this);
//		addKeyListener(mkm);
		panel.setPaintListener(this);
	}

	/*** Listener ************************************************/
	@Override
	public synchronized void setEventListener(EventListener el) {
		System.out.println(el.getClass());
		this.el = el;
	}
	@Override
	public EventListener getEventListener() {
		return el;
	}

	/*** Get and Set Data ************************************************/
	@Override
	public Point getWaku() {
		return new Point(wx, wy);
	}

	/*** Waku **************************************************************/
	@Override
	public void wakuMove(int x, int y) {
		this.wx = x;
		this.wy = y;
		Body b = uw.search(wx, wy);
		if (b != null) {
			el.setSelectBody(b);
		} else {
			el.setSelectPlace(x, y);
		}
		uw.getPanelManager().setHelpLocation(wx, wy);
	}
	@Override
	public void wakuPaint(boolean flag) {
		map.setData(Page.P40, wxs, wys, 0);
		map.setData(Page.P40, wx, wy, 1);
		if (flag) {
			int x = Math.min(wx, wxs) * 32;
			int y = Math.min(wy, wys) * 32;
			int xs = Math.abs(wx - wxs) * 32 + 32;
			int ys = Math.abs(wy - wys) * 32 + 32;
			fw.getMapPanel().repaint(x, y, xs, ys);
		}
		wxs = wx;
		wys = wy;
	}

	/*** Paint ************************************************/

	@Override
	public void ppaint(int px, int py) {
		fw.getMapPanel().repaint(px * 32, py * 32, 32, 32);
	}
	@Override
	public void ppaint(int[] box) {
		fw.getMapPanel().repaint(box[0] * 32, box[1] * 32, box[2] * 32, box[3] * 32);
	}
	@Override
	public void repaint() {
		fw.getMapPanel().repaint();
	}

	/*** Paint *****************************************************/

	@Override
	public void paint(MineGraphics g) {
		map.draw(g);
	}

	@Override
	public void leftPressed(int x, int y) {
		mouseMoved(x, y);
		el.leftPressed();
	}

	@Override
	public void rightPressed(int x, int y) {
		mouseMoved(x, y);
		el.rightPressed();
	}

	@Override
	public void leftReleased(int x, int y) {
		el.leftReleased();
	}

	@Override
	public void rightReleased(int x, int y) {
		el.rightReleased();
	}

	@Override
	public void mouseMoved(int x, int y) {
		Point p = new Point(x / 32, y / 32);
		Point ps = getWaku();
		if (p.x != ps.x || p.y != ps.y) {
			el.mouseMoved(p.x, p.y);
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
