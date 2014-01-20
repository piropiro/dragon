package dragon3.attack.special;

import java.util.Set;

import dragon3.anime.AnimeManager;
import dragon3.common.Body;

public interface SpecialEffect {
	public boolean isEffective(Body ba, Body bb, Set<String> effect);
	public void execute(Body ba, Body bb, AnimeManager anime);
}
