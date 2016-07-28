package dragon3.panel;

import dragon3.common.constant.GameColor;
import mine.event.PaintComponent;
import mine.event.PaintListener;
import mine.paint.MineColor;
import mine.paint.MineGraphics;

public class HelpPanel implements PaintListener {

	public static final int WIDTH = 288;
	public static final int HEIGHT = 48;
	
	private PaintComponent panel;

	private String[] line;
	private boolean leftf;
	private boolean upf;
	private GameColor bgcolor = GameColor.BLUE;

	public HelpPanel(PaintComponent panel) {
		super();
		this.panel = panel;
		panel.setVisible(false);
		panel.setFontSize(14);
		//setBackground(new Color(0, 0, 150, 200));
		panel.setPaintListener(this);
	}

	public void setLine(String[] line, GameColor bgcolor) {
		this.line = line;
		this.bgcolor = bgcolor;
	}

	/*** Locate ***********************************************/

	public void setLocate(int x, int y, boolean flag) {

		boolean leftfs = leftf;
		boolean upfs = upf;
		if (flag) {
			leftf = (x > 5);
			upf = (y > 5);
		} else {
			if (leftf) {
				if (x < 4)
					leftf = false;
			} else {
				if (x > 15)
					leftf = true;
			}
			if (upf) {
				if (y < 4)
					upf = false;
			} else {
				if (y > 10)
					upf = true;
			}
		}

		if (flag || leftf != leftfs || upf != upfs) {
			int mx = (leftf) ? 16 : (20 * 32 - WIDTH - 16);
			int my = (upf) ? 8 : (15 * 32 - HEIGHT - 8);
			panel.setLocation(mx, my);
		}
	}

	/*** Paint ******************************************/

	@Override
	public void paint(MineGraphics g) {
		g.setFont("Dialog", 14);
		clear(bgcolor, g);

		if (line == null)
			return;
		for (int i = 0; i < line.length; i++) {
			g.drawString(line[i], 10, 19 + 19 * i);
		}
	}

	/*** Clear *********************************************/

	public boolean clear(GameColor color, MineGraphics g) {
		g.setColor(color.getAlphaBg());
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(MineColor.WHITE);
		g.drawRect(2, 2, WIDTH - 5, HEIGHT - 5);
		return true;
	}
	
	public void setVisible(boolean flag) {
		panel.setVisible(flag);
	}
	
	public void repaint() {
		panel.repaint();
	}
}
