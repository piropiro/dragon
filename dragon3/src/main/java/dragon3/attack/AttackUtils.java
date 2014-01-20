/*
 * 作成日: 2004/03/24
 */
package dragon3.attack;

import java.util.HashSet;
import java.util.Set;

/**
 * @author k-saito
 */
public class AttackUtils {

	public static Set<String> createEffectSet(String[] effect) {
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < effect.length; i++) {
			set.add(effect[i]);
		}
		return set;
	}
}
