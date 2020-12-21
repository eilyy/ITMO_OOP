package UI;

import system.dal.IReportsDB;
import system.TimeHelper;
import system.reports.DailyReport;

import java.util.Calendar;

public class TimeUI {
    private TimeHelper tmdb = TimeHelper.getInstance();
    private IReportsDB rdb;

    public TimeUI(IReportsDB reportsDB) {
        this.rdb = reportsDB;
    }

    public long getTime(int hour, int min) throws Exception {
        return tmdb.newTime(hour, min);
    }

    public void setDateNow(int year, int month, int day) throws Exception {
        tmdb.setDateNow(year, month, day);
        rdb.newDailyReport(new DailyReport(rdb));
    }

    public void nextDay() throws Exception {
        rdb.getDailyReportByDay
                (TimeHelper.getInstance().getDate().get(Calendar.YEAR),
                TimeHelper.getInstance().getDate().get(Calendar.MONTH) + 1,
                TimeHelper.getInstance().getDate().get(Calendar.DAY_OF_MONTH)).finish();
        tmdb.nextDay();
    }
}
