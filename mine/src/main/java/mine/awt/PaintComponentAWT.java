package mine.awt;

import java.awt.Graphics;

import javax.swing.JComponent;

import mine.event.PaintComponent;
import mine.event.PaintListener;

@SuppressWarnings("serial")
public class PaintComponentAWT extends JComponent implements PaintComponent {

	PaintListener pl;
	
	@Override
	public void paintComponent(Graphics g) {
		if (pl != null) {
			pl.paint(new GraphicsAWT(g));
		}
	}
	
	public void setPaintListener(PaintListener pl) {
		this.pl = pl;
	}
}
