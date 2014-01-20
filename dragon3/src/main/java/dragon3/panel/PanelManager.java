/*
 * Created on 2005/01/08
 */
package dragon3.panel;

import java.awt.Point;

import dragon3.common.Body;
import dragon3.common.util.Equip;
import dragon3.manage.Attack;
import dragon3.manage.SaveManager;

/**
 * @author saito
 */
public interface PanelManager {
	
	// DataPanel
	public void displayData(int x, int y);
	public boolean displayPlace(int x, int y);
	public void displayWazaList(Body b);
	public void displayWaza(Body b, int i);
	public void displayAnalyze(Body b);
	public void displayStatus(Body b);
	public void displayAttack(Attack attack, Attack counter);
	public void displayCampData(int x, int y, int tikei, String bgcolor);
	public void displayScore(Equip equip, SaveManager saveManager);
	public void repaintData();
	public void closeData();

	// HPPanel
	public void selectHp(boolean flag);
	public void displayHp(Body ba, Body bb, int damage, boolean hit);
	public void damageHp(Body b, int damage);
	public void animeHp();
	public void closeHp();
	
	// MessagePanel
	public void addMessage(String message);
	public void startMessage(Body b);
	
	// SmallPanel
	public void displaySmall(String label, String color, Body b);
	public void closeSmall();
	
	// HelpPanel
	public void displayHelp(Point waku, String[] line, String color);
	public void setHelpLocation(int x, int y);
	public void setHelpVisible(boolean flag);
	public boolean isHelpVisible();
	public void closeHelp();
	
	// LargePanel
	public void displayLarge(String text, String color, int sleep);

}
