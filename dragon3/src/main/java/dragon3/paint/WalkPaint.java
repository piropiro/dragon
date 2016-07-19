package dragon3.paint;

import java.awt.Point;
import java.util.List;

import mine.paint.UnitMap;
import dragon3.FightManager;
import dragon3.Rewalk;
import dragon3.UnitWorks;
import dragon3.common.Body;
import dragon3.common.constant.GameColors;
import dragon3.common.constant.Page;
import dragon3.common.constant.Texts;
import dragon3.common.constant.BodyAttribute;
import dragon3.common.util.MoveUtils;

public class WalkPaint extends PaintAdapter {

	private Body ba;
	private int step;

	/**
	 * @param uw
	 * @param charaList
	 * @param ba
	 */
	public WalkPaint(UnitWorks uw, Body ba) {
		super(uw);
		this.ba = ba;
		List<Body> charaList = uw.getCharaList();

		step = MoveUtils.getStep(ba);
		int[] stepList = MoveUtils.getStepList(ba);

		for (int i = 0; i < stepList.length; i++) {
			if (stepList[i] > step) {
				stepList[i] = 255;
			}
		}
		map.clear(Page.P02, 0);
		map.change(Page.P00, Page.P02, stepList);
		map.change(Page.P02, Page.P00, 2, 1);
		map.copyPage(Page.P02, Page.P12);
		for (Body b : charaList) {
			if (!b.isAlive())
				continue;
			map.setData(Page.P02, b.getX(), b.getY(), 255);
		}
		map.setData(Page.P02, ba.getX(), ba.getY(), 1);
		map.paintStep(Page.P02, Page.P03, ba.getX(), ba.getY(), step + 1);
		map.change(Page.P03, UnitMap.FALSE, Page.P10, 0, 1);
		for (Body b : charaList) {
			if (!b.isAlive())
				continue;
			map.setData(Page.P10, b.getX(), b.getY(), 0);
		}
		map.setData(Page.P10, ba.getX(), ba.getY(), 1);
		setHelp();
	}

	/**
	 *
	 */
	private void setHelp() {
		if (!GameColors.isPlayer(ba))
			return;
		uw.getPanelManager().displayHelp(mw.getWaku(), Texts.help[Texts.H_WALK], GameColors.BLUE);
	}

	/**
	 *
	 */
	public void action() {
		Rewalk.set(ba);
		Point waku = mw.getWaku();
		walk(waku.x, waku.y);
		map.clear(Page.P10, 0);
		FightManager fm = new FightManager(uw, ba);
		PaintUtils.setAttackPaint(uw, fm, ba);
	}

	/**
	 * @param x
	 * @param y
	 */
	private void walk(int x, int y) {
		map.paintStep(Page.P02, Page.P03, x, y, step);
		anime.walkAnime(ba.getX(), ba.getY());
		ba.setX(x);
		ba.setY(y);
	}

	/**
	 * @param x
	 * @param y
	 */
	public void enemy(int x, int y) {
		Rewalk.set(ba);
		walk(x, y);
		map.clear(Page.P10, 0);
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#leftPressed()
	 */
	public void leftPressed() {
		Point waku = mw.getWaku();
		if (map.getData(Page.P10, waku.x, waku.y) == 0) {
			rightPressed();
			return;
		}

		if (ba.hasAttr(BodyAttribute.SLEEP))
			return;
		if (GameColors.isPlayer(ba)) {
			if (ba.hasAttr(BodyAttribute.CHARM))
				return;
		} else {
			if (!ba.hasAttr(BodyAttribute.CHARM))
				return;
		}
		action();
	}

	/* (非 Javadoc)
	 * @see dragon3.paint.PaintListener#rightPressed()
	 */
	public void rightPressed() {
		map.clear(Page.P10, 0);
		PaintUtils.setBasicPaint(uw);
		mw.repaint();
	}

}
