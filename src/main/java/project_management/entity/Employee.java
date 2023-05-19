package project_management.entity;

import static project_management.ui.Factory.BASIC_SALARY;
import static project_management.ui.Factory.SCANNER;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

public abstract class Employee extends Person {

    protected static int EMPLOYEE_AMOUNT = 0;
    protected String id;
    protected String email;
    protected double salary;

    {
        EMPLOYEE_AMOUNT++;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public abstract double getAllowance();

    public abstract double getCoefficient();

    public double calculateSalary() {
        return BASIC_SALARY * this.getCoefficient() + this.getAllowance();
    }

    @Override
    public void setInfo() throws ParseException {
        super.setInfo();
        System.out.print("- Email: ");
        this.email = SCANNER.nextLine();
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
        return id.equals(employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("> %-12s %s %-30s |", id, super.toString(), email);
    }
}