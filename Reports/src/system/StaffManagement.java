package system;

import system.databases.StaffDB;
import system.databases.TasksDB;
import system.staff.Employee;
import system.staff.TeamLead;

public class StaffManagement {
    private static int employeeId = 0;
    private static int maxLevel = 0;

    private StaffDB sdb = new StaffDB();
    private TasksDB tdb = new TasksDB();

    private int generateEmployeeId() {
        employeeId++;
        return employeeId;
    }

    public TeamLead getTeamLead() {
        return sdb.getTeamLead();
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        StaffManagement.maxLevel = maxLevel;
    }

    public void newEmployee(String name, int managerId) throws Exception {
        if((!sdb.getEmployeesIds().contains(managerId) && managerId != 0) || (managerId == 0 && sdb.getTeamLead() == null))
            throw new Exception("Employee with given ID doesn't exist");

        int newId = generateEmployeeId();
        var manager = (managerId == 0) ? sdb.getTeamLead() : sdb.getEmployeeById(managerId);
        sdb.addEmployee(newId, new Employee(newId, name, manager));
        manager.addSubordinate(newId);
    }

    public void newTeamLead(String name) throws Exception {
        if(sdb.getTeamLead() != null)
            throw new Exception("There is acting team-lead already");

        sdb.setTeamLead(new TeamLead(name));
    }

    public void changeManagerForEmployee(int employeeId, int managerId) throws Exception {
        if(employeeId == 0)
            throw new Exception("Team-lead cannot have a manager");

        sdb.getEmployeeById(employeeId).getManager().removeSubordinate(employeeId);
        if(managerId == 0)
            sdb.getTeamLead().addSubordinate(employeeId);
        else
            sdb.getEmployeeById(managerId).addSubordinate(employeeId);
    }

    public String getHierarchy() {
        String hierarchy = "===== HIERARCHY =====\n";

        hierarchy += "Level 0:\n" + String.valueOf(this.getTeamLead().getId()) + "(" + this.getTeamLead().getName() + "): ";
        for(Employee i : this.getTeamLead().getSubordinates()) {
            hierarchy += String.valueOf(i.getId()) + " ";
        }
        hierarchy += "\n\n";
        for(int i = 1; i < this.getMaxLevel() + 1; i++) {
            hierarchy += "Level " + String.valueOf(i) + ":\n";
            for(Employee j : sdb.getEmployees()) {
                if(j.getLevel() == i) {
                    hierarchy += String.valueOf(j.getId()) + "(" + j.getName() + "): ";
                    for(Employee k : j.getSubordinates()) {
                        hierarchy += String.valueOf(k.getId()) + " ";
                    }
                    hierarchy += "\n";
                }
            }
            hierarchy += "\n";
        }
        return hierarchy.substring(0, hierarchy.length() - 1) + "=====================";
    }
}
