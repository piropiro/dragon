package dragon2.attack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dragon2.common.constant.Effects;
import dragon2.common.constant.EnergyType;
import dragon2.common.constant.TargetType;

public class AttackData implements Serializable {

        public static final long serialVersionUID = -7934201877652688018L;
        
	public AttackData() {
		name = "none";
		sname = "none";
		effect = new ArrayList<>();
	}

	public String name;
	public String sname;
	public int color;
	public List<Effects> effect;
	public int attackType;
	public int attackN1;
	public TargetType targetType;
	public int animeType;
	public int animeN1;
	public EnergyType energyType;
	public int energyCost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public List<Effects> getEffect() {
        return effect;
    }

    public void setEffect(List<Effects> effect) {
        this.effect = effect;
    }

    public int getAttackType() {
        return attackType;
    }

    public void setAttackType(int AttackType) {
        this.attackType = AttackType;
    }

    public int getAttackN1() {
        return attackN1;
    }

    public void setAttackN1(int AttackN1) {
        this.attackN1 = AttackN1;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }


    public int getAnimeType() {
        return animeType;
    }

    public void setAnimeType(int AnimeType) {
        this.animeType = AnimeType;
    }

    public int getAnimeN1() {
        return animeN1;
    }

    public void setAnimeN1(int AnimeN1) {
        this.animeN1 = AnimeN1;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public void setEnergyCost(int energyCost) {
        this.energyCost = energyCost;
    }
}
