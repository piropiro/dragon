package dragon3.manage;

import mine.util.Point;

public interface SummonManager {

	/*** Limit ******************************/

	public abstract int getLimitTurn(Point p);

	/*** Summon ******************************/

	public abstract void summon();

}