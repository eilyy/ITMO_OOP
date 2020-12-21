package system;

import system.reports.Sprint;
import system.tasks.Task;

import java.util.Collection;

public interface IReportsManagement {
    void startSprint(int hour, int min) throws Exception;

    void finishSprint(int hour, int min) throws Exception;

    void newDailyReport() throws Exception;

    Collection<Task> getSprintTasks(int userId) throws Exception;

    Collection<Task> getTasksOfUsersSubordinates(int userId) throws Exception;

    String getDailyReportBody(int year, int month, int day) throws Exception;

    String getCurrentSprintReportBody() throws Exception;

    Sprint getSprintByFinishTime(int finishYear, int finishMonth, int finishDay, int finishHour, int finishMin) throws Exception;
}
