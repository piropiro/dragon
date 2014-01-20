/*
 * Created on 2005/01/03
 */
package dragon3.panel.paint;


import mine.paint.MineGraphics;
import dragon3.Statics;
import dragon3.bean.Data;
import dragon3.common.Body;
import dragon3.panel.PanelWorks;

/**
 * @author saito
 */
public class WazaListPaint implements DataPanelPainter {

	private Body ba;

	public WazaListPaint(Body ba) {
		this.ba = ba;
	}

	public void paint(PanelWorks pw, MineGraphics g) {
		pw.drawMain(ba, g, false);
		int n = 0;
		String[] wazaList = ba.getWazaList();
		for (int i = 0; i < wazaList.length; i++) {
			if (wazaList[i] == null) { 
				continue;
			}
			Data waza = Statics.wazaList.getData(wazaList[i]);
			pw.drawLine(waza.getName(), 0, n++, g);
			if (n == 4)
				break;
		}
	}
}
