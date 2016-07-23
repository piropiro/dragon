/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dragon2.OldAttackData;
import dragon2.OldAttackData2;
import dragon2.OldAttackData3;
import dragon2.attack.AttackData;
import dragon2.common.constant.AnimeType;
import dragon2.common.constant.DamageType;
import dragon2.common.constant.AttackEffect;
import dragon2.common.constant.EnergyType;
import dragon2.common.constant.GameColor;
import dragon2.common.constant.TargetType;
import mine.io.BeanIO;
import mine.io.JsonIO;
import net.arnx.jsonic.JSON;

/**
 *
 * @author rara
 */
public class AttackDataMigration {

	public AttackDataMigration() {
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
		Vector data = (Vector) BeanIO.read("data/waza/AttackData.xml");

		String json = JSON.encode(data, true);

		FileUtils.write(new File("target/AttackData.json"), json, "UTF-8");

	}

	@Test
	public void migrate_002() throws Exception {
		OldAttackData[] oldDatas = JsonIO.read("data/waza/AttackData.json", OldAttackData[].class);

		List<AttackData> newDatas = new ArrayList<>();
		for (OldAttackData oldData : oldDatas) {
			AttackData newData = new AttackData();

			newData.name = oldData.name;
			newData.label = oldData.sname;
//			newData.color = oldData.color;
			newData.damageType = DamageType.convert(oldData.AttackType);
			newData.attackN1 = oldData.AttackN1;
//			newData.trType = oldData.TRType;
//			newData.targetType = oldData.TargetType;
//			newData.targetN1 = oldData.TargetN1;
//			newData.targetN2 = oldData.TargetN2;
//			newData.rangeType = oldData.RangeType;
//			newData.rangeN1 = oldData.RangeN1;
//			newData.rangeN2 = oldData.RangeN2;
//			newData.animeType = oldData.AnimeType;
			newData.animeN1 = oldData.AnimeN1;
			newData.energyType = EnergyType.convert(oldData.FuelType);
			newData.energyCost = oldData.FuelN1;

			for (int i : oldData.effect) {
				switch (i) {
				case 0:
					break;
				default:
					newData.effect.add(AttackEffect.convert(i));
				}
			}
			
			newDatas.add(newData);
		}

		String json = JSON.encode(newDatas, true);

		FileUtils.write(new File("target/AttackData.json"), json, "UTF-8");

	}
	
	@Test
	public void migrate_003() throws Exception {
		OldAttackData2[] oldDatas = JsonIO.read("data/waza/AttackData.json", OldAttackData2[].class);

		List<AttackData> newDatas = new ArrayList<>();
		for (OldAttackData2 oldData : oldDatas) {
			AttackData newData = new AttackData();

			newData.name = oldData.name;
			newData.label = oldData.sname;
//			newData.color = oldData.color;
			newData.damageType = DamageType.convert(oldData.attackType);
			newData.attackN1 = oldData.attackN1;
			newData.targetType = TargetType.convert(oldData.trType);
//			newData.trType = oldData.TRType;
//			newData.targetType = oldData.TargetType;
//			newData.targetN1 = oldData.TargetN1;
//			newData.targetN2 = oldData.TargetN2;
//			newData.rangeType = oldData.RangeType;
//			newData.rangeN1 = oldData.RangeN1;
//			newData.rangeN2 = oldData.RangeN2;
//			newData.animeType = oldData.animeType;
			newData.animeN1 = oldData.animeN1;
			newData.energyType = EnergyType.convert(oldData.fuelType);
			newData.energyCost = oldData.fuelN1;
			newData.effect = oldData.effect;
			
			
			newDatas.add(newData);
		}

		String json = JSON.encode(newDatas, true);

		FileUtils.write(new File("target/AttackData.json"), json, "UTF-8");

	}
	
	@Test
	public void migrate_004() throws Exception {
		OldAttackData3[] oldDatas = JsonIO.read("data/waza/AttackData.json", OldAttackData3[].class);

		List<AttackData> newDatas = new ArrayList<>();
		for (OldAttackData3 oldData : oldDatas) {
			AttackData newData = new AttackData();

			newData.name = oldData.name;
			newData.label = oldData.label;
			newData.labelColor = GameColor.convert(oldData.color);
			newData.damageType = oldData.damageType;
			newData.attackN1 = oldData.attackN1;
			newData.targetType = oldData.targetType;
//			newData.trType = oldData.TRType;
//			newData.targetType = oldData.TargetType;
//			newData.targetN1 = oldData.TargetN1;
//			newData.targetN2 = oldData.TargetN2;
//			newData.rangeType = oldData.RangeType;
//			newData.rangeN1 = oldData.RangeN1;
//			newData.rangeN2 = oldData.RangeN2;
			newData.animeType = oldData.animeType;
			newData.animeN1 = oldData.animeN1;
			newData.energyType = oldData.energyType;
			newData.energyCost = oldData.energyCost;
			newData.effect = oldData.effect;
			
			
			newDatas.add(newData);
		}

		String json = JSON.encode(newDatas, true);

		FileUtils.write(new File("target/AttackData.json"), json, "UTF-8");

	}
	
	@Test
	public void migrate_005() throws Exception {
		AttackData[] datas = JsonIO.read("data/waza/AttackData.json", AttackData[].class);

		for (AttackData data : datas) {
			data.setImage(convertAttackN1(data.attackN1));
		}

		String json = JSON.encode(datas, true);

		FileUtils.write(new File("target/AttackData.json"), json, "UTF-8");

	}
	
	private String convertAttackN1(int n) {
		switch (n) {
		case 0: return "none.png";
		case 1: return "waza_sword.png";
		case 2: return "waza_ax.png";
		case 3: return "waza_body.png";
		case 4: return "waza_knife.png";
		case 5: return "waza_spear.png";
		case 6: return "waza_bow.png";
		case 7: return "waza_magic.png";
		case 8: return "waza_breath.png";
		default:
			throw new IllegalArgumentException("AttackN1 unmatch:" + n);
		}
	}
}
