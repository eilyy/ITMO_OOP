package system.dal;

import system.IReportsManagement;
import system.ITasksManagement;
import system.reports.DailyReport;
import system.reports.Sprint;

import java.util.Vector;

public interface IReportsDB {
    void addSprint(long time, ITasksManagement tasksManagement);

    boolean onSprint();

    void set_onSprint(boolean _onSprint);

    void newDailyReport(DailyReport dailyReport) throws Exception;

    void newResolvedTask(int taskId);

    Sprint getCurrentSprint() throws Exception;

    Vector<Integer> getResolvedTasksToday();

    DailyReport getDailyReportByDay(int year, int month, int day) throws Exception;

    Vector<Sprint> getSprints();

    void clearResolvedTasks();
}
