package system;

import system.databases.ReportsDB;
import system.databases.TimeDB;
import system.reports.DailyReport;
import system.reports.Sprint;
import system.tasks.Task;

import java.util.Collection;

public class ReportsManagement {
    private static ReportsManagement instance;

    private static ReportsDB rdb = ReportsDB.getInstance();

    public static ReportsManagement getInstance() {
        if(instance == null)
            instance = new ReportsManagement();
        return instance;
    }

    public void startSprint(int hour, int min) throws Exception {
        if(rdb.onSprint())
            throw new Exception("Finish current sprint to start the new one");
        rdb.addSprint(TimeDB.getInstance().newTime(hour, min));
        rdb.set_onSprint(true);
    }

    public void finishSprint(int hour, int min) throws Exception {
        if(!rdb.onSprint())
            throw new Exception("There is no sprint to finish");
        rdb.getCurrentSprint().finish(TimeDB.getInstance().newTime(hour, min));
        rdb.set_onSprint(false);
    }

    public void newDailyReport() throws Exception {
        ReportsDB.getInstance().newDailyReport(new DailyReport());
    }

    public Collection<Task> getSprintTasks(int userId) throws Exception {
        if(!rdb.onSprint())
            throw new Exception("There is no sprint ongoing");
        return rdb.getCurrentSprint().getTasksOfUser(userId);
    }

    public Collection<Task> getTasksOfUsersSubordinates(int userId) throws Exception {
        if(!rdb.onSprint())
            throw new Exception("There is no sprint ongoing");
        return rdb.getCurrentSprint().getTasksOfUsersSubordinates(userId);
    }

    public String getDailyReportBody(int year, int month, int day) throws Exception {
        return rdb.getDailyReportByDay(year, month, day).getBody();
    }

    public String getCurrentSprintReportBody() throws Exception {
        return rdb.getCurrentSprint().getReport().getBody();
    }

    public Sprint getSprintByFinishTime(int finishYear, int finishMonth, int finishDay, int finishHour, int finishMin) throws Exception {
        for(Sprint i : rdb.getSprints()) {
            if(i.getFinishTime() == TimeDB.getInstance().makeTimeStamp(finishYear, finishMonth, finishDay, finishHour, finishMin))
                return i;
        }
        throw new Exception("There is no sprint with such finish time");
    }
}
