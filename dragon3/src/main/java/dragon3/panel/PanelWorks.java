/*
 * Created on 2005/01/03
 */
package dragon3.panel;

import mine.paint.MineGraphics;
import dragon3.common.Body;

/**
 * @author saito
 */
public interface PanelWorks {
	public void drawMain(Body ba, MineGraphics g, boolean hpDrawFlag);

	public void drawLine(String name, int st, int x, int y, MineGraphics g);
	
	public void drawLine(String name, int x, int y, MineGraphics g);
	
	public void drawText(String lines, MineGraphics g);
}
