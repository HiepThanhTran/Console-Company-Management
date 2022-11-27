package topic3;

import java.util.List;

public class Conversation extends CauHoi {

    private List<MultipleChoice> dsCauHoi;

    public Conversation() {
    }

    public Conversation(int mucDo, String noiDung, DanhMuc danhMuc, List<MultipleChoice> dsCauHoi) {
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
