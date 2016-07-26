package dragon3.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.StringTokenizer;

import javax.swing.JComponent;

import mine.awt.MineAwtUtils;
import mine.event.SleepManager;
import mine.paint.Colors;
import mine.paint.MineGraphics;
import dragon3.Level;
import dragon3.common.Body;
import dragon3.common.constant.GameColor;
import dragon3.common.constant.Texts;
import dragon3.image.ImageManager;
import dragon3.manage.Attack;
import dragon3.panel.item.EXPBar;
import dragon3.panel.item.HPBar;

public abstract class PanelBase extends JComponent implements PanelWorks {


	private static final long serialVersionUID = 1L;
	private HPBar hpb;
	private EXPBar expb;
	private boolean left;
	private SleepManager sm;
	private ImageManager im;

	public PanelBase(SleepManager sm, ImageManager im, int width, int height, boolean left) {
		super();
		this.sm = sm;
		this.im = im;
		this.left = left;
		MineAwtUtils.setSize(this, width, height);
		setVisible(false);
		setFont(MineAwtUtils.getFont(14));
		setBackground(new Color(0, 0, 150, 200));

		hpb = new HPBar();
		expb = new EXPBar();
	}

	public void setHPBar(boolean hit, Body b) {
		hpb.setup(hit, b.getHp(), b.getHpMax());
	}
	
	public void setEXPBar(Body b) {
		expb.setup(b.getExp(), Level.MAX_EXP);
	}

	public void setHPBar(Body b, Attack attack) {
		if (attack != null) {
			int damage = attack.getDamage() * attack.getRate() / 100;
			hpb.setup(attack.isHit(), b.getHp(), b.getHpMax());
			hpb.setMin(b.getHp() - damage, false);
		} else {
			hpb.setup(false, b.getHp(), b.getHpMax());
		}
	}

	/*** Locate ***********************************************/

	public void setLocate(Body ba, int size) {
		Point bap = new Point(ba.getX(), ba.getY());
		setLocate(bap, bap, size);
	}
	public void setLocate(Body ba, Body bb, int size) {
		Point bap = new Point(ba.getX(), ba.getY());
		Point bbp = new Point(bb.getX(), bb.getY());
		setLocate(bap, bbp, size);
	}

	public void setLocate(Point ba, int size) {
		setLocate(ba, ba, size);
	}
	public void setLocate(Point ba, Point bb, int size) {
		Dimension d = new Dimension(20, 15);
		Dimension m = getSize();
		int mx = 0;
		int my = 0;

		mx =
			Math.min(
				(ba.x + bb.x) * 16 + 64 + 16,
				d.width * 32 - m.width * size);
		if (Math.max(ba.y, bb.y) < 10) {
			my =
				Math.min(
					Math.max(ba.y, bb.y) * 32 + 96 + 16,
					d.height * 32 - m.height);
		} else if (Math.min(ba.y, bb.y) >= 5) {
			my = Math.max(0, Math.min(ba.y, bb.y) * 32 - m.height - 64 - 16);
		} else {
			my = (ba.y + bb.y) * 16 + 16 - m.height / 2;
		}
		if (!left) {
			mx += m.width;
		}
		setLocation(mx, my);
	}

	/*** Main **********************************************/

	@Override
	public void drawMain(Body ba, MineGraphics g) {
		g.drawImage(im.getBack()[0], 10, 10);
		g.drawImage(im.getBodyList().getImage(ba.getImageNum()), 10, 10);
		g.drawString(ba.base.getName(), 50, 22);
		g.drawString("Lv." + ba.getLevel(), 52, 41);
	}
	
	@Override
	public void drawHp(Body ba, MineGraphics g) {
		drawLine(Texts.hp, 0, 0, g);
		hpb.paint(52, 60, g);
	}
	
	@Override
	public void drawExp(Body ba, MineGraphics g) {
		drawLine("EXP", 0, 0, g);
		expb.paint(52, 60, g);
	}

	/*** Line ***************************************/

	public void drawText(String lines, MineGraphics g) {
		StringTokenizer st = new StringTokenizer(lines, "&");
		g.drawString(st.nextToken(), 50, 32);
		for (int i = 0; i <= 3; i++) {
			if (!st.hasMoreTokens())
				break;
			drawLine(st.nextToken(), 0, i, g);
		}
	}

	public void drawLine(String name, int st, int x, int y, MineGraphics g) {
		g.drawString(name, 10 + 70 * x, 60 + 19 * y);
		g.drawString("" + st, 52 + 70 * x, 60 + 19 * y);
	}
	public void drawLine(String name, int x, int y, MineGraphics g) {
		g.drawString(name, 10 + 70 * x, 60 + 19 * y);
	}

	/*** Clear *********************************************/

	public boolean clear(GameColor color, MineGraphics g) {
		g.setColor(color.getAlphaBg());

		Dimension d = getSize();
		g.fillRect(0, 0, d.width, d.height);
		g.setColor(Colors.WHITE);
		g.drawRect(2, 2, d.width - 5, d.height - 5);
		return true;
	}

	/*** Damage **********************************************/

	public void damage(Body ba, int damage) {
		hpb.setMin(ba.getHp() - damage, true);
		repaint(50, 50, 96, 12);
	}

	/*** Henka **************************************************/

	public void henka() {
		int st = hpb.getSleepTime() / 2;
		while (hpb.henka()) {
			repaint(50, 50, 96, 12);
			sleep(st);
		}
		repaint();
	}

	protected void sleep(long t) {
		sm.sleep(t);
	}
}
