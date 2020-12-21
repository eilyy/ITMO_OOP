package system.dal;

import system.IReportsManagement;
import system.ITasksManagement;
import system.TimeHelper;
import system.reports.DailyReport;
import system.reports.Sprint;

import java.util.HashMap;
import java.util.Vector;

public class ReportsDB implements IReportsDB {
    private HashMap<Integer, DailyReport> dailyReports = new HashMap<>();
    private Vector<Integer> resolvedTasksToday = new Vector<>();
    private Vector<Sprint> sprints = new Vector<>();

    private ITasksDB tdb;

    private boolean _onSprint = false;

    public ReportsDB(ITasksDB tasksDB) {
        this.tdb = tasksDB;
    }

    @Override
    public void addSprint(long time, ITasksManagement tasksManagement) {
        sprints.add(new Sprint(time, tdb, this, tasksManagement));
    }

    @Override
    public boolean onSprint() {
        return _onSprint;
    }

    @Override
    public void set_onSprint(boolean _onSprint) {
        this._onSprint = _onSprint;
    }

    @Override
    public void newDailyReport(DailyReport dailyReport) throws Exception {
        dailyReports.put(TimeHelper.getInstance().getDateNow(), dailyReport);
    }

    @Override
    public void newResolvedTask(int taskId) {
        resolvedTasksToday.add(taskId);
    }

    @Override
    public Sprint getCurrentSprint() throws Exception {
        if(_onSprint)
            return sprints.lastElement();
        else
            throw new Exception("Team is not on sprint now");
    }

    @Override
    public Vector<Integer> getResolvedTasksToday() {
        return resolvedTasksToday;
    }

    @Override
    public DailyReport getDailyReportByDay(int year, int month, int day) throws Exception {
        if(!dailyReports.containsKey(year * 10000 + month * 100 + day))
            throw new Exception("There is no any daily report with given date");
        return dailyReports.get(year * 10000 + month * 100 + day);
    }

    @Override
    public Vector<Sprint> getSprints() {
        return sprints;
    }

    @Override
    public void clearResolvedTasks() {
        resolvedTasksToday.clear();
    }
}
