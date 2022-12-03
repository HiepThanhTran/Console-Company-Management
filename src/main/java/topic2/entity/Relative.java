package topic2.entity;

import static topic2.ui.Factory.SCANNER;
import static topic2.ui.Factory.SIMPLEDATEFORMAT;

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
        System.out.print("- Họ tên: ");
        this.name = SCANNER.nextLine();
        System.out.print("- Giới tính: ");
        this.gender = SCANNER.nextLine();
        System.out.print("- Ngày sinh: ");
        this.dob = SIMPLEDATEFORMAT.parse(SCANNER.nextLine());
        System.out.print("- Mối quan hệ: ");
        this.relationship = SCANNER.nextLine();
    }

    public void showInfo() {
        System.out.println(this);
    }

    public String toString() {
        return String.format("%s\n- Mối quan hệ: %s", super.toString(), relationship);
    }
}
