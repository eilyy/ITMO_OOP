package system.staff;

import system.*;
import system.dal.*;
import system.tasks.Task;

import java.util.Calendar;
import java.util.Collection;
import java.util.Vector;

public class Employee extends AbstractEmployee {
    private IEmployee manager;

    public Employee(int employeeId, String name, IEmployee manager, IStaffDB staffDB, ITasksDB tasksDB, IReportsDB reportsDB, IReportsManagement reportsManagement, IStaffManagement staffManagement) {
        this.employeeId = employeeId;
        this.name = name;
        this.manager = manager;

        this.sdb = staffDB;
        this.tdb = tasksDB;
        this.rdb = reportsDB;
        this.rm = reportsManagement;
        this.sm = staffManagement;

        this.renewLevel();
    }

    public void renewLevel() {
        this.level = this.manager.getLevel() + 1;
        for(Employee i : this.subordinates.values()) {
            i.renewLevel();
        }

        if(this.level > sm.getMaxLevel())
            sm.setMaxLevel(this.level);
    }

    @Override
    public void addSubordinate(int subordinateId) throws Exception {
        if(subordinateId == 0)
            throw new Exception("Team-lead cannot be subordinate");

        this.subordinates.put(subordinateId, sdb.getEmployeeById(subordinateId));
        sdb.getEmployeeById(subordinateId).changeManager(this);
    }

    @Override
    public Vector<Integer> getResolvedTasksToday() {
        return rdb.getResolvedTasksToday();
    }

    @Override
    public Collection<Task> getSprintTasks() throws Exception {
        return rm.getSprintTasks(this.employeeId);
    }

    @Override
    public void fillDailyReport(String filling) throws Exception {
        rdb.getDailyReportByDay
                (TimeHelper.getInstance().getDate().get(Calendar.YEAR),
                        TimeHelper.getInstance().getDate().get(Calendar.MONTH) + 1,
                        TimeHelper.getInstance().getDate().get(Calendar.DAY_OF_MONTH)).fillBody(this.employeeId, filling);
    }

    @Override
    public void fillSprintReport(String filling) throws Exception {
        rdb.getCurrentSprint().fillSprintReport(this.employeeId, filling);
    }

    @Override
    public Collection<Task> getSubordinatesSprintTasks() throws Exception {
        if(this.subordinates.isEmpty())
            throw new Exception("This employee has no subordinates");
        return rm.getTasksOfUsersSubordinates(this.employeeId);
    }

    @Override
    public void takeTask(int taskId) throws Exception {
        this.myTasks.put(taskId, tdb.getTaskById(taskId));
        this.allTasks.add(tdb.getTaskById(taskId));
    }

    public IEmployee getManager() {
        return this.manager;
    }

    public void changeManager(IEmployee manager) {
        this.manager = manager;
        this.renewLevel();
    }
}
