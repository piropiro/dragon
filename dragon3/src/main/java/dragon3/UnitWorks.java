package dragon3;

import mine.util.Point;
import java.util.List;

import mine.event.SleepManager;
import mine.paint.UnitMap;
import dragon3.anime.AnimeManager;
import dragon3.common.Body;
import dragon3.common.constant.GameColor;
import dragon3.image.ImageManager;
import dragon3.manage.SaveManager;
import dragon3.manage.TurnManager;
import dragon3.map.MapWorks;
import dragon3.panel.PanelManager;

public interface UnitWorks {

	/*** Main ************************/

	public void title();
	public void setMensEnd();
	public void escape();
	public boolean endJudge(Body b);
	public Point getCrystal(GameColor color);
	public void changeChara(Body before, Body after);
	public Body getChangeChara(Body before);
	public void bersekChara(Body doll);
	public void startup();
	public void backToCamp();
	public void backFromImogari();
	public List<Body> loadEnemyData(String file);

	/*** Main2 **************************/

	public void dead(Body ba, Body bb);
	public void levelup(Body ba);
	public void setEnd(Body b, boolean flag);
	public Body search(int x, int y);
	public void putUnit(List<Body> vec);


	public boolean have(Body b);
	public void limitOver();

	public void addMember(Body b);
	public void message();

	public TurnManager getTurnManager();
	public SaveManager getSaveManager();
	public SleepManager getSleepManager();
	public AnimeManager getAnimeManager();
	public ImageManager getImageManager();
	public PanelManager getPanelManager();
	public FrameWorks getFrameWorks();
	public MapWorks getMapWorks();
	public UnitMap getUnitMap();
	public List<Body> getCharaList();

	// CardPanel
	public void displayCardBattle(Body ba, Body bb);
	public boolean isCardBattleEnd();
	
	/*** Event *************************/
	
	public void executeFKeyCommand(int n, boolean shiftDown);
	public void executeMenuCommand(String command);
}
