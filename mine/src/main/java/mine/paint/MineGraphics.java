/*
 * Created on 2004/10/10
 */
package mine.paint;



/**
 * @author saito
 */
public interface MineGraphics {
	
	public void setColor(MineColor color);
	
	public void setColor(int r, int g, int b);
	
	public void setColor(int r, int g, int b, int a);

	public void setColor(int[] rgba);
	
	public void fillRect(int x, int y, int w, int h);
	
	public void drawRect(int x, int y, int w, int h);
	
	public void drawLine(int sx, int sy, int dx, int dy);
	
	public void drawString(String s, int x, int y);
	
	public void drawImage(MineImage image, int x, int y);
	
	public void drawImage(MineImage image, int sx, int sy, int w, int h, int dx, int dy);

	/**
	 * イメージの一部を回転させます。
	 * @param img - ソースイメージ
	 * @param x - 回転の中心座標X
	 * @param y - 回転の中心座標Y
	 * @param w - 回転させる部分の幅
	 * @param h - 回転させる部分の高さ
	 * @param theta - 回転させる角度
	 * @return 指定された一部を回転させたイメージ
	 */
	public MineImage rotate(MineImage image, int x, int y, int w, int h, double theta);
	
	public void drawRotateImage(MineImage image, int dx, int dy, double theta);

	public void drawRotateImage(MineImage image, int sx, int sy, int w, int h, int dx, int dy, double theta);

	public void setAlpha(double alpha);
	
	public void setAntialias(boolean flag);
	
	public void setFont(String font, int size);

	/**
	 * 文字列を中央に描画する。<p>
	 * 
	 * @param s
	 * @param x
	 * @param y
	 * @param xs
	 * @param g
	 */
	public void drawString(String s, int x, int y, int xs);
}
