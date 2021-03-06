package dragon3.paint;

import dragon3.common.Body;

public interface PaintListener {

    public void setSelectBody( Body b );
    public void setSelectPlace( int x, int y );
    public boolean isNextPoint( int x, int y );
    public void leftPressed();
    public void rightPressed();
    public void mouseMoved( int x, int y );
    public void leftReleased();
    public void rightReleased();
}

