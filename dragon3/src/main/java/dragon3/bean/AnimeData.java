/*
 * 作成日: 2004/03/07
 */
package dragon3.bean;

/**
 * @author k-saito
 */
public class AnimeData implements Data {

	private String id;
	private String name;
	private String type;
	private String image;
	private int sleep;

	public AnimeData() {
		id = "none";
		name = "none";
		type = "none";
		image = "0.png";
	}

	public String toString(){
		return id + " - " + name;
	}

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public int getSleep() {
		return sleep;
	}

	/**
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param string
	 */
	public void setId(String string) {
		id = string;
	}

	/**
	 * @param string
	 */
	public void setImage(String string) {
		image = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param i
	 */
	public void setSleep(int i) {
		sleep = i;
	}

	/**
	 * @param string
	 */
	public void setType(String string) {
		type = string;
	}

}
