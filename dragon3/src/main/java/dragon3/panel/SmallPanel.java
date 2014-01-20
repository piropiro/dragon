package dragon3.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

import mine.MineUtils;
import mine.awt.MineAwtUtils;
import dragon3.common.constant.GameColors;

public class SmallPanel extends JComponent {

	private static final long serialVersionUID = -7340604998316778900L;

	private String label;

	/*** Constructer *********************************************/

	public SmallPanel() {
		setVisible(false);
		MineAwtUtils.setSize(this, 30, 15);
	}

	/*** Locate ***********************************************/

	private void calcLocation(int x, int y) {
		Dimension d = getSize();
		int mx = x * 32 + 16 - d.width / 2;
		mx = MineUtils.mid(0, mx, 640 - d.width);
		int my = y * 32 + 20;
		my = MineUtils.mid(0, my, 480 - d.height);
		setLocation(mx, my);
	}

	/*** Display ******************************************************/

	public void display(String label_, String color, int x, int y) {

		this.label = label_;

		if (color.equals(GameColors.WHITE)) {
			setBackground(Color.white);
			setForeground(Color.black);
		} else if (color.equals(GameColors.BLUE)) {
			setBackground(Color.blue);
			setForeground(Color.white);
		} else if (color.equals(GameColors.RED)) {
			setBackground(Color.red);
			setForeground(Color.white);
		} else if (color.equals(GameColors.GREEN)) {
			setBackground(Color.green);
			setForeground(Color.black);
		} else if (color.equals(GameColors.YELLOW)) {
			setBackground(Color.yellow);
			setForeground(Color.black);
		} else if (color.equals(GameColors.SKY)) {
			setBackground(new Color(50, 100, 255));
			setForeground(Color.white);
		}
		setSize(getFontMetrics(getFont()).stringWidth(label) + 3, 15);
		calcLocation(x, y);
		setVisible(true);
	}

	/*** Paint *********************************************************/

	public void paintComponent(Graphics g) {
		Dimension d = getSize();
		g.setColor(getBackground());
		g.fillRect(0, 0, d.width, d.height);
		g.setColor(Color.black);
		g.drawRect(0, 0, d.width - 1, d.height - 1);
		g.setColor(getForeground());
		g.drawString(label, 2, 12);
	}
}
