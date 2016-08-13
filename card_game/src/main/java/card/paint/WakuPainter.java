package card.paint;

import javax.inject.Inject;
import javax.inject.Singleton;

import card.UnitWorks;
import card.common.Page;
import lombok.Getter;
import mine.paint.UnitMap;

@Singleton
public class WakuPainter {

	@Inject UnitMap map;

	@Getter private int x;
	@Getter private int y;

	@Inject
	public WakuPainter(){
	}

	public boolean isMoved(int new_x, int new_y){
		return ( x != new_x || y != new_y );
	}

	public void moveWaku(UnitWorks uw, int new_x, int new_y) {

		if ( isMoved(new_x, new_y) ) { 
			map.setData(Page.WAKU, x, y, 0);
			map.setData(Page.WAKU, new_x, new_y, 1);
			x = new_x;
			y = new_y;
			uw.repaint(map.getPaintBox());
		}
	}
}
