package topic3.cauhoi;

import java.util.List;

public class InComplete extends CauHoi {

    private List<MultipleChoice> dsCauHoi;

    public InComplete() {
    }

    public InComplete(int mucDo, String noiDung, DanhMuc danhMuc, List<MultipleChoice> dsCauHoi) {
        super(mucDo, noiDung, danhMuc);
        this.dsCauHoi = dsCauHoi;
    }

    public List<MultipleChoice> getDsCauHoi() {
        return dsCauHoi;
    }

    public void setDsCauHoi(List<MultipleChoice> dsCauHoi) {
        this.dsCauHoi = dsCauHoi;
    }
}
