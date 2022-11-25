package topic2.entity.people;

import static topic2.ui.Factory.SCANNER;
import static topic2.ui.Factory.SIMPLEDATEFORMAT;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

public abstract class Employee extends Person {

    protected static final double BASIC_SALARY = 5000000;
    protected static final double ERROR_SALARY = 200000;
    protected static int EMPLOYEE_AMOUNT = 0;
    protected String id;
    protected String email;

    {
        EMPLOYEE_AMOUNT++;
        id = String.format("N-%05d", EMPLOYEE_AMOUNT);
    }

    public Employee() {
    }

    public Employee(String name, String gender, Date dob, String email) {
        super(name, gender, dob);
        this.email = email;
    }

    public Employee(String name, String gender, Date dob, String id, String email) {
        super(name, gender, dob);
        this.id = id;
        this.email = email;
    }

    public static int getEmployeeAmount() {
        return EMPLOYEE_AMOUNT;
    }

    public static void decreaseEmployeeAmount(int x) {
        EMPLOYEE_AMOUNT -= x;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public abstract double getAllowance();

    public abstract double getCoefficient();

    public double getSalary() {
        return BASIC_SALARY * this.getCoefficient() + this.getAllowance();
    }

    @Override
    public void setInfo() throws ParseException {
        System.out.print("- Họ tên: ");
        this.name = SCANNER.nextLine();
        System.out.print("- Giới tính (1 - Nam, 0 - Nữ): ");
        this.gender = SCANNER.nextLine();
        System.out.print("- Ngày sinh: ");
        this.dob = SIMPLEDATEFORMAT.parse(SCANNER.nextLine());
        System.out.print("- Email: ");
        this.email = SCANNER.nextLine();
    }

    @Override
    public void showInfo() {
        System.out.printf("\t\t== Thông tin nhân viên %s ==", id);
        System.out.println(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return id.equals(employee.id) && email.equals(employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return String.format("\n- Mã nhân viên: %s%s\n- Email: %s", id, super.toString(), email);
    }
}
