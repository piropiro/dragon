package card;

import javax.inject.Named;
import javax.inject.Singleton;

import card.common.ImageList;
import card.common.Page;
import dagger.Module;
import dagger.Provides;
import mine.awt.ImageLoaderAWT;
import mine.awt.MineCanvasAWT;
import mine.awt.SleepManagerAWT;
import mine.event.MineCanvas;
import mine.event.PaintComponent;
import mine.event.SleepManager;
import mine.paint.MineImageLoader;
import mine.paint.UnitMap;

@Module(injects=CardDialog.class)
public class CardModule {

	private MineCanvas mc;
	private PaintComponent cardPanel;
	
	public CardModule() {
		
		this.mc = new MineCanvasAWT(new ImageLoaderAWT());

		cardPanel = mc.newLayer(0, 0, CardCanvas.WIDTH, CardCanvas.HEIGHT);
	}
	
	@Provides @Singleton
	MineCanvas provieMineCanvas() {
		return mc;
	}
	
	@Provides @Singleton
	MineImageLoader provideMineImageLoader() {
		return new ImageLoaderAWT();
	}
	
	@Provides @Singleton
	SleepManager provideSleepManager() {
		return new SleepManagerAWT();
	}
	
	@Provides @Singleton @Named("cardC")
	PaintComponent provideCardC() {
		return cardPanel;
	}
	
	@Provides @Singleton
	UnitMap provideUnitMap(ImageList il, MineImageLoader mil) {
		UnitMap map = new UnitMap(3, 11, 13, mil);
		map.setTile(Page.BACK, il.getBack(), -1);
		map.setTile(Page.WAKU, il.getWaku(), 0);
		map.clear(Page.BACK, -1);
		map.clear(Page.CHARA, -1);
		map.clear(Page.WAKU, 0);
		map.setVisible(0, true);
		map.setVisible(1, true);
		map.setVisible(2, true);
		return map;
	}
	
}
