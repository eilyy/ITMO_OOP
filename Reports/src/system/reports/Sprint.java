package system.reports;

import system.TasksManagement;
import system.databases.ReportsDB;
import system.databases.TasksDB;
import system.tasks.Status;
import system.tasks.Task;

import java.util.Collection;
import java.util.Vector;

public class Sprint {
    private Vector<DailyReport> dailyReports = new Vector<>();

    private SprintReport report;

    private Vector<Integer> tasks = new Vector<>();

    private long startTime, finishTime;
    private boolean Active;

    public Sprint(long startTime) {
        this.startTime = startTime;
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
            if(TasksDB.getInstance().getTaskById(i).getStatus() != Status.RESOLVED)
                throw new Exception("Unable to finish the sprint until all tasks are resolved");
        }
        this.finishTime = time;
        ReportsDB.getInstance().getCurrentSprint().report.finish(time);
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
        Collection<Task> userTasks = TasksManagement.getInstance().getTasksByDoer(userId);
        Collection<Task> sprintTasks = new Vector<>();
        for(int i : tasks) {
            sprintTasks.add(TasksDB.getInstance().getTaskById(i));
        }
        userTasks.retainAll(sprintTasks);
        return userTasks;
    }

    public Collection<Task> getTasksOfUsersSubordinates(int userId) throws Exception {
        Collection<Task> userTasks = TasksManagement.getInstance().getSubordinatesTasks(userId);
        Collection<Task> sprintTasks = new Vector<>();
        for(int i : tasks) {
            sprintTasks.add(TasksDB.getInstance().getTaskById(i));
        }
        userTasks.retainAll(sprintTasks);
        return userTasks;
    }
}
