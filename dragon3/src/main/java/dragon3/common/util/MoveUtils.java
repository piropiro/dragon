/*
 * 作成日: 2004/03/25
 */
package dragon3.common.util;

import dragon3.common.Body;
import dragon3.common.constant.MoveType;
import dragon3.common.constant.Page;
import dragon3.common.constant.Types;
import mine.paint.UnitMap;

/**
 * @author k-saito
 */
public class MoveUtils {

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

		MoveType moveType = b.getMoveType();

		if (b.isType(Types.SORA))
			moveType = MoveType.FLY;
			
		if (b.isType(Types.RIKU))
			moveType = MoveType.HEAVY;
			
		if (b.isType(Types.FLY_ABLE))
			moveType = MoveType.FLY;

		int[] stepList = moveType.getSteps().clone();

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
			if (b.getMoveType().equals(MoveType.FLY))
				return T_SKY;
			if (b.getMoveType().equals(MoveType.HOVER))
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
