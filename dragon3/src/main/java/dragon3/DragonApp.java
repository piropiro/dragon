package dragon3;

import dragon3.controller.DragonController;
import dragon3.view.DragonFrame;

public class DragonApp {

	public static void main(String[] args) {
		DragonFrame fw = new DragonFrame();
		DragonController vp = new DragonController(fw);
		fw.setCommandListener(vp);
		vp.title();
		fw.launch();
	}

}
