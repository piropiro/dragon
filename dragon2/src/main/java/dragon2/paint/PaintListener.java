package dragon2.paint;





import mine.util.Point;

import dragon2.common.Body;

public interface PaintListener {

	public abstract void setSelectBody(Body body);

	public abstract void setSelectPlace(Point point);

	public abstract boolean isNextPoint(Point point);

	public abstract void leftPressed();

	public abstract void rightPressed();

	public abstract void mouseMoved(Point point);

	public abstract void leftReleased();

	public abstract void rightReleased();
}
