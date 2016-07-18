/*
 * 作成日: 2004/03/23
 */
package dragon3.attack.special;

import java.util.Set;

import dragon3.anime.AnimeManager;
import dragon3.common.Body;
import dragon3.common.constant.AttackEffect;
import dragon3.common.constant.Types;

/**
 * @author k-saito
 */
public class Charm implements SpecialEffect {


	public boolean isEffective(Body ba, Body bb, Set<AttackEffect> effect) {

		if (bb.isType(Types.ANTI_ALL))
			return false;
		if (!effect.contains(AttackEffect.CHARM))
			return false;

		if (bb.getMdf() * 2 >= ba.getMst())
			return false;
		if (bb.isType(Types.GUARD_UP))
			return false;

		if (bb.isType(Types.ANTI_CHARM))
			return false;
		if (bb.isType(Types.CHARM))
			return false;

		return true;
	}


	public void execute(Body ba, Body bb, AnimeManager anime) {

		if (bb.isType(Types.CHARM_LOCK)) {
			bb.removeType(Types.CHARM_LOCK);
		} else {
			anime.statusAnime(AnimeManager.STATUS_CHARM, bb.getX(), bb.getY());
			bb.addType(Types.CHARM);
			bb.addType(Types.CHARM_LOCK);
		}
	}

}
