package topic2;

import static topic2.ui.Factory.SCANNER;

import topic2.entity.other.Department;
import topic2.entity.other.Project;
import topic2.entity.people.Employee;
import topic2.ui.Factory;
import topic2.ui.UIManager;

public class Main {

    public static void main(String[] args) {
        UIManager uiManager = UIManager.getINSTANCE();
        uiManager.readFile();
        while (true) {
            System.out.println("\n*** H&H COMPANY ***");
            System.out.printf("\t\tSố lượng phòng ban hiện có: %d\n", Department.getDepartmentAmount());
            System.out.printf("\t\tSố lượng nhân viên hiện có: %d\n", Employee.getEmployeeAmount());
            System.out.printf("\t\tSố lượng dự án hiện có: %d\n", Project.getProjectAmount());
            System.out.println("\n1- Quản lý phòng ban");
            System.out.println("2- Quản lý nhân viên");
            System.out.println("3- Quản lý dự án");
            System.out.println("4- Quản lý nhân thân của nhân viên");
            System.out.println("5- Kết thúc chương trình và lưu thông tin hệ thống vào file");
            System.out.print("- Chọn chức năng: ");
            String choice = SCANNER.nextLine();
            switch (choice) {
                case "1" -> {
                    Factory.printLine(120, "-");
                    uiManager.UIDepartmentManager();
                    Factory.printLine(120, "-");
                }
                case "2" -> {
                    Factory.printLine(120, "-");
                    uiManager.UIEmployeeManager();
                    Factory.printLine(120, "-");
                }
                case "3" -> {
                    Factory.printLine(120, "-");
                    uiManager.UIProjectManager();
                    Factory.printLine(120, "-");
                }
                case "4" -> {
                    Factory.printLine(120, "-");
                    uiManager.UIRelativeManager();
                    Factory.printLine(120, "-");
                }
                case "5" -> {
                    uiManager.writeFile();
                    System.out.println("\n*** KẾT THÚC CHƯƠNG TRÌNH ***");
                    return;
                }
                default -> System.out.println("\n== CHỨC NĂNG HIỆN CHƯA CÓ ==");
            }
            SCANNER.nextLine();
        }
    }
}
