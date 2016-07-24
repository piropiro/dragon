




package imo;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import mine.RunCanvas;

// Referenced classes of package imo:
//            PaintListener, MainWorks

public class UnitPanel extends RunCanvas implements KeyListener {

	UnitPanel(MainWorks mainworks) {
		super(300, 300);
		mw = mainworks;
		addKeyListener(this);
	}

	public void setPaintListener(PaintListener paintlistener) {
		pl = paintlistener;
	}

	public void paint(Graphics g) {
		pl.paint(g);
		requestFocus();
	}

	public void keyTyped(KeyEvent keyevent) {
	}

	public void keyReleased(KeyEvent keyevent) {
		pl.keyReleased(keyevent);
	}

	public void keyPressed(KeyEvent keyevent) {
		pl.keyPressed(keyevent);
	}

	public void run() {
		pl.run();
	}

	private MainWorks mw;
	private PaintListener pl;
}
