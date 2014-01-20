package dragon3.map;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;

import mine.awt.GraphicsAWT;
import mine.awt.MineAwtUtils;
import mine.event.MouseAllListener;
import mine.paint.MineGraphics;
import mine.paint.UnitMap;
import dragon3.UnitWorks;
import dragon3.common.Body;
import dragon3.common.constant.Page;
import dragon3.paint.PaintListener;

public class MapPanel extends JComponent implements MapWorks, MouseAllListener {

	private static final long serialVersionUID = 1L;
	private UnitWorks uw;
	private PaintListener pl;

	private int wx, wy, wxs, wys;

	private UnitMap map;

	/*** Constructer *****************************************************/

	public MapPanel(UnitWorks uw) {
		super();
		this.uw = uw;
		this.map = uw.getUnitMap();
		MineAwtUtils.setSize(this, 640, 480);

		MapKeyManager mkm = new MapKeyManager(this);
		addKeyListener(mkm);
	}

	/*** Listener ************************************************/

	public synchronized void setPaintListener(PaintListener pl) {
		this.pl = pl;
	}

	public PaintListener getPaintListener() {
		return pl;
	}

	/*** Get and Set Data ************************************************/

	public Point getWaku() {
		return new Point(wx, wy);
	}

	/*** Waku **************************************************************/

	public void wakuMove(int x, int y) {
		this.wx = x;
		this.wy = y;
		Body b = uw.search(wx, wy);
		if (b != null) {
			pl.setSelectBody(b);
		} else {
			pl.setSelectPlace(x, y);
		}
		uw.getPanelManager().setHelpLocation(wx, wy);
	}

	public void wakuPaint(boolean flag) {
		map.setData(Page.P40, wxs, wys, 0);
		map.setData(Page.P40, wx, wy, 1);
		if (flag) {
			int x = Math.min(wx, wxs) * 32;
			int y = Math.min(wy, wys) * 32;
			int xs = Math.abs(wx - wxs) * 32 + 32;
			int ys = Math.abs(wy - wys) * 32 + 32;
			repaint(x, y, xs, ys);
		}
		wxs = wx;
		wys = wy;
	}

	/*** Paint ************************************************/

	public void ppaint(int px, int py) {
		repaint(px * 32, py * 32, 32, 32);
	}

	public void ppaint(int[] box) {
		repaint(box[0] * 32, box[1] * 32, box[2] * 32, box[3] * 32);
	}

	/*** Paint *****************************************************/

	public void paintComponent(Graphics g) {
		MineGraphics mg = new GraphicsAWT(g);
		map.draw(mg);
	}

	/* (非 Javadoc)
	 * @see mine.event.MouseAllListener#leftPressed(int, int)
	 */
	public void leftPressed(int x, int y) {
		mouseMoved(x, y);
		pl.leftPressed();
	}

	/* (非 Javadoc)
	 * @see mine.event.MouseAllListener#rightPressed(int, int)
	 */
	public void rightPressed(int x, int y) {
		mouseMoved(x, y);
		pl.rightPressed();
	}

	public void leftReleased(int x, int y) {
		pl.leftReleased();
	}

	public void rightReleased(int x, int y) {
		pl.rightReleased();
	}

	public void mouseMoved(int x, int y) {
		Point p = new Point(x / 32, y / 32);
		Point ps = getWaku();
		if (p.x != ps.x || p.y != ps.y) {
			pl.mouseMoved(p.x, p.y);
		}
	}


	public void leftDragged(int x, int y) {
	}
	public void rightDragged(int x, int y) {
	}
	public void mouseEntered(int x, int y) {
	}
	public void mouseExited(int x, int y) {
	}

}
