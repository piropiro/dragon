package dragon3.panel;

import card.CardCanvas;
import dragon3.FrameWorks;
import dragon3.UnitWorks;
import dragon3.anime.AnimePanel;
import dragon3.bean.AnimeData;
import dragon3.bean.load.AnimeDataLoader;
import dragon3.common.Body;
import dragon3.common.DataList;
import dragon3.common.constant.GameColor;
import dragon3.common.constant.Page;
import dragon3.common.util.Equip;
import dragon3.common.util.MoveUtils;
import dragon3.image.ImageManager;
import dragon3.manage.Attack;
import dragon3.manage.SaveManager;
import dragon3.manage.SummonManager;
import dragon3.manage.TreasureManager;
import dragon3.manage.TurnManager;
import dragon3.map.MapPanel;
import lombok.Getter;
import mine.event.SleepManager;
import mine.paint.MineImageLoader;
import mine.paint.UnitMap;
import mine.util.Point;

/**
 * @author saito
 */
public class PanelManagerImpl implements PanelManager {

	@Getter private AnimePanel animeP;
	@Getter private MapPanel mapP;
	@Getter private CardCanvas cardP;
	private DataPanel dataP1;
	private DataPanel dataP2;
	private HPanel hpP1;
	private HPanel hpP2;
	private HelpPanel helpP;
	private LargePanel largeP;
	private MessagePanel messageP;
	private SmallPanel smallP;
	
	private TreasureManager treasureManager;
	private SummonManager summonManager;
	
	private TurnManager turnManager;
	private UnitMap map;
	
	private boolean helpVisible;

	public PanelManagerImpl(FrameWorks fw, UnitWorks uw, UnitMap map, SleepManager sleepManager, ImageManager imageManager, MineImageLoader mil) {
		this.map = map;
		
		DataList<AnimeData> animeList = AnimeDataLoader.loadAnimeList();
		
		// AnimePanel
		animeP = new AnimePanel(fw.getAnimePanel(), sleepManager, map, animeList, imageManager);

		// MapPanel
		mapP = new MapPanel(fw.getMapPanel(), uw, map);
		
		// HPanel
		hpP1 = new HPanel(fw.getHPanel1(), sleepManager, true);
		hpP2 = new HPanel(fw.getHPanel2(), sleepManager, false);
		
		// DataPanel
		dataP1 = new DataPanel(fw.getDataPanel1(), sleepManager, imageManager, true);
		dataP2 = new DataPanel(fw.getDataPanel2(), sleepManager, imageManager, false);
		
		// HelpPanel
		helpP = new HelpPanel(fw.getHelpPanel());
		
		// SmallPanel
		smallP = new SmallPanel(fw.getSmallPanel());
		
		// LargePanel
		largeP = new LargePanel(fw.getLargePanel());
		
		// MessagePanel
		messageP = new MessagePanel(fw.getMessagePanel(), sleepManager, imageManager);
		
		// CardPanel
		cardP = new CardCanvas(fw.getCardPanel(), mil, sleepManager);
		cardP.setVisible(false);
	}



	public void displayData(int x, int y) {
		int tikei = map.getData(Page.P00, x, y);
		if (tikei == MoveUtils.WHITE) {
			dataP1.displayData(new Point(x, y), turnManager.getTurn(), treasureManager.getLimitTurn(), treasureManager.getCount());
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
				dataP1.displayItem(
						p, 
						turnManager.getTurn(), 
						treasureManager.getLimitTurn(p), 
						tikei);
				return true;
			case MoveUtils.CLOSE_MAGIC :
			case MoveUtils.OPEN_MAGIC :
				dataP1.displaySummon(p, turnManager.getTurn(), summonManager.getLimitTurn(p), tikei);
				return true;
			default :
				dataP1.setVisible(false);
				return false;
		}
	}
	public void displayCampData(int x, int y, int tikei, GameColor bgcolor) {
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
		dataP2.displayAttack(counter, attack);
	}

	public void closeData() {
		dataP1.setVisible(false);
		dataP2.setVisible(false);
	}

	/*** HPanel ************************************************/

	public void displayHp(boolean hpFlag, Body ba, Body bb, int damage, boolean hit) {
		if (hpFlag) {
			hpP1.display(ba, bb, damage, hit);
		} else {
			hpP2.display(ba, bb, damage, hit);
		}
	}

	public void damageHp(boolean hpFlag, Body b, int damage) {
		if (hpFlag) {
			dataP2.damage(b, damage);
			hpP1.damage(damage);
		} else {
			dataP1.damage(b, damage);
			hpP2.damage(damage);
		}
	}

	public void animeHp(boolean hpFlag) {
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
	@Override
	public void displaySmall(String label, GameColor color, Body b) {
		smallP.display(label, color, b.getX(), b.getY());
	}
	
	public void closeSmall() {
		smallP.setVisible(false);
	}	
	
	// HelpPanel
	@Override
	public void displayHelp(Point p, String[] line, GameColor color) {
		helpP.setLocate(p.x, p.y, true);
		helpP.setLine(line, color);
		helpP.setVisible(helpVisible);
		helpP.repaint();
	}
	@Override
	public void setHelpLocation(int x, int y) {
		helpP.setLocate(x, y, false);
	}
	@Override
	public void setHelpVisible(boolean helpVisible){
		this.helpVisible = helpVisible;
		helpP.setVisible(helpVisible);
	}
	@Override
	public boolean isHelpVisible(){
		return helpVisible;
	}
	@Override
	public void closeHelp(){
		helpP.setVisible(false);
	}

	// LargePanel
	@Override
	public void displayLarge(String text, GameColor color, int sleep) {
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
	public void setSummon(SummonManager summonManager) {
		this.summonManager = summonManager;
	}
	/**
	 * @param treasure The treasure to set.
	 */
	public void setTreasure(TreasureManager treasureManager) {
		this.treasureManager = treasureManager;
	}
	
	public void setTurnManager(TurnManager turnManager) {
		this.turnManager = turnManager;
	}

	public void setUnitMap(UnitMap map) {
		this.map = map;
	}
	
	
}
