/*
 * 作成日: 2004/03/23
 */
package dragon3.attack.calc;

import java.util.Set;

import mine.MineUtils;
import dragon3.common.Body;
import dragon3.common.constant.Effects;
import dragon3.common.constant.Types;
import dragon3.common.util.Luck;

/**
 * @author k-saito
 */
public class HitRate {

	public static final int SINGLE_HIT = 16;
	public static final int DOUBLE_HIT = 32;

	public static int calcPredict(Body ba, Body bb, Set<String> effect) {
		return calc(ba, bb, effect, 0);
	}

	public static int calcReal(Body ba, Body bb, Set<String> effect) {
		return calc(ba, bb, effect, Luck.rnd(1, ba));
	}

	private static int calc(Body ba, Body bb, Set<String> effect, int rnd) {
		if (bb == null)
			return 0;
		if (ba.getHit() == 0)
			return 0;

		if (effect.contains(Effects.HICHU))
			return SINGLE_HIT;

		int hit = DOUBLE_HIT - bb.getMis() * DOUBLE_HIT / ba.getHit();

		hit += rnd;

		if (effect.contains(Effects.HIT_12))
			hit += 12;
		if (effect.contains(Effects.HIT_4))
			hit += 4;
		if (effect.contains(Effects.MISS_4))
			hit -= 4;

		if (bb.isType(Types.SLEEP)) {
			hit = Math.max(SINGLE_HIT - bb.getStore(), hit);
		}
		if (bb.isType(Types.RIKU)) {
			hit = Math.max(SINGLE_HIT - bb.getStore(), hit);
		}
		if (effect.contains(Effects.COMBO)) {
			hit = Math.max(2, hit);
		} else {
			hit = MineUtils.mid(2, hit, DOUBLE_HIT - bb.getStore() - 1);
		}
		if (ba.isType(Types.ATTACK_UP)) {
			hit += 2;
		}
		if (bb.isType(Types.GUARD_UP)) {
			hit -= 2;
		}
		return hit;
	}

}
