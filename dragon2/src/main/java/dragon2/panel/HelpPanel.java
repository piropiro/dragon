package dragon2.panel;





import java.awt.*;
import mine.JCanvas;
import mine.Mine;

public class HelpPanel extends JCanvas {

	public HelpPanel() {
		super(288, 48);
		setFont(Mine.getFont(14));
		setBackground(new Color(0, 0, 150, 200));
	}

	public void setLine(String as[], int i) {
		line = as;
		bgcolor = i;
	}

	public void setLocate(int i, int j, boolean flag) {
		Dimension dimension = new Dimension(20, 15);
		Dimension dimension1 = getSize();
		boolean flag1 = leftf;
		boolean flag2 = upf;
		if (flag) {
			leftf = i > 5;
			upf = j > 5;
		} else {
			if (leftf) {
				if (i < 4)
					leftf = false;
			} else if (i > 15)
				leftf = true;
			if (upf) {
				if (j < 4)
					upf = false;
			} else if (j > 10)
				upf = true;
		}
		if (flag || leftf != flag1 || upf != flag2) {
			int k = leftf ? 16 : dimension.width * 32 - dimension1.width - 16;
			int l = upf ? 8 : dimension.height * 32 - dimension1.height - 8;
			setLocation(k, l);
		}
	}

	public void paint(Graphics g) {
		g.setFont(getFont());
		clear(bgcolor, g);
		if (line == null)
			return;
		for (int i = 0; i < line.length; i++)
			g.drawString(line[i], 10, 19 + 19 * i);

	}

	protected void drawImage(Image image, int i, int j, int k, Graphics g) {
		int l = j;
		int i1 = k;
		int j1 = l + 32;
		int k1 = i1 + 32;
		int l1 = (i % 15) * 32;
		int i2 = (i / 15) * 32;
		int j2 = l1 + 32;
		int k2 = i2 + 32;
		g.drawImage(image, l, i1, j1, k1, l1, i2, j2, k2, null);
	}

	public boolean clear(int i, Graphics g) {
		switch (i) {
		case 1: // '\001'
			g.setColor(new Color(0, 0, 150, 200));
			break;

		case 3: // '\003'
			g.setColor(new Color(150, 0, 0, 200));
			break;

		case 2: // '\002'
			g.setColor(new Color(0, 100, 0, 220));
			break;
		}
		Dimension dimension = getSize();
		g.fillRect(0, 0, dimension.width, dimension.height);
		g.setColor(Color.white);
		g.drawRect(2, 2, dimension.width - 5, dimension.height - 5);
		return true;
	}

	String line[];
	boolean leftf;
	boolean upf;
	int bgcolor;
}
