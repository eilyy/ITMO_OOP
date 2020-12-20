package UI;

import system.StaffManagement;
import system.databases.StaffDB;
import system.staff.TeamLead;
import system.tasks.Task;

import java.util.Collection;
import java.util.Vector;

public class TeamLeadUI {
    private StaffManagement sm = StaffManagement.getInstance();
    private StaffDB sdb = StaffDB.getInstance();
    private TeamLead user;

    public TeamLeadUI() throws Exception {
        this.user = sdb.getTeamLead();
    }

    public void commitTask(int taskId, String comment, long time) throws Exception {
        this.user.commitTask(taskId, comment, time);
    }

    public void finishTask(int taskId, long time) throws Exception {
        this.user.finishTask(taskId, time);
    }

    public void fillDailyReport(String filling) throws Exception {
        this.user.fillDailyReport(filling);
    }

    public void fillSprintReport(String filling) throws Exception {
        this.user.fillSprintReport(filling);
    }

    public Collection<Task> getSprintTasks() throws Exception {
        return this.user.getSprintTasks();
    }

    public Collection<Task> getSubordinatesSprintTasks() throws Exception {
        return this.user.getSubordinatesSprintTasks();
    }

    public Vector<Integer> getResolvedTasksToday() {
        return this.user.getResolvedTasksToday();
    }

    public boolean isSprintReportDone(int year, int month, int day, int hour, int min) throws Exception {
        return this.user.isSprintReportDone(year, month, day, hour, min);
    }
}
