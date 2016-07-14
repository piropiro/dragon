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
import dragon2.attack.AttackData;
import dragon2.common.constant.Effects;
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
			newData.sname = oldData.sname;
			newData.color = oldData.color;
			newData.attackType = oldData.AttackType;
			newData.attackN1 = oldData.AttackN1;
			newData.trType = oldData.TRType;
			newData.targetType = oldData.TargetType;
			newData.targetN1 = oldData.TargetN1;
			newData.targetN2 = oldData.TargetN2;
			newData.rangeType = oldData.RangeType;
			newData.rangeN1 = oldData.RangeN1;
			newData.rangeN2 = oldData.RangeN2;
			newData.animeType = oldData.AnimeType;
			newData.animeN1 = oldData.AnimeN1;
			newData.fuelType = oldData.FuelType;
			newData.fuelN1 = oldData.FuelN1;

			for (int i : oldData.effect) {
				switch (i) {
				case 0:
					break;
				default:
					newData.effect.add(Effects.convert(i));
				}
			}
			
			newDatas.add(newData);
		}

		String json = JSON.encode(newDatas, true);

		FileUtils.write(new File("target/AttackData.json"), json, "UTF-8");

	}
}
