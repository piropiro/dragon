package mine.event;

/**
 * @author k-saito
 *
 */
public class MouseThread extends Thread {

	private int x;
	private int y;
	private int event;
	private MouseAllListener listener;


	public static final int LEFT_PRESSED = 1;
	public static final int RIGHT_PRESSED = 2;
	public static final int LEFT_RELEASED = 3;
	public static final int RIGHT_RELEASED = 4;


	public MouseThread(MouseAllListener listener, int event, int x, int y){
		this.listener = listener;
		this.event = event;
		this.x = x;
		this.y = y;
	}
		
	public void run(){
		if (listener == null) return;

		switch (event) {
			case LEFT_PRESSED:
				listener.leftPressed(x, y);
				break;
			case RIGHT_PRESSED:
				listener.rightPressed(x, y);
				break;
			case LEFT_RELEASED:
				listener.leftReleased(x, y);
				break;
			case RIGHT_RELEASED:
				listener.rightReleased(x, y);
				break;
		}
	}

}
