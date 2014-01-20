package dragon3.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

import mine.awt.GraphicsAWT;
import mine.awt.MineAwtUtils;
import mine.paint.Colors;
import mine.paint.MineGraphics;
import dragon3.common.constant.GameColors;

public class HelpPanel extends JComponent {

	private static final long serialVersionUID = 7812782714043373429L;

	private String[] line;
	private boolean leftf;
	private boolean upf;
	private String color;

	public HelpPanel() {
		super();
		setVisible(false);
		MineAwtUtils.setSize(this, 288, 48);
		setFont(MineAwtUtils.getFont(14));
		setBackground(new Color(0, 0, 150, 200));
	}

	public void setLine(String[] line, String color) {
		this.line = line;
		this.color = color;
	}

	/*** Locate ***********************************************/

	public void setLocate(int x, int y, boolean flag) {
		Dimension d = new Dimension(20, 15);
		Dimension m = getSize();

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
			int mx = (leftf) ? 16 : (d.width * 32 - m.width - 16);
			int my = (upf) ? 8 : (d.height * 32 - m.height - 8);
			setLocation(mx, my);
		}
	}

	/*** Paint ******************************************/

	public void paintComponent(Graphics g) {
		g.setFont(getFont());
		clear(color, new GraphicsAWT(g));

		if (line == null)
			return;
		for (int i = 0; i < line.length; i++) {
			g.drawString(line[i], 10, 19 + 19 * i);
		}
	}

	/*** Image *************************************/

	protected void drawImage(Image img, int t, int x, int y, Graphics g) {
		int axs = x;
		int ays = y;
		int axf = axs + 32;
		int ayf = ays + 32;
		int bxs = t % 15 * 32;
		int bys = t / 15 * 32;
		int bxf = bxs + 32;
		int byf = bys + 32;
		g.drawImage(img, axs, ays, axf, ayf, bxs, bys, bxf, byf, null);
	}

	/*** Clear *********************************************/

	public boolean clear(String color_, MineGraphics g) {
		Dimension d = getSize();
		g.setColor(GameColors.getColor(color_));
		g.fillRect(0, 0, d.width, d.height);
		g.setColor(Colors.WHITE);
		g.drawRect(2, 2, d.width - 5, d.height - 5);
		return true;
	}
}
