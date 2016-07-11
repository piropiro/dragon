/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migration;

import java.io.File;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mine.io.BeanIO;
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
        Vector data = (Vector)BeanIO.read("data/waza/AttackData.xml");
        
        String json = JSON.encode(data, true);
    	
    	FileUtils.write(new File("target/AttackData.json"), json, "UTF-8");
    
    }
}
