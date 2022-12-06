package topic2;

import static topic2.ui.Factory.SCANNER;

import topic2.color.Color;
import topic2.entity.Department;
import topic2.entity.Employee;
import topic2.entity.Project;
import topic2.ui.Factory;
import topic2.ui.FileUtils;
import topic2.ui.UIManager;

public class Main {

    public static void main(String[] args) {
        UIManager uiManager = UIManager.getINSTANCE();
        while (true) {
            System.out.println("\n*** H&H COMPANY ***");
            System.out.printf("\t\tSố lượng dự án hiện có: %d\n", Project.getProjectAmount());
            System.out.printf("\t\tSố lượng nhân viên hiện có: %d\n", Employee.getEmployeeAmount());
            System.out.printf("\t\tSố lượng phòng ban hiện có: %d\n", Department.getDepartmentAmount());
            System.out.println("\n1- Quản lý dự án");
            System.out.println("2- Quản lý nhân viên");
            System.out.println("3- Quản lý phòng ban");
            System.out.println("4- Quản lý nhân thân của nhân viên");
            System.out.println("5- Lưu thông tin và kết thúc chương trình");
            System.out.print("- Chọn chức năng: ");
            String choice = SCANNER.nextLine();
            switch (choice) {
                case "1" -> {
                    Factory.printLine(180, "-");
                    uiManager.UIProjectManager();
                }
                case "2" -> {
                    Factory.printLine(180, "-");
                    uiManager.UIEmployeeManager();
                }
                case "3" -> {
                    Factory.printLine(180, "-");
                    uiManager.UIDepartmentManager();
                }
                case "4" -> {
                    Factory.printLine(180, "-");
                    uiManager.UIRelativeManager();
                }
                case "5" -> {
                    System.out.println("\n*** KẾT THÚC CHƯƠNG TRÌNH ***");
                    FileUtils.writeFile();
                    return;
                }
                default -> System.err.println("\n== CHỨC NĂNG HIỆN CHƯA CÓ ==");
            }
            System.out.printf("%s=> BẤM PHÍM BẤT KỲ ĐỂ TIẾP TỤC%s", Color.RED_BOLD_BRIGHT, Color.RESET);
            SCANNER.nextLine();
        }
    }
}