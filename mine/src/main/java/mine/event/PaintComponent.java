package mine.event;

public interface PaintComponent {
	public void setPaintListener(PaintListener pl);
	
	public void repaint();
	
	public void repaint(int x, int y, int w, int h);
	
	public void setVisible(boolean flag);
	
	public void setBounds(int x, int y, int w, int h);
	
	public void setLocation(int x, int y);

	public void setSize(int w, int h);
	
	public void setFontSize(int size);
	
	public void requestFocus();
}
