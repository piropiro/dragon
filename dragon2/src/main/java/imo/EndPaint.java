




package imo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import mine.Mine;

// Referenced classes of package imo:
//            PaintListener, Images, MainWorks

public class EndPaint implements PaintListener {

	EndPaint(MainWorks mainworks, int i) {
		mw = mainworks;
		type = i;
		trueFlag = i == 5;
		count = 0;
	}

	public void paint(Graphics g) {
		int i = Math.min(320, count * 6);
		if (trueFlag)
			g.drawImage(Images.endi, 0, 0, 300, i, 180, 0, 480, i, null);
		else
			g.drawImage(Images.endi, 0, 0, 300, i, 0, 0, 300, i, null);
		if ((count / 12) % 2 == 0)
			g.setColor(Color.black);
		else
			g.setColor(new Color(180, 240, 180));
		Mine.setAntialias(g, true);
		g.setFont(Mine.getFont(14));
		g.drawString("press  C  to close", 20, 290);
	}

	public void keyReleased(KeyEvent keyevent) {
	}

	public void keyPressed(KeyEvent keyevent) {
		switch (keyevent.getKeyCode()) {
		case 32: // ' '
		case 67: // 'C'
			mw.gameExit();
			break;

		case 27: // '\033'
		case 88: // 'X'
			mw.gameStart();
			break;
		}
	}

	public void run() {
		mw.sleep(30L);
		mw.repaint();
		count++;
	}

	MainWorks mw;
	private boolean trueFlag;
	private int count;
	private int type;
}
