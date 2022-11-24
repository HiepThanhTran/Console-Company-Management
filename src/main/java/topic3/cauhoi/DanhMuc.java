package topic3.cauhoi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DanhMuc {

    private String tenDanhMuc;
    private List<CauHoi> dsCauHoi = new ArrayList<>();

    public DanhMuc() {
    }

    public DanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
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

    public void themCauHoi(CauHoi ch) {
        this.dsCauHoi.add(ch);
    }

    public void xoaCauHoi(CauHoi ch) {
        this.dsCauHoi.remove(ch);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DanhMuc danhMuc = (DanhMuc) o;
        return tenDanhMuc.equals(danhMuc.tenDanhMuc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenDanhMuc);
    }

    @Override
    public String toString() {
        return String.format("- Danh muc: %s", tenDanhMuc);
    }
}
