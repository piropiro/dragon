




package mine;

import java.awt.Component;
import java.awt.event.*;

public class MouseManager implements MouseListener, MouseMotionListener {

	public MouseManager() {
		reset();
	}

	public MouseManager(Component component) {
		component.addMouseListener(this);
		component.addMouseMotionListener(this);
	}

	public void reset() {
		mX = 0;
		mY = 0;
		M_LEFT = false;
		M_RIGHT = false;
	}

	public int getX() {
		return mX;
	}

	public int getY() {
		return mY;
	}

	public boolean isLeft() {
		return M_LEFT;
	}

	public boolean isRight() {
		return M_RIGHT;
	}

	public void mouseClicked(MouseEvent mouseevent) {
	}

	public void mouseEntered(MouseEvent mouseevent) {
	}

	public void mouseExited(MouseEvent mouseevent) {
	}

	public void mouseMoved(MouseEvent mouseevent) {
		mX = mouseevent.getX();
		mY = mouseevent.getY();
		mouseMoveds(mouseevent);
	}

	public void mouseReleased(MouseEvent mouseevent) {
		switch (mouseevent.getModifiers()) {
		case 16: // '\020'
			M_LEFT = false;
			leftReleased(mouseevent);
			break;

		case 4: // '\004'
			M_RIGHT = false;
			rightReleased(mouseevent);
			break;

		default:
			if (M_LEFT)
				leftReleased(mouseevent);
			if (M_RIGHT)
				rightReleased(mouseevent);
			M_LEFT = false;
			M_RIGHT = false;
			break;
		}
	}

	public void mousePressed(MouseEvent mouseevent) {
		switch (mouseevent.getModifiers()) {
		case 16: // '\020'
			M_LEFT = true;
			leftPressed(mouseevent);
			break;

		case 4: // '\004'
			M_RIGHT = true;
			rightPressed(mouseevent);
			break;

		default:
			if (M_LEFT)
				rightPressed(mouseevent);
			if (M_RIGHT)
				leftPressed(mouseevent);
			M_LEFT = true;
			M_RIGHT = true;
			break;
		}
	}

	public void mouseDragged(MouseEvent mouseevent) {
		mX = mouseevent.getX();
		mY = mouseevent.getY();
		switch (mouseevent.getModifiers()) {
		case 16: // '\020'
			leftDragged(mouseevent);
			break;

		case 4: // '\004'
			rightDragged(mouseevent);
			break;
		}
	}

	public void mouseMoveds(MouseEvent mouseevent) {
	}

	public void leftDragged(MouseEvent mouseevent) {
	}

	public void rightDragged(MouseEvent mouseevent) {
	}

	public void leftPressed(MouseEvent mouseevent) {
	}

	public void rightPressed(MouseEvent mouseevent) {
	}

	public void leftReleased(MouseEvent mouseevent) {
	}

	public void rightReleased(MouseEvent mouseevent) {
	}

	private int mX;
	private int mY;
	private boolean M_LEFT;
	private boolean M_RIGHT;
}
