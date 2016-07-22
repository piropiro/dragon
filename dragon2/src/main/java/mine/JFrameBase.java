




package mine;

import java.awt.event.WindowEvent;
import javax.swing.JFrame;

// Referenced classes of package mine:
//            Mine

public class JFrameBase extends JFrame {

	public JFrameBase(String s) {
		super(s);
		enableEvents(64L);
	}

	public void display() {
		pack();
		Mine.setCenter(this);
		setVisible(true);
	}

	protected void processWindowEvent(WindowEvent windowevent) {
		super.processWindowEvent(windowevent);
		if (windowevent.getID() == 201)
			System.exit(0);
	}
}
