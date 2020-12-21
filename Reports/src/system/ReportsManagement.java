package system;

import system.dal.IReportsDB;
import system.reports.DailyReport;
import system.reports.Sprint;
import system.tasks.Task;

import java.util.Collection;

public class ReportsManagement implements IReportsManagement {
    private IReportsDB rdb;
    private ITasksManagement tm;

    public ReportsManagement(IReportsDB reportsDB, ITasksManagement tm) {
        this.rdb = reportsDB;
        this.tm = tm;
    }

    @Override
    public void startSprint(int hour, int min) throws Exception {
        if(rdb.onSprint())
            throw new Exception("Finish current sprint to start the new one");
        rdb.addSprint(TimeHelper.getInstance().newTime(hour, min), tm);
        rdb.set_onSprint(true);
    }

    @Override
    public void finishSprint(int hour, int min) throws Exception {
        if(!rdb.onSprint())
            throw new Exception("There is no sprint to finish");
        rdb.getCurrentSprint().finish(TimeHelper.getInstance().newTime(hour, min));
        rdb.set_onSprint(false);
    }

    @Override
    public void newDailyReport() throws Exception {
        rdb.newDailyReport(new DailyReport(rdb));
    }

    @Override
    public Collection<Task> getSprintTasks(int userId) throws Exception {
        if(!rdb.onSprint())
            throw new Exception("There is no sprint ongoing");
        return rdb.getCurrentSprint().getTasksOfUser(userId);
    }

    @Override
    public Collection<Task> getTasksOfUsersSubordinates(int userId) throws Exception {
        if(!rdb.onSprint())
            throw new Exception("There is no sprint ongoing");
        return rdb.getCurrentSprint().getTasksOfUsersSubordinates(userId);
    }

    @Override
    public String getDailyReportBody(int year, int month, int day) throws Exception {
        return rdb.getDailyReportByDay(year, month, day).getBody();
    }

    @Override
    public String getCurrentSprintReportBody() throws Exception {
        return rdb.getCurrentSprint().getReport().getBody();
    }

    @Override
    public Sprint getSprintByFinishTime(int finishYear, int finishMonth, int finishDay, int finishHour, int finishMin) throws Exception {
        for(Sprint i : rdb.getSprints()) {
            if(i.getFinishTime() == TimeHelper.getInstance().makeTimeStamp(finishYear, finishMonth, finishDay, finishHour, finishMin))
                return i;
        }
        throw new Exception("There is no sprint with such finish time");
    }
}
