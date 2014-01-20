/*
 * Created on 2005/01/03
 */
package dragon3.panel.paint;


import mine.paint.MineGraphics;
import mine.paint.MineImage;
import dragon3.common.constant.Texts;
import dragon3.common.util.MoveUtils;
import dragon3.panel.PanelWorks;

/**
 * @author saito
 */
public class DataPaint implements DataPanelPainter {

	private int turn;
	private int treasureLimit;
	private String treasureCount;
	private MineImage[] back;

	public DataPaint(int turn, int treasureLimit, String treasureCount, MineImage[] back) {
		this.turn = turn;
		this.treasureLimit = treasureLimit;
		this.treasureCount = treasureCount;
		this.back = back;
	}

	public void paint(PanelWorks pw, MineGraphics g) {
		g.drawImage(back[MoveUtils.C_BLUE], 10, 10);
		g.drawString(Texts.sp[60], 50, 32);
		pw.drawLine(
			Texts.sp[61] + turn + " / " + treasureLimit,
			0,
			0,
			g);
		pw.drawLine(Texts.sp[63] + treasureCount, 0, 2, g);
		switch (turn % 3) {
			case 0 :
				pw.drawLine(Texts.sp[64], 0, 3, g);
				break;
			case 1 :
				pw.drawLine(Texts.sp[65], 0, 3, g);
				break;
			case 2 :
				pw.drawLine(Texts.sp[66], 0, 3, g);
				break;
		}
	}
}
