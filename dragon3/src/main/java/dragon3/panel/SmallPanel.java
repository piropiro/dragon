package dragon3.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

import mine.MineUtils;
import mine.awt.MineAwtUtils;
import dragon3.common.constant.GameColor;

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

	public void display(String label_, GameColor color, int x, int y) {

		this.label = label_;
		
		int[] bg = color.getBg();
		int[] fg = color.getFg();
		
		setBackground(new Color(bg[0], bg[1], bg[2]));
		setForeground(new Color(fg[0], fg[1], fg[2]));
		
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
