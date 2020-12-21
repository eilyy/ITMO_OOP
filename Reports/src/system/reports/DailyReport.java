package system.reports;

import system.dal.IReportsDB;
import system.dal.ReportsDB;
import system.TimeHelper;

import java.util.Vector;

public class DailyReport {
    private Vector<Integer> resolvedTasks;
    private int reportDay;
    private Status status;

    private String body = "===== DAILY REPORT =====\n";

    private TimeHelper tmdb = TimeHelper.getInstance();
    private IReportsDB rdb;

    public DailyReport(IReportsDB reportsDB) throws Exception {
        this.reportDay = tmdb.getDateNow();
        this.rdb = reportsDB;
        this.rdb.newDailyReport(this);
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
