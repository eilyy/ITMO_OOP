package UI;

import system.IReportsManagement;
import system.reports.Sprint;
import system.tasks.Task;

import java.util.Collection;

public class ReportsUI {
    private IReportsManagement rm;

    public ReportsUI(IReportsManagement reportsManagement) {
        this.rm = reportsManagement;
    }

    public void startSprint(int hour, int min) throws Exception {
        rm.startSprint(hour, min);
    }

    public void finishSprint(int hour, int min) throws Exception {
        rm.finishSprint(hour, min);
    }

    public Collection<Task> getSprintTasks(int userId) throws Exception {
        return rm.getSprintTasks(userId);
    }

    public void newDailyReport() throws Exception {
        rm.newDailyReport();
    }

    public Collection<Task> getTasksOfUsersSubordinates(int userId) throws Exception {
        return rm.getTasksOfUsersSubordinates(userId);
    }

    public String getDailyReportBody(int year, int month, int day) throws Exception {
        return rm.getDailyReportBody(year, month, day);
    }

    public String getCurrentSprintReportBody() throws Exception {
        return rm.getCurrentSprintReportBody();
    }

    public Sprint getSprintByFinishTime(int finishYear, int finishMonth, int finishDay, int finishHour, int finishMin) throws Exception {
        return rm.getSprintByFinishTime(finishYear, finishMonth, finishDay, finishHour, finishMin);
    }

    public IReportsManagement getRm() {
        return this.rm;
    }
}
