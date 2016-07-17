package dragon3;

import java.util.List;

import dragon3.bean.WazaData;
import dragon3.common.Body;
import dragon3.common.constant.Texts;
import dragon3.common.util.Equip;
import dragon3.panel.PanelManager;

public class Level {

	private Equip equip;
	private PanelManager pm;

	/*** Level Up **************************************/

	public Level(Equip equip, PanelManager pm) {
		this.equip = equip;
		this.pm = pm;
	}
	public void levelup(Body ba) {

		if (ba.getLevel() == 0) {
			if (ba.getExp() >= 1000) {
				ba.setExp(ba.getExp() - 1000);
				pm.addMessage(ba.getName() + Texts.ha);
				pm.addMessage(Texts.equip1);
				statusup(ba);
				pm.closeData();
				pm.startMessage(ba);
			}
			for (int i = 2; i <= 11; i++) {
				Body item = equip.searchItem(ba.getGoalX() + i, ba.getGoalY());
				itemup(item);
			}
			return;
		}

		Body job = equip.searchItem(ba.getGoalX() + 1, ba.getGoalY());
		Body wepon = equip.searchItem(ba.getGoalX() + 2, ba.getGoalY());
		Body armor = equip.searchItem(ba.getGoalX() + 3, ba.getGoalY());
		Body item = equip.searchItem(ba.getGoalX() + 4, ba.getGoalY());

		itemup(job);
		itemup(wepon);
		itemup(armor);
		itemup(item);

		while (ba.getExp() >= 100) {
			ba.setExp(ba.getExp() - 100);
			if (ba.getName().length() <= 1) {
				pm.addMessage(ba.getName() + Texts.ha + Texts.equip1);
			} else {
				pm.addMessage(ba.getName() + Texts.ha);
				pm.addMessage(Texts.equip1);
			}
			statusup(ba);
			ba.setLevel(ba.getLevel() + 1);
		}
		attackup(ba);
		pm.startMessage(ba);
	}

	private void itemup(Body item) {
		if (item == null)
			return;
		if (item.getExp() < 1000)
			return;
		item.setExp(item.getExp() - 1000);
		item.setLevel(item.getLevel() + 10);
		item.setHpMax(item.getHpMax()*3/2 + 1);
		item.setStr(item.getStr()*3/2 + 1);
		item.setDef(item.getDef()*3/2 + 1);
		item.setMst(item.getMst()*3/2 + 1);
		item.setMdf(item.getMdf()*3/2 + 1);
		item.setHit(item.getHit()*3/2 + 1);
		item.setMis(item.getMis()*3/2 + 1);
		Equip.restrict(item);
	}

	private void attackup(Body ba) {
		boolean flag[] = equip.getAttack(ba);
		List<String> baAtk = ba.getWazaList();

//		attacklearn(Texts.buki, flag[1], baAtk.get(1));
//		attacklearn(Texts.bougu, flag[2], baAtk.get(2));
//		attacklearn(Texts.shokugyo, flag[3], baAtk.get(3));
//		attacklearn(Texts.shokugyo, flag[4], baAtk.get(4));
//		attacklearn(Texts.komono, flag[5], baAtk.get(5));
	}

	private void attacklearn(String type, boolean flag, String id) {
		if (!flag)
			return;
		WazaData ad = (WazaData)Statics.wazaList.getData(id);
		pm.addMessage(type + Texts.equip1);
		if (ad.getName().length() <= 5) {
			pm.addMessage(ad.getName() + Texts.wo + Texts.equip2);
		} else {
			pm.addMessage(ad.getName() + Texts.wo);
			pm.addMessage(Texts.equip2);
		}
	}

	@SuppressWarnings("unused")
	private int statusup(String name, int max, int n) {
		int ns = max / 10 - n;
		if (ns > 0)
			pm.addMessage(name + Texts.ga + " " + ns + Texts.equip3);
		return ns;
	}

	@SuppressWarnings("unused")
	private void statusup(Body ba) {
		int hp = ba.getHpMax();
		int str = ba.getStr();
		int def = ba.getDef() / 10;
		int mst = ba.getMst() / 10;
		int mdf = ba.getMdf() / 10;
		int hit = ba.getHit() / 10;
		int mis = ba.getMis() / 10;
	}

	@SuppressWarnings("unused")
	private boolean getAttack(Body ba, int na, Body bb, int nb, int exp) {
		return false;
	}
}
