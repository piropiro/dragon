package dragon3.camp;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import dragon3.UnitWorks;
import dragon3.common.Body;
import dragon3.common.constant.BodyAttribute;
import dragon3.common.constant.BodyKind;
import dragon3.common.constant.GameColors;
import dragon3.common.constant.Page;
import dragon3.common.constant.Texts;
import dragon3.common.util.Equip;
import dragon3.manage.TreasureManager;
import dragon3.map.MapWorks;
import dragon3.paint.PaintListener;
import dragon3.panel.PanelManager;
import dragon3.panel.paint.CampDataPaint;
import mine.paint.UnitMap;

public class Camp implements PaintListener {

	private UnitWorks uw;
	private MapWorks mw;
	private UnitMap map;
	private PanelManager pm;
	
	Equip equip;
	List<Body> equips;



	Point ps; // Last Position
	Point end;
	Body ba; // Selected Body
	Body[] items; // Stock Item
	boolean sortf; // Sorting Flag

	static final int T_NONE = 0;
	static final int T_FREE = 1;
	static final int T_PASTE = 2;
	static final int T_ERASE = 3;
	static final int T_STORE = 4;

	/*** Constructer **********************************/

	public Camp(UnitWorks uw, TreasureManager treasure, Equip equip) {
		this.uw = uw;
		this.mw = uw.getMapWorks();
		this.map = uw.getUnitMap();
		this.pm = uw.getPanelManager();
		this.pm = uw.getPanelManager();
		
		this.equip = equip;
		this.mw = uw.getMapWorks();
		equips = equip.getEquips();
		setEquip();
		if (treasure != null) {
			setSource(treasure.getSources(), false);
		}
		setColor();
		setHelp();
	}

	public void setHelp() {
		String[] line;

		if (ba == null) {
			Point p = mw.getWaku();
			if (map.getData(Page.P30, p.x, p.y) != 0) {
				if (p.x < 14) {
					line = Texts.help[Texts.H_CAMP1];
				} else {
					line = Texts.help[Texts.H_CAMP2];
				}
			} else {
				line = Texts.help[Texts.H_CAMP3];
			}
		} else {
			if (ba.isKind(BodyKind.SOUL)) {
				line = Texts.help[Texts.H_CAMP4];
			} else if (ba.isKind(BodyKind.WEPON)) {
				line = Texts.help[Texts.H_CAMP4];
			} else if (ba.isKind(BodyKind.ARMOR)) {
				line = Texts.help[Texts.H_CAMP4];
			} else if (ba.isKind(BodyKind.ITEM)) {
				line = Texts.help[Texts.H_CAMP4];
			} else {
				line = Texts.help[Texts.H_CAMP6];
			}
		}
		pm.displayHelp(uw.getMapWorks().getWaku(), line, GameColors.BLUE);
	}

	/*** Repaint ******************************************/

	public void repaint(int[][] data) {
		map.setPage(Page.P10, data);
		map.clear(Page.P00, 0);
		map.clear(Page.P20, 0);
		map.clear(Page.P30, 0);
		map.clear(Page.P40, 0);
		map.clear(Page.P50, 0);
		for (Body b : equips) {
			if (map.getData(Page.P10, b.getX(), b.getY()) == T_FREE) {
				map.setData(Page.P10, b.getX(), b.getY(), T_PASTE);
			}
			map.setData(Page.P20, b.getX(), b.getY(), b.getImageNum());
		}
	}

	/*** Color ******************************************/

	private void setColor() {
		for (Body b : equips) {
			if (GameColors.isPlayer(b)) {
				b.setColor(GameColors.BLUE);
			} else if (GameColors.isEnemy(b)) {
				b.setColor(GameColors.RED);
			}
		}
	}

	/*** Deploy Equip ****************************************/

	private void setEquip() {
		for (int i = equips.size() - 1; i >= 0; i--) {
			Body b = (Body) equips.get(i);
			b.setMax();
			b.setAttrSet(new LinkedHashSet<BodyAttribute>());
			if (GameColors.isPlayer(b)) {
				b.setX(b.getGoalX());
				b.setY(b.getGoalY());
			}
			if (b.getX() > 13 && b.getY() == 14) {
				equips.remove(i);
				continue;
			}
			map.setData(Page.P20, b.getX(), b.getY(), b.getImageNum());
		}
	}

	/*** Deploy Source **************************************/

	private void setSource(List<Body> Sources, boolean reversef) {
		if (Sources == null)
			return;

		for (int i = 1; i < 15; i++) {
			int y = (reversef) ? (14 - i) : i;
			for (int x = 14; x < 20; x++) {
				if (Sources.size() == 0)
					break;
				if (map.getData(Page.P20, x, y) != 0)
					continue;
				Body b = (Body) Sources.get(0);
				b.setX(x);
				b.setY(y);
				b.setMax();
				equips.add(b);
				Sources.remove(b);
				map.setData(Page.P20, b.getX(), b.getY(), b.getImageNum());
			}
		}
	}

	/*** Remove Dust ********************************/

	public void removeDust() {
		for (int i = equips.size() - 1; i >= 0; i--) {
			Body b = (Body) equips.get(i);
			if (b.isKind(BodyKind.WAZA)
				|| map.getData(Page.P10, b.getX(), b.getY()) == T_ERASE) {
				equips.remove(i);
				map.setData(Page.P20, b.getX(), b.getY(), 0);
				mw.ppaint(b.getX(), b.getY());
			}
		}
	}

	/*** Sort Item **********************************/

	public void sortItem() {
		List<Body> itemList = new ArrayList<>();
		List<Body> wazaList = new ArrayList<>();

		for (int i = equips.size() - 1; i >= 0; i--) {
			Body b = (Body) equips.get(i);
			if (b.getY() == 0 || b.getY() == 14)
				continue;
			if (b.getX() < 14)
				continue;
			if (b.isKind(BodyKind.WAZA)) {
				wazaList.add(b);
			} else {
				itemList.add(b);
			}
			equips.remove(i);
			map.setData(Page.P20, b.getX(), b.getY(), 0);
		}
		setSource(itemList, false);
		mw.repaint();
	}

	/*** Back to Source **************************************/

	private void backChara() {
		for (int y = 1; y < 15; y++) {
			for (int x = 14; x < 20; x++) {
				if (map.getData(Page.P20, x, y) == 0) {
					moveChara(x, y);
					putChara(x, y, ba);
					mw.repaint();
					return;
				}
			}
		}
	}

	/*** Move Chara ************************/

	private void moveChara(int x, int y) {
		if (end != null) {
			removeCancel(end.x, end.y);
			end = null;
		}
		if (ba == null)
			return;

		if (ps != null) {
			map.setData(Page.P20, ps.x, ps.y, 0);
		}
		if (map.getData(Page.P20, x, y) == 0) {
			map.setData(Page.P20, x, y, ba.getImageNum());
			ps = new Point(x, y);
		} else {
			ps = null;
		}
	}

	/*** Put Chara ***************************/

	private void putChara(int x, int y, Body b) {
		if (equip.search(x, y) != null)
			return;
		equips.add(b);
		b.setX(x);
		b.setY(y);
		map.setData(Page.P20, x, y, b.getImageNum());
		ps = null;
		ba = null;
	}

	private void putChara2(int x, int y) {
		if (equip.search(x, y) != null)
			return;
		Body bb;

		if (ba.isKind(BodyKind.SOUL)) {
			if (x == 2 || x == 9) {
				bb = charaCheck(x - 1, y);
				if (bb == null)
					return;
				if (!equipCheck(bb, ba, BodyKind.SOUL))
					return;
				if (!levelCheck(bb, ba))
					return;
				if (equip.search(x + 1, y) != null) {
					pm.displayLarge(Texts.warning1, GameColors.RED, 1000);
					return;
				}
				map.setData(Page.P10, x, y, T_PASTE);
				putChara(x, y, ba);
				equip.equip(bb);
				return;
			}
		} else if (ba.isKind(BodyKind.WEPON)) {
			if (x == 3 || x == 10) {
				bb = charaCheck(x - 2, y);
				if (bb == null)
					return;
				if (!equipCheck(bb, ba, BodyKind.WEPON))
					return;
				if (!levelCheck(bb, ba))
					return;
				putChara(x, y, ba);
				return;
			}
		} else if (ba.isKind(BodyKind.ARMOR)) {
			if (x == 4 || x == 11) {
				bb = charaCheck(x - 3, y);
				if (bb == null)
					return;
				if (!levelCheck(bb, ba))
					return;
				putChara(x, y, ba);
				return;
			}
		} else if (ba.isKind(BodyKind.ITEM)) {
			if (x == 5 || x == 12) {
				bb = charaCheck(x - 4, y);
				if (bb == null)
					return;
				if (!levelCheck(bb, ba))
					return;
				putChara(x, y, ba);
				return;
			}
		} else if (ba.isKind(BodyKind.WAZA)) {
		} else {
			if (x == 1 || x == 8) {
				if (sortf) {
					putSortItems(x, y, items);
				} else {
					ba.setGoalX(x);
					ba.setGoalY(y);
					ba.setColor(GameColors.BLUE);
					map.setData(Page.P10, x, y, T_PASTE);
					putChara(x, y, ba);
				}
				return;
			}
		}
		alarm(ba);
	}

	/*** Alarm ***********************************/

	private void alarm(Body ba) {
		String s = null;
		if (ba.isKind(BodyKind.SOUL)) {
			s = Texts.shokugyo;
		} else if (ba.isKind(BodyKind.WEPON)) {
			s = Texts.buki;
		} else if (ba.isKind(BodyKind.ARMOR)) {
			s = Texts.bougu;
		} else if (ba.isKind(BodyKind.ITEM)) {
			s = Texts.komono;
		} else if (ba.isKind(BodyKind.WAZA)) {
			s = Texts.wazasetumei;
		} else {
			s = Texts.nakama;
		}
		pm.displayLarge(Texts.sokoni + s + Texts.haokemasen, GameColors.RED, 1000);
	}

	private Body charaCheck(int x, int y) {
		Body bb = equip.search(x, y);
		if (bb != null)
			return bb;
		pm.displayLarge(Texts.warning2, GameColors.RED, 1000);
		return null;
	}

	private boolean equipCheck(Body ba, Body bb, BodyKind kind) {
		if (ba == null)
			return false;
		if (bb == null)
			return false;

		if (kind.equals(BodyKind.WEPON)) {
			if (ba.getWeponType().equals(bb.getWeponType())) {
				return true;
			}
		}

		if (kind.equals(BodyKind.ARMOR)) {
			if (ba.getArmorType().equals(bb.getArmorType())) {
				return true;
			}
		}

		return false;
	}

	private boolean levelCheck(Body ba, Body bb) {
		if (ba.getLevel() >= bb.getLevel())
			return true;
		pm.displayLarge(Texts.warning5, GameColors.RED, 1000);
		return false;
	}

	private void help(int x, int y) {
		switch (map.getData(Page.P10, x, y)) {
			case T_FREE :
			case T_STORE :
				if (x == 1) {
					pm.displayCampData(x, y, CampDataPaint.C_CHARA1, GameColors.BLUE);
				} else if (x == 8) {
					pm.displayCampData(x, y, CampDataPaint.C_CHARA2, GameColors.BLUE);
				} else if (x == 2 || x == 9) {
					pm.displayCampData(x, y, CampDataPaint.C_CLASS, GameColors.BLUE);
				} else if (x == 3 || x == 10) {
					pm.displayCampData(x, y, CampDataPaint.C_WEPON, GameColors.BLUE);
				} else if (x == 4 || x == 11) {
					pm.displayCampData(x, y, CampDataPaint.C_ARMOR, GameColors.BLUE);
				} else if (x == 5 || x == 12) {
					pm.displayCampData(x, y, CampDataPaint.C_ITEM, GameColors.BLUE);
				}
				break;
			case T_ERASE :
				pm.displayCampData(x, y, CampDataPaint.C_DUST, GameColors.RED);
				break;
		}
	}

	/*** Pick Chara ***************************/

	private Body pickChara(int x, int y) {
		Body b = equip.search(x, y);
		if (b == null) {
			help(x, y);
			return null;
		}
		b.setColor(GameColors.GREEN);
		equips.remove(b);
		ps = new Point(x, y);
		return b;
	}

	/*** Change Chara ***********************/

	private void changeChara(int x, int y) {
		if (equip.search(x, y) == null) {
			putChara(x, y, ba);
			return;
		} else {
			Body bb = pickChara(x, y);
			putChara(x, y, ba);
			ba = bb;
		}
	}

	/*** Remove Chara **********************/

	private void removeChara1(int x, int y) {
		Body bb = equip.search(x, y);
		if (bb == null)
			return;
		if (bb.getColor() == GameColors.BLUE) {
			items = pickSortItems(x, y);
			return;
		}

		end = new Point(x, y);
		map.setData(Page.P10, x, y, T_ERASE);
		map.setData(Page.P30, x, y, 4);
		map.setData(Page.P40, x, y, 0);
		mw.ppaint(x, y);
	}
	private void removeChara2(int x, int y) {
		Body bb = equip.search(x, y);
		equips.remove(bb);

		end = null;
		if (bb.isKind(BodyKind.SOUL)) {
			map.setData(Page.P10, x, y, T_FREE);
			List<Body> list = new ArrayList<>();
			bb.setExp(0);
			list.add(bb);
			setSource(list, false);
			mw.ppaint(bb.getX(), bb.getY());
		} else if (bb.isKind(BodyKind.WAZA)) {
			map.setData(Page.P10, x, y, T_NONE);
		}
		map.setData(Page.P20, x, y, 0);
		map.setData(Page.P30, x, y, 0);
		mw.ppaint(x, y);
	}

	/*** Remove Cancel ***********************************/

	private void removeCancel(int x, int y) {
		if (map.getData(Page.P30, x, y) == 0)
			return;
		Body bb = equip.search(x, y);
		if (bb.isKind(BodyKind.SOUL)) {
			map.setData(Page.P10, x, y, T_PASTE);
		} else if (bb.isKind(BodyKind.WAZA)) {
			map.setData(Page.P10, x, y, T_NONE);
		}
		map.setData(Page.P30, x, y, 0);
		mw.ppaint(x, y);
		setHelp();
	}

	/*** Sort ***************************************/

	private Body[] pickSortItems(int x, int y) {
		Body[] tmp = new Body[5];
		for (int i = 1; i <= 4; i++) {
			tmp[i] = equip.search(x + i, y);
			if (tmp[i] == null)
				continue;
			map.setData(Page.P20, x + i, y, 0);
			tmp[i].setColor(GameColors.GREEN);
			equips.remove(tmp[i]);
			sortf = true;
		}
		map.setData(Page.P10, x, y, T_FREE);
		map.setData(Page.P10, x + 1, y, T_FREE);
		tmp[0] = pickChara(x, y);
		ba = tmp[0];
		mw.repaint();
		return tmp;
	}

	private void putSortItems(int x, int y, Body[] tmp) {
		for (int i = 1; i <= 4; i++) {
			if (tmp[i] == null)
				continue;
			equips.add(tmp[i]);
			tmp[i].setX(x + i);
			tmp[i].setY(y);
			map.setData(Page.P20, x + i, y, tmp[i].getImageNum());
		}
		tmp[0].setGoalX(x);
		tmp[0].setGoalY(y);
		tmp[0].setColor(GameColors.BLUE);
		map.setData(Page.P10, x, y, T_PASTE);
		putChara(x, y, tmp[0]);
		if (tmp[1] != null)
			map.setData(Page.P10, x + 1, y, T_PASTE);
		sortf = false;
		mw.repaint();
	}

	private void changeSortChara(int x, int y) {
		if (x != 1 && x != 8)
			return;
		if (!sortf)
			return;
		Body[] tmp = pickSortItems(x, y);
		putSortItems(x, y, items);
		items = tmp;
		ba = tmp[0];
		for (int i = 1; i <= 4; i++) {
			if (tmp[i] != null)
				sortf = true;
		}
		mw.repaint();
	}


	/*** Left Pressed ***********************************/

	@Override
	public void leftPressed() {
		Point p = mw.getWaku();
		Body b = equip.search(p.x, p.y);
		if (b != null)
			pm.displayWazaList(b);

		switch (map.getData(Page.P10, p.x, p.y)) {
			case T_NONE :
				if (sortf)
					return;
				if (ba != null) {
					changeChara(p.x, p.y);
				} else {
					if (b != null) {
						if (b.isKind(BodyKind.WAZA)) {
							removeChara1(p.x, p.y);
						} else {
							ba = pickChara(p.x, p.y);
						}
					}
				}
				break;
			case T_FREE :
			case T_STORE :
				if (ba != null)
					putChara2(p.x, p.y);
				else
					ba = pickChara(p.x, p.y);
				break;
			case T_PASTE :
				if (ba != null)
					changeSortChara(p.x, p.y);
				else
					removeChara1(p.x, p.y);
				break;
			case T_ERASE :
				if (sortf)
					return;
				if (map.getData(Page.P30, p.x, p.y) == 0) {
					if (ba != null)
						putChara(p.x, p.y, ba);
					else
						ba = pickChara(p.x, p.y);
				} else {
					if (ba == null)
						removeChara2(p.x, p.y);
				}
				break;
		}
		setHelp();
	}

	/*** Right Pressed ******************************/

	@Override
	public void rightPressed() {
		Point p = mw.getWaku();
		if (sortf) {
			moveChara(ba.getGoalX(), ba.getGoalY());
			putChara2(ba.getGoalX(), ba.getGoalY());
			setHelp();
			return;
		}
		switch (map.getData(Page.P10, p.x, p.y)) {
			case T_NONE :
			case T_FREE :
			case T_STORE :
			case T_PASTE :
				if (ba != null) {
					backChara();
				} else {
					Body b = equip.search(p.x, p.y);
					if (b != null) {
						if (b.isKind(BodyKind.WAZA)) {
							removeChara1(p.x, p.y);
						} else {
							pm.displayAnalyze(b);
						}
					} else {
						leftPressed();
					}
				}
				break;
			case T_ERASE :
				removeCancel(p.x, p.y);
				break;
		}
		setHelp();
	}

	/*** Mouse Moved ***********************************/

	@Override
	public void mouseMoved(int x, int y) {
		mw.wakuMove(x, y);
		Body b = equip.search(x, y);
		if (b != null && b.getColor() == GameColors.BLUE) {
			equip.equip(b);
		}
		pm.displayStatus(b);
		moveChara(x, y);
		mw.wakuPaint(true);
	}

	/*** Next Point *************************************/

	@Override
	public boolean isNextPoint(int x, int y) {
		if (map.getData(Page.P10, x, y) != 0)
			return false;

		Body b = equip.search(x, y);
		return (b != null);
	}

	/*** Place *****************************************/

	@Override
	public void setSelectPlace(int x, int y) {
		uw.getPanelManager().displayPlace(x, y);
	}

	/*** Select Body *****************************************/

	@Override
	public void setSelectBody(Body b) {
		pm.displayStatus(b);
	}

	/*** Mouse Moved ***********************************/


	/*** Event ************************************/

	@Override
	public void leftReleased() {
	};
	
	@Override
	public void rightReleased() {
	};

}
