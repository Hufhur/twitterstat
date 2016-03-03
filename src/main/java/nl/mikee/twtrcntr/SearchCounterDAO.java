package nl.mikee.twtrcntr;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mike on 08/02/16.
 */
public class SearchCounterDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchCounterDAO.class);

    MongoClient mongo = new MongoClient();
    MongoDatabase db;

    public SearchCounterDAO() {
        db = mongo.getDatabase("twtrcntr");
        LOGGER.info("Connecting to database {}",db.getName());
    }

    public void saveSearchCounter(SearchCounter searchCounter) {
        LOGGER.info("Saving tweetstat (hashtag / count) : {} / {}", searchCounter.getHashTag(), searchCounter.getCounter());
        db.getCollection("tweetstats").insertOne(
                new Document().append("Date", searchCounter.getDate())
                              .append("Hashtag", searchCounter.getHashTag())
                              .append("Count", searchCounter.getCounter())
                              .append("Retweets", searchCounter.getRetweets())

                );

    }
}
