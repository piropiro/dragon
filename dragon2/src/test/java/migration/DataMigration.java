/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import mine.DataStream;
import mine.io.BeanIO;
import mine.io.MatrixIO;
import mine.io.StringIO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rara
 */
public class DataMigration {
    
    public DataMigration() {
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
        List<String> maps = new ArrayList<>();
//        maps.add("D90.txt");
//        maps.add("D92.txt");
//        for (int i = 0; i< 27; i++) {
//            maps.add("D" + i + ".txt");
//        }
        maps.add("D27.txt");

        DataStream.setup(this);
        for (String map : maps) {
            
            int[][] data = (int[][]) DataStream.read("/data/" + map);
            MatrixIO.write("target/map/" + map, data);
        }
    }
    
    // @Test
    public void migrate_002() throws Exception {
        List<String> texts = Arrays.asList("itype.txt", "tokusei.txt", "effect.txt", "trtype.txt", "atype.txt");
        DataStream.setup(this);
        for (String text : texts) {
            String[] data = (String[]) DataStream.read("/data/" + text);
            StringIO.write("target/text/" + text, data);
        }
    }
    
    //@Test
    public void migrate_003() throws Exception {
        DataStream.setup(this);
        int[] stages = (int[]) DataStream.read("/data/stage.txt");
        
        for (int stage : stages) {
            System.out.println(stage);
        }
    }
    
    @Test
    public void migrate_004() throws Exception {
        List<String> maps = new ArrayList<>();
//        maps.add("D90.txt");
//        maps.add("D92.txt");
//        for (int i = 0; i< 27; i++) {
//            maps.add("D" + i + ".txt");
//        }
        maps.add("D27.txt");

        DataStream.setup(this);
        for (String map : maps) {
            
            int[][] data = (int[][]) MatrixIO.readX("data/map/" + map);
            MatrixIO.write("target/map/" + map, data);
        }
    }
    
    @Test
    public void migrate_005() throws Exception {
        DataStream.setup(this);
        Vector data = (Vector)DataStream.read("data/AD.txt");
        
        BeanIO.write("target/AttackData.xml", data);
    }
}
