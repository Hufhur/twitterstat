package nl.mikee.twtrcntr;

/**
 * Created by mike on 08/02/16.
 */
public class SearchEntity {

    private String hashTag;
    private int daysBack = 1;
    private int periodInDays = 1;

    private SearchEntity(String hashTag) {
        this.hashTag = hashTag;
    }

    static public SearchEntity getInstanceOf(String hashTag) {
        return new SearchEntity(hashTag);
    }

    public SearchEntity whereDaysBackIs(int daysBack) {
        this.daysBack = daysBack;
        return this;
    }

    public SearchEntity wherePeriodInDaysIs(int period) {
        this.periodInDays = period;
        return this;
    }

    public String getHashTag() {
        return hashTag;
    }


    public int getDaysBack() {
        return daysBack;
    }


    public int getPeriodInDays() {
        return periodInDays;
    }

}
