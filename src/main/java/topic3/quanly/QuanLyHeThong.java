package topic3.quanly;

import static topic3.CauHinh.quanLyCauHoi;
import static topic3.CauHinh.sc;

import java.util.ArrayList;
import java.util.List;
import topic3.cauhoi.CauHoi;
import topic3.cauhoi.MultipleChoice;
import topic3.user.ThanhVien;

public class QuanLyHeThong {

    public static void luyenTap(ThanhVien thanhVien, String type, String mucDo) throws ClassNotFoundException {
        CauHoi cauHoi = quanLyCauHoi.randomCauHoi(type, mucDo);
        cauHoi.hienThiThongTin();
        int soCauDung = traLoiCauHoi(cauHoi.getDsCauHoi());
        thanhVien.getThanhTich().setSoLanLam(thanhVien.getThanhTich().getSoLanLam() + 1);
        thanhVien.getThanhTich().themDiem(Double.valueOf(soCauDung * (10 / cauHoi.getDsCauHoi().size())));
    }

    public static void luyenTap(ThanhVien thanhVien, String type, int soLuong) throws ClassNotFoundException {
        List<CauHoi> temp = quanLyCauHoi.randomCauHoi(type, soLuong);
        List<MultipleChoice> dsCauHoi = (List<MultipleChoice>) (Object) temp;
        int soCauDung = traLoiCauHoi(dsCauHoi);
        thanhVien.getThanhTich().setSoLanLam(thanhVien.getThanhTich().getSoLanLam() + 1);
        thanhVien.getThanhTich().themDiem(Double.valueOf(soCauDung * (10 / soLuong)));
    }

    private static int traLoiCauHoi(List<MultipleChoice> dsCauHoi) {
        List<Character> dsDapAn = new ArrayList<>();
        int soCauDung = 0;
        dsCauHoi.forEach(multipleChoice -> {
            multipleChoice.hienThiThongTin();
            multipleChoice.hienThiDsPhuongAn();
            System.out.print("- Chon dap an: ");
            dsDapAn.add(sc.next().toUpperCase().charAt(0));
        });
        System.out.println("====================");
        for (int i = 0; i < dsCauHoi.size(); i++) {
            MultipleChoice cauHoi = dsCauHoi.get(i);
            dsCauHoi.get(i).hienThiThongTin();
            int index = (dsDapAn.get(i)) - 'A';
            boolean dapAn = index >= 0 && index < cauHoi.getDsPhuongAn().size() && cauHoi.getDsPhuongAn().get(index).isDapAn();
            System.out.println("=> " + (dapAn ? "Dung" : "Sai"));
            soCauDung = (dapAn) ? soCauDung + 1 : soCauDung;
            System.out.println();
        }
        return soCauDung;
    }
}