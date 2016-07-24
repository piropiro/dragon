package dragon2;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dragon2.attack.AttackData;
import dragon2.common.Body;
import dragon2.common.constant.BodyAttribute;
import dragon2.common.constant.BodyKind;
import dragon2.common.constant.GameColor;
import dragon2.common.constant.MoveType;
import dragon3.bean.BodyData;
import dragon3.bean.DeployData;
import dragon3.bean.DeployType;
import dragon3.common.constant.ArmorType;
import dragon3.common.constant.WeponType;
import mine.DataStream;
import mine.io.BeanIO;
import mine.io.JsonIO;
import net.arnx.jsonic.JSON;

/**
 *
 * @author rara
 */
public class BodyMigration {

    public BodyMigration() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void migrate_001() throws Exception {
        List<String> bodys = new ArrayList<>();
//        bodys.add("E90");
//        bodys.add("E91");
//        bodys.add("E99");
//        for (int i = 0; i < 27; i++) {
//            bodys.add("E" + i);
//        }
        bodys.add("E27");

        DataStream.setup(this);
        for (String body : bodys) {

            Vector data = (Vector) DataStream.read("data/" + body + ".txt");
            BeanIO.write("target/body/" + body + ".xml", data);
        }

    }

    @Test
    public void migrate_002() throws Exception {
        DataStream.setup(this);
        Vector vector1 = (Vector) DataStream.read("data/E" + 1 + ".txt");
        Vector vector2 = (Vector) BeanIO.read("data/body/E" + 1 + ".xml");

        for (int i = 0; i < vector1.size(); i++) {
            Body body1 = (Body) vector1.get(i);
            Body body2 = (Body) vector2.get(i);

            assertThat(body2.toString(), CoreMatchers.is(body1.toString()));
        }

    }
    
    
    
    @Test
    public void migrate_003() throws Exception {
        List<String> bodys = getFileNames();

      for (String body : bodys) {
          
          Vector vector1 = (Vector) BeanIO.read("data/body/" + body + ".xml");

          String json = JSON.encode(vector1, true);
          
          FileUtils.write(new File("target/body/" + body + ".json"), json, "UTF-8");

      }
    }
    
    @Test
    public void migrate_004() throws Exception {
        List<String> bodys = getFileNames();
        
      for (String body : bodys) {
    	  
    	  OldBody2[] oldBodys = JsonIO.read("data/body/" + body + ".json", OldBody2[].class);
          
          List<Body> newBodys = new ArrayList<>();
    	  for (OldBody2 old : oldBodys) {
    		  Body newBody = new Body();
    		  newBody.name = old.name;
    		  newBody.x = old.x;
    		  newBody.y = old.y;
    		  newBody.level = old.level;
    		  newBody.exp = old.exp;
    		  newBody.hp = old.hp;
    		  newBody.hpMax = old.hpMax;
    		  newBody.str = old.str;
    		  newBody.strMax = old.strMax;
    		  newBody.def = old.def;
    		  newBody.defMax = old.defMax;
    		  newBody.mst = old.mst;
    		  newBody.mstMax = old.mstMax;
    		  newBody.mdf = old.mdf;
    		  newBody.mdfMax = old.mdfMax;
    		  newBody.hit = old.hit;
    		  newBody.hitMax = old.hitMax;
    		  newBody.mis = old.mis;
    		  newBody.misMax = old.misMax;
    		  newBody.moveStep = old.ido;
//    		  newBody.color = old.color;
    		  newBody.img = old.img;
    		  newBody.range = old.maai;
    		  newBody.scope = old.scope;
    		  newBody.limitTurn = old.moveturn;
    		  newBody.goalX = old.gx;
    		  newBody.goalY = old.gy;
    		  newBody.store = old.store;
    		  newBody.atk = old.atk;
    		  newBody.attrList = old.type;
    		  newBody.kind = old.kind;
    		  newBody.moveType = MoveType.convert(old.itype);

    		  
    		  
    		  newBodys.add(newBody);
    	  }
 
          
          String json = JSON.encode(newBodys, true);
          
          FileUtils.write(new File("target/body/" + body + ".json"), json, "UTF-8");

      }
    }
    
    @Test
    public void migrate_005() throws Exception {
        List<String> bodys = getFileNames();

      for (String body : bodys) {
    	  
    	  OldBody[] oldBodys = JsonIO.read("data/body/" + body + ".json", OldBody[].class);
          
          List<Body> newBodys = new ArrayList<>();
    	  for (OldBody old : oldBodys) {
    		  Body newBody = new Body();
    		  newBody.name = old.name;
    		  newBody.x = old.x;
    		  newBody.y = old.y;
    		  newBody.level = old.level;
    		  newBody.exp = old.exp;
    		  newBody.hp = old.hp;
    		  newBody.hpMax = old.hpMax;
    		  newBody.str = old.str;
    		  newBody.strMax = old.strMax;
    		  newBody.def = old.def;
    		  newBody.defMax = old.defMax;
    		  newBody.mst = old.mst;
    		  newBody.mstMax = old.mstMax;
    		  newBody.mdf = old.mdf;
    		  newBody.mdfMax = old.mdfMax;
    		  newBody.hit = old.hit;
    		  newBody.hitMax = old.hitMax;
    		  newBody.mis = old.mis;
    		  newBody.misMax = old.misMax;
    		  newBody.moveStep = old.ido;
//    		  newBody.moveType = old.itype;
//    		  newBody.color = old.color;
    		  newBody.img = old.img;
    		  newBody.range = old.maai;
    		  newBody.scope = old.scope;
    		  newBody.limitTurn = old.moveturn;
    		  newBody.goalX = old.gx;
    		  newBody.goalY = old.gy;
    		  newBody.store = old.store;
    		  newBody.atk = old.atk;

    		  for (int i : old.type) {
    			  switch (i) {
    			  case 0:
    				  if (newBody.kind == null) {
    					  newBody.kind = BodyKind.CHARA;
    				  }
    				  break;
    			  case 1:
    				  newBody.kind = BodyKind.CLASS;
    				  break;
    			  case 2:
    				  newBody.kind = BodyKind.WEPON;
    				  break;
    			  case 3:
    				  newBody.kind = BodyKind.ARMOR;
    				  break;
    			  case 4:
    				  newBody.kind = BodyKind.ITEM;
    				  break;
    			  case 39:
    				  newBody.kind = BodyKind.DOLL;
    				  break;
    			  case 52:
    				  newBody.kind = BodyKind.WAZA;
    				  break;
    			  default:
    				  newBody.attrList.add(BodyAttribute.convert(i));
    			  }
    		  }
    		  
    		  newBodys.add(newBody);
    	  }
 
          
          String json = JSON.encode(newBodys, true);
          
          FileUtils.write(new File("target/body/" + body + ".json"), json, "UTF-8");

      }
    }
    
    @Test
    public void migrate_006() throws Exception {
        List<String> bodys = getFileNames();

      for (String body : bodys) {
    	  
    	  OldBody3[] oldBodys = JsonIO.read("data/body/" + body + ".json", OldBody3[].class);
          
          List<Body> newBodys = new ArrayList<>();
    	  for (OldBody3 old : oldBodys) {
    		  Body newBody = new Body();
    		  newBody.name = old.name;
    		  newBody.x = old.x;
    		  newBody.y = old.y;
    		  newBody.level = old.level;
    		  newBody.exp = old.exp;
    		  newBody.hp = old.hp;
    		  newBody.hpMax = old.hpMax;
    		  newBody.str = old.str;
    		  newBody.strMax = old.strMax;
    		  newBody.def = old.def;
    		  newBody.defMax = old.defMax;
    		  newBody.mst = old.mst;
    		  newBody.mstMax = old.mstMax;
    		  newBody.mdf = old.mdf;
    		  newBody.mdfMax = old.mdfMax;
    		  newBody.hit = old.hit;
    		  newBody.hitMax = old.hitMax;
    		  newBody.mis = old.mis;
    		  newBody.misMax = old.misMax;
    		  newBody.moveStep = old.moveStep;
    		  newBody.moveType = old.moveType;
//    		  newBody.color = old.color;
    		  newBody.img = old.img;
    		  newBody.range = old.maai;
    		  newBody.scope = old.scope;
    		  newBody.limitTurn = old.moveturn;
    		  newBody.goalX = old.gx;
    		  newBody.goalY = old.gy;
    		  newBody.store = old.store;
    		  newBody.atk = old.atk;
    		  newBody.attrList = old.type;
    		  newBody.kind = old.kind;
    		  
    		  newBody.color = GameColor.convert(old.color);
    		  
    		  newBodys.add(newBody);
    	  }
 
          
          String json = JSON.encode(newBodys, true);
          
          FileUtils.write(new File("target/body/" + body + ".json"), json, "UTF-8");

      }
    }
    
    @Test
    public void migrate_007() throws Exception {
        List<String> bodys = getFileNames();

      for (String body : bodys) {
    	  
    	  OldBody4[] oldBodys = JsonIO.read("data/body/" + body + ".json", OldBody4[].class);
          
          List<Body> newBodys = new ArrayList<>();
    	  for (OldBody4 old : oldBodys) {
    		  Body newBody = new Body();
    		  BeanUtils.copyProperties(newBody, old);
    		  newBody.attrList = old.type;
    		  newBody.range = old.maai;
    		  newBody.goalX = old.gx;
    		  newBody.goalY = old.gy;
    		  
    		  newBodys.add(newBody);
    	  }
 
          
          String json = JSON.encode(newBodys, true);
          
          FileUtils.write(new File("target/body/" + body + ".json"), json, "UTF-8");

      }
    }
    
    @Test
    public void migrate_008() throws Exception {
        List<String> bodys = getFileNames();

      for (String body : bodys) {
    	  
    	  OldBody5[] oldBodys = JsonIO.read("data/body/" + body + ".json", OldBody5[].class);
          
          List<Body> newBodys = new ArrayList<>();
    	  for (OldBody5 old : oldBodys) {
    		  Body newBody = new Body();
    		  BeanUtils.copyProperties(newBody, old);
    		  newBody.limitTurn = old.moveturn;
    		  
    		  newBodys.add(newBody);
    	  }
 
          
          String json = JSON.encode(newBodys, true);
          
          FileUtils.write(new File("target/body/" + body + ".json"), json, "UTF-8");

      }
    }
    
    @Test
    public void calc_test() throws Exception {
    	int n = 17;
    	int level = 2;
    	
    	double rate = Math.pow(1.1, 10 - level);
    	int m = (int)(n * rate + 0.5);
    	
    	double rate2 = Math.pow(1.1, level - 10);
    	int m2 = (int)(m * rate2 + 0.5);
    	
    	System.out.println("m=" + m);
    	System.out.println("m2=" + m2);
    }
	@Test
	public void migrate_009() throws Exception {
		AttackData[] attacks = JsonIO.read("data/waza/AttackData.json", AttackData[].class);


		List<String> bodys = getFileNames();
        bodys.remove("E90");
        bodys.remove("E91");
        
		Map<String, BodyData> bodyMap = new HashMap<>();
		Map<BodyKind, List<BodyData>> newBodyMap = new HashMap<>();
		for (BodyKind kind : BodyKind.values()) {
			newBodyMap.put(kind,  new ArrayList<>());
		}
		
		for (String body : bodys) {

			Body[] oldBodys = JsonIO.read("data/body/" + body + ".json", Body[].class);

			List<DeployData> newDeploys = new ArrayList<>();
			for (Body oldBody : oldBodys) {

				DeployData deploy = new DeployData();
				BeanUtils.copyProperties(deploy, oldBody);

				String oldKey = oldBody.getKind().name() + oldBody.getName();
				BodyData newBody = bodyMap.get(oldKey);
				

				if (newBody == null) {
					newBody = new BodyData();
					BeanUtils.copyProperties(newBody, oldBody);
					
					double rate = Math.pow(1.1, 10 - oldBody.getLevel());
					newBody.setHp((int)(oldBody.getHpMax() * rate + 0.5));
					newBody.setStr((int)(oldBody.getStr() * rate + 0.5));
					newBody.setDef((int)(oldBody.getDef() * rate + 0.5));
					newBody.setMst((int)(oldBody.getMst() * rate + 0.5));
					newBody.setMdf((int)(oldBody.getMdf() * rate + 0.5));
					newBody.setHit((int)(oldBody.getHit() * rate + 0.5));
					newBody.setMis((int)(oldBody.getMis() * rate + 0.5));

					// WeponType
					switch (newBody.getKind()) {
					case CLASS:
						switch (oldBody.img) {
						case 154:
							newBody.setWeponType(WeponType.SWORD);
							newBody.setImage("class_sword.png");
							break;
						case 155:
							newBody.setWeponType(WeponType.AX);
							newBody.setImage("class_ax.png");
							break;
						case 156:
							newBody.setWeponType(WeponType.BODY);
							newBody.setImage("class_body.png");
							break;
						case 157:
							newBody.setWeponType(WeponType.MAGIC);
							newBody.setImage("class_magic.png");
							break;
						case 158:
							newBody.setWeponType(WeponType.SPEAR);
							newBody.setImage("class_spear.png");
							break;
						case 159:
							newBody.setWeponType(WeponType.BOW);
							newBody.setImage("class_bow.png");
							break;
						case 160:
							newBody.setWeponType(WeponType.KNIFE);
							newBody.setImage("class_knife.png");
							break;
						default:
						}
						break;
					case WEPON:
					case ARMOR:
					case CHARA:
						newBody.setImage(String.format("chara_%03d.png", oldBody.img));
						for (int n : oldBody.atk) {
							if (n == 0) continue;
							newBody.setWeponType(convertAttackN1(attacks[n].attackN1));
							break;
						}
						break;
					default:
						newBody.setImage(String.format("chara_%03d.png", oldBody.img));
					}
					
					// WazaList
					List<String> wazaList = new ArrayList<>();
					for (int i = 0; i < oldBody.atk.length; i++) {
						if (oldBody.atk[i] == 0) {
							wazaList.add("none");
						} else {
							wazaList.add(String.format("waza_%03d", oldBody.atk[i]));
						}
					}
					newBody.setWazaList(wazaList);
					
					// AttrList
					List<BodyAttribute> attrList = newBody.getAttrList();
					for (int i = 0; i < attrList.size(); i++) {
						switch(attrList.get(i)) {
						case ASK: 
							attrList.set(i, BodyAttribute.TALKABLE); 
							break;
						case HEAL: 
							attrList.set(i, BodyAttribute.REGENE);
							break;
						case NKILL: 
							attrList.set(i, BodyAttribute.ANTI_DEATH);
							break;
						default:
						}
					}
					attrList.remove(BodyAttribute.SUMMON);
					
					// ArmorType
					switch (newBody.getKind()) {
					case ARMOR:
					case CHARA:
						newBody.setArmorType(getArmorType(oldBody));
					default:
					}
					
					List<BodyData> newBodys = newBodyMap.get(newBody.getKind());
					
					newBody.setId(String.format("%s_%03d", newBody.getKind().name().toLowerCase(), newBodys.size() + 1));
					newBodys.add(newBody);
					bodyMap.put(oldKey, newBody);
				}
				deploy.setBodyId(newBody.getId());
				deploy.setDeployType(getDeployType(oldBody));
				
				newDeploys.add(deploy);
			}

			String deployJson = JSON.encode(newDeploys, true);
			FileUtils.write(new File("target/deploy/" + body.replace("E", "D") + ".json"), deployJson, "UTF-8");

		}

		for (BodyKind kind : BodyKind.values()) {
			List<BodyData> newBodys = newBodyMap.get(kind);
			
			String json = JSON.encode(newBodys, true);

			String capKind = kind.name().substring(0, 1).toUpperCase() + kind.name().substring(1).toLowerCase(); 
			FileUtils.write(new File("target/body/" + capKind + "Data.json"), json, "UTF-8");
		}
		

	}
	
		private WeponType convertAttackN1(int n) {
			switch (n) {
			case 0: return WeponType.NONE;
			case 1: return WeponType.SWORD;
			case 2: return WeponType.AX;
			case 3: return WeponType.BODY;
			case 4: return WeponType.KNIFE;
			case 5: return WeponType.SPEAR;
			case 6: return WeponType.BOW;
			case 7: return WeponType.MAGIC;
			case 8: return WeponType.BREATH;
			default:
				throw new IllegalArgumentException("AttackN1 unmatch:" + n);
			}
		}
			
	private ArmorType getArmorType(Body b) {
		switch (b.moveType) {
		case HEAVY: return ArmorType.HEAVY;
		default: return ArmorType.LITE;
		}
	}
    
	private DeployType getDeployType(Body b) {
		switch (b.kind) {
		case ARMOR:
		case CLASS:
		case DOLL:
		case ITEM:
		case WEPON:
			if (b.getGoalX() == 0 && b.getGoalY() == 0) {
				return DeployType.BOX_ITEM;
			} else {
				return DeployType.ENEMY_ITEM;
			}
		case CHARA:
			switch (b.color) {
			case BLUE: return DeployType.PLAYER_CHARA;
			case RED: 
				if (b.attrList.contains(BodyAttribute.SUMMON)) {
					return DeployType.SUMMON;
				} else {
					return DeployType.ENEMY_CHARA;
				}
			case GREEN: return DeployType.NEUTRAL_CHARA;
			default:
				throw new IllegalArgumentException("DeployType undecided:" + b);
			}
		
		default:
			throw new IllegalArgumentException("DeployType undecided:" + b);
		}
	}
	
    private List<String> getFileNames() {
        List<String> bodys = new ArrayList<>();
        bodys.add("E90");
        bodys.add("E91");
        bodys.add("init");
        bodys.add("kakusei");
        for (int i = 1; i <= 27; i++) {
            bodys.add(String.format("E%02d", i));
        }
        
        return bodys;
    }
}
