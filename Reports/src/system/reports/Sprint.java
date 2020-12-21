package system.reports;

import system.IReportsManagement;
import system.ITasksManagement;
import system.TasksManagement;
import system.dal.IReportsDB;
import system.dal.ITasksDB;
import system.dal.TasksDB;
import system.tasks.Status;
import system.tasks.Task;

import java.util.Collection;
import java.util.Vector;

public class Sprint {
    private Vector<DailyReport> dailyReports = new Vector<>();

    private SprintReport report;

    private Vector<Integer> tasks = new Vector<>();

    private ITasksDB tdb;
    private IReportsDB rdb;
    private ITasksManagement tm;

    private long startTime, finishTime;
    private boolean Active;

    public Sprint(long startTime, ITasksDB tdb, IReportsDB rdb, ITasksManagement tasksManagement) {
        this.startTime = startTime;
        this.tdb = tdb;
        this.rdb = rdb;
        this.tm = tasksManagement;
        this.Active = true;
        this.report = new SprintReport(this.tasks, this.dailyReports, startTime);
    }

    public boolean isActive() {
        return this.Active;
    }

    public void fillSprintReport(int userId, String filling) throws Exception {
        this.report.fillBody(userId, filling);
    }

    public void finish(long time) throws Exception {
        for(int i : tasks) {
            if(tdb.getTaskById(i).getStatus() != Status.RESOLVED)
                throw new Exception("Unable to finish the sprint until all tasks are resolved");
        }
        this.finishTime = time;
        rdb.getCurrentSprint().report.finish(time);
        this.Active = false;
    }

    public long getFinishTime() {
        return this.finishTime;
    }

    public void addTask(int taskId) {
        this.tasks.add(taskId);
    }

    public void addDailyReport(DailyReport dailyReport) {
        this.dailyReports.add(dailyReport);
    }

    public SprintReport getReport() {
        return this.report;
    }

    public Collection<Task> getTasksOfUser(int userId) throws Exception {
        Collection<Task> userTasks = tm.getTasksByDoer(userId);
        Collection<Task> sprintTasks = new Vector<>();
        for(int i : tasks) {
            sprintTasks.add(tdb.getTaskById(i));
        }
        userTasks.retainAll(sprintTasks);
        return userTasks;
    }

    public Collection<Task> getTasksOfUsersSubordinates(int userId) throws Exception {
        Collection<Task> userTasks = tm.getSubordinatesTasks(userId);
        Collection<Task> sprintTasks = new Vector<>();
        for(int i : tasks) {
            sprintTasks.add(tdb.getTaskById(i));
        }
        userTasks.retainAll(sprintTasks);
        return userTasks;
    }
}
