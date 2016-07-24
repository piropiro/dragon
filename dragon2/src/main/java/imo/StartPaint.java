




package imo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import mine.Mine;

// Referenced classes of package imo:
//            PaintListener, MainWorks

public class StartPaint implements PaintListener {

	StartPaint(MainWorks mainworks) {
		mw = mainworks;
		count = 0;
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.setFont(Mine.getFont(14));
		g.drawString("\u64CD\u4F5C\u65B9\u6CD5", 200, 240);
		g.drawString("\u653B\u6483 : Z", 200, 260);
		g.drawString("\u79FB\u52D5 : \u2191\u2190\u2193\u2192", 200, 280);
		Mine.setAntialias(g, true);
		if ((count / 12) % 2 == 0)
			g.setColor(Color.black);
		else
			g.setColor(new Color(180, 240, 180));
		g.drawString("press  Z  to start", 100, 140);
	}

	public void run() {
		mw.sleep(30L);
		mw.repaint();
		count++;
	}

	public void keyReleased(KeyEvent keyevent) {
	}

	public void keyPressed(KeyEvent keyevent) {
		switch (keyevent.getKeyCode()) {
		case 10: // '\n'
		case 90: // 'Z'
			mw.gameStart();
			break;
		}
	}

	private MainWorks mw;
	private int count;
}
