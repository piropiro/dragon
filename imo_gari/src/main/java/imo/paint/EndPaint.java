package imo.paint;

import imo.MainWorks;
import imo.common.ImageList;

import java.awt.event.KeyEvent;

import mine.paint.Colors;
import mine.paint.MineGraphics;
import mine.thread.Engine;

public class EndPaint implements PaintListener {

	private int count;

	private MainWorks mw;

	private ImageList imageList;

	/**
	 * Constructer
	 */
	public EndPaint(MainWorks mw, ImageList imageList) {
		this.mw = mw;
		this.imageList = imageList;
		count = 0;
	}

	public void paint(MineGraphics g) {
		int size = Math.min(300, count * 6);

		g.drawImage(imageList.getEndImage(), 0, 0, 300, size, 0, 0);

		if (count / 12 % 2 == 0) {
			g.setColor(Colors.BLACK);
			g.drawString("press  C  to close", 20, 280);
		}
	}

	public void keyReleased(char character, int keyCode) {
	}
	public void keyPressed(char character, int keyCode) {
		switch (keyCode) {
			case KeyEvent.VK_ESCAPE :
				mw.gameStart();
				break;
		}

		switch (character) {
			case 'c' :
				mw.gameExit();
				break;
		}
	}

	public void run() {
		Engine.sleep(30);
		mw.repaint();
		count++;
	}
}
