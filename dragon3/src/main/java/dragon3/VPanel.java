package dragon3;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JLayeredPane;

import org.apache.commons.beanutils.BeanUtils;

import mine.MineException;
import mine.MineUtils;
import mine.awt.ImageLoaderAWT;
import mine.awt.MineAwtUtils;
import mine.awt.MouseManagerAWT;
import mine.awt.SleepManagerAWT;
import mine.awt.TextDialog;
import mine.event.SleepManager;
import mine.paint.MineImageLoader;
import mine.paint.UnitMap;
import dragon3.anime.AnimeManager;
import dragon3.anime.AnimePanel;
import dragon3.bean.AnimeData;
import dragon3.bean.BodyData;
import dragon3.bean.DeployData;
import dragon3.bean.StageData;
import dragon3.bean.load.AnimeDataLoader;
import dragon3.camp.Camp;
import dragon3.card.CardPanel;
import dragon3.common.Body;
import dragon3.common.DataList;
import dragon3.common.constant.GameColors;
import dragon3.common.constant.Page;
import dragon3.common.constant.Texts;
import dragon3.common.constant.BodyAttribute;
import dragon3.common.util.Equip;
import dragon3.common.util.MoveUtils;
import dragon3.common.util.Rank;
import dragon3.image.ImageManager;
import dragon3.impl.SaveManagerImpl;
import dragon3.impl.SummonManagerImpl;
import dragon3.impl.TreasureManagerImpl;
import dragon3.impl.TurnManagerImpl;
import dragon3.manage.SaveManager;
import dragon3.manage.SummonManager;
import dragon3.manage.TreasureManager;
import dragon3.manage.TurnManager;
import dragon3.map.MapPanel;
import dragon3.map.MapWorks;
import dragon3.paint.BasicPaint;
import dragon3.paint.PaintUtils;
import dragon3.paint.TitlePaint;
import dragon3.panel.DataPanel;
import dragon3.panel.HPanel;
import dragon3.panel.HelpPanel;
import dragon3.panel.LargePanel;
import dragon3.panel.MessagePanel;
import dragon3.panel.PanelManager;
import dragon3.panel.PanelManagerImpl;
import dragon3.panel.SmallPanel;

public class VPanel extends JLayeredPane implements UnitWorks, ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private UnitMap map;
	private FrameWorks mw;
	private MapPanel up;
	private CardPanel cp;

	private ImageManager imageManager;
	private AnimeManager animeManager;
	private PanelManagerImpl panelManager;

	private List<Body> Charas;
	private List<Body> Players;
	private List<Body> Enemys;

	private MineImageLoader mil;
	private SleepManagerAWT sleepManager;
	private MouseManagerAWT mouseManager;

	private TurnManagerImpl turnManager;
	private SaveManagerImpl saveManager;
	private Equip equip;
	private Camp camp;
	private TreasureManager treasure;
	private SummonManager summon;

	private Point blueCrystal;
	private Point redCrystal;

	@SuppressWarnings("unused")
	private boolean waitFlag;
	private boolean escape;

	/*** Constructer *************************************/

	public VPanel(FrameWorks mw) {
		super();
		this.mw = mw;
		MineAwtUtils.setSize(this, 640, 480);
		setBackground(new Color(0, 0, 150));

		mil = new ImageLoaderAWT();

		try {
			imageManager = new ImageManager(mil);
		} catch (MineException e) {
			throw new RuntimeException(e);
		}

		mouseManager = new MouseManagerAWT();
		sleepManager = new SleepManagerAWT(this);
		saveManager = new SaveManagerImpl(this);
		Charas = new ArrayList<>();
		map = createMap();
		turnManager = new TurnManagerImpl(this);
		Pinit();

		mouseManager.setMouseAllListener(up);
		addMouseListener(mouseManager);
		addMouseMotionListener(mouseManager);
		addKeyListener(this);
		equip = saveManager.loadData("slgs.dat");


	}

	/*** Title ***********************************/

	public void title() {
		mw.setMenu(FrameWorks.T_TITLE);
		animeManager.openTitle();
		up.setPaintListener(new TitlePaint(this));
	}

	/*** Setup ***********************************/

	public void startup() {
		if (saveManager.isFirst()) {
			panelManager.setHelpVisible(true);
			campStart();
		} else {
			campStart();
		}
	}

	/*** Panel Init ****************************************/

	private void Pinit() {
		DataList<AnimeData> animeList = AnimeDataLoader.loadAnimeList();
		AnimePanel ap = new AnimePanel(sleepManager, map, animeList, imageManager);
		animeManager = ap;

		up = new MapPanel(this);
		HPanel hp = new HPanel(sleepManager, true);
		HPanel hp2 = new HPanel(sleepManager, false);
		DataPanel sp = new DataPanel(sleepManager, imageManager, true);
		DataPanel sp2 = new DataPanel(sleepManager, imageManager, false);
		HelpPanel help = new HelpPanel();
		SmallPanel tp = new SmallPanel();
		LargePanel lp = new LargePanel();
		MessagePanel mp = new MessagePanel(sleepManager, imageManager);
		cp = new CardPanel(this);


		panelManager = new PanelManagerImpl();
		panelManager.setUnitMap(map);
		panelManager.setTurnManager(turnManager);
		panelManager.setDataP1(sp);
		panelManager.setDataP2(sp2);
		panelManager.setHpP1(hp);
		panelManager.setHpP2(hp2);
		panelManager.setHelpP(help);
		panelManager.setSmallP(tp);
		panelManager.setLargeP(lp);
		panelManager.setMessageP(mp);

		Rewalk.setup(this);
		
		setLayout(null);

		add(up, new Integer(1));
		add(cp, new Integer(2));
		add(help, new Integer(3));
		add(sp, new Integer(4));
		add(sp2, new Integer(5));
		add(mp, new Integer(6));
		add(lp, new Integer(7));
		add(hp2, new Integer(8));
		add(hp, new Integer(9));
		add(ap, new Integer(10));
		add(tp, new Integer(11));

		help.setVisible(false);
		cp.setVisible(false);
		tp.setVisible(false);
		hp.setVisible(false);
		hp2.setVisible(false);
		animeManager.setVisible(false);
		lp.setVisible(false);
		mp.setVisible(false);
		sp.setVisible(false);
		sp2.setVisible(false);
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
		map.setTile(Page.P20, imageManager.getBodyList().getImageList(), 0);
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
	// 0-0 Background
	// 0-1 Reverse
	// 0-2 Step Paint Data ( Chara Ari )
	// 0-3 Step Paint Result ( Chara Ari )
	// 1-0 Under Frame
	// 1-1 Search Data
	// 1-2 Step Paint Data ( Chara Nasi )
	// 1-3 Step Paint Result ( Chara Nasi )
	// 2-0 Chara
	// 2-1 Counter
	// 3-0 END
	// 4-0 Over Frame
	// 4-1 Animation Data
	// 5-0 Status

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
		camp.repaint(saveManager.getCampMap());
		PaintUtils.setCampPaint(this, camp);
		cp.setVisible(false);
		up.repaint();
	}

	/*** Map Load **********************************/

	private void mapLoad() {
		StageData stageData = Statics.getStageData("D01");
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
		Enemys = this.loadEnemyData(stageData.getId());
		randomize(Enemys);
		reverse(Enemys);
		treasure = new TreasureManagerImpl(this, Enemys);
		summon = new SummonManagerImpl(this, Enemys);
		panelManager.setTreasure(treasure);
		panelManager.setSummon(summon);
		insertCharas(Enemys);
		putUnit(Enemys);
		turnManager.reset();
		PaintUtils.setWaitPaint(this);
		cp.setVisible(false);
		up.repaint();
	}



	/*** Deploy End *************************************/

	public void setMensEnd() {
		map.change(Page.P00, MoveUtils.S_BLUE, Page.P00, 0);
		map.change(Page.P00, MoveUtils.S_RED, Page.P00, 0);
		putUnit(Charas);
		turnManager.playerTurnStart();
		panelManager.displayLarge("Turn " + turnManager.getTurn(), GameColors.BLUE, 1500);
	}


	/*** Start *************************************/

	private void stageStart() {
		mw.setMenu(DragonBuster.T_SETMENS);
		mapLoad();
		if (saveManager.isFirst()) {
			panelManager.displayLarge("Tutorial", GameColors.BLUE, 1500);
		} else if (saveManager.isFinalStage()) {
			panelManager.displayLarge("Final Stage", GameColors.BLUE, 1500);
		} else {
			char n = (char) ('A' + saveManager.getMapNum() - 1);
			panelManager.displayLarge("Stage " + n, GameColors.BLUE, 1500);
		}
		PaintUtils.setPutPlayersPaint(this, Charas, Players);
		up.repaint();
	}

	private void campStart() {
		mw.setMenu(DragonBuster.T_CAMP);
		panelManager.closeSmall();
		panelManager.closeHelp();
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

	/*** Randomize **************************************************/

	private void randomize(List<Body> v) {
		int lv = saveManager.getEnemyLevel();
		int rate = 256;
		for (int i = lv; i > 0; i--)
			rate *= 1.5;

		for (Body b : v) {
			if (b.getLevel() != 0)
				b.setLevel(b.getLevel() + (lv * 10));
			b.setHpMax(b.getHpMax() * rate / 256);
			Equip.restrict(b);
			b.setMax();
			b.setAttrSet(new LinkedHashSet<BodyAttribute>());
			if (b.getColor() == GameColors.RED) {
				if (!b.hasAttr(BodyAttribute.TALKABLE)) {
					b.setStr(Math.max(0, b.getStr() - 2));
					b.setDef(Math.max(0, b.getDef() - 2));
					b.setMst(Math.max(0, b.getMst() - 2));
					b.setMdf(Math.max(0, b.getMdf() - 2));
					b.setHit(Math.max(0, b.getHit() - 2));
					b.setMis(Math.max(0, b.getMis() - 2));
				}
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

	public Point getCrystal(GameColors color) {
		if (color.equals(GameColors.BLUE)) {
			return blueCrystal;
		} else if (color.equals(GameColors.RED)) {
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
		if (GameColors.isPlayer(ba)) {
			saveManager.getSaveData().countKill();
			equip.getEXP(ba, bb);
			treasure.getTreasure(bb, false);
		}
		if (GameColors.isPlayer(bb)) {
			saveManager.getSaveData().countDead();
		}
	}

	public void levelup(Body ba) {
		if (ba != null && GameColors.isPlayer(ba)) {
			new Level(equip, panelManager).levelup(ba);
		}
	}

	/*** Escape ************************************/

	public void escape() {
		if (escape) {
			saveManager.getSaveData().countEscape();
			PaintUtils.setWaitPaint(this);
			panelManager.displayLarge("ESCAPE", GameColors.RED, 3000);
			mw.setMenu(DragonBuster.T_CLEAR);
		} else {
			panelManager.displayLarge("FAILED", GameColors.RED, 500);
		}
	}


	/*** End *************************************/

	public void setEnd(Body ba, boolean flag) {
		escape = false;

		if (ba.isAlive()) {
			treasure.searchTreasure(ba);
			map.setData(Page.P30, ba.getX(), ba.getY(), 1);
		}
		up.repaint();
		if (endJudge(ba))
			return;

		if (isTurnEnd()) {
			turnManager.enemyTurnStart();
			return;
		}
		BasicPaint bp = new BasicPaint(this);
		up.setPaintListener(bp);
		if (flag)
			bp.leftPressed();
	}

	/*** Turn End ***********************************/

	private boolean isTurnEnd() {
		for (Body b : Charas) {
			if (!b.isAlive())
				continue;
			if (!GameColors.isPlayer(b))
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
			if (!GameColors.isPlayer(b)) {
				if (b.isAlive())
					return false;
			}
		}
		gameClear();
		return true;
	}
	private boolean blueJudge2(Body ba) {
		if (!ba.isAlive())
			return false;
		if (ba.getColor() != GameColors.BLUE)
			return false;
		if (map.getData(Page.P00, ba.getX(), ba.getY()) != MoveUtils.C_RED)
			return false;

		map.fillDia(Page.P00, ba.getX(), ba.getY(), 1, MoveUtils.C_REDC);
		if (GameColors.isPlayer(ba))
			gameClear();
		else
			gameOver();
		return true;
	}

	private void gameClear() {
		treasure.getClearItem();
		PaintUtils.setWaitPaint(this);
		panelManager.closeData();

		if (saveManager.isFinalStage()) {
			panelManager.displayLarge("ALL CLEAR!!", GameColors.BLUE, 5000);
		} else {
			panelManager.displayLarge("STAGE CLEAR", GameColors.BLUE, 5000);
		}
		saveManager.stageClear();
		mw.setMenu(DragonBuster.T_CLEAR);
		panelManager.displayHelp(up.getWaku(), Texts.help[Texts.H_CLEAR], GameColors.BLUE);
	}

	/*** Game Over ****************************/

	private boolean redJudge1() {
		for (Body b : Charas) {
			if (GameColors.isPlayer(b)) {
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
		if (ba.getColor() == GameColors.BLUE)
			return false;
		if (map.getData(Page.P00, ba.getX(), ba.getY()) != MoveUtils.C_BLUE)
			return false;

		map.fillDia(Page.P00, ba.getX(), ba.getY(), 1, MoveUtils.C_BLUEC);
		if (GameColors.isPlayer(ba))
			gameClear();
		else
			gameOver();
		return true;
	}

	private void gameOver() {
		PaintUtils.setWaitPaint(this);
		panelManager.displayLarge("GAME OVER", GameColors.RED, 5000);
		mw.setMenu(DragonBuster.T_GAMEOVER);
		panelManager.displayHelp(up.getWaku(), Texts.help[Texts.H_OVER], GameColors.BLUE);
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

	/*** Name Change **************************************/

	@Deprecated
	public void nameChange() {
		if (saveManager.getSaveData().getPlayerName() != null)
			return;
		Body hero = null;
		Body sister = null;
		for (int x = 1; x <= 8; x += 7) {
			for (int y = 1; y <= 10; y += 3) {
				Body b = equip.search(x, y);
				if (b == null)
					continue;
				b.setAttrSet(new LinkedHashSet<BodyAttribute>());
				if (b.hasAttr(BodyAttribute.HERO))
					hero = b;
				if (b.hasAttr(BodyAttribute.SISTER))
					sister = b;
			}
		}

		TextDialog rd = new TextDialog(mw.getFrame(), 20);
		if (hero != null) {
			rd.setup(Texts.heroname, hero.getName());
			rd.show();
			if (rd.isOK() && !rd.getText().equals("")) {
				hero.setName(rd.getText());
			}
		}
		if (sister != null) {
			rd.setup(Texts.sistername, sister.getName());
			rd.show();
			if (rd.isOK() && !rd.getText().equals("")) {
				sister.setName(rd.getText());
			}
		}

		rd.setup(Texts.rankname, "");
		String name = null;

		while (name == null || name.equals("")) {
			rd.show();
			if (rd.isOK()) {
				name = rd.getText();
			}
		}
		saveManager.getSaveData().setPlayerName(name);

		saveManager.saveData("slgs.dat", equip);
	}

	/*** Score ****************************************/

	private void showScore() {
		panelManager.displayScore(equip, saveManager);

		PaintUtils.setScorePaint(this);
		mw.setMenu(DragonBuster.T_SCORE);
	}

	private void sendScore(boolean hayasaf) {

		TextDialog rd = new TextDialog(mw.getFrame(), 20);

		String comment = "";
		rd.setup(Texts.comment, comment);
		rd.show();
		if (rd.isOK()) {
			comment = rd.getText();
		} else {
			panelManager.displayLarge(Texts.rankcancel, GameColors.RED, 2000);
			return;
		}
		Rank rank = new Rank(equip, saveManager);

		int rankNum;
		if (hayasaf) {
			rankNum = rank.sendHayasaScore(comment);
		} else {
			rankNum = rank.sendTuyosaScore(comment);
		}
		if (rankNum != 0) {
			if (hayasaf) {
				panelManager.displayLarge(
					Texts.speed_rank + rankNum + Texts.idesu,
					GameColors.BLUE,
					4000);
			} else {
				panelManager.displayLarge(
					Texts.power_rank + rankNum + Texts.idesu,
					GameColors.BLUE,
					4000);
			}
		} else {
			panelManager.displayLarge(Texts.conn_fail, GameColors.RED, 2000);
		}
	}
	public void backToCamp() {
		panelManager.closeData();
		mw.setMenu(DragonBuster.T_CAMP);
		PaintUtils.setCampPaint(this, camp);
		camp.repaint(saveManager.getCampMap());
		up.repaint();
	}
	public void backFromImogari() {
		PaintUtils.setCampPaint(this, camp);
	}

	/*** KeyEvent **************************************/

	public void keyTyped(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyPressed(KeyEvent e) {
		int n = 0;
		switch (e.getKeyCode()) {
			case KeyEvent.VK_F1 :
				n = 1;
				break;
			case KeyEvent.VK_F2 :
				n = 2;
				break;
			case KeyEvent.VK_F3 :
				n = 3;
				break;
			case KeyEvent.VK_F4 :
				n = 4;
				break;
			case KeyEvent.VK_F5 :
				n = 5;
				break;
			case KeyEvent.VK_F6 :
				n = 6;
				break;
			case KeyEvent.VK_F7 :
				n = 7;
				break;
			case KeyEvent.VK_F8 :
				n = 8;
				break;
			default :
				return;
		}
		if (mouseManager.isAlive())
			return;
		String filename = "slgs" + n + ".dat";
		if (e.isShiftDown()) {
			saveManager.saveData(filename, equip);
			panelManager.displayLarge("Save " + n, GameColors.BLUE, 1500);
		} else {
			File f = new File(filename);
			if (!f.exists()) {
				panelManager.displayLarge("Not Found " + n, GameColors.RED, 1500);
				return;
			}
			equip = saveManager.loadData(filename);
			treasure = null;
			animeManager.setVisible(false);
			campStart();
			panelManager.displayLarge("Load " + n, GameColors.BLUE, 1500);
		}
	}

	/*** MenuBar ***************************************/

	public void actionPerformed(ActionEvent e) {
		requestFocus();
		if (mouseManager.isAlive())
			return;
		AbstractButton b = (AbstractButton) e.getSource();
		String command = b.getActionCommand();
		if (command.equals("help")) {
			if (panelManager.isHelpVisible()) {
				panelManager.displayLarge(Texts.help_off, GameColors.BLUE, 1000);
				panelManager.setHelpVisible(false);
			} else {
				panelManager.displayLarge(Texts.help_on, GameColors.BLUE, 1000);
				panelManager.setHelpVisible(true);
			}
		} else if (command.equals("remove")) {
			camp.removeDust();
		} else if (command.equals("sort")) {
			camp.sortItem();
		} else if (command.equals("hayasa")) {
			sendScore(true);
		} else if (command.equals("tuyosa")) {
			sendScore(false);
		} else if (command.equals("back")) {
			backToCamp();
		} else if (command.equals("score")) {
			showScore();
		} else if (command.equals("camp")) {
			campStart();
		} else if (command.equals("escape")) {
			escape();
		} else if (command.equals("stage")) {
			stageStart();
		} else if (command.equals("left")) {
			saveManager.selectLR(SaveManagerImpl.LEFT);
			stageStart();
		} else if (command.equals("right")) {
			saveManager.selectLR(SaveManagerImpl.RIGHT);
			stageStart();
		} else if (command.equals("start")) {
			setMensEnd();
		} else if (command.equals("turnend")) {
			turnManager.enemyTurnStart();
		} else if (command.equals("mapload")) {
			equip = saveManager.loadData("slgs.dat");

			if (saveManager.getSaveData().getPlayerName() != null) {
				stageStart();
			} else {
				title();
			}
		} else if (command.equals("campload")) {
			equip = saveManager.loadData("slgs.dat");

			if (saveManager.getSaveData().getPlayerName() != null) {
				campStart();
			} else {
				title();
			}
		} else if (command.equals("save")) {
			saveManager.saveData("slgs.dat", equip);
			panelManager.displayLarge("SAVE", GameColors.BLUE, 1500);
		} else {
			for (int i = 0; i < 16; i++) {
				if (command.equals("Stage " + (char) ('A' + i))) {
					saveManager.getSaveData().setMapNum(i);
					stageStart();
				}
			}
		}
	}

	public List<Body> loadEnemyData(String file) {
		
		List<Body> enemyList = new ArrayList<>();
		
		List<DeployData> deployList = Statics.getDeployData(file);
		for (DeployData deploy : deployList) {
			
			Body body = new Body();
			
			BodyData bodyData = Statics.bodyList.getData(deploy.getBodyId());
			
			try {
				BeanUtils.copyProperties(body, bodyData);
				body.setHpMax(bodyData.getHp());
				body.setImageNum(imageManager.getBodyList().getNum(body.getImage()));
				for (BodyAttribute type : bodyData.getAttrList()) {
					body.addAttr(type);
				}
				body.removeAttr(BodyAttribute.NONE);
				body.getWazaList().removeIf(a -> a.equals("none") );
				
				body.setDeployType(deploy.getDeployType());
				body.setColor(deploy.getColor());
				body.setLevel(deploy.getLevel());
				body.setScope(deploy.getScope());
				body.setRange(deploy.getRange());
				body.setLimitTurn(deploy.getLimitTurn());
				body.setX(deploy.getX());
				body.setY(deploy.getY());
				body.setGoalX(deploy.getGoalX());
				body.setGoalY(deploy.getGoalY());
				
				System.out.println(body);

			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}
			

			
			
			enemyList.add(body);
		}

		return enemyList;
	}
	
	public SaveManager getSaveManager() {
		return saveManager;
	}

	public TurnManager getTurnManager() {
		return turnManager;
	}

	/* (non-Javadoc)
	 * @see dragon3.UnitWorks#getSleepManager()
	 */
	public SleepManager getSleepManager() {
		return sleepManager;
	}

	/* (non-Javadoc)
	 * @see dragon3.UnitWorks#getAnimeManager()
	 */
	public AnimeManager getAnimeManager() {
		return animeManager;
	}

	/* (non-Javadoc)
	 * @see dragon3.UnitWorks#getPanelManager()
	 */
	public PanelManager getPanelManager() {
		return panelManager;
	}

	/* (non-Javadoc)
	 * @see dragon3.UnitWorks#getMapWorks()
	 */
	public MapWorks getMapWorks() {
		return up;
	}

	public FrameWorks getFrameWorks() {
		return mw;
	}

	/* (non-Javadoc)
	 * @see dragon3.UnitWorks#getUnitMap()
	 */
	public UnitMap getUnitMap() {
		return map;
	}

	/* (non-Javadoc)
	 * @see dragon3.UnitWorks#getCharaList()
	 */
	public List<Body> getCharaList() {
		return Charas;
	}

	public ImageManager getImageManager() {
		return imageManager;
	}

	public void displayCardBattle(Body ba, Body bb) {
		cp.setup(ba, bb);
		cp.display();
	}

	public boolean isCardBattleEnd() {
		return cp.isEnd();
	}
}
