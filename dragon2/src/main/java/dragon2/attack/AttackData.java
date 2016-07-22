package dragon2.attack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dragon2.common.constant.AnimeType;
import dragon2.common.constant.Effects;
import dragon2.common.constant.EnergyType;
import dragon2.common.constant.TargetType;
import lombok.Data;

@Data
public class AttackData implements Serializable {

    public static final long serialVersionUID = -7934201877652688018L;

    // dragon3
    private String animeId = "none";
    
    // dragon2
	public String name = "none";
	public String label = "none";
	public int color;
	public List<Effects> effect = new ArrayList<>();
	public int attackType;
	public int attackN1;
	public TargetType targetType;
	public AnimeType animeType;
	public int animeN1;
	public EnergyType energyType;
	public int energyCost;
}
