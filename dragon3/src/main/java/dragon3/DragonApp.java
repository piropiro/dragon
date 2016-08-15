package dragon3;

import dagger.ObjectGraph;
import dragon3.controller.DragonController;
import dragon3.view.DragonFrame;

public class DragonApp {

	public static void main(String[] args) {
		
		ObjectGraph og = ObjectGraph.create(new DragonModule());
		DragonFrame fw = og.get(DragonFrame.class);
		
		
		DragonController dc = og.get(DragonController.class);
		fw.setCommandListener(dc);
		dc.setup();
		dc.title();
		fw.launch();
	}

}
