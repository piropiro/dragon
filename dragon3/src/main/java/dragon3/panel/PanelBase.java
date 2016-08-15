package dragon3.panel;

import java.util.StringTokenizer;

import javax.inject.Inject;

import dragon3.attack.Attack;
import dragon3.common.Body;
import dragon3.common.constant.GameColor;
import dragon3.common.constant.Texts;
import dragon3.image.ImageManager;
import dragon3.manage.LevelManager;
import dragon3.panel.item.EXPBar;
import dragon3.panel.item.HPBar;
import mine.event.PaintListener;
import mine.event.SleepManager;
import mine.paint.MineColor;
import mine.paint.MineGraphics;

public abstract class PanelBase implements PanelWorks, PaintListener {
	
	protected HPBar hpb;
	protected EXPBar expb;
	@Inject SleepManager sm;
	@Inject ImageManager im;

	protected boolean left;	
	protected int width;
	protected int height;

	public PanelBase(int width, int height, boolean left) {
		this.width = width;
		this.height = height;
		this.left = left;
		//MineAwtUtils.setSize(this, width, height);
		//panel.setFontSize(14);

		hpb = new HPBar();
		expb = new EXPBar();
	}

	public void setEXPBar(Body b) {
		expb.setup(b.getExp(), LevelManager.MAX_EXP);
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

	/*** Main **********************************************/

	@Override
	public void drawMain(Body ba, MineGraphics g) {
		g.drawImage(im.getWhiteBack(), 10, 10);
		g.drawImage(im.getBodyImageList().getImage(ba.getImageNum()), 10, 10);
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

		g.fillRect(0, 0, width, height);
		g.setColor(MineColor.WHITE);
		g.drawRect(2, 2, width - 5, height - 5);
		return true;
	}



	protected void sleep(long t) {
		sm.sleep(t);
	}
	

}
