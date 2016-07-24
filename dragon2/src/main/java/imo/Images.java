

// Decompiler options: packimports(3)


package imo;

import java.awt.Image;
import mine.ImageLoader;

class Images {

	Images() {
	}

	static void setup() {
		chara = ImageLoader.getImage("/image/imos.gif", true);
	}

	static void setEndImage(int i) {
		endi = ImageLoader.getImage("/image/end" + i + ".gif", true);
	}

	static Image chara;
	static Image endi;
}
