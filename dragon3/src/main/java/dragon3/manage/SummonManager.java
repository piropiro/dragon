package dragon3.manage;

import java.awt.Point;

public interface SummonManager {

	/*** Limit ******************************/

	public abstract int getLimitTurn(Point p);

	/*** Summon ******************************/

	public abstract void summon();

}