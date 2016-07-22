package dragon2.panel;





import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.AbstractButton;
import javax.swing.JPanel;

import dragon2.ActionBase;
import dragon2.Collection;
import dragon2.Iconable;
import dragon2.MainWorks;
import dragon2.Material;
import dragon2.Rewalk;
import dragon2.SaveManager;
import dragon2.Statics;
import dragon2.Summon;
import dragon2.Treasure;
import dragon2.TurnManager;
import dragon2.UnitWorks;
import dragon2.anime.AnimePanel;
import dragon2.attack.AttackBase;
import dragon2.camp.Camp;
import dragon2.card.CardPaint;
import dragon2.card.CardPanel;
import dragon2.common.Body;
import dragon2.common.constant.Colors;
import dragon2.common.constant.Kinds;
import dragon2.common.constant.Texts;
import dragon2.common.constant.Types;
import dragon2.common.util.Equip;
import dragon2.common.util.Luck;
import dragon2.common.util.Rank;
import dragon2.paint.BasicPaint;
import dragon2.paint.CollectionPaint;
import dragon2.paint.ImogariPaint;
import dragon2.paint.PaintBase;
import dragon2.paint.ScorePaint;
import dragon2.paint.SetMensPaint;
import dragon2.paint.TitlePaint;
import dragon2.paint.WazalistPaint;
import mine.*;

public class VPanel extends JPanelBase implements UnitWorks, ActionListener,
		KeyListener {

	public VPanel(MainWorks mainworks) {
		mw = mainworks;
		setBackground(new Color(0, 0, 150));
		Charas = new Vector();
		col = new Collection(this);
		sm = new SaveManager(this);
		tm = new TurnManager(Charas, this);
		Vinit();
		Pinit();
		addMouseListener(up);
		addMouseMotionListener(up);
		addKeyListener(up);
		addKeyListener(this);
		equip = sm.loadData("slgs.dat");
	}

	public void title() {
		mw.setMenu(9);
		setAnime(-11, 0, null, null);
		up.setPaintListener(new TitlePaint());
	}

	public void startup() {
		if (sm.isFirst()) {
			Statics.setHelp(true);
			//stageStart();
			campStart();
		} else {
			campStart();
		}
	}

	private void Pinit() {
		up = new UPanel(this, V);
		hp = new HPanel(this, true);
		hp2 = new HPanel(this, false);
		sp = new SPanel(this, sm, true);
		sp2 = new SPanel(this, sm, false);
		help = new HelpPanel();
		tp = new TPanel(this, up);
		lp = new LPanel(this);
		mp = new MPanel(this);
		ap = new AnimePanel(this, V, up);
		cp = new CardPanel(this, V);
		PaintBase.setup(this, V, up, Charas);
		AttackBase.setup(this, V, up, Charas);
		CardPaint.setup(cp);
		Rewalk.setup(this, V, up);
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new FreeLayout(640, 480));
		jpanel.add(tp);
		jpanel.add(ap);
		jpanel.add(hp);
		jpanel.add(hp2);
		jpanel.add(lp);
		jpanel.add(mp);
		jpanel.add(sp);
		jpanel.add(sp2);
		jpanel.add(help);
		jpanel.add(cp);
		jpanel.add(up);
		add(jpanel, 1, 1, 1, 1);
		help.setVisible(false);
		cp.setVisible(false);
		tp.setVisible(false);
		hp.setVisible(false);
		hp2.setVisible(false);
		ap.setVisible(false);
		lp.setVisible(false);
		mp.setVisible(false);
		sp.setVisible(false);
		sp2.setVisible(false);
	}

	private void Vinit() {
		byte byte0 = 20;
		byte byte1 = 15;
		byte byte2 = 32;
		byte byte3 = 32;
		V = new UnitMap();
		V.C(6, 0, 0, byte0, byte1, byte2, byte3);
		V.P(0, 0, 0, 15, 2, -1, Statics.back);
		V.P(1, 0, 32, 15, 1, 0, Statics.waku);
		V.P(2, 0, 0, 15, 15, 0, Statics.chara);
		V.P(3, 0, 0, 15, 1, 0, Statics.waku);
		V.P(4, 0, 64, 15, 1, 0, Statics.waku);
		V.P(5, 0, 0, 15, 1, 0, Statics.status);
		V.V(0, true);
		V.V(1, true);
		V.V(2, true);
		V.V(3, true);
		V.V(4, true);
		V.V(5, true);
	}

	private void insertCharas(Vector vector) {
		for (Iterator iterator = vector.iterator(); iterator.hasNext(); Charas
				.add(iterator.next()))
			;
	}

	public void putUnit(Vector vector) {
		V.clear(2, 0, 0);
		for (Iterator iterator = vector.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			if (body.isAlive()) {
				if (body.img == 0)
					body.img = 1;
				V.S(2, 0, body.x, body.y, body.img);
			}
		}

	}

	private void Camp() {
		Charas.removeAllElements();
		camp = new Camp(treasure, equip);
		camp.repaint(sm.getCampMap());
		col.setList(equip.getEquips());
		col.setWazaList(equip.getEquips());
		up.setPaintListener(camp);
		cp.setVisible(false);
		up.repaint();
	}

	private void mapLoad() {
		int ai[][] = sm.getMapData();
		if (ai != null)
			V.R(0, 0, ai);
		V.clear(1, 0, 0);
		V.clear(2, 0, 0);
		V.clear(3, 0, 0);
		V.clear(4, 0, 0);
		V.clear(5, 0, 0);
		reverseMap();
		setCrystal();
		Charas.removeAllElements();
		Players = equip.getPlayers();
		Enemys = sm.getEnemyData();
		randomize(Enemys);
		reverse(Enemys);
		treasure = new Treasure(Enemys, this, V);
		summon = new Summon(Enemys, this, V);
		material = new Material(this);
		insertCharas(Enemys);
		putUnit(Enemys);
		tm.reset();
		up.setWaitPaint();
		cp.setVisible(false);
		up.repaint();
	}

	private void remoteStart(int ai[][]) {
		V.R(0, 0, ai);
		V.clear(1, 0, 0);
		V.clear(2, 0, 0);
		V.clear(3, 0, 0);
		V.clear(4, 0, 0);
		setCrystal();
		Charas.removeAllElements();
		Players = equip.getPlayers();
		mw.setMenu(7);
		SetMensPaint setmenspaint = new SetMensPaint(Players, Charas);
		up.setPaintListener(setmenspaint);
		up.repaint();
	}

	public void setMensEnd() {
		V.change(0, 0, 15, 0, 0, 0);
		V.change(0, 0, 16, 0, 0, 0);
		putUnit(Charas);

		mensTurnStart();
	}

	public void rmiStarterEnd(Vector vector) {
		Enemys = vector;
		if (vector == null)
			return;
		treasure = new Treasure(new Vector(), this, V);
		summon = new Summon(new Vector(), this, V);
		material = new Material(this);
		insertCharas(vector);
		putUnit(Charas);
		tm.reset();
		if (Colors.getPC() == 1)
			mensTurnStart();
		else
			remoteTurnStart();
	}

	private void stageStart() {
		mw.setMenu(3);
		mapLoad();
		if (sm.isFirst())
			lp.displayT("Tutorial", Colors.getPC(), 1500);
		else if (sm.isFinalStage()) {
			lp.displayT("Final Stage", Colors.getPC(), 1500);
		} else {
			char c = (char) ((65 + sm.getMapNum()) - 1);
			lp.displayT("Stage " + c, Colors.getPC(), 1500);
		}
		SetMensPaint setmenspaint = new SetMensPaint(Players, Charas);
		up.setPaintListener(setmenspaint);
		up.repaint();
	}

	private void campStart() {
		mw.setMenu(0);
		closeTPanel();
		closeHelp();
		Camp();
	}

	private void collectionStart() {
		mw.setMenu(11);
		int ai[][] = sm.getCollectionMap();
		V.R(0, 0, ai);
		V.clear(1, 0, 0);
		V.clear(2, 0, 0);
		V.clear(3, 0, 0);
		V.clear(4, 0, 0);
		V.clear(5, 0, 0);
		up.setPaintListener(new CollectionPaint(col));
		lp.displayT("Collection Room", Colors.getPC(), 1500);
		up.repaint();
	}

	private void wazalistStart() {
		mw.setMenu(11);
		int ai[][] = sm.getWazalistMap();
		V.R(0, 0, ai);
		V.clear(1, 0, 0);
		V.clear(2, 0, 0);
		V.clear(3, 0, 0);
		V.clear(4, 0, 0);
		V.clear(5, 0, 0);
		col.setWazaList(equip.getEquips());
		up.setPaintListener(new WazalistPaint(col));
		lp.displayT("Waza Room", Colors.getPC(), 1500);
		up.repaint();
	}

	private void reverseMap() {
		if (!sm.isReverse()) {
			return;
		} else {
			int ai[] = { -1, 0, 19, 0, -1, 14 };
			V.affine(0, 0, 0, 0, 20, 15, 0, 1, 0, 0, ai);
			V.copy(0, 1, 0, 0, 20, 15, 0, 0, 0, 0);
			return;
		}
	}

	private void reverse(Vector vector) {
		if (!sm.isReverse())
			return;
		for (Iterator iterator = vector.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			body.x = 19 - body.x;
			body.y = 14 - body.y;
			if (body.gx != 0 || body.gy != 0) {
				body.gx = 19 - body.gx;
				body.gy = 14 - body.gy;
			}
		}

	}

	private void randomize(Vector vector) {
		int i = sm.getEnemyLevel();
		int j = 256;
		for (int k = i; k > 0; k--)
			j = (int) ((double) j * 1.5D);

		for (Iterator iterator = vector.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			if (body.level != 0)
				body.level += i * 10;
			body.hpMax = (body.hpMax * j) / 256;
			if (body.strMax != 0)
				body.strMax = Math.max(0,
						(body.strMax * j) / 256 + Luck.rnd(10, body) + 5);
			if (body.defMax != 0)
				body.defMax = Math.max(0,
						(body.defMax * j) / 256 + Luck.rnd(10, body) + 5);
			if (body.mstMax != 0)
				body.mstMax = Math.max(0,
						(body.mstMax * j) / 256 + Luck.rnd(10, body) + 5);
			if (body.mdfMax != 0)
				body.mdfMax = Math.max(0,
						(body.mdfMax * j) / 256 + Luck.rnd(10, body) + 5);
			if (body.hitMax != 0)
				body.hitMax = Math.max(0,
						(body.hitMax * j) / 256 + Luck.rnd(10, body) + 5);
			if (body.misMax != 0)
				body.misMax = Math.max(0,
						(body.misMax * j) / 256 + Luck.rnd(10, body) + 5);
			Equip.restrict(body);
			body.setMax();
			body.newType();
			col.setMaterial(body);
			if (body.color == 3 && !body.isType(Types.ASK) && body.isType(Types.TALKABLE)) {
				body.str = Math.max(0, body.str - 2);
				body.def = Math.max(0, body.def - 2);
				body.mst = Math.max(0, body.mst - 2);
				body.mdf = Math.max(0, body.mdf - 2);
				body.hit = Math.max(0, body.hit - 2);
				body.mis = Math.max(0, body.mis - 2);
			}
			if (body.isType(Types.POWERUP)) {
				body.str = Math.min(999, (body.str * 3) / 2);
				body.def = Math.min(999, (body.def * 3) / 2);
				body.mst = Math.min(999, (body.mst * 3) / 2);
				body.mdf = Math.min(999, (body.mdf * 3) / 2);
				body.hit = Math.min(999, (body.hit * 3) / 2);
				body.mis = Math.min(999, (body.mis * 3) / 2);
			}
		}

	}

	public Body search(int i, int j) {
		for (Iterator iterator = Charas.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			if (body.isAlive() && body.x == i && body.y == j)
				return body;
		}

		return null;
	}

	public int getTurn() {
		return tm.getTurn();
	}

	public void setLPanel(String s, int i, int j) {
		lp.displayT(s, i, j);
	}

	public void setMPanel(String s) {
		mp.setText(s);
	}

	public void startMPanel(Body body) {
		closeAPanel();
		mp.display(body);
	}

	public void setTPanel(Iconable iconable, Body body) {
		tp.display(iconable, body);
	}

	public void setCampPanel(Point point, int i, int j) {
		sp.displayC(point, i, j);
	}

	public void setSPanel(Body body) {
		sp.displayS(body, 0);
	}

	public void setAnalyze(Body body) {
		sp.displayS(body, 3);
	}

	public void setAtkList(Body body) {
		sp.displayS(body, 4);
	}

	public void setAPanel() {
		sp.repaint();
		sp2.repaint();
	}

	public void setAPanel(Iconable iconable, Iconable iconable1) {
		sp.displayA(iconable, iconable1);
		sp2.displayA(iconable1, iconable);
	}

	public void setAnime(int i, int j, Point point, Point point1) {
		ap.anime(i, j, point, point1);
	}

	public void setNPanel(Body body, int i) {
		Point point = new Point(body.x, body.y);
		setAnime(-1, i, point, point);
	}

	public void setHelp(String as[], int i) {
		Point point = up.getWaku();
		help.setLocate(point.x, point.y, true);
		help.setLine(as, i);
		help.setVisible(Statics.isHelp());
		help.repaint();
	}

	public void setHelpLocate(int i, int j) {
		help.setLocate(i, j, false);
	}

	public void closeAPanel() {
		sp.dispose();
		sp2.dispose();
	}

	public void closeTPanel() {
		tp.dispose();
	}

	public void closeNPanel() {
		ap.dispose();
	}

	public void closeHPanel() {
		hp.dispose();
		hp2.dispose();
	}

	public void closeHelp() {
		help.setVisible(false);
	}

	public void selectPanel(boolean flag) {
		hpFlag = flag;
	}

	public void setHPanel(Body body, Body body1, int i, boolean flag) {
		if (hpFlag)
			hp.display(body, body1, i, flag);
		else
			hp2.display(body, body1, i, flag);
	}

	public void hpdamage(int i) {
		if (hpFlag) {
			sp2.damage(i);
			hp.damage(i);
		} else {
			sp.damage(i);
			hp2.damage(i);
		}
	}

	public void hphenka() {
		if (hpFlag) {
			hp.henka();
			sp2.henka();
		} else {
			hp2.henka();
			sp.henka();
		}
	}

	public void setCrystal() {
		Dimension dimension = V.getSize();
		blueCrystal = null;
		redCrystal = null;
		for (int i = 0; i < dimension.width; i++) {
			for (int j = 0; j < dimension.height; j++) {
				if (V.G(0, 0, i, j) == 17)
					blueCrystal = new Point(i, j);
				if (V.G(0, 0, i, j) == 18)
					redCrystal = new Point(i, j);
			}

		}

	}

	public Point getCrystal(int i) {
		if (i == 1)
			return blueCrystal;
		if (i == 3)
			return redCrystal;
		else
			return null;
	}

	public void dead(AttackBase attackbase) {
		Body body = attackbase.getBa();
		Body body1 = attackbase.getBb();
		Point point = new Point(body1.x, body1.y);
		setAnime(-2, 0, point, point);
		if (body == null)
			return;
		if (Colors.isPlayer(body)) {
			sm.countKill();
			equip.getEXP(body, body1);
			treasure.getTreasure(body1, false);
			Body body2 = material.getMaterial(attackbase);
			if (body2 != null)
				treasure.add(body2);
		}
		if (Colors.isPlayer(body1))
			sm.countDead();
	}

	public void levelup(Body body) {
		if (body != null && Colors.isPlayer(body))
			equip.levelup(body);
	}

	public void mensTurnEnd() {
		enemyTurnStart();
	}

	private void enemyTurnStart() {
		tm.change(1);
		lp.displayT("Enemy Turn " + tm.getTurn(), Colors.getEC(), 1000);
		mw.setMenu(4);
	}

	private void remoteTurnStart() {
		tm.change(2);
		lp.displayT("Enemy Turn " + tm.getTurn(), Colors.getEC(), 1000);
		mw.setMenu(4);
	}

	public void mensTurnStart() {
		escape = true;
		sm.countTurn();
		tm.change(0);
		lp.displayT("Turn " + tm.getTurn(), Colors.getPC(), 1500);
		mw.setMenu(2);
	}

	public void escape() {
		if (escape) {
			sm.countEscape();
			up.setWaitPaint();
			lp.displayT("ESCAPE", Colors.getEC(), 3000);
			mw.setMenu(5);
		} else {
			lp.displayT("FAILED", Colors.getEC(), 500);
		}
	}

	private void expDown() {
		for (Iterator iterator = Charas.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			if (Colors.isPlayer(body) && body.isAlive())
				body.exp /= 2;
		}

	}

	public void setEnd(Body body, boolean flag) {
		escape = false;
		if (body.isAlive()) {
			treasure.searchTreasure(body);
			V.S(3, 0, body.x, body.y, 1);
		}
		up.repaint();
		if (endJudge(body))
			return;
		if (isTurnEnd()) {
			mensTurnEnd();
			return;
		}
		BasicPaint basicpaint = new BasicPaint();
		up.setPaintListener(basicpaint);
		if (flag)
			basicpaint.leftPressed();
	}

	private boolean isTurnEnd() {
		for (Iterator iterator = Charas.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			if (body.isAlive() && Colors.isPlayer(body) && !body.isType(Types.ANTI_SLEEP)
					&& !body.isType(Types.CHARM)
					&& (body.kind != Kinds.DOLL || !body.isType(Types.BERSERK))) {
				if (getChangeChara(body) != null)
					return false;
				if (V.G(3, 0, body.x, body.y) == 0)
					return false;
			}
		}

		return true;
	}

	public boolean endJudge(Body body) {
		if (blueJudge1())
			return true;
		if (redJudge1())
			return true;
		if (blueJudge2(body))
			return true;
		return redJudge2(body);
	}

	private boolean blueJudge1() {
		for (Iterator iterator = Charas.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			if (!Colors.isPlayer(body) && body.isAlive())
				return false;
		}

		gameClear();
		return true;
	}

	private boolean blueJudge2(Body body) {
		if (!body.isAlive())
			return false;
		if (body.color != 1)
			return false;
		if (V.G(0, 0, body.x, body.y) != 18)
			return false;
		V.fillDia(0, 0, body.x, body.y, 1, 20);
		if (Colors.isPlayer(body))
			gameClear();
		else
			gameOver();
		return true;
	}

	private void gameClear() {
		treasure.getClearItem();
		up.setWaitPaint();
		closeAPanel();
		if (sm.isFinalStage())
			lp.displayT("ALL CLEAR!!", Colors.getPC(), 5000);
		else
			lp.displayT("STAGE CLEAR", Colors.getPC(), 5000);

		sm.stageClear();
		mw.setMenu(5);
		setHelp(Texts.help[20], 1);
	}

	private boolean redJudge1() {
		for (Iterator iterator = Charas.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			if (Colors.isPlayer(body) && body.isAlive())
				return false;
		}

		gameOver();
		return true;
	}

	private boolean redJudge2(Body body) {
		if (!body.isAlive())
			return false;
		if (body.color == 1)
			return false;
		if (V.G(0, 0, body.x, body.y) != 17)
			return false;
		V.fillDia(0, 0, body.x, body.y, 1, 19);
		if (Colors.isPlayer(body))
			gameClear();
		else
			gameOver();
		return true;
	}

	private void gameOver() {
		up.setWaitPaint();
		lp.displayT("GAME OVER", Colors.getEC(), 5000);

		mw.setMenu(6);
		setHelp(Texts.help[21], 1);
	}

	public void displayData(Point point) {
		int i = V.G(0, 0, point.x, point.y);
		switch (i) {
		case 1: // '\001'
		case 2: // '\002'
		case 3: // '\003'
		case 4: // '\004'
		case 5: // '\005'
		case 6: // '\006'
		case 7: // '\007'
		case 8: // '\b'
		case 9: // '\t'
		case 10: // '\n'
			sp.displayP(point, i);
			break;

		case 0: // '\0'
			sp.displayD(point, i, 6);
			break;
		}
	}

	public boolean displayPlace(Point point) {
		int i = V.G(0, 0, point.x, point.y);
		switch (i) {
		case 17: // '\021'
			sp.displayD(point, i, 13);
			return true;

		case 18: // '\022'
			sp.displayD(point, i, 14);
			return true;

		case 21: // '\025'
		case 22: // '\026'
		case 23: // '\027'
			sp.displayD(point, i, 7);
			return true;

		case 24: // '\030'
		case 25: // '\031'
			sp.displayD(point, i, 8);
			return true;

		case 15: // '\017'
			sp.displayD(point, i, 9);
			return true;

		case 16: // '\020'
		case 19: // '\023'
		case 20: // '\024'
		default:
			sp.setVisible(false);
			return false;
		}
	}

	public boolean isDivided() {
		return sm.isDivided();
	}

	public void countItem() {
		sm.countItem();
	}

	public boolean isAllClear() {
		return sm.isAllClear();
	}

	public boolean isFirst() {
		return sm.isFirst();
	}

	public boolean have(Body body) {
		return equip.have(body);
	}

	public void limitOver() {
		summon.summon();
		treasure.limitOver();
	}

	public int getSummonLimit(Point point) {
		return summon.getLimitTurn(point);
	}

	public int getTreasureLimit(Point point) {
		if (point == null)
			return treasure.getLimitTurn();
		else
			return treasure.getLimitTurn(point);
	}

	public String getTreasureCount() {
		return treasure.getCount();
	}

	public int getMaterialCount() {
		return material.getCount();
	}

	public void message() {
		treasure.message();
		material.message();
	}

	public void addMember(Body body) {
		treasure.addMember(body);
	}

	public void bersekChara(Body body) {
		Charas.remove(body);
		Charas.insertElementAt(body, 0);
	}

	public void changeChara(Body body, Body body1) {
		Charas.remove(body);
		Charas.add(body1);
	}

	public Body getChangeChara(Body body) {
		return equip.getChangeChara(body);
	}

	public void waitFast() {
		waitFlag = true;
	}

	public void waitSlow() {
		waitFlag = false;
	}

	public void sleep(long l) {
		if (waitFlag)
			l /= 3L;
		try {
			Thread.currentThread();
			Thread.sleep(l);
		} catch (Exception exception) {
			System.out.println(exception);
		}
	}

	public void stageSelect() {
		ButtonDialog buttondialog = new ButtonDialog(mw.getFrame(), this);
		for (int i = 0; i < 21; i++)
			buttondialog.add("Stage " + (char) (65 + i));

		buttondialog.show();
	}

	public void nameChange() {
		if (sm.getPlayerName() != null)
			return;
		Body body = null;
		Body body1 = null;
		for (int i = 1; i <= 8; i += 7) {
			for (int j = 1; j <= 10; j += 3) {
				Body body2 = equip.search(i, j);
				if (body2 != null) {
					body2.newType();
					if (body2.isType(Types.HERO))
						body = body2;
					if (body2.isType(Types.SISTER))
						body1 = body2;
				}
			}

		}

		TextDialog textdialog = new TextDialog(mw.getFrame(), 20);
		if (body != null) {
			textdialog.setup(Texts.heroname, body.name);
			textdialog.show();
			String s = textdialog.getText();
			if (s != null && !s.equals(""))
				body.name = s;
		}
		if (body1 != null) {
			textdialog.setup(Texts.sistername, body1.name);
			textdialog.show();
			String s1 = textdialog.getText();
			if (s1 != null && !s1.equals(""))
				body1.name = s1;
		}
		textdialog.setup(Texts.rankname, "");
		String s2;
		for (s2 = null; s2 == null || s2.equals(""); s2 = textdialog.getText())
			textdialog.show();

		sm.setPlayerName(s2);
		if (mw.isFrame()) {
			col.saveData();
			sm.saveData("slgs.dat", equip);
		} 
	}

	private void showScore() {
		Rank.setup(sm, equip);
		sp.displayScore(new Point(2, 1), 10);
		sp2.displayScore(new Point(3, 1), 11);
		up.setPaintListener(new ScorePaint());
		mw.setMenu(10);
	}

	private void sendScore(boolean flag) {
		TextDialog textdialog = new TextDialog(mw.getFrame(), 20);
		String s = "";
		textdialog.setup(Texts.comment, s);
		textdialog.show();
		if (textdialog.getText() != null) {
			s = textdialog.getText();
		} else {
			lp.displayT(Texts.rankcancel, Colors.getEC(), 2000);
			return;
		}
		int i;
		if (flag)
			i = Rank.sendHayasaScore(s);
		else
			i = Rank.sendTuyosaScore(s);
		if (i != 0) {
			if (flag)
				lp.displayT(Texts.speed_rank + i + Texts.idesu, Colors.getPC(),
						4000);
			else
				lp.displayT(Texts.power_rank + i + Texts.idesu, Colors.getPC(),
						4000);
		} else {
			lp.displayT(Texts.conn_fail, Colors.getEC(), 2000);
		}
	}

	public void backToCamp() {
		closeAPanel();
		mw.setMenu(0);
		up.setPaintListener(camp);
		camp.repaint(sm.getCampMap());
		camp.setHelp();
		up.repaint();
	}

	public void backFromImogari() {
		up.setPaintListener(camp);
		camp.setHelp();
	}

	public void keyTyped(KeyEvent keyevent) {
	}

	public void keyReleased(KeyEvent keyevent) {
	}

	public void keyPressed(KeyEvent keyevent) {
		byte byte0 = 0;
		switch (keyevent.getKeyCode()) {
		case 112: // 'p'
			byte0 = 1;
			break;

		case 113: // 'q'
			byte0 = 2;
			break;

		case 114: // 'r'
			byte0 = 3;
			break;

		case 115: // 's'
			byte0 = 4;
			break;

		case 116: // 't'
			byte0 = 5;
			break;

		case 117: // 'u'
			byte0 = 6;
			break;

		case 118: // 'v'
			byte0 = 7;
			break;

		case 119: // 'w'
			byte0 = 8;
			break;

		default:
			return;
		}
		if (ActionBase.isRunning())
			return;
		String s = "slgs" + byte0 + ".dat";
		if (keyevent.isShiftDown()) {
			sm.saveData(s, equip);
			setLPanel("Save " + byte0, 1, 1500);
		} else {
			File file = new File(s);
			if (!file.exists()) {
				setLPanel("Not Found " + byte0, 3, 1500);
				return;
			}
			equip = sm.loadData(s);
			treasure = null;
			ap.setVisible(false);
			campStart();
			setLPanel("Load " + byte0, 1, 1500);
		}
	}

	public void actionPerformed(ActionEvent actionevent) {
		requestFocus();
		if (ActionBase.isRunning())
			return;
		AbstractButton abstractbutton = (AbstractButton) actionevent
				.getSource();
		String s = abstractbutton.getActionCommand();
		ImogariPaint imogaripaint;
		if (s.equals("help")) {
			if (Statics.isHelp()) {
				setLPanel(Texts.help_off, 1, 1000);
				Statics.setHelp(false);
				closeHelp();
			} else {
				setLPanel(Texts.help_on, 1, 1000);
				Statics.setHelp(true);
				help.setVisible(true);
			}
		} else if (s.equals("imogari"))
			imogaripaint = new ImogariPaint(mw.getFrame(), equip);
		else if (s.equals("wazalist"))
			wazalistStart();
		else if (s.equals("collect"))
			collectionStart();
		else if (s.equals("remove"))
			camp.removeDust();
		else if (s.equals("sort"))
			camp.sortItem();
		else if (s.equals("hayasa"))
			sendScore(true);
		else if (s.equals("tuyosa"))
			sendScore(false);
		else if (s.equals("back"))
			backToCamp();
		else if (s.equals("waza"))
			camp.wazaList();
		else if (s.equals("score"))
			showScore();
		else if (s.equals("camp"))
			campStart();
		else if (s.equals("escape"))
			escape();
		else if (s.equals("stage"))
			stageStart();
		else if (s.equals("left")) {
			sm.selectLR(true);
			stageStart();
		} else if (s.equals("right")) {
			sm.selectLR(false);
			stageStart();
		} else if (s.equals("start"))
			setMensEnd();
		else if (s.equals("turnend"))
			mensTurnEnd();
		else if (s.equals("mapload")) {
			if (mw.isFrame())
				equip = sm.loadData("slgs.dat");
			
			if (sm.getPlayerName() != null)
				stageStart();
			else
				title();
		} else if (s.equals("campload")) {
			if (mw.isFrame())
				equip = sm.loadData("slgs.dat");
			
			if (sm.getPlayerName() != null)
				campStart();
			else
				title();
		} else if (s.equals("save")) {
			if (mw.isFrame()) {
				col.saveData();
				sm.saveData("slgs.dat", equip);
			}
			setLPanel("SAVE", 1, 1500);
		} else if (s.equals("cancel")) {
			campStart();
		} else if (s.equals("select")) {
			stageSelect();
		} else {
			for (int i = 0; i < 16; i++)
				if (s.equals("Stage " + (char) (65 + i))) {
					sm.setMapNum(i);
					stageStart();
				}

		}
	}

	UnitMap V;
	MainWorks mw;
	UPanel up;
	LPanel lp;
	MPanel mp;
	TPanel tp;
	AnimePanel ap;
	HelpPanel help;
	CardPanel cp;
	HPanel hp;
	HPanel hp2;
	SPanel sp;
	SPanel sp2;
	Vector Charas;
	Vector Players;
	Vector Enemys;
	TurnManager tm;
	SaveManager sm;
	Collection col;
	Equip equip;
	Camp camp;
	Treasure treasure;
	Material material;
	Summon summon;
	Point blueCrystal;
	Point redCrystal;
	private boolean hpFlag;
	private boolean waitFlag;
	private boolean escape;
}
