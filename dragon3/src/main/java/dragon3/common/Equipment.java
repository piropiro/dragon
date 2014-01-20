/*
 * Created on 2005/01/10
 */
package dragon3.common;

import java.io.Serializable;

/**
 * @author saito
 */
public class Equipment implements Serializable {

	private static final long serialVersionUID = 999929930704474088L;

	private Body chara;
	private Body mainSoul;
	private Body subSoul;
	private Body wepon;
	private Body armor;
	private Body item;

	/**
	 * @return Returns the armor.
	 */
	public Body getArmor() {
		return armor;
	}
	/**
	 * @param armor The armor to set.
	 */
	public void setArmor(Body armor) {
		this.armor = armor;
	}
	/**
	 * @return Returns the chara.
	 */
	public Body getChara() {
		return chara;
	}
	/**
	 * @param chara The chara to set.
	 */
	public void setChara(Body chara) {
		this.chara = chara;
	}
	/**
	 * @return Returns the item.
	 */
	public Body getItem() {
		return item;
	}
	/**
	 * @param item The item to set.
	 */
	public void setItem(Body item) {
		this.item = item;
	}
	/**
	 * @return Returns the mainSoul.
	 */
	public Body getMainSoul() {
		return mainSoul;
	}
	/**
	 * @param mainSoul The mainSoul to set.
	 */
	public void setMainSoul(Body mainSoul) {
		this.mainSoul = mainSoul;
	}
	/**
	 * @return Returns the subSoul.
	 */
	public Body getSubSoul() {
		return subSoul;
	}
	/**
	 * @param subSoul The subSoul to set.
	 */
	public void setSubSoul(Body subSoul) {
		this.subSoul = subSoul;
	}
	/**
	 * @return Returns the wepon.
	 */
	public Body getWepon() {
		return wepon;
	}
	/**
	 * @param wepon The wepon to set.
	 */
	public void setWepon(Body wepon) {
		this.wepon = wepon;
	}
}
