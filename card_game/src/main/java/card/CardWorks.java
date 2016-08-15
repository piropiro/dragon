package card;


import mine.paint.PaintBox;
import mine.thread.Lock;
import card.body.Card;

public interface CardWorks {

	public void repaint();
	public void repaint(PaintBox box);
	public void addCard(Card card);
	public void removeCard(Card card);
	public void sleep(long msec);
	public int nextInt(int max);
	
	public void wakuMove(int x, int y);
	public void accept();
	public Lock getLock();
}
