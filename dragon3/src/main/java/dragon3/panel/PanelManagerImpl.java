/*
 * Created on 2005/01/08
 */
package dragon3.panel;

import java.awt.Point;

import mine.paint.UnitMap;
import dragon3.common.Body;
import dragon3.common.constant.Page;
import dragon3.common.util.Equip;
import dragon3.common.util.MoveUtils;
import dragon3.manage.Attack;
import dragon3.manage.SaveManager;
import dragon3.manage.SummonManager;
import dragon3.manage.TreasureManager;
import dragon3.manage.TurnManager;

/**
 * @author saito
 */
public class PanelManagerImpl implements PanelManager {

	private DataPanel dataP1;
	private DataPanel dataP2;
	private HPanel hpP1;
	private HPanel hpP2;
	private HelpPanel helpP;
	private LargePanel largeP;
	private MessagePanel messageP;
	private SmallPanel smallP;

	private TreasureManager treasure;
	private SummonManager summon;
	
	private TurnManager turnManager;
	private UnitMap map;

	private boolean hpFlag;
	
	private boolean helpVisible;

	public PanelManagerImpl() {
	}



	public void displayData(int x, int y) {
		int tikei = map.getData(Page.P00, x, y);
		if (tikei == MoveUtils.WHITE) {
			dataP1.displayData(new Point(x, y), turnManager.getTurn(), treasure.getLimitTurn(), treasure.getCount());
		} else {
			dataP1.displayPlace(new Point(x, y), tikei);
		}
	}

	public boolean displayPlace(int x, int y) {
		int tikei = map.getData(Page.P00, x, y);
		Point p = new Point(x, y);
		switch (tikei) {
			case MoveUtils.C_BLUE :
			case MoveUtils.C_RED :
			case MoveUtils.S_BLUE :
				dataP1.displayPlace(p, tikei);
				return true;
			case MoveUtils.CLOSE_BOX :
			case MoveUtils.OPEN_BOX :
			case MoveUtils.BROKEN_BOX :
				dataP1.displayItem(p, turnManager.getTurn(), treasure.getLimitTurn(p), tikei);
				return true;
			case MoveUtils.CLOSE_MAGIC :
			case MoveUtils.OPEN_MAGIC :
				dataP1.displaySummon(p, turnManager.getTurn(), summon.getLimitTurn(p), tikei);
				return true;
			default :
				dataP1.setVisible(false);
				return false;
		}
	}
	public void displayCampData(int x, int y, int tikei, String bgcolor) {
		dataP1.displayCamp(new Point(x, y), tikei, bgcolor);
	}

	public void displayStatus(Body b) {
		dataP1.displayStatus(b);
	}
	public void displayAnalyze(Body b) {
		dataP1.displayNext(b);
	}
	public void displayWazaList(Body b) {
		dataP1.displayWazaList(b);
	}
	public void displayWaza(Body b, int i) {
		dataP1.displayWaza(b, i);
	}
	
	public void displayScore(Equip equip, SaveManager saveManager) {
		dataP1.displayScore1(saveManager);
		dataP2.displayScore2(equip, saveManager);
	}
	public void repaintData() {
		dataP1.repaint();
		dataP2.repaint();
	}
	public void displayAttack(Attack attack, Attack counter) {
		dataP1.displayAttack(attack, counter);
		dataP2.displayAttack(attack, counter);
	}

	public void closeData() {
		dataP1.setVisible(false);
		dataP2.setVisible(false);
	}

	/*** HPanel ************************************************/

	public void selectHp(boolean hpFlag_) {
		this.hpFlag = hpFlag_;
	}
	public void displayHp(Body ba, Body bb, int damage, boolean hit) {
		if (hpFlag) {
			hpP1.display(ba, bb, damage, hit);
		} else {
			hpP2.display(ba, bb, damage, hit);
		}
	}

	public void damageHp(Body b, int damage) {
		if (hpFlag) {
			dataP2.damage(b, damage);
			hpP1.damage(damage);
		} else {
			dataP1.damage(b, damage);
			hpP2.damage(damage);
		}
	}

	public void animeHp() {
		if (hpFlag) {
			hpP1.henka();
			dataP2.henka();
		} else {
			hpP2.henka();
			dataP1.henka();
		}
	}

	public void closeHp() {
		hpP1.setVisible(false);
		hpP2.setVisible(false);
	}
	
	// MPanel
	public void addMessage(String message) {
		messageP.addMessage(message);
	}

	public void startMessage(Body b) {
		messageP.display(b);
	}
	
	// SmallPanel
	public void displaySmall(String label, String color, Body b) {
		smallP.display(label, color, b.getX(), b.getY());
	}
	
	public void closeSmall() {
		smallP.setVisible(false);
	}	
	
	// HelpPanel
	
	public void displayHelp(Point p, String[] line, String color) {
		helpP.setLocate(p.x, p.y, true);
		helpP.setLine(line, color);
		helpP.setVisible(helpVisible);
		helpP.repaint();
	}
	public void setHelpLocation(int x, int y) {
		helpP.setLocate(x, y, false);
	}
	public void setHelpVisible(boolean helpVisible){
		this.helpVisible = helpVisible;
		helpP.setVisible(helpVisible);
	}
	public boolean isHelpVisible(){
		return helpVisible;
	}
	public void closeHelp(){
		helpP.setVisible(false);
	}

	// LargePanel
	
	public void displayLarge(String text, String color, int sleep) {
		largeP.display(text, color, sleep);
	}

	/**
	 * @param dataP1 The dataP1 to set.
	 */
	public void setDataP1(DataPanel dataP1) {
		this.dataP1 = dataP1;
	}
	/**
	 * @param dataP2 The dataP2 to set.
	 */
	public void setDataP2(DataPanel dataP2) {
		this.dataP2 = dataP2;
	}
	/**
	 * @param helpP The helpP to set.
	 */
	public void setHelpP(HelpPanel helpP) {
		this.helpP = helpP;
	}
	/**
	 * @param hpFlag The hpFlag to set.
	 */
	public void setHpFlag(boolean hpFlag) {
		this.hpFlag = hpFlag;
	}
	/**
	 * @param hpP1 The hpP1 to set.
	 */
	public void setHpP1(HPanel hpP1) {
		this.hpP1 = hpP1;
	}
	/**
	 * @param hpP2 The hpP2 to set.
	 */
	public void setHpP2(HPanel hpP2) {
		this.hpP2 = hpP2;
	}
	/**
	 * @param largeP The largeP to set.
	 */
	public void setLargeP(LargePanel largeP) {
		this.largeP = largeP;
	}

	/**
	 * @param messageP The messageP to set.
	 */
	public void setMessageP(MessagePanel messageP) {
		this.messageP = messageP;
	}
	/**
	 * @param smallP The smallP to set.
	 */
	public void setSmallP(SmallPanel smallP) {
		this.smallP = smallP;
	}
	/**
	 * @param summon The summon to set.
	 */
	public void setSummon(SummonManager summon) {
		this.summon = summon;
	}
	/**
	 * @param treasure The treasure to set.
	 */
	public void setTreasure(TreasureManager treasure) {
		this.treasure = treasure;
	}
	
	public void setTurnManager(TurnManager turnManager) {
		this.turnManager = turnManager;
	}

	public void setUnitMap(UnitMap map) {
		this.map = map;
	}
	
	
}
