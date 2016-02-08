package nl.mikee.twtrcntr;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by mike on 08/02/16.
 */
public class SearchCounter {

    private String date;
    private String hashTag;
    private int counter;
    private int retweets;

    public String getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.date = date.format(formatter);

    }

    public String getHashTag() {
        return hashTag;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getRetweets() {
        return retweets;
    }

    public void setRetweets(int retweets) {
        this.retweets = retweets;
    }

    @Override
    public String toString() {
        return "SearchCounter{" +
                "date=" + date +
                ", hashTag='" + hashTag + '\'' +
                ", counter=" + counter +
                ", retweets=" + retweets +
                '}';
    }
}
