/*
 * Created on 2005/01/03
 */
package dragon3.panel.paint;


import mine.paint.MineGraphics;
import mine.paint.MineImage;
import dragon3.bean.SaveData;
import dragon3.common.constant.Texts;
import dragon3.common.util.MoveUtils;
import dragon3.manage.SaveManager;
import dragon3.panel.PanelWorks;

/**
 * @author saito
 */
public class Score1Paint implements DataPanelPainter {

	private SaveManager sm;
	private MineImage[] back;

	public Score1Paint(SaveManager sm, MineImage[] back) {
		this.sm = sm;
		this.back = back;
	}

	public void paint(PanelWorks pw, MineGraphics g) {
		SaveData sd = sm.getSaveData();
		g.drawImage(back[MoveUtils.C_BLUE], 10, 10);
		g.drawString(sd.getPlayerName(), 50, 32);
		pw.drawLine(Texts.sp[24] + sd.getStage(), 0, 0, g);
		pw.drawLine(Texts.sp[25] + sd.getItem(), 0, 1, g);
		pw.drawLine(Texts.sp[26] + sd.getKill(), 0, 2, g);
		long time = sm.getPlayTime();
		long hour = time / 3600000;
		long min = time % 3600000 / 60000;
		long sec = time % 60000 / 1000;
		String times = "";
		times += (hour > 9) ? ("" + hour) : ("0" + hour);
		times += ":";
		times += (min > 9) ? ("" + min) : ("0" + min);
		times += ":";
		times += (sec > 9) ? ("" + sec) : ("0" + sec);
		pw.drawLine(Texts.sp[27] + times, 0, 3, g);

	}
}
