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
public class AttackUp implements SpecialEffect {


	public boolean isEffective(Body ba, Body bb, Set<AttackEffect> effect) {

		if (bb.isType(Types.ANTI_ALL))
			return false;
		if (!effect.contains(AttackEffect.ATTACK_UP))
			return false;

		if (bb.isType(Types.ATTACK_UP))
			return false;

		return true;
	}


	public void execute(Body ba, Body bb, AnimeManager anime) {

		anime.statusAnime(AnimeManager.STATUS_ATTACK_UP, bb.getX(), bb.getY());
		bb.addType(Types.ATTACK_UP);
		bb.addType(Types.ATTACK_UP_LOCK);

	}

}
