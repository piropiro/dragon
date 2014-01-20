package dragon3.bean;

public class WazaData implements Data {

	private String id;
	private String name;
	private String label;
	private String labelColor;

	private String damageType;
	private String weponType;
	private String targetType;
	private String animeId;
	private int star;

	private String effect[];

	public WazaData() {
		id = "none";
		name = "none";
		label = "none";
		labelColor = "none";
		damageType = "none";
		weponType = "none";
		targetType = "none";
		animeId = "none";
		effect = new String[5];
	}

	public String toString(){
		return id + " - " + name;
	}

	/**
	 * @return
	 */
	public String getAnimeId() {
		return animeId;
	}

	/**
	 * @return
	 */
	public String getDamageType() {
		return damageType;
	}

	/**
	 * @return
	 */
	public String[] getEffect() {
		return effect;
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
	public String getLabel() {
		return label;
	}

	/**
	 * @return
	 */
	public String getLabelColor() {
		return labelColor;
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
	public int getStar() {
		return star;
	}

	/**
	 * @return
	 */
	public String getTargetType() {
		return targetType;
	}

	/**
	 * @return
	 */
	public String getWeponType() {
		return weponType;
	}

	/**
	 * @param string
	 */
	public void setAnimeId(String string) {
		animeId = string;
	}

	/**
	 * @param string
	 */
	public void setDamageType(String string) {
		damageType = string;
	}

	/**
	 * @param strings
	 */
	public void setEffect(String[] strings) {
		effect = strings;
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
	public void setLabel(String string) {
		label = string;
	}

	/**
	 * @param string
	 */
	public void setLabelColor(String string) {
		labelColor = string;
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
	public void setStar(int i) {
		star = i;
	}

	/**
	 * @param string
	 */
	public void setTargetType(String string) {
		targetType = string;
	}

	/**
	 * @param string
	 */
	public void setWeponType(String string) {
		weponType = string;
	}

}
