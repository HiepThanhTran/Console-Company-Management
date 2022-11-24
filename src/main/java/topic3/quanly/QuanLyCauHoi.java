package topic3.quanly;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import topic3.cauhoi.CauHoi;

public class QuanLyCauHoi {

    private List<CauHoi> dsCauHoi = new ArrayList<>();

    public void themCauHoi(CauHoi ch) {
        this.dsCauHoi.add(ch);
    }

    public void xoaCauHoi(CauHoi ch) {
        this.dsCauHoi.remove(ch);
    }

    public void hienThiDS() {
        this.dsCauHoi.forEach(cauHoi -> System.out.printf("\n%s\n", cauHoi));
    }

    public List<CauHoi> traCuu(int mucDo) {
        return this.dsCauHoi.stream().filter(cauHoi -> cauHoi.getMucDo() == mucDo).collect(Collectors.toList());
    }

    public List<CauHoi> traCuu(String tukhoa) {
        return this.dsCauHoi.stream().filter(cauHoi -> cauHoi.getNoiDung().equals(tukhoa) || cauHoi.getDanhMuc().getTenDanhMuc().equals(tukhoa)).collect(Collectors.toList());
    }

    public List<CauHoi> randomCauHoi(int type) {
        return null;
    }

    public List<CauHoi> randomCauHoi(int type, int number) {
        return null;
    }
}
