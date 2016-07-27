package dragon2.panel;






import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import dragon2.UnitWorks;
import dragon2.Walk;
import dragon2.attack.FightManager;
import dragon2.common.Body;
import dragon2.map.Map;
import dragon2.paint.BasicPaint;
import dragon2.paint.BerserkPaint;
import dragon2.paint.ButtonPaint;
import dragon2.paint.ChangePaint;
import dragon2.paint.EndPaint;
import dragon2.paint.KakuseiPaint;
import dragon2.paint.PaintListener;
import dragon2.paint.WaitPaint;
import mine.JCanvas;
import mine.UnitMap;
import mine.util.Point;

public class UPanel extends JCanvas implements Map, MouseListener,
		MouseMotionListener, KeyListener {

	public UPanel(UnitWorks unitworks, UnitMap unitmap) {
		super(640, 480);
		uw = unitworks;
		V = unitmap;
	}

	public void setWalkPaint(Body body) {
		setPaintListener(new Walk(body));
	}

	public void setWaitPaint() {
		setPaintListener(new WaitPaint());
	}

	public void setBasicPaint() {
		setPaintListener(new BasicPaint());
	}

	public void setEndPaint(Body body) {
		setPaintListener(new EndPaint(body));
	}

	public void setFightManager(Body body) {
		setPaintListener(new WaitPaint());
		FightManager fightmanager = new FightManager(body);
		fightmanager.nextSelect();
	}

	public void setKakuseiPaint(Body body) {
		setPaintListener(new KakuseiPaint(body));
	}

	public void setBerserkPaint(Body body) {
		setPaintListener(new BerserkPaint(body));
	}

	public void setChangePaint(Body body, Body body1) {
		setPaintListener(new ChangePaint(body, body1));
	}

	public void setButtonPaint(Point point, PaintListener paintlistener, int i) {
		setPaintListener(new ButtonPaint(point, paintlistener, i));
	}

	public synchronized void setPaintListener(PaintListener paintlistener) {
		pl = paintlistener;
		uw.waitSlow();
	}

	public Point getWaku() {
		return new Point(wx, wy);
	}

	public Dimension getMapSize() {
		return new Dimension(20, 15);
	}

	public void wakuMove(Point point) {
		wx = point.x;
		wy = point.y;
		Body body = uw.search(wx, wy);
		if (body != null)
			pl.setSelectBody(body);
		else
			pl.setSelectPlace(point);
		uw.setHelpLocate(wx, wy);
	}

	public void wakuPaint(boolean flag) {
		V.S(4, 0, wxs, wys, 0);
		V.S(4, 0, wx, wy, 1);
		if (flag) {
			int i = Math.min(wx, wxs) * 32;
			int j = Math.min(wy, wys) * 32;
			int k = Math.abs(wx - wxs) * 32 + 32;
			int l = Math.abs(wy - wys) * 32 + 32;
			repaint(i, j, k, l);
		}
		wxs = wx;
		wys = wy;
	}

	public void ppaint(int i, int j) {
		repaint(i * 32, j * 32, 32, 32);
	}

	public void ppaint(int ai[]) {
		repaint(ai[0] * 32, ai[1] * 32, ai[2] * 32, ai[3] * 32);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		if (offi == null) {
			Dimension dimension = getSize();
			offi = createImage(dimension.width, dimension.height);
			offg = offi.getGraphics();
		}
		V.E(0, 0, 20, 15, true, offg);
		g.drawImage(offi, 0, 0, null);
	}

	public void keyPressed(KeyEvent keyevent) {
		keyAction(keyevent.getKeyCode(), true);
	}

	public void keyReleased(KeyEvent keyevent) {
		keyAction(keyevent.getKeyCode(), false);
	}

	public void keyTyped(KeyEvent keyevent) {
	}

	private void keyAction(int i, boolean flag) {
		switch (i) {
		default:
			break;

		case 27: // '\033'
		case 88: // 'X'
			if (flag) {
				if (right)
					return;
				right = true;
				pl.rightPressed();
			} else {
				pl.rightReleased();
				right = false;
			}
			return;

		case 10: // '\n'
		case 90: // 'Z'
			if (flag) {
				if (left)
					return;
				left = true;
				pl.leftPressed();
			} else {
				pl.leftReleased();
				left = false;
			}
			return;

		case 32: // ' '
		case 67: // 'C'
			if (flag)
				keyNext();
			return;

		case 38: // '&'
			K_U = flag;
			break;

		case 40: // '('
			K_D = flag;
			break;

		case 37: // '%'
			K_L = flag;
			break;

		case 39: // '\''
			K_R = flag;
			break;
		}
		keyMove();
	}

	private void keyMove() {
		Point point = getWaku();
		if (K_U)
			point.y = Math.max(0, point.y - 1);
		if (K_D)
			point.y = Math.min(14, point.y + 1);
		if (K_L)
			point.x = Math.max(0, point.x - 1);
		if (K_R)
			point.x = Math.min(19, point.x + 1);
		pl.mouseMoved(point);
	}

	private void keyNext() {
		Point point = getWaku();
		Point point1 = new Point(point.x, point.y);
		Dimension dimension = V.getSize();
		do {
			do {
				while (++point1.x < dimension.width) {
					if (pl.isNextPoint(point1)) {
						pl.mouseMoved(point1);
						return;
					}
					if (point.x == point1.x && point.y == point1.y)
						return;
				}
				point1.x = -1;
			} while (++point1.y < dimension.height);
			point1.y = 0;
		} while (true);
	}

	public void mousePressed(MouseEvent mouseevent) {
		if (mouseLock)
			return;
		mouseMoved(mouseevent);
		mouseLock = true;
		switch (mouseevent.getModifiers()) {
		case 16: // '\020'
			pl.leftPressed();
			break;

		case 4: // '\004'
			pl.rightPressed();
			break;
		}
		mouseLock = false;
	}

	public void mouseReleased(MouseEvent mouseevent) {
		switch (mouseevent.getModifiers()) {
		case 16: // '\020'
			pl.leftReleased();
			break;

		case 4: // '\004'
			pl.rightReleased();
			break;
		}
	}

	public void mouseClicked(MouseEvent mouseevent) {
	}

	public void mouseEntered(MouseEvent mouseevent) {
	}

	public void mouseExited(MouseEvent mouseevent) {
	}

	public void mouseMoved(MouseEvent mouseevent) {
		if (mouseLock)
			return;
		mouseLock = true;
		Point point = new Point(mouseevent.getX() / 32, mouseevent.getY() / 32);
		Point point1 = getWaku();
		if (point.x != point1.x || point.y != point1.y)
			pl.mouseMoved(point);
		mouseLock = false;
	}

	public void mouseDragged(MouseEvent mouseevent) {
	}

	private UnitWorks uw;
	private PaintListener pl;
	private Image offi;
	private Graphics offg;
	static boolean K_U;
	static boolean K_D;
	static boolean K_L;
	static boolean K_R;
	static boolean left;
	static boolean right;
	static boolean mouseLock;
	private int wx;
	private int wy;
	private int wxs;
	private int wys;
	static final int dx = 20;
	static final int dy = 15;
	UnitMap V;
}
