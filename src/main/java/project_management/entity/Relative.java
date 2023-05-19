package project_management.entity;

import static project_management.ui.Factory.SCANNER;

import java.text.ParseException;
import java.util.Date;

public class Relative extends Person {

    private String relationship;

    public Relative() {
    }

    public Relative(String name, String gender, Date dob, String relationship) {
        super(name, gender, dob);
        this.relationship = relationship;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public void setInfo() throws ParseException {
        super.setInfo();
        System.out.print("- Mối quan hệ: ");
        this.relationship = SCANNER.nextLine();
    }

    @Override
    public String toString() {
        return String.format("%s %-11s |", super.toString(), relationship);
    }
}