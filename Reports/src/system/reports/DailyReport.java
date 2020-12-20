package system.reports;

import system.databases.ReportsDB;
import system.databases.TimeDB;

import java.util.Vector;

public class DailyReport {
    private Vector<Integer> resolvedTasks;
    private int reportDay;
    private Status status;

    private String body = "===== DAILY REPORT =====\n";

    private TimeDB tmdb = TimeDB.getInstance();
    private ReportsDB rdb = ReportsDB.getInstance();

    public DailyReport() throws Exception {
        this.reportDay = tmdb.getDateNow();
        rdb.newDailyReport(this);
        this.status = Status.ACTIVE;
        if(rdb.onSprint())
            rdb.getCurrentSprint().addDailyReport(this);
    }

    public void fillBody(int userId, String str) throws Exception {
        if(this.status == Status.INACTIVE)
            throw new Exception("Unable to edit. This report is inactive.");
        this.body += "[USERID=" + String.valueOf(userId) + "]: " + str + "\n";
    }

    public int getReportDay() {
        return this.reportDay;
    }

    public void finish() {
        this.body += "========================";
        this.status = Status.INACTIVE;
        this.resolvedTasks = rdb.getResolvedTasksToday();
        rdb.clearResolvedTasks();
    }

    public String getBody() {
        return this.body;
    }
}
