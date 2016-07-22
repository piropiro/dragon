package dragon2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dragon2.common.constant.AttackEffect;

public class OldAttackData2 implements Serializable {

        public static final long serialVersionUID = -7934201877652688018L;
        
	public OldAttackData2() {
		name = "none";
		sname = "none";
		effect = new ArrayList<>();
	}

	public String name;
	public String sname;
	public int color;
	public List<AttackEffect> effect;
	public int attackType;
	public int attackN1;
	public int trType;
	public int targetType;
	public int targetN1;
	public int targetN2;
	public int rangeType;
	public int rangeN1;
	public int rangeN2;
	public int animeType;
	public int animeN1;
	public int fuelType;
	public int fuelN1;

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

    public List<AttackEffect> getEffect() {
        return effect;
    }

    public void setEffect(List<AttackEffect> effect) {
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

    public int getTrType() {
        return trType;
    }

    public void setTrType(int TRType) {
        this.trType = TRType;
    }

    public int getTargetType() {
        return targetType;
    }

    public void setTargetType(int TargetType) {
        this.targetType = TargetType;
    }

    public int getTargetN1() {
        return targetN1;
    }

    public void setTargetN1(int TargetN1) {
        this.targetN1 = TargetN1;
    }

    public int getTargetN2() {
        return targetN2;
    }

    public void setTargetN2(int TargetN2) {
        this.targetN2 = TargetN2;
    }

    public int getRangeType() {
        return rangeType;
    }

    public void setRangeType(int RangeType) {
        this.rangeType = RangeType;
    }

    public int getRangeN1() {
        return rangeN1;
    }

    public void setRangeN1(int RangeN1) {
        this.rangeN1 = RangeN1;
    }

    public int getRangeN2() {
        return rangeN2;
    }

    public void setRangeN2(int RangeN2) {
        this.rangeN2 = RangeN2;
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

    public int getFuelType() {
        return fuelType;
    }

    public void setFuelType(int FuelType) {
        this.fuelType = FuelType;
    }

    public int getFuelN1() {
        return fuelN1;
    }

    public void setFuelN1(int FuelN1) {
        this.fuelN1 = FuelN1;
    }
}
