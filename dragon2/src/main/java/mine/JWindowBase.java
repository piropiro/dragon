




package mine;

import java.awt.Window;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JWindow;

public class JWindowBase extends JWindow implements FocusListener {

	public JWindowBase(Window window) {
		super(window);
		addFocusListener(this);
		enableInputMethods(false);
	}

	public void focusGained(FocusEvent focusevent) {
		getOwner().requestFocus();
	}

	public void focusLost(FocusEvent focusevent) {
	}
}
