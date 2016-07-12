package dragon2;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ActionBase.java

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

	abstract void actionMain();

	private static Thread th;
}
