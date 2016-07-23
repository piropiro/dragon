/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dragon2.OldBody;
import dragon2.anime.AnimeData;
import dragon2.attack.AttackData;
import dragon2.common.constant.AnimeType;
import mine.io.BeanIO;
import mine.io.JsonIO;
import net.arnx.jsonic.JSON;

/**
 *
 * @author rara
 */
public class AnimeDataMigration {
    
    public AnimeDataMigration() {
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
        Vector data = (Vector)BeanIO.read("data/anime/AnimeData.xml");
        
        String json = JSON.encode(data, true);
    	
    	FileUtils.write(new File("target/AnimeData.json"), json, "UTF-8");
    
    }
    
    @Test
    public void migrate_002() throws Exception {
     	AnimeData[] datas = JsonIO.read("data/anime/AnimeData.json", AnimeData[].class);
        
     	for (AnimeData data : datas) {
     		data.setImage(data.getId() + ".png");
     	}
        String json = JSON.encode(datas, true);
    	
    	FileUtils.write(new File("target/AnimeData.json"), json, "UTF-8");
    
    }
    
    @Test
    public void migrate_003() throws Exception {
     	AnimeData[] animes = JsonIO.read("data/anime/AnimeData.json", AnimeData[].class);
     	AttackData[] attacks = JsonIO.read("data/waza/AttackData.json", AttackData[].class);
        
     	List<AnimeData> newList = new ArrayList<>();
     	
     	int i = 0;
     	Set<String> animeIdSet = new HashSet<>();
     	for (AttackData attack : attacks) {
     		AnimeData anime = (AnimeData)BeanUtils.cloneBean(animes[attack.getAnimeN1()]);
     		
     		
     		String animeId = attack.getAnimeType().name() + "." + anime.getId();
     		
     		if (animeIdSet.contains(animeId)) {
     			System.out.println("confilct id:" + animeId);
     		} else {
     			animeIdSet.add(animeId);
     			
     			anime.setId(animeId);
     			anime.setType(attack.getAnimeType());
     			anime.setName(attack.getAnimeType().getText() + "." + anime.getName());
     			newList.add(anime);
     		}
     		
 			attack.setId(String.format("waza_%03d",i++));
 			attack.setAnimeId(animeId);
     	}

        String animeJson = JSON.encode(newList, true);
    	
    	FileUtils.write(new File("target/AnimeData.json"), animeJson, "UTF-8");

        String attackJson = JSON.encode(attacks, true);
    	
    	FileUtils.write(new File("target/WazaData.json"), attackJson, "UTF-8");
    }
}
