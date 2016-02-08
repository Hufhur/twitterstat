package nl.mikee.twtrcntr;

import twitter4j.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by mike on 06/02/16.
 */
public class Tweets {

    private Twitter twitter;

    public Tweets(Twitter twitter) {
       this.twitter = twitter;
    }

    public SearchCounter countHashTag(String hashTag, LocalDate since, LocalDate until) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String sinceAsString = since.format(formatter);
        String untilAsString = until.format(formatter);

        return countHashTag(hashTag, sinceAsString, untilAsString);

    }

    public SearchCounter countHashTag(String hashTag, String since, String until) {
        SearchCounter searchCounter = new SearchCounter();
        Query query = new Query(hashTag).since(since).until(until).count(100);
        int retweetCount = 0, tweetCount = 0;

        try {
            QueryResult result = twitter.search(query);


            do {
                result = twitter.search(query);

                tweetCount += result.getTweets().size();

                for (Status tweetStatus : result.getTweets()) {

                    retweetCount += tweetStatus.getRetweetCount();
                    query.setMaxId(tweetStatus.getId());

                }

            } while (result.hasNext());


            searchCounter.setHashTag(hashTag);
            searchCounter.setCounter(tweetCount);
            searchCounter.setDate(LocalDate.parse(since));
            searchCounter.setRetweets(retweetCount);

            return searchCounter;
        }catch(TwitterException e) {
            System.out.println(e.getMessage());
            return null;
        }


    }
}
