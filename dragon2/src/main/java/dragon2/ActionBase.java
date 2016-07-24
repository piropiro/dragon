package dragon2;





import dragon2.paint.PaintBase;

public abstract class ActionBase extends PaintBase implements Runnable {

	public ActionBase() {
	}

	public static boolean isRunning() {
		return th != null && th.isAlive();
	}

	public void action() {
		PaintBase.map.setWaitPaint();
		Thread thread = new Thread(this);
		thread.start();
	}

	public void run() {
		try {
			if (th != null)
				th.join(10000L);
			th = Thread.currentThread();
			actionMain();
		} catch (InterruptedException interruptedexception) {
		}
	}

	public abstract void actionMain();

	private static Thread th;
}
