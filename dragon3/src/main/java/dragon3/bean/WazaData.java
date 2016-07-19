package dragon3.bean;

import java.util.ArrayList;
import java.util.List;

import dragon3.common.constant.AttackEffect;
import dragon3.common.constant.DamageType;
import dragon3.common.constant.GameColors;
import dragon3.common.constant.TargetType;

public class WazaData implements Data {

	private String id;
	private String name;
	private String label;
	private GameColors labelColor;

	private DamageType damageType;
	private TargetType targetType;
	private String animeId;
	private int star;

	private List<AttackEffect> effect;

	public WazaData() {
		id = "none";
		name = "none";
		label = "none";
		labelColor = GameColors.BLACK;
		damageType = DamageType.NONE;
		targetType = TargetType.SINGLE_1;
		animeId = "none";
		effect = new ArrayList<>();
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
	public DamageType getDamageType() {
		return damageType;
	}

	/**
	 * @return
	 */
	public List<AttackEffect> getEffect() {
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
	public GameColors getLabelColor() {
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
	public TargetType getTargetType() {
		return targetType;
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
	public void setDamageType(DamageType string) {
		damageType = string;
	}

	/**
	 * @param strings
	 */
	public void setEffect(List<AttackEffect> strings) {
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
	public void setLabelColor(GameColors string) {
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
	public void setTargetType(TargetType string) {
		targetType = string;
	}

}
