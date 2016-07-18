/*
 * 作成日: 2004/03/23
 */
package dragon3.attack.calc;

import java.util.Set;

import mine.paint.UnitMap;
import dragon3.common.Body;
import dragon3.common.constant.AttackEffect;
import dragon3.common.constant.MoveType;
import dragon3.common.constant.Types;
import dragon3.common.util.MoveUtils;

/**
 * @author k-saito
 */
public class DamageRate {

	public static int calc(UnitMap map, Body ba, Body bb, Set<AttackEffect> effect) {
		if (bb == null)
			return 0;
		int tikei = MoveUtils.getTikei(map, bb);
		int rate = 100;
		if (effect.contains(AttackEffect.DAMAGE_200))
			rate += 100;
		if (effect.contains(AttackEffect.DAMAGE_300))
			rate += 200;
		if (effect.contains(AttackEffect.DAMAGE_150))
			rate += 50;
		if (effect.contains(AttackEffect.FIRE) && bb.isType(Types.FIRE_200))
			rate += 100;
		if (effect.contains(AttackEffect.ICE) && bb.isType(Types.ICE_200))
			rate += 100;
		if (effect.contains(AttackEffect.THUNDER) && bb.isType(Types.THUNDER_200))
			rate += 100;
		if (effect.contains(AttackEffect.SORA_200) && tikei == MoveUtils.T_SKY)
			rate += 100;
		if (effect.contains(AttackEffect.MIZU_200) && tikei == MoveUtils.T_SEA)
			rate += 100;
		if (effect.contains(AttackEffect.RIKU_150) && tikei == MoveUtils.T_LAND)
			rate += 50;
		if (effect.contains(AttackEffect.DRAGON_200) && bb.isType(Types.DRAGON))
			rate += 100;
		if (effect.contains(AttackEffect.UNDEAD_200) && bb.isType(Types.UNDEAD))
			rate += 100;
		if (bb.isType(Types.SLEEP))
			rate += 150;

		if (effect.contains(AttackEffect.FIRE) && bb.isType(Types.WET))
			rate /= 2;
		if (effect.contains(AttackEffect.FIRE) && bb.isType(Types.FIRE_50))
			rate /= 2;
		if (effect.contains(AttackEffect.ICE) && bb.isType(Types.ICE_50))
			rate /= 2;
		if (effect.contains(AttackEffect.THUNDER) && bb.isType(Types.THUNDER_50))
			rate /= 2;
		if (effect.contains(AttackEffect.FIRE) && tikei == MoveUtils.T_SEA)
			rate /= 2;
		if (effect.contains(AttackEffect.FIRE) && tikei == MoveUtils.T_ICE)
			rate /= 2;

		// Aquatic Guard
		if (bb.getMoveType().equals(MoveType.SWIM) || bb.getMoveType().equals(MoveType.TWIN)) {
			if (!effect.contains(AttackEffect.MIZU_100)) {
				if (tikei == MoveUtils.T_SEA && !effect.contains(AttackEffect.THUNDER))
					rate /= 2;
				if (tikei == MoveUtils.T_POOL && !effect.contains(AttackEffect.FIRE))
					rate /= 2;
			}
		}
		// Aquatic Attack
		if (bb.getMoveType().equals(MoveType.SWIM) || bb.getMoveType().equals(MoveType.TWIN)) {
			if (MoveUtils.getTikei(map, ba) == MoveUtils.T_SKY)
				rate /= 2;
			if (MoveUtils.getTikei(map, ba) == MoveUtils.T_LAND)
				rate /= 2;
		}

		if (effect.contains(AttackEffect.RIKU_0) && tikei != MoveUtils.T_LAND)
			rate = 0;
		if (effect.contains(AttackEffect.MIZU_0) && tikei != MoveUtils.T_SEA)
			rate = 0;
		if (effect.contains(AttackEffect.FIRE) && bb.isType(Types.FIRE_0))
			rate = 0;
		if (effect.contains(AttackEffect.ICE) && bb.isType(Types.ICE_0))
			rate = 0;
		if (effect.contains(AttackEffect.THUNDER) && bb.isType(Types.THUNDER_0))
			rate = 0;
		return rate;
	}

}
