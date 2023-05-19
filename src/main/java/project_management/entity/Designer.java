package project_management.entity;

import static project_management.ui.Factory.DECIMALFORMAT;
import static project_management.ui.Factory.SCANNER;

import java.text.ParseException;
import java.util.Date;

public class Designer extends Employee {

    private double bonus;

    {
        id = String.format("D-%05d", EMPLOYEE_AMOUNT);
    }

    public Designer() {
    }

    public Designer(String name, String gender, Date dob, String email, double bonus) {
        super(name, gender, dob, email);
        this.bonus = bonus;
    }

    public Designer(String name, String gender, Date dob, String id, String email, double bonus) {
        super(name, gender, dob, id, email);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public double getCoefficient() {
        return Type.DESIGNER.getCoefficient();
    }

    @Override
    public double getAllowance() {
        return bonus;
    }

    @Override
    public void setInfo() throws ParseException {
        super.setInfo();
        System.out.print("- Thưởng thêm: ");
        this.bonus = Double.parseDouble(SCANNER.nextLine());
    }

    @Override
    public String toString() {
        return String.format("%s Thưởng thêm: %-17s |", super.toString(), DECIMALFORMAT.format(bonus));
    }
}
