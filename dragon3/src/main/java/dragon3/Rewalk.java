package dragon3;

import mine.paint.UnitMap;
import dragon3.common.Body;
import dragon3.common.constant.Page;
import dragon3.image.ImageManager;
import dragon3.map.MapWorks;
import dragon3.paint.PaintUtils;
import dragon3.panel.PanelManager;

public class Rewalk {

	static UnitWorks uw;
	static PanelManager pm;
	static ImageManager image;
	static UnitMap map;
	static MapWorks mw;
	static int x, y;

	/*** Setup *******************************/

	public static void setup(UnitWorks uws) {
		uw = uws;
		image = uw.getImageManager();
		pm = uw.getPanelManager();
		map = uw.getUnitMap();
		mw = uw.getMapWorks();
	}

	/*** Set *********************************/

	public static void set(Body b) {
		x = b.getX();
		y = b.getY();
	}

	/*** Rewalk ******************************/

	public static void rewalk(Body b) {
		map.clear(Page.P40, 0);
		map.clear(Page.P10, 0);
		map.setData(Page.P20, b.getX(), b.getY(), 0);
		map.setData(Page.P30, b.getX(), b.getY(), 0);
		int sts = map.getData(Page.P50, b.getX(), b.getY());
		map.setData(Page.P50, b.getX(), b.getY(), 0);
		b.setX(x);
		b.setY(y);
		map.setData(Page.P20, b.getX(), b.getY(), b.getImageNum());
		map.setData(Page.P50, b.getX(), b.getY(), sts);
		pm.closeHp();
		pm.closeSmall();
		pm.closeData();
		PaintUtils.setWalkPaint(uw, b);
		mw.repaint();
	}

	/*** Walk Judge ********************/

	public static boolean isWalked(Body b) {
		if (b.getX() != x)
			return true;
		if (b.getY() != y)
			return true;
		return false;
	}
}
