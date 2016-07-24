package dragon2;





import java.awt.*;
import java.io.PrintStream;
import java.lang.reflect.Field;
import javax.swing.*;

public class EditBase extends JPanel {

	public EditBase() {
		y = 0;
		n = 0;
		names = new String[50];
		lists = new Component[50];
		types = new int[50];
		index = new int[50];
		layout = new GridBagLayout();
		font = new Font("Serif", 0, 12);
		setLayout(layout);
	}

	public void getData(Object obj) {
		try {
			for (int i = 0; i < n; i++) {
				Field field = obj.getClass().getDeclaredField(names[i]);
				switch (types[i]) {
				case 0: // '\0'
					JTextField jtextfield = (JTextField) lists[i];
					field.set(obj, jtextfield.getText());
					break;

				case 1: // '\001'
					JComboBox jcombobox = (JComboBox) lists[i];
					field.setInt(obj, jcombobox.getSelectedIndex());
					break;

				case 2: // '\002'
					JComboBox jcombobox1 = (JComboBox) lists[i];
					int ai[] = (int[]) field.get(obj);
					ai[index[i]] = jcombobox1.getSelectedIndex();
					break;
				}
			}

		} catch (Exception exception) {
			System.out.println(exception);
		}
	}

	public void setData(Object obj) {
		try {
			for (int i = 0; i < n; i++) {
				Field field = obj.getClass().getDeclaredField(names[i]);
				switch (types[i]) {
				case 0: // '\0'
					JTextField jtextfield = (JTextField) lists[i];
					jtextfield.setText((String) field.get(obj));
					break;

				case 1: // '\001'
					JComboBox jcombobox = (JComboBox) lists[i];
					jcombobox.setSelectedIndex(field.getInt(obj));
					break;

				case 2: // '\002'
					JComboBox jcombobox1 = (JComboBox) lists[i];
					int ai[] = (int[]) field.get(obj);
					jcombobox1.setSelectedIndex(ai[index[i]]);
					break;
				}
			}

		} catch (Exception exception) {
			System.out.println(exception);
		}
	}

	private void add(int i, int j, Component component, String s, String s1,
			int k) {
		types[n] = i;
		index[n] = j;
		names[n] = s;
		lists[n] = component;
		n++;
		JLabel jlabel = new JLabel(s1);
		jlabel.setFont(font);
		jlabel.setForeground(Color.black);
		switch (k) {
		case 2: // '\002'
			y++;
			add(((Component) (jlabel)), 1, y, 1, 1);
			add(component, 2, y, 3, 1);
			break;

		case 0: // '\0'
			y++;
			add(((Component) (jlabel)), 1, y, 1, 1);
			add(component, 2, y, 1, 1);
			break;

		case 1: // '\001'
			add(((Component) (jlabel)), 3, y, 1, 1);
			add(component, 4, y, 1, 1);
			break;
		}
	}

	private JComboBox createJComboBox() {
		JComboBox jcombobox = new JComboBox();
		jcombobox.setFont(font);
		return jcombobox;
	}

	public void set(int i, String s, String s1) {
		JTextField jtextfield = new JTextField(3);
		jtextfield.setFont(font);
		jtextfield.setBackground(Color.white);
		jtextfield.setForeground(Color.black);
		add(0, 0, jtextfield, s, s1, i);
	}

	public void set(int i, String s, String s1, String as[]) {
		JComboBox jcombobox = createJComboBox();
		setItem(jcombobox, as);
		add(1, 0, jcombobox, s, s1, i);
	}

	public void set(int i, String s, String s1, int j) {
		JComboBox jcombobox = createJComboBox();
		setItem(jcombobox, j);
		add(1, 0, jcombobox, s, s1, i);
	}

	public void set(int i, String s, int j, String s1, String as[]) {
		JComboBox jcombobox = createJComboBox();
		setItem(jcombobox, as);
		add(2, j, jcombobox, s, s1, i);
	}

	public void set(int i, String s, int j, String s1, int k) {
		JComboBox jcombobox = createJComboBox();
		setItem(jcombobox, k);
		add(2, j, jcombobox, s, s1, i);
	}

	private void add(Component component, int i, int j, int k, int l) {
		GridBagConstraints gridbagconstraints = new GridBagConstraints();
		gridbagconstraints.gridx = i;
		gridbagconstraints.gridy = j;
		gridbagconstraints.gridwidth = k;
		gridbagconstraints.gridheight = l;
		gridbagconstraints.weightx = 1.0D;
		gridbagconstraints.weighty = 0.0D;
		gridbagconstraints.fill = 2;
		layout.setConstraints(component, gridbagconstraints);
		add(component);
	}

	private static void setItem(JComboBox jcombobox, int i) {
		for (int j = 0; j <= i; j++)
			jcombobox.addItem("" + j);

	}

	private static void setItem(JComboBox jcombobox, String as[]) {
		if (as == null) {
			setItem(jcombobox, 50);
			return;
		}
		for (int i = 0; i < as.length; i++)
			jcombobox.addItem(as[i]);

	}

	static final int LEFT = 0;
	static final int RIGHT = 1;
	static final int CENTER = 2;
	static final int T_STRING = 0;
	static final int T_INTEGER = 1;
	static final int T_ARRAY = 2;
	private static final int MAX = 50;
	private GridBagLayout layout;
	private Font font;
	private String names[];
	private Object lists[];
	private int types[];
	private int index[];
	private int y;
	private int n;
}
