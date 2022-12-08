import static topic2.ui.Factory.SIMPLEDATEFORMAT;

import java.text.ParseException;
import topic2.entity.Designer;
import topic2.entity.Employee;
import topic2.entity.Manager;
import topic2.entity.Normal;
import topic2.entity.Programmer;
import topic2.entity.Project;
import topic2.entity.Tester;
import topic2.ui.Factory;

public class TestConsole {

    public static void main(String[] args) throws ParseException {
        Employee employee1 = new Normal("Trần Thanh Hiệp", false, SIMPLEDATEFORMAT.parse("29/01/2003"),
            "2151050469hiep@ou.edu.vn");
        Employee employee2 = new Manager("Trần Thanh Hậu", false, SIMPLEDATEFORMAT.parse("18/12/1993"),
            "tranhau1993@gmail.com");
        Employee employee3 = new Programmer("Tống Thị Thu Hiền", true, SIMPLEDATEFORMAT.parse("10/07/2003"),
            "2151050126hien@ou.edu.vn", 1000000);
        Employee employee4 = new Designer("Nguyễn Song Hậu", false, SIMPLEDATEFORMAT.parse("27/07/2003"),
            "2151050123hau@ou.edu.vn", 500000);
        Employee employee5 = new Tester("Nguyễn Huỳnh Ngọc Như", true, SIMPLEDATEFORMAT.parse("27/05/2003"),
            "2151053045nhu@ou.edu.vn", 10);

        Factory.employeeMenuHeader();
        System.out.println(employee1);
        Factory.printLine(140, "-");
        System.out.println(employee2);
        Factory.printLine(140, "-");
        System.out.println(employee3);
        Factory.printLine(140, "-");
        System.out.println(employee4);
        Factory.printLine(140, "-");
        System.out.println(employee5);
        Factory.printLine(140, "-");

        System.out.println();

        Project project1 = new Project("SALE", "Web Bán Hàng", SIMPLEDATEFORMAT.parse("19/10/2021"),
            SIMPLEDATEFORMAT.parse("19/12/2021"), 5000000, employee1);
        Project project2 = new Project("CHEAT", "App Chống Gian Lận Thi Cử", SIMPLEDATEFORMAT.parse("12/10/2021"),
            SIMPLEDATEFORMAT.parse("16/01/2022"), 5000000, employee2);
        Project project3 = new Project("COFFE", "App Thanh Toán Quán Cà Phê", SIMPLEDATEFORMAT.parse("18/05/2021"),
            SIMPLEDATEFORMAT.parse("18/07/2021"), 6000000, employee3);
        Project project4 = new Project("STOCK", "Ứng Dụng Sàn Chứng Khoáng", SIMPLEDATEFORMAT.parse("01/10/2021"),
            SIMPLEDATEFORMAT.parse("25/12/2021"), 10000000, employee4);
        Project project5 = new Project("CHAT", "Ứng Dụng Chat Online", SIMPLEDATEFORMAT.parse("01/12/2021"),
            SIMPLEDATEFORMAT.parse("13/04/2022"), 10000000, employee5);

        Factory.projectMenuHeader();
        System.out.println(project1);
        Factory.printLine(162, "-");
        System.out.println(project2);
        Factory.printLine(162, "-");
        System.out.println(project3);
        Factory.printLine(162, "-");
        System.out.println(project4);
        Factory.printLine(162, "-");
        System.out.println(project5);
        Factory.printLine(162, "-");
    }
}
