package topic3.cauhoi;

import java.util.ArrayList;
import java.util.List;

public class DanhMuc {
    private String tenDanhMuc;
    private List<CauHoi> dsCauHoi = new ArrayList<>();

    public DanhMuc() {
    }

    public DanhMuc(String tenDanhMuc, List<CauHoi> dsCauHoi) {
        this.tenDanhMuc = tenDanhMuc;
        this.dsCauHoi = dsCauHoi;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public List<CauHoi> getDsCauHoi() {
        return dsCauHoi;
    }

    public void setDsCauHoi(List<CauHoi> dsCauHoi) {
        this.dsCauHoi = dsCauHoi;
    }

    @Override
    public String toString() {
        return String.format("- Danh muc: %s", tenDanhMuc);
    }
}
