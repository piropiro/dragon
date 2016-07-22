




package mine;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Runner implements Runnable, FocusListener {

	public Runner(Runnable runnable) {
		th = null;
		runner = runnable;
		if (runnable instanceof Component) {
			Component component = (Component) runnable;
			component.addFocusListener(this);
		}
	}

	public void start() {
		if (th == null) {
			th = new Thread(this);
			th.start();
		}
	}

	public void stop() {
		th = null;
	}

	public void run() {
		for (Thread thread = Thread.currentThread(); th == thread;) {
			runner.run();
			Runner _tmp = this;
			Thread.yield();
		}

	}

	public static void sleep(long l) {
		try {
			Thread.currentThread();
			Thread.sleep(l);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void focusGained(FocusEvent focusevent) {
		start();
	}

	public void focusLost(FocusEvent focusevent) {
		stop();
	}

	private Thread th;
	private Runnable runner;
}
