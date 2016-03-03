package nl.mikee.twtrcntr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

/**
 * Created by mike on 06/02/16.
 */
public class Runner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    Config config = new Config();
    SearchHandler searchHandler = new SearchHandler();
    SearchCounter searchCounter;
    SearchCounterDAO searchCounterDAO = new SearchCounterDAO();
    Boolean doSaveResult = true;

    public static void main(String[] args) {
        LOGGER.info("TweetStat started.");


        Runner runner = new Runner();
        if(args.length > 0  && args[0].equalsIgnoreCase("NOSAVE")){
            runner.doSaveResult = false;
            LOGGER.info("App started with NOSAVE option. Result will not be saved.");
        }
        runner.go();
    }

    private void go() {
        Tweets tweets = new Tweets(config.getTwitterInstance());

        if(searchHandler.getSearches() > 0) {

            for(SearchEntity se: searchHandler.getAllSearchers()) {
                String hashtag = se.getHashTag();
                LocalDate since = LocalDate.now().minusDays(se.getDaysBack());
                LocalDate until = since.plusDays(se.getPeriodInDays());

                searchCounter = tweets.countHashTag(hashtag, since, until);

                if(doSaveResult)
                  searchCounterDAO.saveSearchCounter(searchCounter);

            }

        } else {
            LOGGER.info("No searches found.");
        }

    }

}
