package dragon3.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import dragon3.common.constant.GameColor;
import mine.event.PaintComponent;
import mine.event.PaintListener;
import mine.paint.MineGraphics;

public class LargePanel implements ActionListener, PaintListener {

	public static final int WIDTH = 200;
	public static final int HEIGHT = 100;
	
	private PaintComponent panel;
	
	private Timer time;

	private String text;
	
	private GameColor bgcolor;
	
	private int width;
	
	private int height;

	public LargePanel(PaintComponent panel) {
		super();
		this.panel = panel;

		panel.setVisible(false);
		time = new Timer(1000, this);
		time.setRepeats(false);
		panel.setFontSize(24);
		panel.setPaintListener(this);
	}

	/*** Locate ***********************************************/

	public void setLocate() {
		int mx = 300 - width / 2;
		int my = 200 - height / 2;
		panel.setLocation(mx, my);
	}

	/*** Display ******************************************************************/

	public void display(String text, GameColor bgcolor, int sleep) {
		this.text = text;
		this.bgcolor = bgcolor;
		
		width = calcWidth(text) + 20;
		height = 32;
		
		panel.setSize(width, height);
		
		setLocate();
		panel.setVisible(true);
		panel.repaint();
		time.setInitialDelay(sleep);
		time.restart();
	}

	/*** Paint **************************************************************/

	public void paint(MineGraphics g) {
		g.setColor(bgcolor.getAlphaBg());
		g.fillRect(0, 0, width, height);
		g.setColor(bgcolor.getFg());
		g.drawRect(2, 2, width - 5, height - 5);

		//MineAwtUtils.setAntialias(g, true);
		//MineAwtUtils.drawString(text, 0, 24, width, g);
		g.drawString(text, 0 + (width - calcWidth(text)) / 2, 24);
		
	}

	/*** Dispose ******************************************************/

	@Override
	public void actionPerformed(ActionEvent e) {
		panel.setVisible(false);
	}
	
	public void setVisible(boolean flag) {
		panel.setVisible(flag);
	}
	
	private int calcWidth(String text) {
		return text.getBytes().length * 12;
	}
}
