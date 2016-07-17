package dragon3.panel;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

import dragon3.common.Body;
import dragon3.panel.item.HPBar;
import mine.awt.GraphicsAWT;
import mine.awt.MineAwtUtils;
import mine.event.SleepManager;
import mine.paint.Colors;
import mine.paint.MineGraphics;

public class HPanel extends JComponent {

	private static final long serialVersionUID = 8838329817391026870L;

	private SleepManager sm;
	private Body ba;
	private boolean high;

	private HPBar hpb;

	/*** Constructer ***********************************************/

	public HPanel(SleepManager sm, boolean high) {
		super();
		setVisible(false);
		this.sm = sm;
		this.high = high;
		MineAwtUtils.setSize(this, 96, 14);
		setFont(MineAwtUtils.getFont(14));
		hpb = new HPBar();
	}

	/*** Locate ***********************************************/

	public void setLocate(Body ba, Body bb) {
		int cx = ba.getX();
		int cy = ba.getY();
		int mx, my;
		if (ba.getX() >= bb.getX())
			mx = cx * 32 + 32;
		else
			mx = cx * 32 - getSize().width;
		if (cx < 3) {
			mx = cx * 32 + 32;
			if (ba.getY() == bb.getY() && Math.abs(ba.getX() - bb.getX()) < 5)
				mx = Math.max(ba.getX(), bb.getX()) * 32 + 32;
		}
		if (cx > 16) {
			mx = cx * 32 - getSize().width;
			if (ba.getY() == bb.getY() && Math.abs(ba.getX() - bb.getX()) < 5)
				mx = Math.min(ba.getX(), bb.getX()) * 32 - getSize().width;
		}
		if (high) {
			my = cy * 32 + 1;
		} else {
			my = cy * 32 + 17;
		}
		setLocation(mx, my);
	}

	/*** Display ******************************************************/

	public void display(Body ba_, Body bb, int damage, boolean hit) {
		if (ba_ == null) {
			setVisible(false);
			return;
		}
		this.ba = ba_;
		hpb.setup(hit, ba.getHp(), ba.getHpMax());
		hpb.setMin(ba.getHp() - damage, false);

		setLocate(ba, bb);
		setVisible(true);
		repaint();
	}

	/*** Paint *********************************************************/

	public void paintComponent(Graphics g) {
		if (ba == null)
			return;
		MineGraphics mg = new GraphicsAWT(g);
		
		Dimension d = getSize();
		mg.setColor(ba.getColor().getAlphaBg());
		mg.fillRect(0, 0, d.width, d.height);
		mg.setColor(Colors.BLACK);
		mg.drawRect(0, 0, d.width - 1, d.height - 1);

		hpb.paint(2, 12, mg);
	}

	/*** Damage **********************************************/

	public void damage(int damage) {
		hpb.setMin(ba.getHp() - damage, true);
		repaint();
	}

	/*** Henka **************************************************/

	public void henka() {
		int st = hpb.getSleepTime();
		while (hpb.henka()) {
			repaint();
			sm.sleep(st);
		}
		repaint();
	}
}
