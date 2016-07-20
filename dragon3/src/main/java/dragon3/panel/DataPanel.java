package dragon3.panel;

import java.awt.Graphics;
import java.awt.Point;

import mine.awt.GraphicsAWT;
import mine.event.SleepManager;
import mine.paint.MineGraphics;
import dragon3.Statics;
import dragon3.bean.WazaData;
import dragon3.common.Body;
import dragon3.common.constant.GameColors;
import dragon3.common.constant.BodyKind;
import dragon3.common.util.Equip;
import dragon3.image.ImageManager;
import dragon3.manage.Attack;
import dragon3.manage.SaveManager;
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

public class DataPanel extends PanelBase {

	private static final long serialVersionUID = -8166726066821532035L;

	private ImageManager im;

	private DataPanelPainter pp;

	private GameColors bgcolor = GameColors.BLUE;


	/*** Constructer *******************************************/

	public DataPanel(SleepManager sm, ImageManager im, boolean left) {
		super(sm, im, 160, 128, left);
		this.im = im;
	}

	/*** Score *******************************************/

	public void displayScore1(SaveManager save) {
		bgcolor = GameColors.BLUE;
		setLocate(new Point(2, 1), 1);
		pp = new Score1Paint(save, im.getBack());
		repaint();
		setVisible(true);
	}

	public void displayScore2(Equip equip, SaveManager save) {
		bgcolor = GameColors.BLUE;
		setLocate(new Point(3, 1), 1);
		pp = new Score2Paint(equip, save);
		repaint();
		setVisible(true);
	}

	/*** Status *******************************************/

	public void displayCamp(Point pa, int tikei, GameColors bgcolor_) {
		this.bgcolor = bgcolor_;
		setLocate(pa, 1);
		pp = new CampDataPaint(tikei, im.getBack(), im.getWaku());
		repaint();
		setVisible(true);
	}

	public void displayPlace(Point pa, int tikei) {
		bgcolor = GameColors.GREEN;
		setLocate(pa, 1);
		pp = new PlacePaint(tikei, im.getBack());
		repaint();
		setVisible(true);
	}

	public void displayItem(Point pa, int turn, int limit, int tikei) {
		bgcolor = GameColors.GREEN;
		setLocate(pa, 1);
		pp = new ItemPaint(turn, limit, tikei, im.getBack());
		repaint();
		setVisible(true);
	}
	
	public void displaySummon(Point pa, int turn, int limit, int tikei) {
		bgcolor = GameColors.GREEN;
		setLocate(pa, 1);
		pp = new SummonPaint(turn, limit, tikei, im.getBack());
		repaint();
		setVisible(true);
	}

	public void displayData(Point pa, int turn, int treasureLimit, String treasureCount) {
		bgcolor = GameColors.GREEN;
		setLocate(pa, 1);
		pp = new DataPaint(turn, treasureLimit, treasureCount, im.getBack());
		repaint();
		setVisible(true);
	}

	private void display(Body ba, DataPanelPainter pp_) {
		if (ba == null) {
			setVisible(false);
			return;
		}
		this.pp = pp_;
		bgcolor = ba.getColor();
		setLocate(ba, 1);
		setHPBar(false, ba);
		repaint();
		setVisible(true);		
	}

	public void displayAnalyze(Body ba) {
		display(ba, new AnalyzePaint(ba));
	}
	
	public void displayStatus(Body ba) {
		display(ba, new StatusPaint(ba));
	}
	
	public void displayTypeList(Body ba) {
		display(ba, new TypeListPaint(ba));
	}

	public void displayWazaList(Body ba) {
		display(ba, new WazaListPaint(ba));
	}

	public void displayWaza(Body ba, int i) {
		WazaData waza = (WazaData)Statics.wazaList.getData(ba.getWazaList().get(i));
		display(ba, new WazaPaint(waza, im.getBack()));
	}
	
	public void displayNext(Body ba) {
		if (ba != null && ba.isKind(BodyKind.WAZA)) {
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

	public void displayAttack(Attack attack, Attack counter) {
		Body ba = null;
		Body bb = null;

		if (attack == null) {
			if (counter == null) {
				setVisible(false);
				return;
			} else {
				ba = counter.getReceiver();
				bb = counter.getAttacker();
				bgcolor = ba.getColor();
				pp = new CounterPaint(ba);
			}
		} else {
			ba = attack.getAttacker();
			bb = attack.getReceiver();
			bgcolor = ba.getColor();
			pp = new AttackPaint(bb, attack);
		}

		setLocate(ba, bb, 2);
		setHPBar(ba, counter);

		repaint();
		setVisible(true);
	}

	/*** Paint *****************************************************/

	public void paintComponent(Graphics g) {
		g.setFont(getFont());
		MineGraphics mg = new GraphicsAWT(g);
		clear(bgcolor, mg);
		if (pp != null) {
			pp.paint(this, mg);
		}
	}

}
