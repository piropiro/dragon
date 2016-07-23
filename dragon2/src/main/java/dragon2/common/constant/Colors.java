package dragon2.common.constant;





import dragon2.common.Body;

public class Colors {

	public Colors() {
	}

	public static void setup(int i, int j) {
		PC = i;
		EC = j;
	}

	public static int getPC() {
		return PC;
	}

	public static int getEC() {
		return EC;
	}


	private static int PC;
	private static int EC;
	static final int BLUE = 1;
	static final int GREEN = 2;
	static final int RED = 3;
}
