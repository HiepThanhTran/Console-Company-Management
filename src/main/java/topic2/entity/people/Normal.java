package topic2.entity.people;

import java.util.Date;
import topic2.entity.other.TypeEmployees;

public class Normal extends Employee {

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
        return TypeEmployees.NORMAL.getCoefficient();
    }
}
