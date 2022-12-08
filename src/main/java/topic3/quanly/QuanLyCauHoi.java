package topic3.quanly;

import static topic3.CauHinh.sc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import topic3.cauhoi.CauHoi;
import topic3.cauhoi.MultipleChoice;
import topic3.cauhoi.PhuongAn;

public class QuanLyCauHoi {

    private final List<CauHoi> dsCauHoi = new ArrayList<>();

    public static int random(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static List<MultipleChoice> nhapDsMultipleChoice(int soLuong) {
        List<MultipleChoice> dsCauHoi = new ArrayList<>();
        for (int i = 0; i < soLuong; i++) {
            MultipleChoice cauHoi = new MultipleChoice();
            cauHoi.nhapThongTin();
            System.out.print("- Nhap so luong phuong an: ");
            int soLuongPA = Integer.parseInt(sc.next());
            List<PhuongAn> dsPhuongAn = PhuongAn.nhapDSPhuongAn(soLuongPA);
            cauHoi.setDsPhuongAn(dsPhuongAn);
            dsCauHoi.add(cauHoi);
        }
        return dsCauHoi;
    }

    public List<CauHoi> getDsCauHoi() {
        return dsCauHoi;
    }

    public void themCauHoi(CauHoi... dsCauHoi) {
        this.dsCauHoi.addAll(Arrays.asList(dsCauHoi));
    }

    public void xoaCauHoi(CauHoi... dsCauHoi) {
        this.dsCauHoi.removeAll(Arrays.asList(dsCauHoi));
    }

    public void hienThiDS() {
        this.dsCauHoi.forEach(cauHoi -> cauHoi.hienThiThongTin());
    }

    public CauHoi traCuu(int maCauHoi) {
        return this.dsCauHoi.stream().filter(cauHoi -> cauHoi.getMaCauHoi() == maCauHoi).findFirst().orElse(null);
    }

    /**
     * Tìm kiếm câu hỏi theo nội dung hoặc mức độ
     *
     * @param tuKhoa Nội dung, mức độ
     * @return Danh sách câu hoi thỏa điều kiện
     */
    public List<CauHoi> traCuu(String tuKhoa) {
        return this.dsCauHoi.stream()
            .filter(cauHoi -> cauHoi.getNoiDung().equalsIgnoreCase(tuKhoa) || cauHoi.getMucDo().equalsIgnoreCase(tuKhoa))
            .collect(Collectors.toList());
    }

    public CauHoi randomCauHoi(String type, String mucDo) throws ClassNotFoundException {
        Class c = Class.forName(type);
        List<CauHoi> ds = this.dsCauHoi.stream()
            .filter(cauHoi -> c.isInstance(cauHoi) && cauHoi.getMucDo().equalsIgnoreCase(mucDo)).collect(Collectors.toList());
        return ds.get(random(0, ds.size() - 1));
    }

    public List<CauHoi> randomCauHoi(String type, int soLuong) throws ClassNotFoundException {
        List<CauHoi> ds = new ArrayList<>();
        Class c = Class.forName(type);
        for (int i = 0; i < soLuong; i++) {
            CauHoi cauHoi = this.dsCauHoi.get(random(0, this.dsCauHoi.size() - 1));
            if (c.isInstance(cauHoi) && !ds.contains(cauHoi)) {
                ds.add(cauHoi);
            }
        }
        return ds;
    }
}
