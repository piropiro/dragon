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

import mine.DataStream;
import mine.io.BeanIO;
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
        List<String> bodys = new ArrayList<>();
        bodys.add("E90");
        bodys.add("E91");
        bodys.add("init");
        bodys.add("kakusei");
        for (int i = 1; i <= 27; i++) {
            bodys.add(String.format("E%02d", i));
        }

      for (String body : bodys) {
          
          Vector vector1 = (Vector) BeanIO.read("data/body/" + body + ".xml");

          String json = JSON.encode(vector1, true);
          
          FileUtils.write(new File("target/body/" + body + ".json"), json, "UTF-8");

      }

      
        
    }
}
