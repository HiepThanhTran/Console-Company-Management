package topic3.cauhoi;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoice extends CauHoi {

    private List<PhuongAn> dsPhuongAn = new ArrayList<>();

    public MultipleChoice() {
    }

    public MultipleChoice(String mucDo, String noiDung, List<PhuongAn> dsPhuongAn) {
        super(mucDo, noiDung);
        this.dsPhuongAn = dsPhuongAn;
    }

    public MultipleChoice(String mucDo, String noiDung, DanhMuc danhMuc, List<PhuongAn> dsPhuongAn) {
        super(mucDo, noiDung, danhMuc);
        this.dsPhuongAn = dsPhuongAn;
    }

    public List<PhuongAn> getDsPhuongAn() {
        return dsPhuongAn;
    }

    public void setDsPhuongAn(List<PhuongAn> dsPhuongAn) {
        this.dsPhuongAn = dsPhuongAn;
    }

    @Override
    public List<MultipleChoice> getDsCauHoi() {
        return null;
    }

    public void hienThiDsPhuongAn() {
        for (int i = 0; i < this.dsPhuongAn.size(); i++) {
            System.out.println(this.dsPhuongAn.get(i));
        }
    }
}
