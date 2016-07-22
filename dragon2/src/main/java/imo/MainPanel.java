




package imo;

import java.awt.Color;
import javax.swing.JPanel;
import mine.FreeLayout;

// Referenced classes of package imo:
//            UnitPanel, GamePaint, StartPaint, EndPaint, 
//            MainWorks, Images, ImoListener

public class MainPanel extends JPanel implements MainWorks {

	public MainPanel(ImoListener imolistener, String s, int i) {
		il = imolistener;
		name = s;
		level = i;
		setBackground(new Color(180, 240, 180));
		Images.setup();
		up = new UnitPanel(this);
		gp = new GamePaint(this);
		setLayout(new FreeLayout(300, 300));
		add(up);
		up.setPaintListener(new StartPaint(this));
		up.start();
	}

	public void sleep(long l) {
		up.sleep(l);
	}

	public void gameStart() {
		up.stop();
		gp.resetData(name, level);
		up.setPaintListener(gp);
		up.start();
	}

	public void gameEnd(int i) {
		up.stop();
		Images.setEndImage(i);
		up.setPaintListener(new EndPaint(this, i));
		up.start();
	}

	public void gameExit() {
		il.gameExit(gp.getEXP());
	}

	ImoListener il;
	UnitPanel up;
	GamePaint gp;
	String name;
	int level;
}
