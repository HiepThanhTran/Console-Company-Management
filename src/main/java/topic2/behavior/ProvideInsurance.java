package topic2.behavior;

import topic2.entity.people.Employee;
import topic2.entity.people.Relative;

public class ProvideInsurance {

    private int insNumber;
    private Relative relative;
    private Employee employee;

    {
        insNumber = (int) (Math.random() * (9999 - 1000 + 1)) + 1000;
    }

    public ProvideInsurance(Relative relative, Employee employee) {
        this.relative = relative;
        this.employee = employee;
    }

    public ProvideInsurance(int insNumber, Relative relative, Employee employee) {
        this.insNumber = insNumber;
        this.relative = relative;
        this.employee = employee;
    }

    public int getInsNumber() {
        return insNumber;
    }

    public void setInsNumber(int insNumber) {
        this.insNumber = insNumber;
    }

    public Relative getRelative() {
        return relative;
    }

    public void setRelative(Relative relative) {
        this.relative = relative;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void showRelativeInfo() {
        relative.showInfo();
    }

    public void showEmpInfo() {
        employee.showInfo();
    }
}
