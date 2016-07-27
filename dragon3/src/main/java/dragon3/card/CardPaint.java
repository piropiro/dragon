package dragon3.card;

import dragon3.UnitWorks;
import dragon3.common.Body;
import dragon3.common.constant.GameColor;
import dragon3.common.constant.Texts;
import dragon3.paint.PaintAdapter;

public class CardPaint extends PaintAdapter {

	@SuppressWarnings("unused")
	private Body ba;
	@SuppressWarnings("unused")
	private Body bb;

	@SuppressWarnings("unused")
	private int select;


	/**
	 * @param uw
	 * @param ba
	 * @param bb
	 */
	public CardPaint(UnitWorks uw, Body ba, Body bb) {
		super(uw);
		this.ba = ba;
		this.bb = bb;
		uw.displayCardBattle(ba, bb);
		setHelp();
	}

	/**
	 * 
	 */
	private void setHelp() {
		pm.displayHelp(mw.getWaku(), Texts.help[Texts.H_CARD], GameColor.BLUE);
	}


	/**
	 * 
	 */
	public void action() {
		if (!uw.isCardBattleEnd()) {
			mw.setEventListener(this);
		}
	}

	public void leftPressed() {
	}
	public void rightPressed() {
	}
	public void mouseMoved(int x, int y) {
	}
	public void setSelectBody(Body b) {
	}
	public void setSelectPlace(int x, int y) {
	}

}
