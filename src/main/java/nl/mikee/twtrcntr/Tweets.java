package nl.mikee.twtrcntr;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.time.LocalDate;

/**
 * Created by mike on 06/02/16.
 */
public class Tweets {

    private Twitter twitter;

    public Tweets(Twitter twitter) {
       this.twitter = twitter;
    }

    public SearchCounter countHashTag(String hashTag) {
        SearchCounter searchCounter = new SearchCounter();
        Query query = new Query(hashTag);
        try {
            QueryResult result = twitter.search(query);
            searchCounter.setHashTag(hashTag);
            searchCounter.setCounter(result.getTweets().size());
            searchCounter.setDate(LocalDate.now().minusDays(1L));
            searchCounter.setRetweets(result.getCount());

            return searchCounter;
        }catch(TwitterException e) {
            System.out.println(e.getMessage());
            return null;
        }


    }
}
