package dragon3.manage;

import dragon3.common.Body;
import dragon3.common.constant.AttackEffect;
import dragon3.common.constant.GameColors;


public interface Attack {
    public String getName();
    public String getLabel();
    public GameColors getLabelColor();
    public int getStore();
    public int getDamage();
    public int getMeichu();
    public int getRate();
    public boolean isHit();
    public boolean isEffective(AttackEffect effect);
    public boolean hasEffect(AttackEffect effect);
    public Body getAttacker();
    public Body getReceiver();
}
