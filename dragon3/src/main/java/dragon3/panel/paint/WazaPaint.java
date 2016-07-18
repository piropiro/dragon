/*
 * Created on 2005/01/03
 */
package dragon3.panel.paint;


import java.util.HashSet;
import java.util.Set;

import mine.paint.MineGraphics;
import mine.paint.MineImage;
import dragon3.Statics;
import dragon3.bean.WazaData;
import dragon3.common.constant.Effects;
import dragon3.common.constant.Texts;
import dragon3.panel.PanelWorks;

/**
 * @author saito
 */
public class WazaPaint implements DataPanelPainter {

	private WazaData waza;
	private int n;
	private MineImage[] back;


	public WazaPaint(WazaData waza, MineImage[] back) {
		this.waza = waza;
		this.back = back;
	}

	public void paint(PanelWorks pw, MineGraphics g) {
		g.drawImage(back[0], 10, 10);

		//g.drawImage(bodyImageList.getImage(ba.getImage()), 10, 10);
		g.drawString(
			waza.getName().substring(0, Math.min(7, waza.getName().length())),
			50,
			22);
		g.drawString("No." + waza.getStar(), 52, 41);


		if (waza.getDamageType() != null) {
			pw.drawLine(waza.getDamageType().getText(), 0, 0, g);
		} else {
			pw.drawLine(Texts.sp[33], 0, 0, g);
		}

		pw.drawLine(waza.getTargetType().getText(), 1, 0, g);

		Set<String> ect = new HashSet<>(waza.getEffect());

		int hit = 0;
		if (ect.contains(Effects.MISS_4));
			hit -= 4;
		if (ect.contains(Effects.HIT_4))
			hit += 4;
		if (ect.contains(Effects.HIT_12))
			hit += 12;
		if (ect.contains(Effects.HICHU))
			hit = 32;
		switch (hit) {
			case -4 :
				pw.drawLine(Texts.sp[34], 0, 1, g);
				break;
			case 4 :
				pw.drawLine(Texts.sp[35], 0, 1, g);
				break;
			case 12 :
				pw.drawLine(Texts.sp[36], 0, 1, g);
				break;
			case 16 :
				pw.drawLine(Texts.sp[37], 0, 1, g);
				break;
			case 32 :
				pw.drawLine(Texts.sp[38], 0, 1, g);
				break;
		}

		n = 4;
		drawWazaEffect(pw, g, ect, Effects.DAMAGE_150);
		drawWazaEffect(pw, g, ect, Effects.DAMAGE_200);
		drawWazaEffect(pw, g, ect, Effects.DAMAGE_300);
		drawWazaEffect(pw, g, ect, Effects.TAME);
		drawWazaEffect(pw, g, ect, Effects.COUNTER_ONLY);

		drawWazaEffect(pw, g, ect, Effects.RIKU_0);
		drawWazaEffect(pw, g, ect, Effects.RIKU_150);
		drawWazaEffect(pw, g, ect, Effects.MIZU_0);
		drawWazaEffect(pw, g, ect, Effects.MIZU_100);
		drawWazaEffect(pw, g, ect, Effects.MIZU_200);

		drawWazaEffect(pw, g, ect, Effects.FIRE);
		drawWazaEffect(pw, g, ect, Effects.ICE);
		drawWazaEffect(pw, g, ect, Effects.THUNDER);
		drawWazaEffect(pw, g, ect, Effects.SORA_200);
		drawWazaEffect(pw, g, ect, Effects.DRAGON_200);
		drawWazaEffect(pw, g, ect, Effects.UNDEAD_200);

		drawWazaEffect(pw, g, ect, Effects.UPPER);
		drawWazaEffect(pw, g, ect, Effects.CHOP);
		drawWazaEffect(pw, g, ect, Effects.CRITICAL);
		drawWazaEffect(pw, g, ect, Effects.DEATH);
		drawWazaEffect(pw, g, ect, Effects.SLEEP);
		drawWazaEffect(pw, g, ect, Effects.POISON);
		drawWazaEffect(pw, g, ect, Effects.WET);
		drawWazaEffect(pw, g, ect, Effects.CHARM);
		drawWazaEffect(pw, g, ect, Effects.ATTACK_UP);
		drawWazaEffect(pw, g, ect, Effects.GUARD_UP);
		drawWazaEffect(pw, g, ect, Effects.REFRESH);
		drawWazaEffect(pw, g, ect, Effects.HEAL);
		drawWazaEffect(pw, g, ect, Effects.OIL);
	}

	private boolean drawWazaEffect(PanelWorks pw, MineGraphics g, Set<String> ect, String type) {
		if (n == 8)
			return false;
		if (!ect.contains(type))
			return false;
		pw.drawLine((String)Statics.effect.get(type), n % 2, n / 2, g);
		n++;
		return true;
	}
}
