package topic2.entity.people;

import static topic2.ui.Factory.ERROR_SALARY;
import static topic2.ui.Factory.SCANNER;
import static topic2.ui.Factory.SIMPLEDATEFORMAT;

import java.text.ParseException;
import java.util.Date;
import topic2.entity.other.TypeEmployees;

public class Tester extends Employee {

    private int errors;

    {
        id = String.format("T-%05d", EMPLOYEE_AMOUNT);
    }

    public Tester() {
    }

    public Tester(String name, String gender, Date dob, String email, int errors) {
        super(name, gender, dob, email);
        this.errors = errors;
    }

    public Tester(String name, String gender, String dob, String email, int errors) throws ParseException {
        this(name, gender, SIMPLEDATEFORMAT.parse(dob), email, errors);
    }

    public Tester(String name, String gender, Date dob, String id, String email, int errors) {
        super(name, gender, dob, id, email);
        this.errors = errors;
    }

    public Tester(String name, String gender, String dob, String id, String email, int errors) throws ParseException {
        this(name, gender, SIMPLEDATEFORMAT.parse(dob), id, email, errors);
    }

    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    @Override
    public double getCoefficient() {
        return TypeEmployees.TESTER.getCoefficient();
    }

    @Override
    public double getAllowance() {
        return errors * ERROR_SALARY;
    }

    @Override
    public void setInfo() throws ParseException {
        super.setInfo();
        System.out.print("- Số lỗi tìm được: ");
        this.errors = Integer.parseInt(SCANNER.nextLine());
    }

    @Override
    public String toString() {
        return String.format("%s\n- Số lỗi tìm được: %d", super.toString(), errors);
    }
}
