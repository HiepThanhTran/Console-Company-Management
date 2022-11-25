package topic2.ui;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;

public final class Factory {

    public static final File departmentFile = new File("src/main/resources/DepartmentList.txt");
    public static final File employeeFile = new File("src/main/resources/EmployeeList.txt");
    public static final File relativeFile = new File("src/main/resources/RelativeList.txt");
    public static final File projectFile = new File("src/main/resources/ProjectList.txt");
    public static final SimpleDateFormat SIMPLEDATEFORMAT = new SimpleDateFormat("dd/MM/yyyy");
    public static final DecimalFormat DECIMALFORMAT = new DecimalFormat("###,###.##");
    public static final GregorianCalendar GREGORIANCALENDAR = new GregorianCalendar();
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final int MAX_PROJECT = 3;
    public static final int MIN_EMPLOYEE = 5;
    public static final int MAX_EMPLOYEE = 10;
    public static final int MAX_MANAGER_ROOM = 2;

    private Factory() {
    }

    /**
     * In ra 'amount' dòng mới
     *
     * @param amount Số lượng
     */
    public static void printLine(int amount) {
        for (int i = 0; i < amount; i++) {
            System.out.println();
        }
    }

    /**
     * In ra 'amount' dòng có ký tự 'characters'
     *
     * @param amount     Số lượng
     * @param characters Ký tự
     */
    public static void printLine(int amount, String characters) {
        for (int i = 0; i < amount; i++) {
            System.out.print(characters);
        }
        System.out.println();
    }
}