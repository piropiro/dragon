package card.anime;

import javax.inject.Inject;
import javax.inject.Singleton;

import card.CardMap;
import card.UnitWorks;
import card.body.Card;
import card.common.ImageList;

@Singleton
public class AnimeManager {

	@Inject CardMap map;
	@Inject ImageList il;
	
	@Inject
	public AnimeManager(){
	}
	
	public void opening(UnitWorks canvas, Card[] red, Card[] blue){
		new OpeningAnime(canvas, this, map.getMap(), red, blue, il).run();
	}
	public void closing(UnitWorks canvas, Card[] cards){
		new ClosingAnime(canvas, map.getMap(), cards).run();
	}
	
	public void battle(UnitWorks canvas, Card red, Card blue){
		new BattleAnime(canvas, this, red, blue).run();
	}

	public void openCard(UnitWorks canvas, Card card){
		new OpenCardAnime(canvas, card).run();
	}
	public void closeCard(UnitWorks canvas, Card card){
		new CloseCardAnime(canvas, card).run();
	}

	public void loseCard(UnitWorks canvas, Card card){
		new LoseCardAnime(canvas, card).run();
	}
	public void winCard(UnitWorks canvas, Card card){
		new WinCardAnime(canvas, card).run();
	}
	public void drawCard(UnitWorks canvas, Card red, Card blue){
		new DrawCardAnime(canvas, red, blue).run();
	}

	public void moveCard(UnitWorks canvas, Card card, int new_x, int new_y, int max){
		new MoveCardAnime(canvas, card, new_x, new_y, max).run();
	}

	public void moveCard(UnitWorks canvas, Card red, int red_x, int red_y, Card blue, int blue_x, int blue_y, int max){
		new MoveDoubleCardAnime(canvas, red, red_x, red_y, blue, blue_x, blue_y, max).run();
	}
	
	public void openDoubleCard(UnitWorks canvas){
		new OpenDoubleCardAnime(canvas, this, map.getMap(), il).run();
	}
	
	public void closeDoubleCard(UnitWorks canvas){
		new CloseDoubleCardAnime(canvas, map.getMap()).run();
	}
	public void doubleCard(UnitWorks canvas, Card left, Card right){
		new DoubleCardAnime(canvas, this, map.getMap(), left, right).run();
	}
}
