package dragon2;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dragon2.common.Body;
import dragon2.common.constant.BodyKind;
import dragon2.common.constant.GameColor;
import dragon2.common.constant.MoveType;
import dragon2.common.constant.BodyAttribute;
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
    		  newBody.maai = old.maai;
    		  newBody.scope = old.scope;
    		  newBody.moveturn = old.moveturn;
    		  newBody.gx = old.gx;
    		  newBody.gy = old.gy;
    		  newBody.store = old.store;
    		  newBody.atk = old.atk;
    		  newBody.type = old.type;
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
    		  newBody.maai = old.maai;
    		  newBody.scope = old.scope;
    		  newBody.moveturn = old.moveturn;
    		  newBody.gx = old.gx;
    		  newBody.gy = old.gy;
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
    				  newBody.type.add(BodyAttribute.convert(i));
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
    		  newBody.maai = old.maai;
    		  newBody.scope = old.scope;
    		  newBody.moveturn = old.moveturn;
    		  newBody.gx = old.gx;
    		  newBody.gy = old.gy;
    		  newBody.store = old.store;
    		  newBody.atk = old.atk;
    		  newBody.type = old.type;
    		  newBody.kind = old.kind;
    		  
    		  newBody.color = GameColor.convert(old.color);
    		  
    		  newBodys.add(newBody);
    	  }
 
          
          String json = JSON.encode(newBodys, true);
          
          FileUtils.write(new File("target/body/" + body + ".json"), json, "UTF-8");

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
