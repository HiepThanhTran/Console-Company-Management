package project_management.entity;

import static project_management.ui.Factory.ERROR_SALARY;
import static project_management.ui.Factory.SCANNER;

import java.text.ParseException;
import java.util.Date;

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

    public Tester(String name, String gender, Date dob, String id, String email, int errors) {
        super(name, gender, dob, id, email);
        this.errors = errors;
    }

    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    @Override
    public double getCoefficient() {
        return Type.TESTER.getCoefficient();
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
        return String.format("%s Số lỗi tìm được: %-13d |", super.toString(), errors);
    }
}
