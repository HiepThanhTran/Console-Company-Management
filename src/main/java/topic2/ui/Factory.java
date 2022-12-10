package topic2.ui;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;
import topic2.color.Color;
import topic2.service.DepartmentManager;
import topic2.service.EmployeeManager;
import topic2.service.JoinDepartmentManager;
import topic2.service.JoinProjectManager;
import topic2.service.ProjectManager;

public class Factory {

    public static final File departmentFile = new File("src/main/resources/topic2/DepartmentList.txt");
    public static final File employeeFile = new File("src/main/resources/topic2/EmployeeList.txt");
    public static final File relativeFile = new File("src/main/resources/topic2/RelativeList.txt");
    public static final File projectFile = new File("src/main/resources/topic2/ProjectList.txt");

    public static final DepartmentManager departmentManager = new DepartmentManager();
    public static final EmployeeManager employeeManager = new EmployeeManager();
    public static final ProjectManager projectManager = new ProjectManager();
    public static final JoinDepartmentManager joinDepartmentManager = new JoinDepartmentManager();
    public static final JoinProjectManager joinProjectManger = new JoinProjectManager();

    public static final SimpleDateFormat SIMPLEDATEFORMAT = new SimpleDateFormat("dd/MM/yyyy");
    public static final DecimalFormat DECIMALFORMAT = new DecimalFormat("###,###.##");
    public static final GregorianCalendar GREGORIANCALENDAR = new GregorianCalendar();
    public static final Scanner SCANNER = new Scanner(System.in);

    public static final int MAX_PROJECT = 3;
    public static final int MIN_EMPLOYEE = 5;
    public static final int MAX_EMPLOYEE = 10;
    public static final int MAX_MANAGER_ROOM = 2;
    public static final double BASIC_SALARY = 5000000;
    public static final double ERROR_SALARY = 200000;

    private Factory() {
    }

    public static String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public static void printLine(int amount) {
        for (int i = 0; i < amount; i++) {
            System.out.println();
        }
        System.out.println();
    }

    public static void printLine(int amount, String characters) {
        for (int i = 0; i < amount; i++) {
            System.out.print(characters);
        }
        System.out.println();
    }

    public static void printLine(int amount, String characters, Color color) {
        System.out.printf(color.toString());
        for (int i = 0; i < amount; i++) {
            System.out.print(characters);
        }
        System.out.println(Color.RESET);
    }

    public static void projectMenuHeader() {
        Factory.printLine(157, "-");
        System.out.printf("| %-23s | %-41s | %-12s | %-12s | %-29s | %-51s | %-21s |\n",
            Color.RED + "MÃ DỰ ÁN" + Color.RESET,
            Color.CYAN + "TÊN DỰ ÁN" + Color.RESET,
            Color.MAGENTA + "NGÀY BẮT ĐẦU" + Color.RESET,
            Color.YELLOW + "NGÀY KẾT THÚC" + Color.RESET,
            Color.BLUE + "KINH PHÍ ĐẦU TƯ" + Color.RESET,
            Color.GREEN + "CHỦ NHIỆM DỰ ÁN" + Color.RESET,
            Color.GREEN_BOLD_BRIGHT + "TRẠNG THÁI" + Color.RESET);
        Factory.printLine(157, "-");
    }

    public static void employeeMenuHeader() {
        Factory.printLine(140, "-");
        System.out.printf("| %-12s | %-41s | %-9s | %-21s | %-41s | %-30s |\n",
            Color.RED + "MÃ NHÂN VIÊN" + Color.RESET,
            Color.CYAN + "HỌ TÊN" + Color.RESET,
            Color.MAGENTA + "GIỚI TÍNH" + Color.RESET,
            Color.YELLOW + "NGÀY SINH" + Color.RESET,
            Color.BLUE + "EMAIL" + Color.RESET,
            Color.GREEN + "//////////////////////////////" + Color.RESET);
        Factory.printLine(140, "-");
    }

    public static void relativeMenuHeader() {
        Factory.printLine(112, "-");
        System.out.printf("| %-41s | %-9s | %-21s | %-22s | %-47s |\n",
            Color.CYAN + "HỌ TÊN" + Color.RESET,
            Color.MAGENTA + "GIỚI TÍNH" + Color.RESET,
            Color.YELLOW + "NGÀY SINH" + Color.RESET,
            Color.BLUE + "MỐI QUAN HỆ" + Color.RESET,
            Color.GREEN + "SỐ BẢO HIỂM" + Color.RESET);
        Factory.printLine(112, "-");
    }
}