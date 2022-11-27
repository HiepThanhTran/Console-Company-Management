package topic3;

import static topic3.CauHinh.f;
import static topic3.CauHinh.quanLyCauHoi;
import static topic3.CauHinh.quanLyDanhMuc;
import static topic3.CauHinh.quanLyThanhVien;
import static topic3.CauHinh.sc;

import java.text.ParseException;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
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
                                int maThanhVien = Integer.parseInt(sc.next());
                                ThanhVien tv = quanLyThanhVien.traCuu(maThanhVien);
                                if (tv != null) {
                                    quanLyThanhVien.xoaTV(tv);
                                    System.out.println("\n== XOA THANH VIEN THANH CONG ==");
                                } else {
                                    System.out.println("\n== KHONG TIM THAY THANH VIEN ==");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("\n** DU LIEU KHONG HOP LE **");
                            }
                        }
                        case "3" -> {
                            try {
                                System.out.print("\n- Nhap ma thanh vien muon xoa: ");
                                int maThanhVien = Integer.parseInt(sc.next());
                                ThanhVien tv = quanLyThanhVien.traCuu(maThanhVien);
                                if (tv != null) {
                                    tv.nhapThongTin();
                                    System.out.println("\n== CAP NHAT THANH VIEN THANH CONG ==");
                                } else {
                                    System.out.println("\n== KHONG TIM THAY THANH VIEN ==");
                                }
                            } catch (NumberFormatException | ParseException e) {
                                System.out.println("\n** DU LIEU KHONG HOP LE **");
                            }
                        }
                        case "4" -> {
                            System.out.println("\n*** DANH SACH HOC VIEN ***");
                            quanLyThanhVien.hienThiDS();
                        }
                        case "5" -> {
                            try {
                                System.out.print("- Nhap ngay sinh can tra cuu: ");
                                Date ngaySinh = f.parse(sc.next());
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
                            int soLuongPhuongAn = 0, soLuongCauHoi = 0;
                            System.out.println("1. MultipleChoice");
                            System.out.println("2. InComplete");
                            System.out.println("3. Conversation");
                            System.out.print("- Chon loai cau hoi: ");
                            String c2 = sc.next();
                            CauHoi cauHoi = null;
                            try {
                                switch (c2) {
                                    case "1" -> {
                                        
                                    }
                                    case "2" -> {

                                    }
                                    case "3" -> {

                                    }
                                    default -> System.out.println("\n** CHUC NANG HIEN CHUA CO **");
                                }
                            } catch (NullPointerException e) {
                                System.out.println("\n== CAU HOI KHONG HOP LE ==");
                            }
                        }
                        case "3" -> {

                        }
                        case "4" -> {
                            System.out.println("*** DANH SACH CAU HOI ***");
                            quanLyCauHoi.hienThiDS();
                        }
                        case "5" -> {

                        }
                        case "6" -> {

                        }
                        case "7" -> {

                        }
                        default -> System.out.println("\n** CHUC NANG HIEN CHUA CO **");
                    }
                }
                case "3" -> {

                }
                case "4" -> {

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
