




package imo;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

interface PaintListener {

	public abstract void keyPressed(KeyEvent keyevent);

	public abstract void keyReleased(KeyEvent keyevent);

	public abstract void paint(Graphics g);

	public abstract void run();
}
