




package mine;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;

// Referenced classes of package mine:
//            JPanelBase, Mine

public class ButtonDialog extends JDialog implements ActionListener {

	public ButtonDialog(Frame frame, ActionListener actionlistener) {
		super(frame, true);
		al = actionlistener;
		jp = new JPanelBase();
		setContentPane(jp);
		n = 0;
	}

	public void show() {
		pack();
		Mine.setCenter(this);
		super.show();
	}

	public void add(String s, String s1) {
		JButton jbutton = new JButton(s);
		jbutton.setActionCommand(s1);
		jbutton.addActionListener(al);
		jbutton.addActionListener(this);
		jp.add(jbutton, n % 2, n / 2, 1, 1);
		n++;
	}

	public void add(String s) {
		add(s, s);
	}

	public void actionPerformed(ActionEvent actionevent) {
		dispose();
	}

	private JPanelBase jp;
	private ActionListener al;
	private int n;
}
