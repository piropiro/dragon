package dragon3.impl;

import java.util.HashSet;
import java.util.Set;

import dragon3.anime.AnimeManager;
import dragon3.attack.calc.Damage;
import dragon3.attack.calc.DamageRate;
import dragon3.attack.calc.HitRate;
import dragon3.attack.special.SpecialEffectManager;
import dragon3.bean.WazaData;
import dragon3.common.Body;
import dragon3.common.constant.Effects;
import dragon3.common.constant.GameColors;
import dragon3.common.constant.Types;
import dragon3.manage.Attack;
import mine.paint.UnitMap;

public class AttackImpl implements Attack {

	private WazaData waza;

	private UnitMap map;

	private Body ba;
	private Body bb;


	private Set<String> effectSet;

	private int meichu;

	/*** Constructer *********************************************************/

	public AttackImpl(AnimeManager anime, UnitMap map, Body ba, WazaData waza) {

		this.map = map;
		this.ba = ba;
		this.waza = waza;
		this.effectSet = new HashSet<>(waza.getEffect());

		bb = null;
	}



	/*** Effect ********************************/

	public boolean isEffective(String effect) {
		SpecialEffectManager sem = SpecialEffectManager.getInstance(map);
		return sem.isEffective(ba, bb, effectSet, effect);
	}

	public boolean hasEffect(String type) {
		return effectSet.contains(type);
	}

	public Set<String> getEffectSet() {
		return effectSet;
	}

	/*** Iconable *************************************************/

	public String getName() {
		return waza.getName();
	}
	public String getLabel() {
		return waza.getLabel();
	}
	public GameColors getLabelColor() {
		return waza.getLabelColor();
	}

	public int getStore() {
		return bb.getStore();
	}

	public int getDamage() {
		return Damage.calc(waza.getDamageType(), map, ba, bb, effectSet);
	}

	public int getMeichu() {
		return meichu;
	}
	public int getRate() {
		return DamageRate.calc(map, ba, bb, effectSet);
	}

	public Body getAttacker() {
		return ba;
	}

	public Body getReceiver() {
		return bb;
	}

	
	public void setReceiver(Body bb) {
		this.bb = bb;
	}



	public boolean isHit() {
		if (hasEffect(Effects.HICHU))
			return true;
		if (bb.isType(Types.SLEEP))
			return true;
		if (bb.isType(Types.RIKU))
			return true;
		int hit = HitRate.calcPredict(ba, bb, effectSet);
		if (hit + bb.getStore() > HitRate.SINGLE_HIT)
			return true;
		return false;
	}
}
