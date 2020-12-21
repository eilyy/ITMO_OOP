package UI;

import system.IStaffManagement;

public class StaffUI {
    private IStaffManagement sm;

    public StaffUI(IStaffManagement staffManagement) {
        this.sm = staffManagement;
    }

    public void newEmployee(String name, int managerId) throws Exception {
        this.sm.newEmployee(name, managerId);
    }

    public void newTeamLead(String name) throws Exception {
        this.sm.newTeamLead(name);
    }

    public void changeManagerForEmployee(int employeeId, int managerId) throws Exception {
        this.sm.changeManagerForEmployee(employeeId, managerId);
    }

    public String getHierarchy() throws Exception {
        return this.sm.getHierarchy();
    }
}
