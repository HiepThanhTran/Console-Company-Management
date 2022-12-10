package topic3.cauhoi;

import static topic3.CauHinh.sc;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoice extends CauHoi {

    private List<PhuongAn> dsPhuongAn = new ArrayList<>();

    public MultipleChoice(DanhMuc danhMuc) {
        super(danhMuc);
    }

    public MultipleChoice(String mucDo, String noiDung, DanhMuc danhMuc, List<PhuongAn> dsPhuongAn) {
        super(mucDo, noiDung, danhMuc);
        this.dsPhuongAn = dsPhuongAn;
    }

    public static List<MultipleChoice> nhapDsMultipleChoice(int soLuong) {
        List<MultipleChoice> dsCauHoi = new ArrayList<>();
        for (int i = 0; i < soLuong; i++) {
            System.out.printf("\n== NHAP THONG TIN CAU HOI THU %d ==\n", i + 1);
            System.out.print("- Cau hoi thuoc danh muc: ");
            String tenDanhMuc = sc.nextLine();
            MultipleChoice cauHoi = new MultipleChoice(new DanhMuc(tenDanhMuc));
            cauHoi.nhapThongTin();
            System.out.print("- Nhap so luong phuong an: ");
            int soLuongPA = Integer.parseInt(sc.nextLine());
            List<PhuongAn> dsPhuongAn = PhuongAn.nhapDSPhuongAn(soLuongPA);
            cauHoi.setDsPhuongAn(dsPhuongAn);
            dsCauHoi.add(cauHoi);
        }
        return dsCauHoi;
    }

    public List<PhuongAn> getDsPhuongAn() {
        return dsPhuongAn;
    }

    public void setDsPhuongAn(List<PhuongAn> dsPhuongAn) {
        this.dsPhuongAn = dsPhuongAn;
    }

    @Override
    public List<MultipleChoice> getDsCauHoi() {
        return null;
    }

    public void hienThiDsPhuongAn() {
        for (int i = 0; i < this.dsPhuongAn.size(); i++) {
            System.out.println("\t" + this.dsPhuongAn.get(i));
        }
    }
}
