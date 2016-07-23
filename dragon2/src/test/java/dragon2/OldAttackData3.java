package dragon2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dragon2.common.constant.AnimeType;
import dragon2.common.constant.AttackEffect;
import dragon2.common.constant.DamageType;
import dragon2.common.constant.EnergyType;
import dragon2.common.constant.TargetType;
import lombok.Data;

@Data
public class OldAttackData3 implements Serializable {

    public static final long serialVersionUID = -7934201877652688018L;

    // dragon3
	private String id = "none";
    private String animeId = "none";
	private String image = "none.png";
	
    // dragon2
	public String name = "none";
	public String label = "none";
	public int color;
	public List<AttackEffect> effect = new ArrayList<>();
	public DamageType damageType;
	public int attackN1;
	public TargetType targetType;
	public AnimeType animeType;
	public int animeN1;
	public EnergyType energyType;
	public int energyCost;
}
