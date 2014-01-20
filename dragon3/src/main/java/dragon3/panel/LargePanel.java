package dragon3.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.Timer;

import mine.awt.MineAwtUtils;
import dragon3.common.constant.GameColors;

public class LargePanel extends JComponent implements ActionListener {

	private static final long serialVersionUID = 5743301980793715168L;

	private Timer time;

	private String text;

	public LargePanel() {
		super();

		setVisible(false);
		MineAwtUtils.setSize(this, 200, 100);
		time = new Timer(1000, this);
		time.setRepeats(false);
		setFont(MineAwtUtils.getFont(24));
	}

	/*** Locate ***********************************************/

	public void setLocate() {
		Dimension m = getSize();
		int mx = 300 - m.width / 2;
		int my = 200 - m.height / 2;
		setLocation(mx, my);
	}

	/*** Display ******************************************************************/

	public void display(String text_, String color, int sleep) {
		this.text = text_;
		int[] rgb = GameColors.getColor(color);
		setBackground(new Color(rgb[0], rgb[1], rgb[2]));
		setSize(getFontMetrics(getFont()).stringWidth(text) + 20, 32);
		setLocate();
		setVisible(true);
		repaint();
		time.setInitialDelay(sleep);
		time.restart();
	}

	/*** Paint **************************************************************/

	public void paintComponent(Graphics g) {
		Dimension d = getSize();
		g.setColor(getBackground());
		g.fillRect(0, 0, d.width, d.height);
		g.setColor(Color.white);
		g.drawRect(2, 2, d.width - 5, d.height - 5);

		MineAwtUtils.setAntialias(g, true);
		MineAwtUtils.drawString(text, 0, 24, d.width, g);
	}

	/*** Dispose ******************************************************/

	public void actionPerformed(ActionEvent e) {
		setVisible(false);
	}
}
