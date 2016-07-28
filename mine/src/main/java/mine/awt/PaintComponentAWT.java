package mine.awt;

import java.awt.Graphics;

import javax.swing.JComponent;

import mine.event.MouseAllListener;
import mine.event.MouseManager;
import mine.event.PaintComponent;
import mine.event.PaintListener;

@SuppressWarnings("serial")
public class PaintComponentAWT extends JComponent implements PaintComponent {

	PaintListener pl;
	MouseManager mm;
	
	public PaintComponentAWT(int width, int height) {
		super();
		MineAwtUtils.setSize(this, width, height);
	}

	@Override
	public void paintComponent(Graphics g) {
		if (pl != null) {
			pl.paint(new GraphicsAWT(g));
		}
	}
	
	public void setPaintListener(PaintListener pl) {
		this.pl = pl;
	}
	
	public void setMouseAllListener(MouseAllListener mal) {
		if (mm == null) {
			mm = new MouseManagerAWT(this);
		}
		mm.setMouseAllListener(mal);
	}

	@Override
	public void setFontSize(int size) {
		setFont(MineAwtUtils.getFont(size));
	}
}
