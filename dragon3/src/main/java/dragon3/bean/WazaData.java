package dragon3.bean;

import java.util.ArrayList;
import java.util.List;

import dragon3.common.constant.AttackEffect;
import dragon3.common.constant.DamageType;
import dragon3.common.constant.EnergyType;
import dragon3.common.constant.GameColors;
import dragon3.common.constant.TargetType;

@lombok.Data
public class WazaData implements Data {

	private String id = "none";
	private String name = "none";
	private String label = "none";
	private String image = "none.png";
	private GameColors labelColor = GameColors.NONE;

	private DamageType damageType = DamageType.NONE;
	private TargetType targetType = TargetType.SINGLE_1;
	private String animeId = "none";
	private EnergyType energyType = EnergyType.NONE;
	private int energyCost;

	private List<AttackEffect> effect = new ArrayList<>();

	@Override
	public String toString(){
		return id + " - " + name;
	}
}
