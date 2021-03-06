package dragon3.map;

import java.awt.Point;

import dragon3.paint.PaintListener;

public interface MapWorks {

	/*** Paint *************************/

	public void repaint();
	public void ppaint(int x, int y);
	public void ppaint(int[] box);

	/*** Waku *************************/

	public Point getWaku();
	public void wakuMove(int x, int y);
	public void wakuPaint(boolean flag);

	/*** Set Listener *******************/

	public void setPaintListener(PaintListener pl);
	public PaintListener getPaintListener();
}
