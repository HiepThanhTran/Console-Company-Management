package topic3.quanly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import topic3.cauhoi.CauHoi;
import topic3.cauhoi.DanhMuc;

public class QuanLyCauHoi {

    private final List<CauHoi> dsCauHoi = new ArrayList<>();

    public static void hienThiDS(List<CauHoi> dsCauHoi) {
        dsCauHoi.forEach(cauHoi -> System.out.printf("\n%s\n", cauHoi));
    }

    public List<CauHoi> getDsCauHoi() {
        return dsCauHoi;
    }

    public void themCauHoi(CauHoi cauHoi) {
        this.dsCauHoi.add(cauHoi);
    }

    public void themCauHoi(CauHoi... dsCauHoi) {
        this.dsCauHoi.addAll(Arrays.asList(dsCauHoi));
    }

    public void xoaCauHoi(CauHoi cauHoi) {
        this.dsCauHoi.remove(cauHoi);
    }

    public void xoaCauHoi(CauHoi... dsCauHoi) {
        this.dsCauHoi.removeAll(Arrays.asList(dsCauHoi));
    }

    public List<CauHoi> traCuu(int mucDo) {
        return this.dsCauHoi.stream().filter(cauHoi -> cauHoi.getMucDo() == mucDo).collect(Collectors.toList());
    }

    public List<CauHoi> traCuu(DanhMuc danhMuc) {
        return this.dsCauHoi.stream().filter(cauHoi -> cauHoi.getDanhMuc().equals(danhMuc)).collect(Collectors.toList());
    }

    public List<CauHoi> traCuu(String noiDung) {
        return this.dsCauHoi.stream().filter(cauHoi -> cauHoi.getNoiDung().equals(noiDung)).collect(Collectors.toList());
    }

    public List<CauHoi> randomCauHoi(int type) {
        return null;
    }

    public List<CauHoi> randomCauHoi(int type, int number) {
        return null;
    }
}
