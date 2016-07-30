package migration;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mine.io.MatrixIO;
import net.arnx.jsonic.JSON;

/**
 *
 * @author rara
 */
public class MapMigration {

    public MapMigration() {
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
        List<String> files = getFileNames();

      for (String file : files) {
    	  
    	  int[][] map = MatrixIO.read("data/map/" + file + ".txt");
    	 
          
          String json = JSON.encode(map, true);
          
          FileUtils.write(new File("target/map/map_" + file + ".json"), json, "UTF-8");

      }
    }
	
    private List<String> getFileNames() {
        List<String> files = new ArrayList<>();
        files.add("camp");
        files.add("collection");
        files.add("wazalist");
        files.add("init");
        for (int i = 1; i <= 27; i++) {
            files.add(String.format("D%02d", i));
        }
        
        return files;
    }
}
