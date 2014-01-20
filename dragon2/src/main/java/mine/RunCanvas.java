// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RunCanvas.java

package mine;

// Referenced classes of package mine:
//            JCanvas, Runner

public abstract class RunCanvas extends JCanvas implements Runnable {

	public RunCanvas(int i, int j) {
		super(i, j);
		runner = new Runner(this);
	}

	public void start() {
		runner.start();
		requestFocus();
	}

	public void stop() {
		runner.stop();
	}

	public void sleep(long l) {
		RunCanvas _tmp = this;
		Runner.sleep(l);
	}

	Runner runner;
}
