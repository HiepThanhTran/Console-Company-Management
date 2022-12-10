package topic3.cauhoi;

import java.util.ArrayList;
import java.util.List;

public class Conversation extends CauHoi {

    private List<MultipleChoice> dsCauHoi = new ArrayList<>();

    public Conversation(DanhMuc danhMuc) {
        super(danhMuc);
    }

    public Conversation(String mucDo, String noiDung, DanhMuc danhMuc, List<MultipleChoice> dsCauHoi) {
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
