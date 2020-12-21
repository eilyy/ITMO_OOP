package system;

import system.staff.TeamLead;

public interface IStaffManagement {
    TeamLead getTeamLead() throws Exception;

    int getMaxLevel();

    void setMaxLevel(int maxLevel);

    void newEmployee(String name, int managerId) throws Exception;

    void newTeamLead(String name) throws Exception;

    void changeManagerForEmployee(int employeeId, int managerId) throws Exception;

    String getHierarchy() throws Exception;
}
