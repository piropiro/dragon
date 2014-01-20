package dragon3.paint;

import dragon3.UnitWorks;
import dragon3.common.Body;
import dragon3.common.constant.Page;

/**
 * @author k-saito
 */
public class ChangePaint extends PaintAdapter {

	private Body ba;
	private Body bb;

	/**
	 * @param uw
	 * @param ba
	 * @param bb
	 */
	public ChangePaint(UnitWorks uw, Body ba, Body bb) {
		super(uw);
		this.ba = ba;
		this.bb = bb;
		map.clear(Page.P10, 0);
		map.setData(Page.P10, ba.getX(), ba.getY(), 4);
		map.setData(Page.P30, ba.getX(), ba.getY(), 5);
		map.setData(Page.P40, ba.getX(), ba.getY(), 0);
	}

	/**
	 * 
	 */
	public void action() {
		anime.eraseAnime(ba.getX(), ba.getY());
		anime.summonAnime(bb.getImageNum(), bb.getX(), bb.getY());
		map.setData(Page.P10, bb.getX(), bb.getY(), 0);
		map.setData(Page.P20, bb.getX(), bb.getY(), bb.getImageNum());
		mw.repaint();
		PaintUtils.setBasicPaint(uw);
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#mouseMoved(int, int)
	 */
	public void mouseMoved(int x, int y) {
		rightPressed();
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#leftPressed()
	 */
	public void leftPressed() {
		bb.setX(ba.getX());
		bb.setY(ba.getY());
		uw.changeChara(ba, bb);
		action();
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#rightPressed()
	 */
	public void rightPressed() {
		map.setData(Page.P10, ba.getX(), ba.getY(), 0);
		map.setData(Page.P30, ba.getX(), ba.getY(), 1);
		mw.repaint();
		PaintUtils.setBasicPaint(uw);
	}

}
