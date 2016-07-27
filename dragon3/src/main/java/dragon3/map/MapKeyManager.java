/*
 * 作成日: 2004/03/28
 */
package dragon3.map;

import mine.util.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import dragon3.paint.EventListener;

/**
 * @author k-saito
 */
public class MapKeyManager implements KeyListener {

	private MapWorks mw;
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;

	public MapKeyManager(MapWorks mw) {
		this.mw = mw;
	}

	/*** Key *********************************/

	public void keyPressed(KeyEvent e) {
		keyAction(e.getKeyCode(), true);
	}

	public void keyReleased(KeyEvent e) {
		keyAction(e.getKeyCode(), false);
	}

	public void keyTyped(KeyEvent e) {
	}

	/*** Move Key ***********************************/

	private void keyAction(int key, boolean flag) {
		switch (key) {
			case KeyEvent.VK_ESCAPE :
			case KeyEvent.VK_X :
				if (flag) {
					if (right)
						return;
					right = true;
					mw.getEventListener().rightPressed();
				} else {
					mw.getEventListener().rightReleased();
					right = false;
				}
				return;
			case KeyEvent.VK_ENTER :
			case KeyEvent.VK_Z :
				if (flag) {
					if (left)
						return;
					left = true;
					mw.getEventListener().leftPressed();
				} else {
					mw.getEventListener().leftReleased();
					left = false;
				}
				return;
			case KeyEvent.VK_SPACE :
			case KeyEvent.VK_C :
				if (flag)
					keyNext();
				return;
			case KeyEvent.VK_UP :
				up = flag;
				break;
			case KeyEvent.VK_DOWN :
				down = flag;
				break;
			case KeyEvent.VK_LEFT :
				left = flag;
				break;
			case KeyEvent.VK_RIGHT :
				right = flag;
				break;
		}
		keyMove();
	}

	/*** Move *************************/

	private void keyMove() {
		Point p = mw.getWaku();
		if (up)
			p.y = Math.max(0, p.y - 1);
		if (down)
			p.y = Math.min(14, p.y + 1);
		if (left)
			p.x = Math.max(0, p.x - 1);
		if (right)
			p.x = Math.min(19, p.x + 1);
		mw.getEventListener().mouseMoved(p.x, p.y);
	}

	/*** Next *************************************/

	private void keyNext() {
		Point p = mw.getWaku();
		Point n = new Point(p.x, p.y);

		int width = 20;
		int height = 15;

		EventListener pl = mw.getEventListener();

		while (true) {
			do {
				while (++n.x < width) {
					if (pl.isNextPoint(n.x, n.y)) {
						pl.mouseMoved(n.x, n.y);
						return;
					}
					if (p.x == n.x && p.y == n.y)
						return;
				}
				n.x = -1;
			}
			while (++n.y < height);
			n.y = 0;
		}
	}

}
