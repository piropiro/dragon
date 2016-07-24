package dragon2;





import java.io.Serializable;

public class OldAttackData implements Serializable {

        public static final long serialVersionUID = -7934201877652688018L;
        
	public OldAttackData() {
		name = "none";
		sname = "none";
		effect = new int[5];
	}

	public String name;
	public String sname;
	public int color;
	public int effect[];
	public int AttackType;
	public int AttackN1;
	public int TRType;
	public int TargetType;
	public int TargetN1;
	public int TargetN2;
	public int RangeType;
	public int RangeN1;
	public int RangeN2;
	public int AnimeType;
	public int AnimeN1;
	public int FuelType;
	public int FuelN1;

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

    public int[] getEffect() {
        return effect;
    }

    public void setEffect(int[] effect) {
        this.effect = effect;
    }

    public int getAttackType() {
        return AttackType;
    }

    public void setAttackType(int AttackType) {
        this.AttackType = AttackType;
    }

    public int getAttackN1() {
        return AttackN1;
    }

    public void setAttackN1(int AttackN1) {
        this.AttackN1 = AttackN1;
    }

    public int getTRType() {
        return TRType;
    }

    public void setTRType(int TRType) {
        this.TRType = TRType;
    }

    public int getTargetType() {
        return TargetType;
    }

    public void setTargetType(int TargetType) {
        this.TargetType = TargetType;
    }

    public int getTargetN1() {
        return TargetN1;
    }

    public void setTargetN1(int TargetN1) {
        this.TargetN1 = TargetN1;
    }

    public int getTargetN2() {
        return TargetN2;
    }

    public void setTargetN2(int TargetN2) {
        this.TargetN2 = TargetN2;
    }

    public int getRangeType() {
        return RangeType;
    }

    public void setRangeType(int RangeType) {
        this.RangeType = RangeType;
    }

    public int getRangeN1() {
        return RangeN1;
    }

    public void setRangeN1(int RangeN1) {
        this.RangeN1 = RangeN1;
    }

    public int getRangeN2() {
        return RangeN2;
    }

    public void setRangeN2(int RangeN2) {
        this.RangeN2 = RangeN2;
    }

    public int getAnimeType() {
        return AnimeType;
    }

    public void setAnimeType(int AnimeType) {
        this.AnimeType = AnimeType;
    }

    public int getAnimeN1() {
        return AnimeN1;
    }

    public void setAnimeN1(int AnimeN1) {
        this.AnimeN1 = AnimeN1;
    }

    public int getFuelType() {
        return FuelType;
    }

    public void setFuelType(int FuelType) {
        this.FuelType = FuelType;
    }

    public int getFuelN1() {
        return FuelN1;
    }

    public void setFuelN1(int FuelN1) {
        this.FuelN1 = FuelN1;
    }
}
