package topic2;

import static topic2.ui.Factory.SCANNER;

import topic2.color.Color;
import topic2.entity.Department;
import topic2.entity.Employee;
import topic2.entity.Project;
import topic2.ui.Factory;
import topic2.ui.UIManager;
import topic2.utils.FileUtils;
import topic2.utils.StringUtils;

public class Main {

    public static void main(String[] args) {
        UIManager uiManager = UIManager.getINSTANCE();
        while (true) {
            System.out.println("\n" + StringUtils.center(50, "*** H&H COMPANY ***"));
            System.out.println(StringUtils.center(50, String.format("Số lượng dự án hiện có: %d", Project.getProjectAmount())));
            System.out.println(
                StringUtils.center(50, String.format("Số lượng nhân viên hiện có: %d", Employee.getEmployeeAmount())));
            System.out.println(
                StringUtils.center(50, String.format("Số lượng phòng ban hiện có: %d\n", Department.getDepartmentAmount())));
            Factory.printLine(50, "=");
            System.out.printf("\t\t%-3s Quản lý dự án\n", "1-");
            System.out.printf("\t\t%-3s Quản lý nhân viên\n", "2-");
            System.out.printf("\t\t%-3s Quản lý phòng ban\n", "3-");
            System.out.printf("\t\t%-3s Quản lý nhân thân của nhân viên\n", "4-");
            System.out.printf("\t\t%-3s Lưu thông tin và kết thúc chương trình\n", "5-");
            Factory.printLine(50, "=");
            System.out.print("- Chọn chức năng: ");
            String choice = SCANNER.nextLine();
            switch (choice) {
                case "1" -> {
                    Factory.printLine(158, "-");
                    uiManager.UIProjectManager();
                }
                case "2" -> {
                    Factory.printLine(158, "-");
                    uiManager.UIEmployeeManager();
                }
                case "3" -> {
                    Factory.printLine(158, "-");
                    uiManager.UIDepartmentManager();
                }
                case "4" -> {
                    Factory.printLine(158, "-");
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