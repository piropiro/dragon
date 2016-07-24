




package mine;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	public KeyManager() {
		reset();
	}

	public KeyManager(Component component) {
		this();
		component.addKeyListener(this);
	}

	public void reset() {
		keys = new boolean[256];
		K_UP = false;
		K_DOWN = false;
		K_LEFT = false;
		K_RIGHT = false;
		K_ENTER = false;
		K_ESCAPE = false;
		K_SHIFT = false;
		K_SPACE = false;
	}

	public boolean isEnter() {
		return K_ENTER;
	}

	public boolean isEscape() {
		return K_ESCAPE;
	}

	public boolean isShift() {
		return K_SHIFT;
	}

	public boolean isSpace() {
		return K_SPACE;
	}

	public boolean isUp() {
		return K_UP;
	}

	public boolean isDown() {
		return K_DOWN;
	}

	public boolean isLeft() {
		return K_LEFT;
	}

	public boolean isRight() {
		return K_RIGHT;
	}

	public boolean isNeutral() {
		if (K_UP)
			return false;
		if (K_DOWN)
			return false;
		if (K_LEFT)
			return false;
		return !K_RIGHT;
	}

	public boolean isKey(char c) {
		char c1 = c;
		if (c1 < '\u0100')
			return keys[c1];
		else
			return false;
	}

	public void keyTyped(KeyEvent keyevent) {
	}

	public void keyPressed(KeyEvent keyevent) {
		setKey(keyevent, true);
	}

	public void keyReleased(KeyEvent keyevent) {
		setKey(keyevent, false);
	}

	private void setKey(KeyEvent keyevent, boolean flag) {
		char c = keyevent.getKeyChar();
		if (c < '\u0100')
			keys[c] = flag;
		switch (keyevent.getKeyCode()) {
		case 10: // '\n'
			K_ENTER = flag;
			break;

		case 27: // '\033'
			K_ESCAPE = flag;
			break;

		case 16: // '\020'
			K_SHIFT = flag;
			break;

		case 32: // ' '
			K_SPACE = flag;
			break;

		case 38: // '&'
			K_UP = flag;
			break;

		case 40: // '('
			K_DOWN = flag;
			break;

		case 37: // '%'
			K_LEFT = flag;
			break;

		case 39: // '\''
			K_RIGHT = flag;
			break;
		}
	}

	private boolean keys[];
	private boolean K_UP;
	private boolean K_DOWN;
	private boolean K_LEFT;
	private boolean K_RIGHT;
	private boolean K_ENTER;
	private boolean K_ESCAPE;
	private boolean K_SHIFT;
	private boolean K_SPACE;
}
