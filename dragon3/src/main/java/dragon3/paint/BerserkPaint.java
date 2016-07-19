package dragon3.paint;

import dragon3.UnitWorks;
import dragon3.anime.AnimeManager;
import dragon3.common.Body;
import dragon3.common.constant.Page;
import dragon3.common.constant.BodyAttribute;

/**
 * @author k-saito
 */
public class BerserkPaint extends PaintAdapter {

	private Body ba;


	/**
	 * @param uw
	 * @param ba
	 */
	public BerserkPaint(UnitWorks uw, Body ba) {
		super(uw);
		this.ba = ba;
		map.clear(Page.P10, 0);
		map.setData(Page.P10, ba.getX(), ba.getY(), 4);
		map.setData(Page.P30, ba.getX(), ba.getY(), 6);
		map.setData(Page.P40, ba.getX(), ba.getY(), 0);
	}

	/**
	 * 
	 */
	public void action() {
		map.setData(Page.P30, ba.getX(), ba.getY(), 0);
		anime.systemAnime(AnimeManager.ID_REFRESH, ba.getX(), ba.getY());
		anime.statusAnime(AnimeManager.STATUS_BERSERK, ba.getX(), ba.getY());
		map.setData(Page.P10, ba.getX(), ba.getY(), 0);
		PaintUtils.setBasicPaint(uw);
	}

	/**
	 * 
	 */
	private void setStatus() {
		ba.addAttr(BodyAttribute.BERSERK);
		ba.setHp(ba.getHpMax());
		ba.setStr((int) (ba.getStr() * 1.5));
		ba.setDef((int) (ba.getDef() * 1.5));
		ba.setMst((int) (ba.getMst() * 1.5));
		ba.setMdf((int) (ba.getMdf() * 1.5));
		ba.setHit((int) (ba.getHit() * 1.5));
		ba.setMis((int) (ba.getMis() * 1.5));
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
		setStatus();
		uw.bersekChara(ba);
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
