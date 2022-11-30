package topic3;

import java.util.ArrayList;
import java.util.List;

public class QuanLyDanhMuc {

    private final List<DanhMuc> dsDanhMuc = new ArrayList<>();

    public List<DanhMuc> getDsDanhMuc() {
        return dsDanhMuc;
    }

    public void themDanhMuc(DanhMuc danhMuc) {
        this.dsDanhMuc.add(danhMuc);
    }

    public void xoaDanhMuc(DanhMuc danhMuc) {
        this.dsDanhMuc.remove(danhMuc);
    }

    public void hienThiDS() {
        this.dsDanhMuc.forEach(danhMuc -> System.out.printf("\n%s\n", danhMuc));
    }

    public DanhMuc traCuu(String tenDanhMuc) {
        return this.dsDanhMuc.stream().filter(danhMuc -> danhMuc.getTenDanhMuc().equals(tenDanhMuc)).findFirst().orElse(null);
    }
}
