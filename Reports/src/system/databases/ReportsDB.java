package system.databases;

import system.reports.DailyReport;
import system.reports.Sprint;

import java.util.HashMap;
import java.util.Vector;

public class ReportsDB {
    private static ReportsDB instance;

    private static HashMap<Integer, DailyReport> dailyReports = new HashMap<>();

    private static Vector<Integer> resolvedTasksToday = new Vector<>();

    private static Vector<Sprint> sprints = new Vector<>();

    private static boolean _onSprint = false;

    public static ReportsDB getInstance() {
        if(instance == null)
            instance = new ReportsDB();
        return instance;
    }

    public void addSprint(long time) {
        sprints.add(new Sprint(time));
    }

    public boolean onSprint() {
        return _onSprint;
    }

    public void set_onSprint(boolean _onSprint) {
        ReportsDB._onSprint = _onSprint;
    }

    public void newDailyReport(DailyReport dailyReport) throws Exception {
        dailyReports.put(TimeDB.getInstance().getDateNow(), dailyReport);
    }

    public void newResolvedTask(int taskId) {
        resolvedTasksToday.add(taskId);
    }

    public Sprint getCurrentSprint() throws Exception {
        if(_onSprint)
            return sprints.lastElement();
        else
            throw new Exception("Team is not on sprint now");
    }

    public Vector<Integer> getResolvedTasksToday() {
        return resolvedTasksToday;
    }

    public DailyReport getDailyReportByDay(int year, int month, int day) throws Exception {
        if(!dailyReports.containsKey(year * 10000 + month * 100 + day))
            throw new Exception("There is no any daily report with given date");
        return dailyReports.get(year * 10000 + month * 100 + day);
    }

    public Vector<Sprint> getSprints() {
        return sprints;
    }

    public void clearResolvedTasks() {
        resolvedTasksToday.clear();
    }
}
