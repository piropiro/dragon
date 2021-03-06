/*
 * Created on 2005/01/03
 */
package dragon3.panel.paint;


import mine.paint.MineGraphics;
import mine.paint.MineImage;
import dragon3.common.constant.Texts;
import dragon3.panel.PanelWorks;

/**
 * @author saito
 */
public class ItemPaint implements DataPanelPainter {

	private int turn;
	private int limit;
	private int tikei;
	private MineImage[] back;

	public ItemPaint(int turn, int limit, int tikei, MineImage[] back) {
		this.turn = turn;
		this.limit = limit;
		this.tikei = tikei;
		this.back = back;
	}

	public void paint(PanelWorks pw, MineGraphics g) {
		g.drawImage(back[tikei], 10, 10);
		if (limit == 0) {
			g.drawString(Texts.sp[67], 50, 32);
		} else if (limit < turn) {
			g.drawString(Texts.sp[68], 50, 32);
			pw.drawLine(Texts.sp[69] + turn + " / " + limit, 0, 0, g);
			pw.drawLine(Texts.sp[70], 0, 2, g);
			pw.drawLine(Texts.sp[71], 0, 3, g);
		} else {
			g.drawString(Texts.sp[72], 50, 32);
			pw.drawLine(Texts.sp[73] + turn + " / " + limit, 0, 0, g);
			pw.drawLine(Texts.sp[74], 0, 2, g);
			pw.drawLine(Texts.sp[75], 0, 3, g);
		}
	}
}
