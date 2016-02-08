package nl.mikee.twtrcntr;

/**
 * Created by mike on 06/02/16.
 */
public class Runner {

    Config config = new Config();
    SearchHandler searchHandler = new SearchHandler();
    SearchCounter searchCounter;
    SearchCounterDAO searchCounterDAO = new SearchCounterDAO();


    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.go();
    }

    private void go() {
        Tweets tweets = new Tweets(config.getTwitterInstance());


        if(searchHandler.getSearches() > 0) {


        for(SearchEntity se: searchHandler.getAllSearchers()) {
            searchCounter = tweets.countHashTag(se.getHashTag());
            searchCounterDAO.saveSearchCounter(searchCounter);
            searchCounter.toString();

        }


        } else {
            System.out.println("No searches found.");
        }

    }

}
