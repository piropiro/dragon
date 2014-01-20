package dragon3.paint;

import java.util.List;

import dragon3.Statics;
import dragon3.UnitWorks;
import dragon3.anime.AnimeManager;
import dragon3.common.Body;
import dragon3.common.constant.Page;

public class KakuseiPaint extends PaintAdapter {

	private Body sister;
	private Body kakusei;

	/**
	 * @param uw
	 * @param sister
	 */
	public KakuseiPaint(UnitWorks uw, Body sister) {
		super(uw);
		this.sister = sister;
		map.clear(Page.P10, 0);
		map.setData(Page.P10, sister.getX(), sister.getY(), 4);
		map.setData(Page.P30, sister.getX(), sister.getY(), 2);
		map.setData(Page.P40, sister.getX(), sister.getY(), 0);
	}

	/**
	 *
	 */
	public void action() {
		map.setData(Page.P10, sister.getX(), sister.getY(), 0);
		map.setData(Page.P30, kakusei.getX(), kakusei.getY(), 0);
		map.setData(Page.P50, kakusei.getX(), kakusei.getY(), 0);
		anime.systemAnime(AnimeManager.ID_KAKUSEI, kakusei.getX(), kakusei.getY());
		map.setData(Page.P20, kakusei.getX(), kakusei.getY(), kakusei.getImageNum());
		mw.repaint();
		PaintUtils.setBasicPaint(uw);
	}

	/**
	 * @return
	 */
	private Body getKakuseiData() {
		List<Body> data = Statics.getEnemyData(99);
		return (Body) data.get(0);
	}

	/**
	 *
	 */
	private void setStatus() {
		int t = sister.getDef() + sister.getMdf() + sister.getMis();
		kakusei.setStr(sister.getStr() * 2 + t);
		kakusei.setMst(sister.getMst() + t);
		kakusei.setHit(sister.getHit() + t);
		kakusei.setMax();
		kakusei.setX(sister.getX());
		kakusei.setY(sister.getY());
		kakusei.setGoalX(sister.getGoalX());
		kakusei.setGoalY(sister.getGoalY());
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
		kakusei = getKakuseiData();
		setStatus();
		uw.changeChara(sister, kakusei);
		action();
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#rightPressed()
	 */
	public void rightPressed() {
		map.setData(Page.P10, sister.getX(), sister.getY(), 0);
		map.setData(Page.P30, sister.getX(), sister.getY(), 1);
		mw.repaint();
		PaintUtils.setBasicPaint(uw);
	}
}
