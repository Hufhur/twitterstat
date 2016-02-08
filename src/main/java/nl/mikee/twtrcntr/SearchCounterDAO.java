package nl.mikee.twtrcntr;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by mike on 08/02/16.
 */
public class SearchCounterDAO {

    MongoClient mongo = new MongoClient();
    MongoDatabase db;

    public SearchCounterDAO() {
        db = mongo.getDatabase("twtrcntr");
    }

    public void saveSearchCounter(SearchCounter searchCounter) {
        db.getCollection("tweetstats").insertOne(
                new Document().append("Date", searchCounter.getDate())
                              .append("Hashtag", searchCounter.getHashTag())
                              .append("Count", searchCounter.getCounter())
                              .append("Retweets", searchCounter.getRetweets())

                );

    }
}
