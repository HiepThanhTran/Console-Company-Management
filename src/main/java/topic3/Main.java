package topic3;

import static topic3.CauHinh.f;
import static topic3.CauHinh.quanLyCauHoi;
import static topic3.CauHinh.quanLyDanhMuc;
import static topic3.CauHinh.quanLyHeThong;
import static topic3.CauHinh.quanLyThanhVien;
import static topic3.CauHinh.sc;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        while (true) {
            System.out.println("1- Quan ly hoc vien");
            System.out.println("2- Quan ly cau hoi");
            System.out.println("3- Luyen tap");
            System.out.println("4- Thong ke ket qua hoc tap cua 1 hoc vien");
            System.out.println("5- Ket thuc");
            System.out.print("- Chon chuc nang: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1" -> {
                    System.out.println("\n1. Them thanh vien moi");
                    System.out.println("2. Xoa thanh vien");
                    System.out.println("3. Cap nhat thong tin thanh vien");
                    System.out.println("4. Xem danh sach thanh vien");
                    System.out.println("5. Tra cuu thanh vien theo ngay sinh");
                    System.out.println("6. Tra cuu thanh vien theo ten, gioi tinh, hoac que quan");
                    System.out.print("- Chon chuc nang: ");
                    String choice1 = sc.nextLine();
                    switch (choice1) {
                        case "1" -> {
                            System.out.println("\n== NHAP THONG TIN THANH VIEN MOI ==");
                            ThanhVien tv = new ThanhVien();
                            try {
                                tv.nhapThongTin();
                                quanLyThanhVien.themTV(tv);
                                System.out.println("\n== THEM THANH VIEN MOI THANH CONG ==");
                            } catch (ParseException e) {
                                System.out.println("\n** DU LIEU KHONG HOP LE **");
                            }
                        }
                        case "2" -> {
                            try {
                                System.out.print("\n- Nhap ma thanh vien muon xoa: ");
                                int maThanhVien = Integer.parseInt(sc.nextLine());
                                ThanhVien tv = quanLyThanhVien.traCuu(maThanhVien);
                                quanLyThanhVien.xoaTV(tv);
                                System.out.println("\n== XOA THANH VIEN THANH CONG ==");
                            } catch (NumberFormatException e) {
                                System.out.println("\n** DU LIEU KHONG HOP LE **");
                            } catch (NullPointerException e) {
                                System.out.println("\n== KHONG TIM THAY THANH VIEN ==");
                            }
                        }
                        case "3" -> {
                            try {
                                System.out.print("\n- Nhap ma thanh vien muon cap nhat: ");
                                int maThanhVien = Integer.parseInt(sc.nextLine());
                                ThanhVien tv = quanLyThanhVien.traCuu(maThanhVien);
                                tv.nhapThongTin();
                                System.out.println("\n== CAP NHAT THANH VIEN THANH CONG ==");
                            } catch (NumberFormatException | ParseException e) {
                                System.out.println("\n** DU LIEU KHONG HOP LE **");
                            } catch (NullPointerException e) {
                                System.out.println("\n== KHONG TIM THAY THANH VIEN ==");
                            }
                        }
                        case "4" -> {
                            System.out.println("\n*** DANH SACH HOC VIEN ***");
                            quanLyThanhVien.hienThiDS();
                        }
                        case "5" -> {
                            try {
                                System.out.print("- Nhap ngay sinh can tra cuu: ");
                                Date ngaySinh = f.parse(sc.nextLine());
                                System.out.println("\n*** DANH SACH CAC HOC VIEN TRA CUU THEO NGAY SINH ***");
                                quanLyThanhVien.traCuu(ngaySinh).forEach(thanhVien -> System.out.printf("\n%s\n", thanhVien));
                            } catch (ParseException e) {
                                System.out.println("\n** DU LIEU KHONG HOP LE **");
                            }
                        }
                        case "6" -> {
                            System.out.print("- Nhap tu khoa can tra cuu (Ho ten, gioi tinh, que quan): ");
                            String tuKhoa = sc.nextLine();
                            System.out.println("\n*** DANH SACH CAC HOC VIEN TRA CUU DUOC ***");
                            quanLyThanhVien.traCuu(tuKhoa).forEach(thanhVien -> System.out.printf("\n%s\n", thanhVien));
                        }
                        default -> System.out.println("\n** CHUC NANG HIEN CHUA CO **");
                    }
                }
                case "2" -> {
                    System.out.println("\n1. Tao danh muc moi");
                    System.out.println("2. Them cau hoi moi");
                    System.out.println("3. Xoa cau hoi");
                    System.out.println("4. Xem danh sach cau hoi");
                    System.out.println("5. Tra cuu cau hoi theo muc do");
                    System.out.println("6. Tra cuu cau hoi theo danh muc");
                    System.out.println("7. Tra cuu cau hoi theo noi dung cau hoi");
                    System.out.print("- Chon chuc nang: ");
                    String choice2 = sc.nextLine();
                    switch (choice2) {
                        case "1" -> {
                            DanhMuc danhMuc = new DanhMuc();
                            danhMuc.nhapThongTin();
                            quanLyDanhMuc.themDanhMuc(danhMuc);
                            System.out.println("\n== THEM DANH MUC MOI THANH CONG ==");
                        }
                        case "2" -> {
                            try {
                                System.out.print("- Cau hoi thuoc danh muc: ");
                                DanhMuc danhMuc = quanLyDanhMuc.traCuu(sc.nextLine());
                                if (danhMuc == null) {
                                    throw new NullPointerException("\n== KHONG TIM THAY DANH MUC ==\n");
                                }
                                System.out.println("1. MultipleChoice");
                                System.out.println("2. InComplete");
                                System.out.println("3. Conversation");
                                System.out.print("- Chon loai cau hoi: ");
                                String c2 = sc.nextLine();
                                CauHoi cauHoi = null;
                                switch (c2) {
                                    case "1" -> {
                                        cauHoi = new MultipleChoice();
                                        cauHoi.nhapThongTin();
                                        System.out.print("- Nhap so luong phuong an: ");
                                        int soLuongPA = Integer.parseInt(sc.nextLine());
                                        List<PhuongAn> dsPhuongAn = PhuongAn.nhapDSPhuongAn(soLuongPA);
                                        ((MultipleChoice) cauHoi).setDsPhuongAn(dsPhuongAn);
                                    }
                                    case "2" -> {
                                        cauHoi = new InComplete();
                                        cauHoi.nhapThongTin();
                                        System.out.print("- Nhap so luong cau hoi: ");
                                        int soLuongCH = Integer.parseInt(sc.nextLine());
                                        List<MultipleChoice> dsCauHoi = QuanLyCauHoi.nhapDsMultipleChoice(soLuongCH);
                                        ((InComplete) cauHoi).setDsCauHoi(dsCauHoi);
                                    }
                                    case "3" -> {
                                        cauHoi = new Conversation();
                                        cauHoi.nhapThongTin();
                                        System.out.print("- Nhap so luong cau hoi: ");
                                        int soLuongCH = Integer.parseInt(sc.nextLine());
                                        List<MultipleChoice> dsCauHoi = QuanLyCauHoi.nhapDsMultipleChoice(soLuongCH);
                                        ((Conversation) cauHoi).setDsCauHoi(dsCauHoi);
                                    }
                                }
                                if (cauHoi == null) {
                                    throw new NullPointerException("\n== KHONG TIM THAY CAU HOI ==\n");
                                }
                                cauHoi.setDanhMuc(danhMuc);
                                quanLyCauHoi.themCauHoi(cauHoi);
                                System.out.println("\n== THEM CAU HOI MOI THANH CONG ==");
                            } catch (NullPointerException e) {
                                System.out.println(e.getMessage());
                            } catch (NumberFormatException e) {
                                System.out.println("\n** DU LIEU KHONG HOP LE **");
                            }
                        }
                        case "3" -> {
                            try {
                                System.out.print("- Ma cau hoi can xoa: ");
                                int maCauHoi = Integer.parseInt(sc.nextLine());
                                CauHoi cauHoi = quanLyCauHoi.traCuuTheoId(maCauHoi);
                                quanLyCauHoi.xoaCauHoi(cauHoi);
                                System.out.println("\n== XOA CAU HOI THANH CONG ==");
                            } catch (NumberFormatException e) {
                                System.out.println("\n** DU LIEU KHONG HOP LE **");
                            } catch (NullPointerException e) {
                                System.out.println("\n== KHONG TIM THAY CAU HOI ==");
                            }
                        }
                        case "4" -> {
                            System.out.println("*** DANH SACH CAU HOI ***");
                            quanLyCauHoi.hienThiDS();
                        }
                        case "5" -> {
                            try {
                                System.out.println("1. De");
                                System.out.println("2. Trung binh");
                                System.out.println("3. Kho");
                                System.out.print("- Chon muc do can tra cuu: ");
                                int mucDo = Integer.parseInt(sc.nextLine());
                                System.out.println("\n*** DANH SACH CAU HOI TRA CUU THEO MUC DO ***");
                                quanLyCauHoi.traCuu(mucDo).forEach(cauHoi -> System.out.printf("\n%s\n", cauHoi));
                            } catch (NumberFormatException e) {
                                System.out.println("\n** DU LIEU KHONG HOP LE **");
                            }
                        }
                        case "6" -> {
                            try {
                                System.out.print("- Nhap ten danh muc: ");
                                String tenDanhMuc = sc.nextLine();
                                DanhMuc danhMuc = quanLyDanhMuc.traCuu(tenDanhMuc);
                                System.out.println("\n*** DANH SACH CAU HOI THEO DANH MUC ***");
                                danhMuc.hienThiDsCauHoi();
                            } catch (NullPointerException e) {
                                System.out.println("\n== KHONG TIM THAY DANH MUC ==");
                            }
                        }
                        case "7" -> {
                            System.out.print("- Nhap noi dung cau hoi can tra cuu: ");
                            String noiDung = sc.nextLine();
                            System.out.println("\n*** DANH SACH CAU HOI TRA CUU THEO NOI DUNG ***");
                            quanLyCauHoi.traCuu(noiDung).forEach(cauHoi -> System.out.printf("\n%s\n", cauHoi));
                        }
                        default -> System.out.println("\n** CHUC NANG HIEN CHUA CO **");
                    }
                }
                case "3" -> {
                    System.out.print("\n- Nhap ma thanh vien: ");
                    int maThanhVien = Integer.parseInt(sc.nextLine());
                    ThanhVien tv = quanLyThanhVien.traCuu(maThanhVien);
                    LuyenTap lt = quanLyHeThong.traCuu(tv);
                    if (tv != null) {
                        if (lt != null) {
                            System.out.println("1. MultipleChoice");
                            System.out.println("2. InComplete");
                            System.out.println("3. Conversation");
                            System.out.print("- Chon loai cau hoi: ");
                            String c3 = sc.nextLine();
                            switch (c3) {
                                case "1" -> {
                                    try {
                                        System.out.print("- So luong cau hoi muon luyen tap: ");
                                        int soLuong = Integer.parseInt(sc.nextLine());
                                        lt.luyenTap(soLuong);
                                    } catch (NumberFormatException e) {
                                        System.out.println("\n** DU LIEU KHONG HOP LE **");
                                    }
                                }
                                case "2" -> lt.luyenTap("topic3.InComplete");
                                case "3" -> lt.luyenTap("topic3.Conversation");
                                default -> System.out.println("\n** CHUC NANG HIEN CHUA CO **");
                            }
                        } else {
                            LuyenTap luyenTap = new LuyenTap(new ThanhTich(), tv);
                            quanLyHeThong.themLuyenTap(luyenTap);
                            System.out.println("\n== THEM MOI TAI KHOAN LUYEN TAP THANH CONG ==");
                        }
                    } else {
                        System.out.println("\n== KHONG TIM THAY THANH VIEN ==");
                    }

                }
                case "4" -> {
                    try {
                        System.out.print("\n- Nhap ma thanh vien: ");
                        int maThanhVien = Integer.parseInt(sc.nextLine());
                        ThanhVien tv = quanLyThanhVien.traCuu(maThanhVien);
                        LuyenTap lt = quanLyHeThong.traCuu(tv);
                        if (tv != null && lt != null) {
                            quanLyHeThong.thongKe(lt);
                        } else {
                            System.out.println("\n== KHONG TIM THAY THANH VIEN ==");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("\n** DU LIEU KHONG HOP LE **");
                    }
                }
                case "5" -> {
                    return;
                }
                default -> System.out.println("\n== KHONG HOP LE ==");
            }
            sc.nextLine();
        }
    }
}
