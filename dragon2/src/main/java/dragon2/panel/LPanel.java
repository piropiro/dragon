package dragon2.panel;





import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import dragon2.UnitWorks;
import dragon2.common.constant.GameColor;
import mine.JCanvas;
import mine.Mine;

public class LPanel extends JCanvas implements ActionListener {

	public LPanel(UnitWorks unitworks) {
		super(200, 100);
		uw = unitworks;
		time = new Timer(1000, this);
		time.setRepeats(false);
		setFont(Mine.getFont(24));
	}

	public void setLocate() {
		Dimension dimension = getSize();
		int i = 300 - dimension.width / 2;
		int j = 200 - dimension.height / 2;
		setLocation(i, j);
	}

	public void display(String s, GameColor i) {
		text = s;
		switch (i) {
		case BLUE: // '\001'
			setBackground(new Color(0, 0, 150, 200));
			break;

		case RED: // '\003'
			setBackground(new Color(150, 0, 0, 200));
			break;

		case GREEN: // '\002'
			setBackground(new Color(0, 100, 0, 200));
			break;
		}
		setSize(getFontMetrics(getFont()).stringWidth(s) + 20, 32);
		setLocate();
		setVisible(true);
		repaint();
	}

	public void displayT(String s, GameColor i, int j) {
		display(s, i);
		time.setInitialDelay(j);
		time.restart();
	}

	public void displayR(String s, GameColor i, int j) {
		display(s, i);
		uw.sleep(j);
		setVisible(false);
	}

	public void paint(Graphics g) {
		Dimension dimension = getSize();
		g.setColor(getBackground());
		g.fillRect(0, 0, dimension.width, dimension.height);
		g.setColor(Color.white);
		g.drawRect(2, 2, dimension.width - 5, dimension.height - 5);
		Mine.setAntialias(g, true);
		Mine.drawString(text, 0, 24, dimension.width, g);
	}

	public void actionPerformed(ActionEvent actionevent) {
		setVisible(false);
	}

	UnitWorks uw;
	String text;
	Timer time;
}
