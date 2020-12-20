package UI;

import system.StaffManagement;

public class StaffUI {
    private StaffManagement sm = StaffManagement.getInstance();

    public void newEmployee(String name, int managerId) throws Exception {
        sm.newEmployee(name, managerId);
    }

    public void newTeamLead(String name) throws Exception {
        sm.newTeamLead(name);
    }

    public void changeManagerForEmployee(int employeeId, int managerId) throws Exception {
        sm.changeManagerForEmployee(employeeId, managerId);
    }

    public String getHierarchy() throws Exception {
        return sm.getHierarchy();
    }
}
