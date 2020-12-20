package UI;

import system.databases.ReportsDB;
import system.databases.TimeDB;
import system.reports.DailyReport;

import java.util.Calendar;

public class TimeUI {
    TimeDB tmdb = TimeDB.getInstance();

    public long getTime(int hour, int min) throws Exception {
        return tmdb.newTime(hour, min);
    }

    public void setDateNow(int year, int month, int day) throws Exception {
        tmdb.setDateNow(year, month, day);
        ReportsDB.getInstance().newDailyReport(new DailyReport());
    }

    public void nextDay() throws Exception {
        ReportsDB.getInstance().getDailyReportByDay
                (TimeDB.getInstance().getDate().get(Calendar.YEAR),
                TimeDB.getInstance().getDate().get(Calendar.MONTH) + 1,
                TimeDB.getInstance().getDate().get(Calendar.DAY_OF_MONTH)).finish();
        tmdb.nextDay();
    }
}
