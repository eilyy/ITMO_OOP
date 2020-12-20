package system.staff;

import system.StaffManagement;

public class Employee extends AbstractEmployee {
    private IEmployee manager;

    public Employee(int employeeId, String name, IEmployee manager) {
        this.employeeId = employeeId;
        this.name = name;
        this.manager = manager;
        this.renewLevel();
    }

    public void renewLevel() {
        this.level = this.manager.getLevel() + 1;
        for(Employee i : this.subordinates.values()) {
            i.renewLevel();
        }

        var sm = StaffManagement.getInstance();

        if(this.level > sm.getMaxLevel())
            sm.setMaxLevel(this.level);
    }

    public IEmployee getManager() {
        return this.manager;
    }

    public void changeManager(IEmployee manager) {
        this.manager = manager;
        this.renewLevel();
    }
}
