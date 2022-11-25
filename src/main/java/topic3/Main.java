package topic3;

import static topic3.CauHinh.f;
import static topic3.CauHinh.quanLyThanhVien;
import static topic3.CauHinh.sc;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import topic3.quanly.QuanLyThanhVien;
import topic3.user.ThanhVien;

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
                    System.out.println("\n1. Them hoc vien moi");
                    System.out.println("2. Xoa hoc vien");
                    System.out.println("3. Cap nhat thong tin hoc vien");
                    System.out.println("4. Xem danh sach hoc vien");
                    System.out.println("5. Tra cuu hoc vien theo ngay sinh");
                    System.out.println("6. Tra cuu hoc vien theo ten, gioi tinh, hoac que quan");
                    System.out.print("- Chon chuc nang: ");
                    String choice1 = sc.nextLine();
                    switch (choice1) {
                        case "1" -> {
                            System.out.println("\n== NHAP THONG TIN HOC VIEN MOI ==");
                            ThanhVien tv = new ThanhVien();
                            try {
                                tv.nhapThongTin();
                                quanLyThanhVien.themTV(tv);
                                System.out.println("\n== THEM HOC VIEN MOI THANH CONG ==");
                            } catch (ParseException e) {
                                System.out.println("\n** DU LIEU KHONG HOP LE **");
                            }
                        }
                        case "2" -> {
                            System.out.println("\n*** DANH SACH HOC VIEN ***");
                            QuanLyThanhVien.hienThiDS(quanLyThanhVien.getDsThanhVien());
                            System.out.print("\n- Nhap so thu tu hoc vien muon xoa: ");
                            String stt = sc.next();
                            try {
                                quanLyThanhVien.xoaTV(Integer.parseInt(stt) - 1);
                                System.out.println("\n== XOA HOC VIEN THANH CONG ==");
                            } catch (NumberFormatException e) {
                                System.out.println("\n** DU LIEU KHONG HOP LE **");
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("\n** KHONG TON TAI HOC VIEN NAY **");
                            }
                        }
                        case "3" -> {
                            System.out.println("\n*** DANH SACH HOC VIEN ***");
                            QuanLyThanhVien.hienThiDS(quanLyThanhVien.getDsThanhVien());
                            System.out.print("\n- Nhap so thu tu hoc vien can sua: ");
                            String stt = sc.next();
                            try {
                                System.out.println("\n== NHAP THONG TIN MOI CHO HOC VIEN ==");
                                quanLyThanhVien.capNhat(Integer.parseInt(stt) - 1);
                                System.out.println("\n== CAP NHAT HOC VIEN THANH CONG ==");
                            } catch (NumberFormatException | ParseException e) {
                                System.out.println("\n** DU LIEU KHONG HOP LE **");
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("\n** KHONG TON TAI HOC VIEN NAY **");
                            }
                        }
                        case "4" -> {
                            System.out.println("\n*** DANH SACH HOC VIEN ***");
                            QuanLyThanhVien.hienThiDS(quanLyThanhVien.getDsThanhVien());
                        }
                        case "5" -> {
                            System.out.print("- Nhap ngay sinh can tra cuu: ");
                            try {
                                Date ngaySinh = f.parse(sc.nextLine());
                                List<ThanhVien> dsThanhVien = quanLyThanhVien.traCuu(ngaySinh);
                                if (dsThanhVien.size() > 0) {
                                    System.out.println("\n*** DANH SACH CAC HOC VIEN TRA CUU THEO NGAY SINH ***");
                                    QuanLyThanhVien.hienThiDS(dsThanhVien);
                                } else {
                                    System.out.println("\n== KHONG TIM THAY HOC VIEN ==");
                                }
                            } catch (ParseException e) {
                                System.out.println("\n** DU LIEU KHONG HOP LE **");
                            }
                        }
                        case "6" -> {
                            System.out.print("- Nhap tu khoa can tra cuu (Ho ten, gioi tinh, que quan): ");
                            String tuKhoa = sc.nextLine();
                            List<ThanhVien> dsThanhVien = quanLyThanhVien.traCuu(tuKhoa);
                            if (dsThanhVien.size() > 0) {
                                System.out.println("\n*** DANH SACH CAC HOC VIEN TRA CUU DUOC ***");
                                QuanLyThanhVien.hienThiDS(dsThanhVien);
                            } else {
                                System.out.println("\n== KHONG TIM THAY HOC VIEN ==");
                            }
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

                        }
                        case "2" -> {

                        }
                        case "3" -> {

                        }
                        case "4" -> {

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
