package nl.mikee.twtrcntr;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by mike on 06/02/16.
 */
public class SearchHandler {

    private PropertiesConfiguration prop;
    private ArrayList<SearchEntity> searchEntities = new ArrayList<SearchEntity>();
    private String singleHashTag;
    private int singleDaysBack, singlePeriod;
    //private String searchPath = "/Users/mike/zoekertjes/";
    private String searchPath = "zoekertjes/";

    public int getSearches(){
        //File folder = new File("/Users/mike/zoekertjes/");

        try {
            Files.walk(Paths.get(searchPath)).forEach(filePath -> {

                readPropertiesFile(filePath.toString());

            });

            return searchEntities.size();

        }catch(IOException e) {
            System.out.println(e.getMessage());
            return -1;
        }

    }

    private void readPropertiesFile(String file) {
        System.out.println("read props in " + file);
        try {
            prop = new PropertiesConfiguration(file);
        }catch(ConfigurationException e){
            System.out.println(e.getMessage());
            return;
        }

        singleHashTag =  prop.getString("hashtag");
        singleDaysBack = prop.getInt("daysback");
        singlePeriod = prop.getInt("period");

        searchEntities.add(SearchEntity.getInstanceOf(singleHashTag)
                .whereDaysBackIs(singleDaysBack)
                .wherePeriodInDaysIs(singlePeriod));


    }

    public ArrayList<SearchEntity> getAllSearchers() {
        return searchEntities;
    }

    public SearchEntity extractNextSearch() {
        if(searchEntities.size() > 0) {
            SearchEntity searchEntity = searchEntities.get(0);
            searchEntities.remove(0);
            return searchEntity;
        } else {
            return null;
        }
    }

    public String getHashTagToSearchFor() {
        if(prop != null)
            return prop.getString("hashtag");
        else
            return null;

        //prop != null ? return prop.getString("hastag") : return null;

    }


}
