




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
