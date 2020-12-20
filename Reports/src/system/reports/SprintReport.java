package system.reports;

import java.util.Vector;

public class SprintReport {
    private Vector<Integer> resolvedTasks = new Vector<>();

    private Vector<DailyReport> dailyReports = new Vector<>();

    private long startTime, finishTime;

    private String body = "===== SPRINT REPORT =====\n";

    public SprintReport(Vector<Integer> resolvedTasks, Vector<DailyReport> dailyReports, long startTime) {
        this.resolvedTasks = resolvedTasks;
        this.dailyReports = dailyReports;
        this.startTime = startTime;
    }

    public void fillBody(int userId, String str) throws Exception {
        this.body += "[USERID=" + String.valueOf(userId) + "]: " + str + "\n";
    }

    public void finish(long finishTime) {
        this.finishTime = finishTime;
        this.body += "=======================";
    }

    public String getBody() {
        return this.body;
    }
}
