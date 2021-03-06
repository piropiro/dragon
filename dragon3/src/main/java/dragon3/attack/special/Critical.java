/*
 * 作成日: 2004/03/23
 */
package dragon3.attack.special;

import java.util.Set;

import dragon3.anime.AnimeManager;
import dragon3.common.Body;
import dragon3.common.constant.Effects;
import dragon3.common.constant.Types;

/**
 * @author k-saito
 */
public class Critical implements SpecialEffect {

	public boolean isEffective(Body ba, Body bb, Set<String> effect) {

		if (bb.isType(Types.ANTI_ALL))
			return false;

		if (!effect.contains(Effects.CRITICAL))
			return false;
		if (bb.isType(Types.ANTI_CRITICAL))
			return false;
		if (ba.getLevel() < bb.getLevel())
			return false;
		if (bb.isType(Types.GUARD_UP))
			return false;
		if (bb.isType(Types.POISON))
			return true;
		if (bb.getDef() >= ba.getStr())
			return false;

		return true;
	}

	/* (非 Javadoc)
	 * @see dragon3.attack.special.SpecialEffect#execute(dragon3.common.Body, dragon3.common.Body)
	 */
	public void execute(Body ba, Body bb, AnimeManager anime) {
		anime.systemAnime(AnimeManager.ID_FINISH, bb.getX(), bb.getY());
		anime.criticalAnime(bb.getX(), bb.getY());
		anime.slideText(AnimeManager.TEXT_FINISH, bb.getX(), bb.getY());
	}

}
