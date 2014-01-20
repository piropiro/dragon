package dragon3.panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import mine.awt.GraphicsAWT;
import mine.awt.MineAwtUtils;
import mine.event.SleepManager;
import mine.paint.MineGraphics;
import dragon3.common.Body;
import dragon3.image.ImageManager;

public class MessagePanel extends PanelBase {

	private static final long serialVersionUID = 1861839566390484906L;

	private Vector<String> list;
	private Body ba;
	private int n;
	private int line;

	static final int MAX = 3;

	public MessagePanel(SleepManager sm, ImageManager im) {
		super(sm, im, 160, 128, true);
		list = new Vector<String>();
		setFont(MineAwtUtils.getFont(14));
	}

	/*** Locate ***********************************************/

	public void setLocate() {
		Dimension m = getSize();
		int mx = 300 - m.width / 2;
		int my = 240 - m.height / 2;
		setLocation(mx, my);
	}

	/*** Text **************************************/

	public void addMessage(String message) {
		list.add(message);
	}

	/*** Display ******************************************************************/

	public void display(Body ba_) {
		this.ba = ba_;
		if (list.size() == 0)
			return;
		setHPBar(false, ba);
		setLocate();
		setVisible(true);

		for (int i = 0; i < list.size(); i++) {
			n = i;
			String text = (String) list.elementAt(n);
			for (line = 0; line <= text.length(); line++) {
				repaint();
				sleep(80);
			}
			sleep(200);
		}
		sleep(700);
		list.removeAllElements();
		setVisible(false);
	}

	/*** Paint **************************************************************/

	public void paintComponent(Graphics g) {
		g.setFont(getFont());
		MineGraphics mg = new GraphicsAWT(g);
		clear(ba.getColor(), mg);
		drawMain(ba, mg, true);

		int s = Math.max(0, n - MAX + 1);

		for (int i = s; i <= n; i++) {
			if (i >= list.size())
				break;
			String text = (String) list.elementAt(i);
			if (i == n) {
				drawLine(
					text.substring(0, Math.min(line, text.length())),
					0,
					i - s + 1,
					mg);
			} else {
				drawLine(text, 0, i - s + 1, mg);
			}
		}
	}

}
