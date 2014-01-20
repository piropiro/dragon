/*
 * 作成日: 2004/03/27
 */
package dragon3.paint;

import java.util.List;

import dragon3.FightManager;
import dragon3.UnitWorks;
import dragon3.card.CardPaint;
import dragon3.common.Body;
import dragon3.map.MapWorks;

/**
 * @author k-saito
 */
public class PaintUtils {

	public static void setWalkPaint(UnitWorks uw, Body b) {
		MapWorks mw = uw.getMapWorks();
		mw.setPaintListener(new WalkPaint(uw, b));
	}
	public static void setWaitPaint(UnitWorks uw) {
		MapWorks mw = uw.getMapWorks();
		mw.setPaintListener(new WaitPaint(uw));
	}
	public static void setBasicPaint(UnitWorks uw) {
		MapWorks mw = uw.getMapWorks();
		mw.setPaintListener(new BasicPaint(uw));
	}
	public static void setScorePaint(UnitWorks uw) {
		MapWorks mw = uw.getMapWorks();
		mw.setPaintListener(new ScorePaint(uw));
	}
	public static void setPutPlayersPaint(UnitWorks uw, List<Body> charaList, List<Body> playerList) {
		MapWorks mw = uw.getMapWorks();
		mw.setPaintListener(new PutPlayersPaint(uw, charaList, playerList));
	}
	public static void setEndPaint(UnitWorks uw, Body b) {
		MapWorks mw = uw.getMapWorks();
		mw.setPaintListener(new EndPaint(uw, b));
	}
	public static void setAttackPaint(UnitWorks uw, FightManager fm, Body b) {
		MapWorks mw = uw.getMapWorks();
		mw.setPaintListener(new AttackPaint(uw, fm, b));
	}
	public static void setKakuseiPaint(UnitWorks uw, Body b) {
		MapWorks mw = uw.getMapWorks();
		mw.setPaintListener(new KakuseiPaint(uw, b));
	}
	public static void setBerserkPaint(UnitWorks uw, Body b) {
		MapWorks mw = uw.getMapWorks();
		mw.setPaintListener(new BerserkPaint(uw, b));
	}
	public static void setChangePaint(UnitWorks uw, Body ba, Body bb) {
		MapWorks mw = uw.getMapWorks();
		mw.setPaintListener(new ChangePaint(uw, ba, bb));
	}
	public static void setButtonPaint(UnitWorks uw, int x, int y, PaintListener pl, int type) {
		MapWorks mw = uw.getMapWorks();
		mw.setPaintListener(new ButtonPaint(uw, x, y, pl, type));
	}
	public static void setCardPaint(UnitWorks uw, Body ba, Body bb) {
		MapWorks mw = uw.getMapWorks();
		mw.setPaintListener(new CardPaint(uw, ba, bb));
	}

}
