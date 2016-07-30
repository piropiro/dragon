package dragon3.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dragon3.Statics;
import dragon3.anime.AnimeManager;
import dragon3.camp.Camp;
import dragon3.camp.Equip;
import dragon3.card.CardManager;
import dragon3.common.Body;
import dragon3.common.constant.BodyAttribute;
import dragon3.common.constant.GameColor;
import dragon3.common.constant.Page;
import dragon3.common.constant.Texts;
import dragon3.common.util.MoveUtils;
import dragon3.data.StageData;
import dragon3.data.load.BodyDataLoader;
import dragon3.image.ImageManager;
import dragon3.manage.LevelManager;
import dragon3.manage.RewalkManager;
import dragon3.manage.SummonManager;
import dragon3.manage.SummonManagerImpl;
import dragon3.manage.TreasureManager;
import dragon3.manage.TreasureManagerImpl;
import dragon3.manage.TurnManager;
import dragon3.manage.TurnManagerImpl;
import dragon3.map.MapWorks;
import dragon3.paint.BasicPaint;
import dragon3.paint.PaintUtils;
import dragon3.paint.TitlePaint;
import dragon3.panel.PanelManager;
import dragon3.panel.PanelManagerImpl;
import dragon3.save.SaveManager;
import dragon3.save.SaveManagerImpl;
import dragon3.stage.StageManager;
import dragon3.view.FrameWorks;
import lombok.Getter;
import mine.MineException;
import mine.MineUtils;
import mine.event.SleepManager;
import mine.paint.MineImageLoader;
import mine.paint.UnitMap;
import mine.util.Point;

public class DragonController implements UnitWorks, CommandListener {

	private UnitMap map;
	private FrameWorks fw;
	private MapWorks mw;
	
	private ImageManager imageManager;
	private AnimeManager animeManager;
	private PanelManagerImpl panelManager;

	private List<Body> Charas;
	private List<Body> Players;
	private List<Body> Enemys;

	private MineImageLoader mil;
	private SleepManager sleepManager;

	private CardManager cardManager;
	private TurnManager turnManager;
	private SaveManager saveManager;
	@Getter private RewalkManager rewalkManager;
	private Equip equip;
	private Camp camp;
	private TreasureManager treasure;
	private SummonManager summon;
	private StageManager stageManager;

	private Point blueCrystal;
	private Point redCrystal;

	private boolean escape;

	/*** Constructer *************************************/

	public DragonController(FrameWorks fw) {
		super();
		this.fw = fw;
		this.mil = fw.getImageLoader();
		this.sleepManager = fw.getSleepManager();

		try {
			imageManager = new ImageManager(mil);
		} catch (MineException e) {
			throw new RuntimeException(e);
		}

		saveManager = new SaveManagerImpl(this);
		Charas = new ArrayList<>();
		map = createMap();
		turnManager = new TurnManagerImpl(this);
		
		panelManager = new PanelManagerImpl(fw, this, map, sleepManager, imageManager, mil);
		panelManager.setTurnManager(turnManager);
		
		animeManager = panelManager.getAnimeP();
		mw = panelManager.getMapP();
		
		rewalkManager = new RewalkManager(this);
		
		cardManager = new CardManager(panelManager.getCardP(), this, map, panelManager, sleepManager, imageManager);
		equip = saveManager.loadData("slgs.dat");

		stageManager = panelManager.getStageSelectP();

	}

	/*** Title ***********************************/

	public void title() {
		fw.setMenu(FrameWorks.T_TITLE);
		animeManager.openTitle();
		mw.setEventListener(new TitlePaint(this));
	}

	/*** Setup ***********************************/

	public void startup() {
		if (isTutorial()) {
			panelManager.setHelpVisible(true);
		}
		campStart();
	}

	/*** UnitMap *********************************/

	private UnitMap createMap() {
//		int mapW = 20;
//		int mapH = 15;
//		int unitW = 32;
//		int unitH = 32;

		UnitMap map = new UnitMap(14, 20, 15, mil);
		map.setTile(Page.P00, imageManager.getBack(), -1);
		map.setTile(Page.P10, imageManager.getWaku()[1], 0);
		map.setTile(Page.P20, imageManager.getBodyImageList().getImageList(), 0);
		map.setTile(Page.P30, imageManager.getWaku()[0], 0);
		map.setTile(Page.P40, imageManager.getWaku()[2], 0);
		map.setTile(Page.P50, imageManager.getStatus(), 0);
		map.setVisible(Page.P00, true);
		map.setVisible(Page.P10, true);
		map.setVisible(Page.P20, true);
		map.setVisible(Page.P30, true);
		map.setVisible(Page.P40, true);
		map.setVisible(Page.P50, true);
		
		return map;
	}


	/*** Create Chara *********************************/

	private void insertCharas(List<Body> v) {
		Charas.addAll(v);
	}

	/*** Deploy Chara *****************************************/

	public void putUnit(List<Body> v) {
		map.clear(Page.P20, 0);
		for (Body b : v) {
			if (!b.isAlive())
				continue;
			map.setData(Page.P20, b.getX(), b.getY(), b.getImageNum());
		}
	}

	/*** Camp **************************************/

	private void Camp() {
		Charas.clear();
		camp = new Camp(this, treasure, equip);
		camp.repaint(Statics.getCampMap());
		PaintUtils.setCampPaint(this, camp);
		panelManager.getCardP().setVisible(false);
		mw.repaint();
	}

	/*** Map Load **********************************/

	private StageData mapLoad(StageData stageData) {
		int[][] data = Statics.getMapData(stageData.getId());
		if (data != null) {
			map.setPage(Page.P00, data);
		}
		map.clear(Page.P10, 0);
		map.clear(Page.P20, 0);
		map.clear(Page.P30, 0);
		map.clear(Page.P40, 0);
		map.clear(Page.P50, 0);
		reverseMap();
		setCrystal();
		Charas.clear();
		Players = equip.getPlayers();
		String stageId = stageManager.getSelectedStage().getId();
		int addLevel = saveManager.getSaveData().getStarNum(stageId) * 10;
		Enemys = this.loadEnemyData(stageData.getId(), addLevel);
		//randomize(Enemys);
		reverse(Enemys);
		treasure = new TreasureManagerImpl(this, Enemys);
		summon = new SummonManagerImpl(this, Enemys);
		panelManager.setTreasure(treasure);
		panelManager.setSummon(summon);
		insertCharas(Enemys);
		putUnit(Enemys);
		turnManager.reset();
		PaintUtils.setWaitPaint(this);
		panelManager.getCardP().setVisible(false);
		mw.repaint();
		
		return stageData;
	}



	/*** Deploy End *************************************/

	public void setMensEnd() {
		map.change(Page.P00, MoveUtils.S_BLUE, Page.P00, 0);
		map.change(Page.P00, MoveUtils.S_RED, Page.P00, 0);
		putUnit(Charas);
		turnManager.playerTurnStart();
		panelManager.displayLarge("Turn " + turnManager.getTurn(), GameColor.BLUE, 1500);
	}


	/*** Start *************************************/

	private void stageSelect() {
		fw.setMenu(FrameWorks.T_STAGESELECT);
		panelManager.displayStageSelect(saveManager.getSaveData());
	}
	
	@Override
	public void stageStart(StageData stageData) {
		fw.setMenu(FrameWorks.T_SETMENS);
		mapLoad(stageData);
		panelManager.displayLarge(stageData.getName(), GameColor.BLUE, 1500);
		
//		if (saveManager.isFirst()) {
//			panelManager.displayLarge("Tutorial", GameColor.BLUE, 1500);
//		} else if (saveManager.isFinalStage()) {
//			panelManager.displayLarge("Final Stage", GameColor.BLUE, 1500);
//		} else {
//			char n = (char) ('A' + saveManager.getMapNum() - 1);
//			panelManager.displayLarge("Stage " + n, GameColor.BLUE, 1500);
//		}
		PaintUtils.setPutPlayersPaint(this, Charas, Players);
		mw.repaint();
		panelManager.closeStageSelect();
	}

	private void campStart() {
		fw.setMenu(FrameWorks.T_CAMP);
		panelManager.closeSmall();
		panelManager.closeHelp();
		panelManager.closeStageSelect();
		Camp();
	}

	/*** Map Reverse ***********************************/

	private void reverseMap() {
		if (!saveManager.getSaveData().isReverse())
			return;
		int[] af = { -1, 0, 20 - 1, 0, -1, 15 - 1 };
		int[][] data = new int[15][20];
		MineUtils.affine(map.getPage(Page.P00), data, af);
		map.setPage(Page.P00, data);
	}

	private void reverse(List<Body> v) {
		if (!saveManager.getSaveData().isReverse())
			return;
		for (Body b : v) {
			b.setX(19 - b.getX());
			b.setY(14 - b.getY());
			if (b.getGoalX() != 0 || b.getGoalY() != 0) {
				b.setGoalX(19 - b.getGoalX());
				b.setGoalY(14 - b.getGoalY());
			}
		}
	}

	/*** UnitWorks *************************************/

	/*** Main ************************/

	public Body search(int x, int y) {
		for (Body b : Charas) {
			if (!b.isAlive())
				continue;
			if (b.getX() == x && b.getY() == y)
				return b;
		}
		return null;
	}

	/*** Set Crystal ***************************/

	public void setCrystal() {
		int width = map.getMapWidth();
		int height = map.getMapHeight();
		blueCrystal = null;
		redCrystal = null;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (map.getData(Page.P00, x, y) == MoveUtils.C_BLUE) {
					blueCrystal = new Point(x, y);
				}
				if (map.getData(Page.P00, x, y) == MoveUtils.C_RED) {
					redCrystal = new Point(x, y);
				}
			}
		}
	}

	public Point getCrystal(GameColor color) {
		if (color.equals(GameColor.BLUE)) {
			return blueCrystal;
		} else if (color.equals(GameColor.RED)) {
			return redCrystal;
		} else {
			return null;
		}
	}

	/*** Die ******************************************************/

	// ba win
	// bb lose
	public void dead(Body ba, Body bb) {
		animeManager.eraseAnime(bb.getX(), bb.getY());

		if (ba == null)
			return;
		if (GameColor.isPlayer(ba)) {
			saveManager.getSaveData().countKill();
			equip.getExp(ba, bb);
			treasure.getTreasure(bb, false);
		}
		if (GameColor.isPlayer(bb)) {
			saveManager.getSaveData().countDead();
		}
	}

	public void levelup(Body ba) {
		if (ba != null && GameColor.isPlayer(ba)) {
			new LevelManager(equip, panelManager).levelup(ba);
		}
	}

	/*** Escape ************************************/

	public void escape() {
		if (escape) {
			saveManager.getSaveData().countEscape();
			PaintUtils.setWaitPaint(this);
			panelManager.displayLarge("ESCAPE", GameColor.RED, 3000);
			fw.setMenu(FrameWorks.T_CLEAR);
		} else {
			panelManager.displayLarge("FAILED", GameColor.RED, 500);
		}
	}


	/*** End *************************************/

	public void setEnd(Body ba, boolean flag) {
		escape = false;

		if (ba.isAlive()) {
			treasure.searchTreasure(ba);
			map.setData(Page.P30, ba.getX(), ba.getY(), 1);
		}
		mw.repaint();
		if (endJudge(ba))
			return;

		if (isTurnEnd()) {
			turnManager.enemyTurnStart();
			return;
		}
		BasicPaint bp = new BasicPaint(this);
		mw.setEventListener(bp);
		if (flag)
			bp.leftPressed();
	}

	/*** Turn End ***********************************/

	private boolean isTurnEnd() {
		for (Body b : Charas) {
			if (!b.isAlive())
				continue;
			if (!GameColor.isPlayer(b))
				continue;
			if (b.hasAttr(BodyAttribute.SLEEP))
				continue;
			if (b.hasAttr(BodyAttribute.CHARM))
				continue;
			if (getChangeChara(b) != null)
				return false;
			if (map.getData(Page.P30, b.getX(), b.getY()) == 0)
				return false;
		}
		return true;
	}

	/*** End Judge *******************************************/

	public boolean endJudge(Body ba) {
		if (blueJudge1())
			return true;
		if (redJudge1())
			return true;
		if (blueJudge2(ba))
			return true;
		if (redJudge2(ba))
			return true;
		return false;
	}

	/*** Game Clear ***********************************/

	private boolean blueJudge1() {
		for (Body b : Charas) {
			if (!GameColor.isPlayer(b)) {
				if (b.isAlive())
					return false;
			}
		}
		stageClear();
		return true;
	}
	private boolean blueJudge2(Body ba) {
		if (!ba.isAlive())
			return false;
		if (ba.getColor() != GameColor.BLUE)
			return false;
		if (map.getData(Page.P00, ba.getX(), ba.getY()) != MoveUtils.C_RED)
			return false;

		map.fillDia(Page.P00, ba.getX(), ba.getY(), 1, MoveUtils.C_REDC);
		if (GameColor.isPlayer(ba))
			stageClear();
		else
			gameOver();
		return true;
	}

	private void stageClear() {
		treasure.getClearItem();
		PaintUtils.setWaitPaint(this);
		panelManager.closeData();

		if (stageManager.isFinalStage()) {
			panelManager.displayLarge("ALL CLEAR!!", GameColor.BLUE, 5000);
		} else {
			panelManager.displayLarge("STAGE CLEAR", GameColor.BLUE, 5000);
		}
		saveManager.getSaveData().countStarNum(stageManager.getSelectedStage().getId());
		fw.setMenu(FrameWorks.T_CLEAR);
		panelManager.displayHelp(mw.getWaku(), GameColor.BLUE, Texts.help[Texts.H_CLEAR]);
	}

	/*** Game Over ****************************/

	private boolean redJudge1() {
		for (Body b : Charas) {
			if (GameColor.isPlayer(b)) {
				if (b.isAlive())
					return false;
			}
		}
		gameOver();
		return true;
	}
	private boolean redJudge2(Body ba) {
		if (!ba.isAlive())
			return false;
		if (ba.getColor() == GameColor.BLUE)
			return false;
		if (map.getData(Page.P00, ba.getX(), ba.getY()) != MoveUtils.C_BLUE)
			return false;

		map.fillDia(Page.P00, ba.getX(), ba.getY(), 1, MoveUtils.C_BLUEC);
		if (GameColor.isPlayer(ba))
			stageClear();
		else
			gameOver();
		return true;
	}

	private void gameOver() {
		PaintUtils.setWaitPaint(this);
		panelManager.displayLarge("GAME OVER", GameColor.RED, 5000);
		fw.setMenu(FrameWorks.T_GAMEOVER);
		panelManager.displayHelp(mw.getWaku(), GameColor.BLUE, Texts.help[Texts.H_OVER]);
	}

	/***************************************************/

	public boolean have(Body b) {
		return equip.have(b);
	}
	public void limitOver() {
		summon.summon();
		treasure.limitOver();
	}


	public void message() {
		treasure.message();
	}

	public void addMember(Body b) {
		treasure.addMember(b);
	}

	public void bersekChara(Body doll) {
		Charas.remove(doll);
		Charas.add(0, doll);
	}

	public void changeChara(Body before, Body after) {
		Charas.remove(before);
		Charas.add(after);
	}

	public Body getChangeChara(Body before) {
		return equip.getChangeChara(before);
	}

	/*** Score ****************************************/

	private void showScore() {
		panelManager.displayScore(equip, saveManager.getSaveData());

		PaintUtils.setScorePaint(this);
		fw.setMenu(FrameWorks.T_SCORE);
	}

	@Override
	public void backToCamp() {
		panelManager.closeData();
		fw.setMenu(FrameWorks.T_CAMP);
		PaintUtils.setCampPaint(this, camp);
		camp.repaint(Statics.getCampMap());
		mw.repaint();
	}
	
	@Override
	public void backFromImogari() {
		PaintUtils.setCampPaint(this, camp);
	}

	/*** KeyEvent **************************************/

	@Override
	public void executeFKeyCommand(int n, boolean shiftDown) {
//		if (mouseManager.isAlive())
//			return;
		String filename = "slgs" + n + ".dat";
		if (shiftDown) {
			saveManager.saveData(filename, equip);
			panelManager.displayLarge("Save " + n, GameColor.BLUE, 1500);
		} else {
			File f = new File(filename);
			if (!f.exists()) {
				panelManager.displayLarge("Not Found " + n, GameColor.RED, 1500);
				return;
			}
			equip = saveManager.loadData(filename);
			treasure = null;
			animeManager.setVisible(false);
			campStart();
			panelManager.displayLarge("Load " + n, GameColor.BLUE, 1500);
		}
	}

	/*** MenuBar ***************************************/

	@Override
	public void executeMenuCommand(String command) {
		switch (command) {
		case "help":
			if (panelManager.isHelpVisible()) {
				panelManager.displayLarge(Texts.help_off, GameColor.BLUE, 1000);
				panelManager.setHelpVisible(false);
			} else {
				panelManager.displayLarge(Texts.help_on, GameColor.BLUE, 1000);
				panelManager.setHelpVisible(true);
			}
			break;
		case "remove":
			camp.removeDust();
			break;
		case "sort":
			camp.sortItem();
			break;
		case "back":
			backToCamp();
			break;
		case "score":
			showScore();
			break;
		case "camp":
			campStart();
			break;
		case "escape":
			escape();
			break;
		case "stage":
			stageSelect();
			break;
		case "start":
			setMensEnd();
			break;
		case "turnend":
			turnManager.enemyTurnStart();
			break;
		case "mapload":
			equip = saveManager.loadData("slgs.dat");

			if (saveManager.getSaveData().getPlayerName() != null) {
				stageSelect();
			} else {
				title();
			}
			break;
		case "campload":
			equip = saveManager.loadData("slgs.dat");

			if (saveManager.getSaveData().getPlayerName() != null) {
				campStart();
			} else {
				title();
			}
			break;
		case "save":
			saveManager.saveData("slgs.dat", equip);
			panelManager.displayLarge("SAVE", GameColor.BLUE, 1500);
		default:
		}
	}

	@Override
	public List<Body> loadEnemyData(String stageId, int addLevel) {

		List<Body> bodyList = BodyDataLoader.loadBodyDataList(stageId, addLevel);
		
		for (Body body : bodyList) {
			body.setImageNum(imageManager.getBodyImageList().getNum(body.base.getImage()));
		}
		return bodyList;
	}
	
	@Override
	public SaveManager getSaveManager() {
		return saveManager;
	}

	@Override
	public TurnManager getTurnManager() {
		return turnManager;
	}

	@Override
	public SleepManager getSleepManager() {
		return sleepManager;
	}

	@Override
	public AnimeManager getAnimeManager() {
		return animeManager;
	}

	@Override
	public PanelManager getPanelManager() {
		return panelManager;
	}

	@Override
	public MapWorks getMapWorks() {
		return mw;
	}

	@Override
	public FrameWorks getFrameWorks() {
		return fw;
	}

	@Override
	public UnitMap getUnitMap() {
		return map;
	}

	@Override
	public List<Body> getCharaList() {
		return Charas;
	}

	@Override
	public ImageManager getImageManager() {
		return imageManager;
	}

	@Override
	public void displayCardBattle(Body ba, Body bb) {
		cardManager.setup(ba, bb);
		cardManager.display();
	}

	@Override
	public boolean isCardBattleEnd() {
		return cardManager.isEnd();
	}
	
	@Override
	public boolean isTutorial() {
		return saveManager.getSaveData().sumStars() == 0;
	}
}
