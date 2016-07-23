package dragon2.panel;

import java.awt.*;

import dragon2.Statics;
import dragon2.UnitWorks;
import dragon2.common.Body;
import dragon2.common.constant.Texts;
import dragon2.common.constant.BodyAttribute;
import dragon2.common.constant.GameColor;
import mine.JCanvas;
import mine.Mine;

@SuppressWarnings("serial")
public abstract class StatusBase extends JCanvas {

	public StatusBase(int i, int j, boolean flag) {
		super(i, j);
		setFont(Mine.getFont(14));
		setBackground(new Color(0, 0, 150, 200));
		left = flag;
		hpb = new HPBar();
	}

	public void setLocate(Body body, int i) {
		Point point = new Point(body.x, body.y);
		setLocate(point, point, i);
	}

	public void setLocate(Point point, int i) {
		setLocate(point, point, i);
	}

	public void setLocate(Body body, Body body1, int i) {
		Point point = new Point(body.x, body.y);
		Point point1 = new Point(body1.x, body1.y);
		setLocate(point, point1, i);
	}

	public void setLocate(Point point, Point point1, int i) {
		Dimension dimension = new Dimension(20, 15);
		Dimension dimension1 = getSize();
		int j = 0;
		int k = 0;
		j = Math.min((point.x + point1.x) * 16 + 64 + 16, dimension.width * 32
				- dimension1.width * i);
		if (Math.max(point.y, point1.y) < 10)
			k = Math.min(Math.max(point.y, point1.y) * 32 + 96 + 16,
					dimension.height * 32 - dimension1.height);
		else if (Math.min(point.y, point1.y) >= 5)
			k = Math.max(0, Math.min(point.y, point1.y) * 32
					- dimension1.height - 64 - 16);
		else
			k = ((point.y + point1.y) * 16 + 16) - dimension1.height / 2;
		if (!left)
			j += dimension1.width;
		setLocation(j, k);
	}

	protected void drawMain(UnitWorks unitworks, Body body, Graphics g,
			boolean flag) {
		drawImage(Statics.back, 0, 10, 10, g);
		drawImage(Statics.chara, body.img, 10, 10, g);
		g.drawString(body.name, 50, 22);
		g.drawString("Lv." + body.level, 52, 41);
		String s = body.isType(BodyAttribute.M_RED) ? "R" : "";
		String s1 = body.isType(BodyAttribute.M_GREEN) ? "G" : "";
		String s2 = body.isType(BodyAttribute.M_BLUE) ? "B" : "";
		g.drawString(s + s1 + s2, 112, 41);
		if (flag) {
			drawLine(Texts.hp, 0, 0, g);
			hpb.paint(52, 60, g);
		}
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

	protected void drawLine(String s, int i, int j, int k, Graphics g) {
		g.drawString(s, 10 + 70 * j, 60 + 19 * k);
		g.drawString("" + i, 52 + 70 * j, 60 + 19 * k);
	}

	protected void drawLine(String s, int i, int j, Graphics g) {
		g.drawString(s, 10 + 70 * i, 60 + 19 * j);
	}

	public boolean clear(GameColor i, Graphics g) {
		switch (i) {
		case BLUE: // '\001'
			g.setColor(new Color(0, 0, 150, 200));
			break;

		case RED: // '\003'
			g.setColor(new Color(150, 0, 0, 200));
			break;

		case GREEN: // '\002'
			g.setColor(new Color(0, 100, 0, 220));
			break;
		}
		Dimension dimension = getSize();
		g.fillRect(0, 0, dimension.width, dimension.height);
		g.setColor(Color.white);
		g.drawRect(2, 2, dimension.width - 5, dimension.height - 5);
		return true;
	}

	HPBar hpb;
	private boolean left;
}
