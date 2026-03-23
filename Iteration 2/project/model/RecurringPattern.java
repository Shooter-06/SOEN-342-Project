package model;

import java.util.Date;

public class RecurringPattern {
    private String frequency;
    private int interval;
    private Date startDate;
    private Date endDate;

    public RecurringPattern(String frequency, int interval) {
        this.frequency = frequency;
        this.interval = interval;
    }
}
