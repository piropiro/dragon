package dragon2;





import mine.util.Point;
import java.util.Vector;

import dragon2.attack.AttackBase;
import dragon2.common.Body;
import dragon2.common.constant.AnimeType;
import dragon2.common.constant.GameColor;

public interface UnitWorks {

	public abstract void rmiStarterEnd(Vector vector);

	public abstract void mensTurnEnd();

	public abstract void mensTurnStart();

	public abstract void setMensEnd();

	public abstract void escape();

	public abstract int getTurn();

	public abstract boolean endJudge(Body body);

	public abstract Point getCrystal(int i);

	public abstract void displayData(Point point);

	public abstract boolean displayPlace(Point point);

	public abstract void changeChara(Body body, Body body1);

	public abstract Body getChangeChara(Body body);

	public abstract void bersekChara(Body body);

	public abstract void startup();

	public abstract void backToCamp();

	public abstract void backFromImogari();

	public abstract void nameChange();

	public abstract void sleep(long l);

	public abstract void waitFast();

	public abstract void waitSlow();

	public abstract void dead(AttackBase attackbase);

	public abstract void levelup(Body body);

	public abstract void setEnd(Body body, boolean flag);

	public abstract Body search(int i, int j);

	public abstract void putUnit(Vector vector);

	public abstract void setCampPanel(Point point, int i, GameColor j);

	public abstract void setMPanel(String s);

	public abstract void startMPanel(Body body);

	public abstract void setAtkList(Body body);

	public abstract void setAnalyze(Body body);

	public abstract void setSPanel(Body body);

	public abstract void setAPanel(Iconable iconable, Iconable iconable1);

	public abstract void setAPanel();

	public abstract void setTPanel(Iconable iconable, Body body);

	public abstract void setAnime(int i, int j, Point point, Point point1);

	public abstract void setNPanel(Body body, int i);

	public abstract void setLPanel(String s, GameColor i, int j);

	public abstract void setHelp(String as[], int i);

	public abstract void setHelpLocate(int i, int j);

	public abstract void closeAPanel();

	public abstract void closeTPanel();

	public abstract void closeNPanel();

	public abstract void closeHelp();

	public abstract void selectPanel(boolean flag);

	public abstract void setHPanel(Body body, Body body1, int i, boolean flag);

	public abstract void hpdamage(int i);

	public abstract void hphenka();

	public abstract void closeHPanel();

	public abstract boolean have(Body body);

	public abstract void limitOver();

	public abstract int getSummonLimit(Point point);

	public abstract int getTreasureLimit(Point point);

	public abstract String getTreasureCount();

	public abstract int getMaterialCount();

	public abstract void addMember(Body body);

	public abstract void message();

	public abstract boolean isAllClear();

	public abstract boolean isFirst();

	public abstract void countItem();
}
