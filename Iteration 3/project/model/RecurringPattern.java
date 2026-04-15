package model;

import java.util.Date;

public class RecurringPattern {
    private final String frequency;
    private final int interval;
    private final Date startDate;
    private final Date endDate;

    // frequency can be "DAILY", "WEEKLY", "MONTHLY", "YEARLY"
    public RecurringPattern(String frequency, Date startDate, Date endDate, int interval) {
        this.frequency = frequency;
        this.interval = interval;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getFrequency() {
        return frequency;
    }

    public int getInterval() {
        return interval;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
