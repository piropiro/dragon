/*
 * 作成日: 2004/03/07
 */
package dragon3.bean;

/**
 * @author k-saito
 */
public class StageData implements Data {

	private String id;
	private String name;
	private String image;
	private String deployFile;
	private String mapFile;
	private int level;

	public StageData() {
		id = "none";
		name = "none";
	}

	public String toString(){
		return id + " - " + name;
	}

	
	/**
	 * @return Returns the deployFile.
	 */
	public String getDeployFile() {
		return deployFile;
	}
	/**
	 * @param deployFile The deployFile to set.
	 */
	public void setDeployFile(String deployFile) {
		this.deployFile = deployFile;
	}
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return Returns the image.
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image The image to set.
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return Returns the level.
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level The level to set.
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * @return Returns the mapFile.
	 */
	public String getMapFile() {
		return mapFile;
	}
	/**
	 * @param mapFile The mapFile to set.
	 */
	public void setMapFile(String mapFile) {
		this.mapFile = mapFile;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
}
