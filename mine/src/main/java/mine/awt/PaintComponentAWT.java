package mine.awt;

import java.awt.Graphics;

import javax.swing.JComponent;

import mine.event.PaintComponent;
import mine.event.PaintListener;

@SuppressWarnings("serial")
public class PaintComponentAWT extends JComponent implements PaintComponent {

	private PaintListener pl;
	
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
	
	@Override
	public void setPaintListener(PaintListener pl) {
		this.pl = pl;
	}
}
