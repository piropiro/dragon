package dragon2.panel;





import java.awt.*;

import dragon2.Iconable;
import dragon2.UnitWorks;
import dragon2.common.Body;
import dragon2.map.Map;
import mine.JCanvas;
import mine.Mine;

public class TPanel extends JCanvas {

	public TPanel(UnitWorks unitworks, Map map1) {
		super(30, 15);
		uw = unitworks;
		map = map1;
	}

	public void setLocate() {
		Dimension dimension = getSize();
		int i = (b.x * 32 + 16) - dimension.width / 2;
		i = Mine.mid(0, i, 640 - dimension.width);
		int j = b.y * 32 + 20;
		j = Mine.mid(0, j, 480 - dimension.height);
		setLocation(i, j);
	}

	public void display(Iconable iconable, Body body) {
		name = iconable.getSubName();
		b = body;
		
		int[] bg = iconable.getColor().getBg();
		int[] fg = iconable.getColor().getFg();
		
		setBackground(new Color(bg[0], bg[1], bg[2]));
		setForeground(new Color(fg[0], fg[1], fg[2]));
		
		setSize(getFontMetrics(getFont()).stringWidth(name) + 3, 15);
		setLocate();
		setVisible(true);
	}

	public void dispose() {
		setVisible(false);
	}

	public void paint(Graphics g) {
		Dimension dimension = getSize();
		g.setColor(getBackground());
		g.fillRect(0, 0, dimension.width, dimension.height);
		g.setColor(Color.black);
		g.drawRect(0, 0, dimension.width - 1, dimension.height - 1);
		g.setColor(getForeground());
		g.drawString(name, 2, 12);
	}

	UnitWorks uw;
	Map map;
	static final int C_WHITE = 0;
	static final int C_BLUE = 1;
	static final int C_RED = 2;
	static final int C_GREEN = 3;
	static final int C_YELLOW = 4;
	static final int C_SKY = 5;
	String name;
	Body b;
}
