package topic3;

import static topic3.CauHinh.sc;

public class Main {

    public static void main(String[] args) {
        while (true) {
            System.out.println("1- Quan ly nguoi hoc");
            System.out.println("2- Quan ly cau hoi");
            System.out.println("3- Luyen tap");
            System.out.println("4- Thong ke ket qua hoc tap cua 1 nguoi dung");
            System.out.println("5- Ket thuc");
            System.out.print("\n- Chon chuc nang: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1" -> {
                    System.out.println("1. Them nguoi hoc");
                    System.out.println("2. Xoa nguoi hoc");
                    System.out.println("3. Cap nhat thong tin nguoi hoc");
                    System.out.println("4. Xem danh sach nguoi hoc");
                    System.out.println("5. Tra cuu nguoi hoc theo ten");
                    System.out.println("6. Tra cuu nguoi hoc theo gioi tinh");
                    System.out.println("7. Tra cuu nguoi hoc theo que quan");
                    System.out.println("8. Tra cuu nguoi hoc theo ngay sinh");
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
                        case "8" -> {

                        }
                    }
                }
                case "2" -> {

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
