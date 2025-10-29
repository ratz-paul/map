package filter;

import domain.Session;

public class sessionByDate implements AbstractFilter<Session> {
    private final String startDate;
    private final String startTime;
    private final String endDate;
    private final String endTime;

    public sessionByDate(String startDate, String startTime, String endDate, String endTime) {
        if (startDate == null || endDate == null || startTime == null || endTime == null)
            throw new IllegalArgumentException("Dates and times cannot be null");
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    @Override
    public boolean accept(Session s) {
        String sessionDT = s.getDate() + " " + s.getTime();
        String startDT = startDate + " " + startTime;
        String endDT = endDate + " " + endTime;
        return sessionDT.compareTo(startDT) >= 0 && sessionDT.compareTo(endDT) <= 0;
    }
}
