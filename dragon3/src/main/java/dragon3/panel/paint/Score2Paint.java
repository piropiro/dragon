/*
 * Created on 2005/01/03
 */
package dragon3.panel.paint;


import mine.paint.MineGraphics;
import dragon3.bean.SaveData;
import dragon3.common.constant.Texts;
import dragon3.common.util.Equip;
import dragon3.common.util.Rank;
import dragon3.manage.SaveManager;
import dragon3.panel.PanelWorks;

/**
 * @author saito
 */
public class Score2Paint implements DataPanelPainter {

	private Rank rank;
	private SaveManager sm;

	public Score2Paint(Equip equip, SaveManager sm) {
		this.sm = sm;
		rank = new Rank(equip, sm);
	}

	public void paint(PanelWorks pw, MineGraphics g) {
		SaveData sd = sm.getSaveData();
		pw.drawLine(Texts.sp[28] + sd.getTurn(), 0, -1, g);
		pw.drawLine(Texts.sp[29] + sd.getEscape(), 0, 0, g);
		pw.drawLine(Texts.sp[30] + sd.getDead(), 0, 1, g);
		pw.drawLine(Texts.sp[31] + rank.getScore1(), 0, 2, g);
		pw.drawLine(Texts.sp[32] + rank.getScore2(), 0, 3, g);

	}
}
