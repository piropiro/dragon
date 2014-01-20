/*
 * 作成日: 2004/03/25
 */
package dragon3.common.util;

import java.util.HashMap;

import dragon3.common.Body;
import dragon3.common.constant.Page;
import dragon3.common.constant.Types;


import mine.paint.UnitMap;

/**
 * @author k-saito
 */
public class MoveUtils {

	public static final String NONE = "none";
	public static final String FLY = "fly";
	public static final String HEAVY = "heavy";
	public static final String LITE = "lite";
	public static final String SWIM = "swim";
	public static final String TWIN = "twin";
	public static final String HOVER = "hover";

	public static final int WHITE = 0;
	public static final int YELLOW = 1;
	public static final int GREEN = 2;
	public static final int AQUA = 3;
	public static final int BLUE = 4;
	public static final int BLACK = 5;
	public static final int ICE = 6;
	public static final int POISONP = 7;
	public static final int OILP = 8;
	public static final int FIREP = 9;
	public static final int SKYP = 10;
	public static final int S_BLUE = 15;
	public static final int S_RED = 16;
	public static final int C_BLUE = 17;
	public static final int C_RED = 18;
	public static final int C_BLUEC = 19;
	public static final int C_REDC = 20;
	public static final int CLOSE_BOX = 21;
	public static final int OPEN_BOX = 22;
	public static final int BROKEN_BOX = 23;
	public static final int OPEN_MAGIC = 24;
	public static final int CLOSE_MAGIC = 25;

	public static final int T_SKY = 1;
	public static final int T_LAND = 2;
	public static final int T_SEA = 3;
	public static final int T_POOL = 4;
	public static final int T_ICE = 5;

	private static final HashMap<String, int[]> stepMap;

	static {
		stepMap = new HashMap<String, int[]>();
		stepMap.put(FLY,   new int[]{ 1, 1, 1, 1, 1, 99, 1, 1, 1, 1, 1 });
		stepMap.put(HEAVY, new int[]{ 1, 3, 99, 6, 99, 99, 1, 2, 2, 2, 99 });
		stepMap.put(LITE,  new int[]{ 1, 1, 99, 3, 99, 99, 1, 2, 2, 2, 99 });
		stepMap.put(SWIM,  new int[]{ 99, 99, 99, 1, 1, 99, 1, 1, 1, 1, 99 });
		stepMap.put(TWIN,  new int[]{ 2, 6, 99, 1, 1, 99, 1, 1, 1, 1, 99 });
		stepMap.put(HOVER, new int[]{ 1, 1, 99, 1, 1, 99, 1, 1, 1, 1, 99 });
		stepMap.put(NONE,  new int[]{ 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 });
	}
	
	/**
	 * キャラの歩数を返す。<p>
	 * 
	 * @param b
	 * @return
	 */
	public static int getStep(Body b) {

		int step = b.getMoveStep();

		if (b.isType(Types.MOVE_UP_2))
			step = step + 2;
		if (b.isType(Types.MOVE_UP_1))
			step = step + 1;
		if (b.isType(Types.MOVE_DOWN_1))
			step = step - 1;
		if (b.isType(Types.OIL))
			step = 1;
		
		return step;
	}

	/**
	 * 必要歩数のリストを返す。<p>
	 * 
	 * @param b
	 * @return
	 */
	public static int[] getStepList(Body b) {

		String moveType = b.getMoveType();

		if (b.isType(Types.SORA))
			moveType = FLY;
			
		if (b.isType(Types.RIKU))
			moveType = HEAVY;
			
		if (b.isType(Types.FLY_ABLE))
			moveType = FLY;

		int[] stepList = (int[])stepMap.get(moveType);

		if (b.isType(Types.LITE_WALK)) {
			stepList[WHITE] = 1;
			stepList[YELLOW] = 1;
		}
		if (b.isType(Types.SWIM_ABLE)) {
			stepList[AQUA] = 1;
			stepList[BLUE] = 1;
		}
		
		return stepList;
	}


	/**
	 * キャラの地形（陸、空、海、沼、氷）を返す。<p>
	 * 
	 * @param map
	 * @param b
	 * @return
	 */
	public static int getTikei(UnitMap map, Body b) {
		if (b.isType(Types.SORA))
			return T_SKY;
		if (!b.isType(Types.RIKU) && !b.isType(Types.SLEEP)) {
			if (b.getMoveType().equals(FLY))
				return T_SKY;
			if (b.getMoveType().equals(HOVER))
				return T_SKY;
		}
		switch (map.getData(Page.P00, b.getX(), b.getY())) {
			case ICE :
				return T_ICE;
			case AQUA :
			case BLUE :
				return T_SEA;
			case POISONP :
			case OILP :
			case FIREP :
				return T_POOL;
			default :
				return T_LAND;
		}
	}
}
