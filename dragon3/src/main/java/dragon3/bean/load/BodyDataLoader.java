package dragon3.bean.load;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import dragon3.Statics;
import dragon3.attack.calc.HitRate;
import dragon3.bean.BodyData;
import dragon3.bean.DeployData;
import dragon3.common.Body;

public class BodyDataLoader {

	public static List<Body> loadBodyDataList(String file) {
		
		List<Body> enemyList = new ArrayList<>();
		
		List<DeployData> deployList = Statics.getDeployData(file);
		for (DeployData deploy : deployList) {
			
			Body body = loadBodyData(deploy.getBodyId());
			calcLevel(body, deploy.getLevel());

			body.setDeployType(deploy.getDeployType());
			body.setColor(deploy.getDeployType().getColor());
			body.setLevel(deploy.getLevel());
			body.setScope(deploy.getScope());
			body.setRange(deploy.getRange());
			body.setLimitTurn(deploy.getLimitTurn());
			body.setX(deploy.getX());
			body.setY(deploy.getY());
			body.setGoalX(deploy.getGoalX());
			body.setGoalY(deploy.getGoalY());
			
			System.out.println(body);
			
			enemyList.add(body);
		}

		return enemyList;
	}
	
	/**
	 * BodyDataはLv.10時点のステータスを表している。
	 * レベルアップ時の上昇率を1.1倍で計算する。
	 * 
	 * @param body
	 * @param level
	 */
	private static void calcLevel(Body body, int level) {
		System.out.print("level=" + level);
		System.out.print(" str=" + body.getStr());
		
		double rate = Math.pow(1.1, level - 10);
		body.setBaseHp((int)(body.getHp()   * 10 * rate + 5));
		body.setBaseStr((int)(body.getStr() * 10 * rate + 5));
		body.setBaseDef((int)(body.getDef() * 10 * rate + 5));
		body.setBaseMst((int)(body.getMst() * 10 * rate + 5));
		body.setBaseMdf((int)(body.getMdf() * 10 * rate + 5));
		body.setBaseHit((int)(body.getHit() * 10 * rate + 5));
		body.setBaseMis((int)(body.getMis() * 10 * rate + 5));
		
		System.out.print(" baseStr=" + body.getBaseStr());
		
		body.setHpMax(body.getBaseHp() / 10);
		body.setHp(body.getHpMax());
		body.setStr(body.getBaseStr() / 10);
		body.setDef(body.getBaseDef() / 10);
		body.setMst(body.getBaseMst() / 10);
		body.setMdf(body.getBaseMdf() / 10);
		body.setHit(body.getBaseHit() / 10);
		body.setMis(body.getBaseMis() / 10);
		
		body.setStore(HitRate.SINGLE_HIT / 2);
		
		System.out.println(" str=" + body.getStr());
	}
	
	private static Body loadBodyData(String bodyId) {
		Body body = new Body();
		
		BodyData bodyData = Statics.bodyList.getData(bodyId);
		
		try {
			BeanUtils.copyProperties(body, bodyData);
			
			body.resetAttr();			
			
			List<String> baseWazaList = new ArrayList<>(body.getWazaList());
			body.setBaseWazaList(baseWazaList);

			body.getWazaList().removeIf(a -> a.equals("none") );

		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		return body;
	}
}
