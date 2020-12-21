package UI;

import system.IStaffManagement;
import system.StaffManagement;
import system.dal.IStaffDB;
import system.dal.StaffDB;
import system.staff.Employee;
import system.tasks.Task;

import java.util.Collection;
import java.util.Vector;

public class EmployeeUI {
    private IStaffDB sdb;
    private Employee user;

    public EmployeeUI(int userId, IStaffDB staffDB) throws Exception {
        this.sdb = staffDB;
        this.user = sdb.getEmployeeById(userId);
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
}
