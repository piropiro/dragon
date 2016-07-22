




package mine;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Referenced classes of package mine:
//            JPanelBase, Mine

public class TextDialog extends JDialog implements ActionListener {

	public TextDialog(Frame frame, int i) {
		super(frame, true);
		size = i;
		Pinit();
	}

	public TextDialog(Frame frame) {
		this(frame, 15);
	}

	public void setup(String s, String s1) {
		if (Mine.isWindows()) {
			setTitle(s);
		} else {
			setTitle("Java");
			label.setText("@ " + s);
		}
		text.setText(s1);
	}

	public void show() {
		life = false;
		pack();
		Mine.setCenter(this);
		super.show();
	}

	private void Pinit() {
		JButton jbutton = new JButton("OK");
		jbutton.addActionListener(this);
		text = new JTextField(size);
		text.addActionListener(this);
		label = new JLabel();
		label.setFont(Mine.getFont(12));
		label.setForeground(Color.white);
		JPanelBase jpanelbase = new JPanelBase();
		jpanelbase.setBackground(new Color(0, 0, 150));
		jpanelbase.add(label, 1, 1, 2, 1);
		jpanelbase.add(text, 1, 2, 1, 1);
		jpanelbase.add(jbutton, 2, 2, 1, 1);
		setContentPane(jpanelbase);
	}

	public String getText() {
		if (life)
			return text.getText();
		else
			return null;
	}

	public void actionPerformed(ActionEvent actionevent) {
		life = true;
		dispose();
	}

	private JLabel label;
	private JTextField text;
	private boolean life;
	private int size;
}
