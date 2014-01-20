/*
 * Created on 2005/01/03
 */
package dragon3.panel.paint;


import mine.paint.MineGraphics;
import dragon3.Statics;
import dragon3.common.Body;
import dragon3.common.constant.Types;
import dragon3.panel.PanelWorks;

/**
 * @author saito
 */
public class TypeListPaint implements DataPanelPainter {

	private Body ba;
	private int n;

	public TypeListPaint(Body ba) {
		this.ba = ba;
	}

	public void paint(PanelWorks pw, MineGraphics g) {
		pw.drawMain(ba, g, false);
		n = 0;
		drawType(pw, g, Types.DRAGON);
		drawType(pw, g, Types.UNDEAD);
		drawType(pw, g, Types.DRAGON_KILLER);
		drawType(pw, g, Types.UNDEAD_KILLER);
		drawType(pw, g, Types.MAGIC_50);

		drawType(pw, g, Types.FIRE_200, Types.FIRE_50, Types.FIRE_0);
		drawType(pw, g, Types.ICE_200, Types.ICE_50, Types.ICE_0);
		drawType(pw, g, Types.THUNDER_200, Types.THUNDER_50, Types.THUNDER_0);

		if (!drawType(pw, g, Types.ANTI_ALL)) {
			drawType(pw, g, Types.ANTI_CRITICAL);
			drawType(pw, g, Types.ANTI_DEATH);

			drawType(pw, g, Types.ANTI_SLEEP);
			drawType(pw, g, Types.ANTI_POISON);
			drawType(pw, g, Types.ANTI_CHARM);
			drawType(pw, g, Types.POISON);
			drawType(pw, g, Types.REGENE);
		}

		drawType(pw, g, Types.FLY_ABLE);
		drawType(pw, g, Types.SWIM_ABLE);
		drawType(pw, g, Types.LITE_WALK);

		int ido = 0;
		if (ba.isType(Types.MOVE_UP_1))
			ido++;
		if (ba.isType(Types.MOVE_UP_2))
			ido += 2;
		if (ba.isType(Types.MOVE_DOWN_1))
			ido--;
		switch (ido) {
			case -1 :
				drawType(pw, g, Types.MOVE_DOWN_1);
				break;
			case 1 :
				drawType(pw, g, Types.MOVE_UP_1);
				break;
			case 2 :
				drawType(pw, g, Types.MOVE_UP_2);
				break;
		}

		drawType(pw, g, Types.SLEEP_LOCK);
		drawType(pw, g, Types.CHARM_LOCK);
	}

	private boolean drawType(PanelWorks pw, MineGraphics g, String t2, String th, String t0) {
		if (drawType(pw, g, t0))
			return true;
		if (ba.isType(t2) && ba.isType(th))
			return false;
		drawType(pw, g, t2);
		drawType(pw, g, th);
		return true;
	}

	private boolean drawType(PanelWorks pw, MineGraphics g, String type) {
		if (n == 8)
			return false;
		if (!ba.isType(type))
			return false;
		pw.drawLine((String)Statics.tokusei.get(type), n / 4, n % 4, g);
		n++;
		return true;
	}
}
