package topic3;

import static topic3.CauHinh.quanLyDanhMuc;
import static topic3.CauHinh.sc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class QuanLyCauHoi {

    private final List<CauHoi> dsCauHoi = new ArrayList<>();

    public static int random(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static List<MultipleChoice> nhapDsMultipleChoice(int soLuong) {
        List<MultipleChoice> dsCauHoi = new ArrayList<>();
        for (int i = 0; i < soLuong; i++) {
            MultipleChoice cauHoi = new MultipleChoice();
            System.out.print("- Cau hoi thuoc danh muc: ");
            DanhMuc danhMuc = quanLyDanhMuc.traCuu(sc.nextLine());
            cauHoi.nhapThongTin();
            System.out.print("- Nhap so luong phuong an: ");
            int soLuongPA = Integer.parseInt(sc.next());
            List<PhuongAn> dsPhuongAn = PhuongAn.nhapDSPhuongAn(soLuongPA);
            cauHoi.setDanhMuc(danhMuc);
            cauHoi.setDsPhuongAn(dsPhuongAn);
            dsCauHoi.add(cauHoi);
        }
        return dsCauHoi;
    }

    public List<CauHoi> getDsCauHoi() {
        return dsCauHoi;
    }

    public void themCauHoi(CauHoi cauHoi) {
        this.dsCauHoi.add(cauHoi);
    }

    public void xoaCauHoi(CauHoi cauHoi) {
        this.dsCauHoi.remove(cauHoi);
    }

    public void hienThiDS() {
        this.dsCauHoi.forEach(cauHoi -> System.out.printf("\n%s\n", cauHoi));
    }

    public CauHoi traCuuTheoId(int maCauHoi) {
        return this.dsCauHoi.stream().filter(cauHoi -> cauHoi.getMaCauHoi() == maCauHoi).findFirst().orElse(null);
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

    public CauHoi randomCauHoi(String type) throws ClassNotFoundException {
        Class c = Class.forName(type);
        List<CauHoi> temp = this.dsCauHoi.stream().filter(cauHoi -> c.isInstance(cauHoi)).collect(Collectors.toList());
        return temp.get(random(0, temp.size() - 1));
    }

    public List<MultipleChoice> randomCauHoi(int soLuong) {
        List<MultipleChoice> temp = new ArrayList<>();
        while (soLuong > 0) {
            CauHoi cauHoi = this.dsCauHoi.get(random(0, this.dsCauHoi.size() - 1));
            if (cauHoi instanceof MultipleChoice multipleChoice && !temp.contains(cauHoi)) {
                temp.add(multipleChoice);
            }
            soLuong--;
        }
        return temp;
    }
}
