/*
 * 作成日: 2004/03/23
 */
package dragon3.attack.calc;

import java.util.Set;

import mine.paint.UnitMap;
import dragon3.common.Body;
import dragon3.common.constant.Effects;
import dragon3.common.constant.Types;
import dragon3.common.util.MoveUtils;

/**
 * @author k-saito
 */
public class Damage {

	public static final String DAMAGE_SWORD = "sword";
	public static final String DAMAGE_MAGIC = "magic";
	public static final String DAMAGE_SWORD_ALL = "sword.all";
	public static final String DAMAGE_MAGIC_ALL = "magic.all";


	public static int calc(String damageType, UnitMap map, Body ba, Body bb, Set<String> effect) {

		if (bb == null)
			return 0;

		int tikei = MoveUtils.getTikei(map, bb);
		int damage = 0;
		int attack = 0;
		int guard = 0;

		if (damageType.equals(DAMAGE_SWORD)) {
			attack = ba.getStr();
			guard = bb.getDef();
		} else if (damageType.equals(DAMAGE_MAGIC)) {
			attack = ba.getMst();
			guard = bb.getMdf();
		} else if (damageType.equals(DAMAGE_SWORD_ALL)) {
			attack = ba.getStr();
		} else if (damageType.equals(DAMAGE_MAGIC_ALL)) {
			attack = ba.getMst();
		}

		if (ba.isType(Types.ATTACK_UP))
			attack += (ba.getStr() + ba.getMst()) / 4;
		if (bb.isType(Types.GUARD_UP))
			attack -= (bb.getDef() + bb.getMdf()) / 4;

		if (effect.contains(Effects.ICE) && bb.isType(Types.SLEEP))
			attack *= 1.25;
		if (effect.contains(Effects.THUNDER) && bb.isType(Types.CHARM))
			attack *= 1.25;
		if (effect.contains(Effects.ICE) && bb.isType(Types.WET))
			attack *= 1.25;
		if (effect.contains(Effects.THUNDER) && bb.isType(Types.WET))
			attack *= 1.25;
		if (effect.contains(Effects.FIRE) && bb.isType(Types.OIL))
			attack *= 1.5;
		if (effect.contains(Effects.THUNDER) && tikei == MoveUtils.T_SEA)
			attack *= 1.5;
		if (effect.contains(Effects.ICE) && tikei == MoveUtils.T_ICE)
			attack *= 1.5;
		if (ba.isType(Types.DRAGON_KILLER) && bb.isType(Types.DRAGON))
			attack *= 1.5;
		if (ba.isType(Types.UNDEAD_KILLER) && bb.isType(Types.UNDEAD))
			attack *= 1.5;

		damage = Math.max(0, attack - guard);

		if (effect.contains(Effects.HEAL) && !bb.isType(Types.UNDEAD))
			damage *= -1;
		return damage;
	}

}
