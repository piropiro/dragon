package dragon3.bean.load;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import dragon3.Statics;
import dragon3.bean.BodyData;
import dragon3.bean.DeployData;
import dragon3.common.Body;

public class BodyDataLoader {

	public static List<Body> loadBodyDataList(String file) {
		
		List<Body> enemyList = new ArrayList<>();
		
		List<DeployData> deployList = Statics.getDeployData(file);
		for (DeployData deploy : deployList) {
			
			Body body = loadBodyData(deploy.getBodyId());
				
			body.setDeployType(deploy.getDeployType());
			body.setColor(deploy.getColor());
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
	
	public static Body loadBodyData(String bodyId) {
		Body body = new Body();
		
		BodyData bodyData = Statics.bodyList.getData(bodyId);
		
		try {
			BeanUtils.copyProperties(body, bodyData);
			body.setHpMax(bodyData.getHp());
			body.setBaseStr(bodyData.getStr() * 10);
			body.setBaseDef(bodyData.getDef() * 10);
			body.setBaseMst(bodyData.getMst() * 10);
			body.setBaseMdf(bodyData.getMdf() * 10);
			body.setBaseHit(bodyData.getHit() * 10);
			body.setBaseMis(bodyData.getMis() * 10);
			body.resetAttr();			
			body.getWazaList().removeIf(a -> a.equals("none") );
						
			System.out.println(body);

		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		return body;
	}
}
