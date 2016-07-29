package dragon3.manage;

import card.CardCanvas;
import dragon3.panel.DataPanel;
import dragon3.panel.HPanel;
import dragon3.panel.HelpPanel;
import dragon3.panel.LargePanel;
import dragon3.panel.MessagePanel;
import dragon3.panel.SmallPanel;
import dragon3.view.FrameWorks;
import mine.awt.PaintComponentAWT;
import mine.event.PaintComponent;
import mine.event.SleepManager;
import mine.paint.MineImageLoader;

public class FrameWorksMock implements FrameWorks {

	@Override
	public void setMenu(int n) {
	}

	@Override
	public PaintComponent getMapPanel() {
		return new PaintComponentAWT(640, 480);
	}

	@Override
	public PaintComponent getAnimePanel() {
		return new PaintComponentAWT(32, 32);
	}

	@Override
	public PaintComponent getHPanel1() {
		return new PaintComponentAWT(HPanel.WIDTH, HPanel.HEIGHT);
	}

	@Override
	public PaintComponent getHPanel2() {
		return new PaintComponentAWT(HPanel.WIDTH, HPanel.HEIGHT);
	}

	@Override
	public PaintComponent getHelpPanel() {
		return new PaintComponentAWT(HelpPanel.WIDTH, HelpPanel.HEIGHT);
	}

	@Override
	public PaintComponent getSmallPanel() {
		return new PaintComponentAWT(SmallPanel.WIDTH, SmallPanel.HEIGHT);
	}

	@Override
	public PaintComponent getLargePanel() {
		return new PaintComponentAWT(LargePanel.WIDTH, LargePanel.HEIGHT);
	}

	@Override
	public PaintComponent getCardPanel() {
		return new PaintComponentAWT(CardCanvas.WIDTH, CardCanvas.HEIGHT);
	}

	@Override
	public PaintComponent getDataPanel1() {
		return new PaintComponentAWT(DataPanel.WIDTH, DataPanel.HEIGHT);
	}

	@Override
	public PaintComponent getDataPanel2() {
		return new PaintComponentAWT(DataPanel.WIDTH, DataPanel.HEIGHT);
	}

	@Override
	public PaintComponent getMessagePanel() {
		return new PaintComponentAWT(MessagePanel.WIDTH, MessagePanel.HEIGHT);
	}
	@Override
	public PaintComponent getStageSelectPanel() {
		return new PaintComponentAWT(640, 480);
	}

	@Override
	public MineImageLoader getImageLoader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SleepManager getSleepManager() {
		// TODO Auto-generated method stub
		return null;
	}


	
}
