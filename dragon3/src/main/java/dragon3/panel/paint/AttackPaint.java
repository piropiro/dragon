/*
 * Created on 2005/01/03
 */
package dragon3.panel.paint;


import mine.paint.MineGraphics;
import dragon3.common.Body;
import dragon3.common.constant.Effects;
import dragon3.common.constant.Texts;
import dragon3.common.constant.Types;
import dragon3.manage.Attack;
import dragon3.panel.PanelWorks;

/**
 * @author saito
 */
public class AttackPaint implements DataPanelPainter {

	private Body bb;
	private Attack attack;

	public AttackPaint(Body bb, Attack attack) {
		this.bb = bb;
		this.attack = attack;
	}

	public void paint(PanelWorks pw, MineGraphics g) {
		if (attack == null) {
			return;
		}
		
		pw.drawLine(attack.getName(), 0, 1, g);
		if (attack.hasEffect(Effects.NO_ATTACK)) {
			paintEffect(pw, g);
		} else {
			pw.drawLine(Texts.sp[55], Math.abs(attack.getDamage()), 0, 2, g);
			double rate = (double) attack.getRate();
			pw.drawLine(Texts.sp[56] + rate / 100, 1, 2, g);
		}
		int meichu = attack.getMeichu();
		int store = attack.getStore();

		pw.drawLine(Texts.sp[57] + meichu, 0, 3, g);
		pw.drawLine(Texts.sp[58] + store, 1, 3, g);
	}
	
	private void paintEffect(PanelWorks pw, MineGraphics g) {
		String s = "NO EFFECT";
		if (attack.isEffective(Effects.REFRESH))
			s = "REFRESH";
		if (attack.isEffective(Effects.OIL))
			s = "OIL";
		if (attack.isEffective(Effects.ATTACK_UP))
			s = "ATTACK";
		if (attack.isEffective(Effects.GUARD_UP))
			s = "GUARD";
		if (attack.isEffective(Effects.UPPER))
			s = "UP";
		if (attack.isEffective(Effects.CHOP))
			s = "DOWN";
		if (attack.isEffective(Effects.WET))
			s = "CLOSE";
		if (attack.isEffective(Effects.POISON))
			s = "POISON";
		if (attack.isEffective(Effects.SLEEP))
			s = "SLEEP";
		if (attack.isEffective(Effects.CHARM))
			s = "CHARM";
		if (attack.isEffective(Effects.CRITICAL))
			s = "FINISH";
		if (attack.isEffective(Effects.DEATH))
			s = "DEATH";
		if (s.equals("SLEEP")) {
			if (bb.isType(Types.SLEEP_LOCK)) {
				s = "SLEEP_LOCK";
			}
		}
		if (s.equals("CHARM")) {
			if (bb.isType(Types.CHARM_LOCK)) {
				s = "CHARM_LOCK";
			}
		}
		pw.drawLine(Texts.sp[59] + s, 0, 2, g);
	}
}
