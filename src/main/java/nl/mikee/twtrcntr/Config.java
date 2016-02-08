package nl.mikee.twtrcntr;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;


/**
 * Created by mike on 06/02/16.
 */
public class Config {

    public Twitter getTwitterInstance() {
        return  TwitterFactory.getSingleton();
    }




}
