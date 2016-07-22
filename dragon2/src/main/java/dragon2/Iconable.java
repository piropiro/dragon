package dragon2;





import dragon2.common.Body;
import dragon2.common.constant.AttackEffect;

public interface Iconable {

	public abstract String getSubName();

	public abstract String getName();

	public abstract int getColor();

	public abstract Body getBody(boolean flag);

	public abstract int getStore();

	public abstract int getDamage();

	public abstract int getMeichu();

	public abstract int getRate();

	public abstract boolean isHit();

	public abstract boolean isPossible(AttackEffect i);

	public abstract boolean isEffect(AttackEffect effect);
}
