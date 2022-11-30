package topic3;

import static topic3.CauHinh.sc;

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

    public void themCauHoi(CauHoi cauHoi) {
        this.dsCauHoi.add(cauHoi);
    }

    public void xoaCauHoi(CauHoi cauHoi) {
        this.dsCauHoi.remove(cauHoi);
    }

    public void nhapThongTin() {
        System.out.print("- Ten danh muc: ");
        this.tenDanhMuc = sc.nextLine();
    }

    public void hienThiDsCauHoi() {
        this.dsCauHoi.forEach(cauHoi -> System.out.printf("\n%s\n", cauHoi));
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
