package topic3.cauhoi;

import static topic3.CauHinh.sc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DanhMuc {

    private String tenDanhMuc;
    private List<CauHoi> dsCauHoi;

    public DanhMuc() {
    }

    public DanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
        this.dsCauHoi = new ArrayList<>();
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

    public void themCauHoi(CauHoi... dsCauHoi) {
        this.dsCauHoi.addAll(Arrays.asList(dsCauHoi));
    }

    public void xoaCauHoi(CauHoi... dsCauHoi) {
        this.dsCauHoi.removeAll(Arrays.asList(dsCauHoi));
    }

    public void nhapThongTin() {
        System.out.print("- Ten danh muc: ");
        this.tenDanhMuc = sc.nextLine();
    }

    public void hienThiThongTin() {
        System.out.printf("\n== THONG TIN DANH MUC %s ==\n", tenDanhMuc);
//        System.out.println(this);
        System.out.printf("- Ten danh muc: %s\n", tenDanhMuc);
        this.hienThiDsCauHoi();
    }

    public void hienThiDsCauHoi() {
        this.dsCauHoi.forEach(cauHoi -> cauHoi.hienThiThongTin());
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
