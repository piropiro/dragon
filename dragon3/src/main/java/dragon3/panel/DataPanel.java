package dragon3.panel;

import javax.inject.Inject;

import dragon3.Statics;
import dragon3.attack.Attack;
import dragon3.camp.Equip;
import dragon3.common.Body;
import dragon3.common.constant.BodyKind;
import dragon3.common.constant.GameColor;
import dragon3.common.util.MoveUtils;
import dragon3.data.WazaData;
import dragon3.image.ImageManager;
import dragon3.panel.paint.AnalyzePaint;
import dragon3.panel.paint.AttackPaint;
import dragon3.panel.paint.CampDataPaint;
import dragon3.panel.paint.CounterPaint;
import dragon3.panel.paint.DataPaint;
import dragon3.panel.paint.DataPanelPainter;
import dragon3.panel.paint.ItemPaint;
import dragon3.panel.paint.PlacePaint;
import dragon3.panel.paint.Score1Paint;
import dragon3.panel.paint.Score2Paint;
import dragon3.panel.paint.StatusPaint;
import dragon3.panel.paint.SummonPaint;
import dragon3.panel.paint.TypeListPaint;
import dragon3.panel.paint.WazaListPaint;
import dragon3.panel.paint.WazaPaint;
import dragon3.save.SaveData;
import mine.event.PaintComponent;
import mine.paint.MineGraphics;
import mine.paint.MineImage;
import mine.util.Point;

public class DataPanel extends PanelBase {

	public static final int WIDTH = 160;
	public static final int HEIGHT = 128;

	@Inject Statics statics;
	
	private PaintComponent panel;

	@Inject ImageManager im;

	private DataPanelPainter pp;

	private GameColor bgcolor = GameColor.BLUE;


	/*** Constructer *******************************************/

	public DataPanel(PaintComponent panel, boolean left) {
		super(WIDTH, HEIGHT, left);
		this.panel = panel;
		panel.setPaintListener(this);
	}

	/*** Score *******************************************/

	public void displayScore1(SaveData sd) {
		MineImage cBlueImage = im.getStageObj()[MoveUtils.C_BLUE];
		pp = new Score1Paint(sd, cBlueImage);
		display();
	}

	public void displayScore2(Equip equip, SaveData sd) {
		pp = new Score2Paint(equip, sd);
		display();
	}

	/*** Status *******************************************/

	public void displayCamp(Point pa, int tikei, GameColor bgcolor_) {
		pp = new CampDataPaint(tikei, im.getWhiteBack(), im.getWaku(), bgcolor_, pa);
		display();
	}

	public void displayPlace(Point pa, int tikei) {
		pp = new PlacePaint(tikei, im.getStageObj(), pa);
		display();
	}

	public void displayItem(Point pa, int turn, int limit, int tikei) {
		pp = new ItemPaint(turn, limit, tikei, im.getStageObj(), pa);
		display();
	}
	
	public void displaySummon(Point pa, int turn, int limit, int tikei) {
		pp = new SummonPaint(turn, limit, tikei, im.getStageObj(), pa);
		display();
	}

	public void displayData(Point pa, int turn, int treasureLimit, String treasureCount) {
		MineImage cBlueImage = im.getStageObj()[MoveUtils.C_BLUE];
		pp = new DataPaint(turn, treasureLimit, treasureCount, cBlueImage, pa);
		display();
	}

	public void displayAnalyze(Body ba) {
		pp = new AnalyzePaint(ba);
	
		setHPBar(ba, null);
		setEXPBar(ba);
		display();
	}
	
	public void displayStatus(Body ba) {		
		pp = new StatusPaint(ba);

		setHPBar(ba, null);
		setEXPBar(ba);
		
		display();
	}
	
	public void displayTypeList(Body ba) {
		pp = new TypeListPaint(ba);

		setHPBar(ba, null);
		setEXPBar(ba);
		
		display();
	}

	public void displayWazaList(Body ba) {
		pp = new WazaListPaint(ba);

		setHPBar(ba, null);
		setEXPBar(ba);
		
		display();
	}

	public void displayWaza(Body ba, int i) {
		WazaData waza = statics.getWazaData(ba.getWazaList().get(i));	
		pp = new WazaPaint(ba, waza, im.getWhiteBack());

		setHPBar(ba, null);
		setEXPBar(ba);
		
		display();
	}
	
	public void displayCounter(Attack counter) {

		Body ba = counter.getReceiver();

		pp = new CounterPaint(counter);
		bgcolor = pp.getColor();
		setLocate(pp.getPoint1(), pp.getPoint2(), 2);
		
		setHPBar(ba, counter);

		panel.repaint();
		panel.setVisible(true);
	}
	
	public void displayAttack(Attack attack, Attack counter) {
		Body ba = attack.getAttacker();

		pp = new AttackPaint(attack);
		bgcolor = pp.getColor();
		setLocate(pp.getPoint1(), pp.getPoint2(), 2);
		setHPBar(ba, counter);

		panel.repaint();
		panel.setVisible(true);
	}
	
	public void displayNext(Body ba) {
		if (ba != null && ba.base.getKind() == BodyKind.WAZA) {
			displayWaza(ba, 0);
		} else if (pp == null) {
			displayAnalyze(ba);
		} else if (pp instanceof StatusPaint) {
			displayAnalyze(ba);
		} else if (pp instanceof AnalyzePaint) {
			displayTypeList(ba);
		} else if (pp instanceof TypeListPaint) {
			displayWazaList(ba);
		} else if (pp instanceof WazaListPaint) {
			displayStatus(ba);
		}
	}
	
	private void display() {
		setLocate(pp.getPoint1(), pp.getPoint2(), 1);
		bgcolor = pp.getColor();
		panel.repaint();
		panel.setVisible(true);
	}

	/*** Locate ***********************************************/

	public void setLocate(Point ba, Point bb, int size) {

		int mx = 0;
		int my = 0;

		mx =
			Math.min(
				(ba.x + bb.x) * 16 + 64 + 16,
				20 * 32 - width * size);
		if (Math.max(ba.y, bb.y) < 10) {
			my =
				Math.min(
					Math.max(ba.y, bb.y) * 32 + 96 + 16,
					15 * 32 - height);
		} else if (Math.min(ba.y, bb.y) >= 5) {
			my = Math.max(0, Math.min(ba.y, bb.y) * 32 - height - 64 - 16);
		} else {
			my = (ba.y + bb.y) * 16 + 16 - height / 2;
		}
		if (!left) {
			mx += width;
		}
		panel.setLocation(mx, my);
	}
	
	/*** Paint *****************************************************/

	@Override
	public void paint(MineGraphics g) {
		g.setFont("Dialog", 14);
		clear(bgcolor, g);
		if (pp != null) {
			pp.paint(this, g);
		}
	}

	/*** Damage **********************************************/

	public void damage(Body ba, int damage) {
		hpb.setMin(ba.getHp() - damage, true);
		panel.repaint(50, 50, 96, 12);
	}

	/*** Henka **************************************************/

	public void henka() {
		int st = hpb.getSleepTime() / 2;
		while (hpb.henka()) {
			panel.repaint(50, 50, 96, 12);
			sleep(st);
		}
		panel.repaint();
	}
	
	public void repaint() {
		panel.repaint();
	}
	
	public void setVisible(boolean flag) {
		panel.setVisible(flag);
	}
}
