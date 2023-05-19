package project_management.entity;

import static project_management.ui.Factory.DECIMALFORMAT;
import static project_management.ui.Factory.SCANNER;

import java.text.ParseException;
import java.util.Date;

public class Programmer extends Employee {

    private double salaryOT;

    {
        id = String.format("P-%05d", EMPLOYEE_AMOUNT);
    }

    public Programmer() {
    }

    public Programmer(String name, String gender, Date dob, String email, double salaryOT) {
        super(name, gender, dob, email);
        this.salaryOT = salaryOT;
    }

    public Programmer(String name, String gender, Date dob, String id, String email, double salaryOT) {
        super(name, gender, dob, id, email);
        this.salaryOT = salaryOT;
    }

    public double getSalaryOT() {
        return salaryOT;
    }

    public void setSalaryOT(double salaryOT) {
        this.salaryOT = salaryOT;
    }

    @Override
    public double getCoefficient() {
        return Type.PROGRAMMER.getCoefficient();
    }

    @Override
    public double getAllowance() {
        return salaryOT;
    }

    @Override
    public void setInfo() throws ParseException {
        super.setInfo();
        System.out.print("- Lương OT: ");
        this.salaryOT = Double.parseDouble(SCANNER.nextLine());
    }

    @Override
    public String toString() {
        return String.format("%s Lương OT: %-20s |", super.toString(), DECIMALFORMAT.format(salaryOT));
    }
}
