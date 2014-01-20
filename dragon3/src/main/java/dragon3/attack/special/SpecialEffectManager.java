/*
 * 作成日: 2004/03/23
 */
package dragon3.attack.special;

import java.util.HashMap;
import java.util.Set;

import mine.paint.UnitMap;
import dragon3.anime.AnimeManager;
import dragon3.common.Body;
import dragon3.common.constant.Effects;

/**
 * @author k-saito
 */
public class SpecialEffectManager {

	private static SpecialEffectManager instance;

	private HashMap<String, SpecialEffect> specialEffectMap;

	public static SpecialEffectManager getInstance(UnitMap map) {
		if (instance == null) {
			instance = new SpecialEffectManager(map);
		}
		return instance;
	}

	private SpecialEffectManager(UnitMap map) {
		specialEffectMap = new HashMap<String, SpecialEffect>();
		specialEffectMap.put(Effects.CRITICAL, new Critical());
		specialEffectMap.put(Effects.DEATH, new Death());
		specialEffectMap.put(Effects.SLEEP, new Sleep());
		specialEffectMap.put(Effects.CHARM, new Charm());
		specialEffectMap.put(Effects.POISON, new Poison());
		specialEffectMap.put(Effects.REGENE, new Regene());
		specialEffectMap.put(Effects.WET, new Wet());
		specialEffectMap.put(Effects.OIL, new Oil());
		specialEffectMap.put(Effects.ATTACK_UP, new AttackUp());
		specialEffectMap.put(Effects.GUARD_UP, new GuardUp());
		specialEffectMap.put(Effects.UPPER, new Upper());
		specialEffectMap.put(Effects.CHOP, new Chop());
		specialEffectMap.put(Effects.REFRESH, new Refresh(map));
	}

	public boolean isEffective(Body ba, Body bb, Set<String> effectSet, String effect) {
		SpecialEffect se = specialEffectMap.get(effect);
		return se.isEffective(ba, bb, effectSet);
	}

	public void execute(Body ba, Body bb, AnimeManager anime, Set<String> effectSet, String effect) {
		SpecialEffect se = specialEffectMap.get(effect);
		if (se.isEffective(ba, bb, effectSet)) {
			se.execute(ba, bb, anime);
		}
	}

}
