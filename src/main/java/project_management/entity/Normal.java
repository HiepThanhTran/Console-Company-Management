package project_management.entity;

import java.util.Date;

public class Normal extends Employee {

    {
        id = String.format("N-%05d", EMPLOYEE_AMOUNT);
    }

    public Normal() {
    }

    public Normal(String name, String gender, Date dob, String email) {
        super(name, gender, dob, email);
    }

    public Normal(String name, String gender, Date dob, String id, String email) {
        super(name, gender, dob, id, email);
    }

    @Override
    public double getAllowance() {
        return 0;
    }

    @Override
    public double getCoefficient() {
        return Type.NORMAL.getCoefficient();
    }

    @Override
    public String toString() {
        return String.format("%s %-30s |", super.toString(), "");
    }
}
