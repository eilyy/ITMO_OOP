import UI.StaffUI;

public class Main {
    public static void main(String[] args) {
        try {
            var sui = new StaffUI();

            sui.newTeamLead("Ilya");
            sui.newEmployee("huy", 0);
            sui.newEmployee("pizda", 0);
            sui.newEmployee("chlen", 0);
            sui.newEmployee("huy1", 1);
            sui.newEmployee("huy2", 1);
            sui.newEmployee("pizda1", 2);

            System.out.println(sui.getHierarchy());

            sui.changeManagerForEmployee(6, 3);

            System.out.println(sui.getHierarchy());

        } catch(Exception e) {
            System.err.println(e);
        }
    }
}
