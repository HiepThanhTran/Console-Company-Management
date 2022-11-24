package topic3.cauhoi;

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
}
