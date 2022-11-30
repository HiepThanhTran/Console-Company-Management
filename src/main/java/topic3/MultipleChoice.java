package topic3;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoice extends CauHoi {

    private List<PhuongAn> dsPhuongAn = new ArrayList<>();

    public MultipleChoice() {
    }

    public MultipleChoice(int mucDo, String noiDung, DanhMuc danhMuc, List<PhuongAn> dsPhuongAn) {
        super(mucDo, noiDung, danhMuc);
        this.dsPhuongAn = dsPhuongAn;
    }

    public List<PhuongAn> getDsPhuongAn() {
        return dsPhuongAn;
    }

    public void setDsPhuongAn(List<PhuongAn> dsPhuongAn) {
        this.dsPhuongAn = dsPhuongAn;
    }

    public String formula() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.dsPhuongAn.size(); i++) {
            sb.append(((char) ('A' + 1)) + "" + this.dsPhuongAn.get(i));
        }
        return sb.toString();
    }

    public String toString() {
        return String.format("%s\n%s", super.toString(), this.formula());
    }
}
